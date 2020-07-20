package com.brins.nba.viewmodel.news

import android.util.Log
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.ServiceApi.APPKEY
import com.brins.nba.api.data.BaseRequestData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.data.NewsInfoRequestData
import com.brins.nba.api.data.news.SingleNewsListData
import com.brins.nba.api.result.CommentResultData
import com.brins.nba.ui.data.BaseData
import com.brins.nba.ui.data.BaseMainCommentData
import com.brins.nba.ui.data.BaseMainContentData
import com.brins.nba.ui.data.BaseMainImageData
import com.brins.nba.utils.MD5Util
import com.brins.nba.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class NewsViewModel(repository: NewsRepository) : BaseViewModel(repository) {


    val mNewsList: SingleNewsListData = SingleNewsListData.get()
    val mNewsComment: MutableLiveData<MutableList<BaseData>> = MutableLiveData()
    val mContent: MutableLiveData<MutableList<BaseData>> = MutableLiveData()

    /***获取新闻列表*/
    fun fetchNewsList(page: Int = 0) {
        launch(
            {
                val result = fetchNewsList(NewsInfoRequestData(page))
                mNewsList.value = result.data
            }, {

            }
        )
    }

    /***内部获取新闻列表*/
    private suspend fun fetchNewsList(data: NewsInfoRequestData) =
        withContext(Dispatchers.Main) {
            val resultData = (respository as NewsRepository).fetchNewsList(data)
            resultData
        }

    /***获取新闻评论*/
    fun fetchNewsComment(data: CommentRequestData) {
        launch(
            {
                val result = fetchNewsCommentInternal(data)
                mNewsComment.value = result
            }, {
                //todo 异常处理
            }
        )
    }

    /***内部获取新闻评论*/
    private suspend fun fetchNewsCommentInternal(data: CommentRequestData) =
        withContext(Dispatchers.Main) {
            val resultData = (respository as NewsRepository).fetchNewsComment(data)
            val resultComments = mutableListOf<BaseData>()
            if (!resultData.data.isNullOrEmpty()) {
                for (comment in resultData.data!!) {
                    resultComments.add(BaseMainCommentData(comment))
                }
            }
            resultComments

        }


    fun parseHtml(pos: Int) {
        launch({
            mNewsList.value?.get(pos)?.let {
                val contents = parseHtmlContent(it.url)
                GlobalScope.launch(Dispatchers.Main) {
                    mContent.value = contents
                    it.content = contents
                    fetchNewsComment(CommentRequestData(it.docid))
                }
            }

        }, {})
    }

    private suspend fun parseHtmlTitle(url: String) = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(url).get()
        val element = doc.select("div.head")

        val titleElement = element.select("h1.title")
        var title = ""
        titleElement?.let {
            if (titleElement.isNotEmpty())
                title = titleElement[0].childNodes().toString()
        }
        title
    }

    private suspend fun parseHtmlDate(url: String) = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(url).get()
        val element = doc.select("div.head")

        val dateElement = element.select("div.info")
        var date = ""
        dateElement?.let {
            if (dateElement.isNotEmpty())
                date = dateElement[0].childNode(1).childNodes().toString()
        }
        date
    }

    private suspend fun parseHtmlContent(url: String) = withContext(Dispatchers.IO) {
        val doc = Jsoup.connect(url).get()
        val element = doc.select("div.content")

        val contentElement = element[0]
        val pngs = doc.select("div.photo") // img中src以.png结尾
        var j = 0
        val list = mutableListOf<BaseData>()
        contentElement?.let {
            if (it.childNodes().isNotEmpty()) {
                for (element in it.childNodes()) {
                    for (i in 0 until element.childNodeSize()) {
                        val content = element.childNode(i).toString()
                        if (content.startsWith("<div class=\"photo\"> ", true)) {
                            //图片链接
                            list.add(
                                BaseMainImageData(
                                    pngs[j].select("a").select("img").attr("data-src")
                                        .toString()
                                )
                            )
                            j++
                        } else {
                            list.add(
                                BaseMainContentData(
                                    HtmlCompat.fromHtml(
                                        content,
                                        HtmlCompat.FROM_HTML_MODE_LEGACY
                                    )
                                )
                            )
                        }
                        Log.d("NewsViewModel", content)
                    }
                }
            }

        }
        list

    }
}