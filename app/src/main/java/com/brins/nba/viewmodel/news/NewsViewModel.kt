package com.brins.nba.viewmodel.news

import android.graphics.drawable.Drawable
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.data.BaseData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.data.news.SingleNewsListData
import com.brins.nba.api.result.CommentResultData
import com.brins.nba.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.net.URL
import java.util.regex.Matcher
import java.util.regex.Pattern

class NewsViewModel(repository: NewsRepository) : BaseViewModel(repository) {


    val mNewsList: SingleNewsListData = SingleNewsListData.get()
    val mNewsComment: MutableLiveData<List<CommentResultData>> = MutableLiveData()
    val mContent: MutableLiveData<MutableList<BaseData>> = MutableLiveData()

    fun fetchNewsList() {
        launch(
            {
                val result = fetchNewsList(BaseData())
                mNewsList.value = result.data
            }, {

            }
        )
    }

    private suspend fun fetchNewsList(data: BaseData) =
        withContext(Dispatchers.Main) {
            val resultData = (respository as NewsRepository).fetchNewsList(data)
            resultData
        }


    fun fetchNewsComment(data: CommentRequestData) {
        launch(
            {
                val result = fetchNewsCommentInternal(data)
                mNewsComment.value = result.data
            }, {
                //todo 异常处理
            }
        )
    }

    private suspend fun fetchNewsCommentInternal(data: CommentRequestData) =
        withContext(Dispatchers.Main) {
            val resultData = (respository as NewsRepository).fetchNewsComment(data)
            resultData
        }


    fun parseHtml(pos: Int) {
        launch({
            val content = parseHtmlContent(mNewsList.value?.get(pos)!!.url)
            mContent.value = content
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
        var content: StringBuilder = StringBuilder()
        contentElement?.let {
            if (contentElement.childNodes().isNotEmpty()) {
                for (i in 0 until it.childNodeSize()) {
                    content.append(it.childNode(i))
                }
            }

        }
        val pngs = doc.select("div.photo") // img中src以.png结尾
        var i = 0
        HtmlCompat.fromHtml(
            content.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY,
            object : Html.ImageGetter {
                override fun getDrawable(source: String?): Drawable? {
                    if (pngs.isNotEmpty()) {
                        return try {
                            val d =
                                Drawable.createFromStream(
                                    URL(
                                        pngs[i].select("a").select("img").attr("data-src")
                                            .toString()
                                    ).openStream(), ""
                                )

                            d.setBounds(0, 0, d.intrinsicWidth, d.intrinsicHeight)
                            i++
                            d
                        } catch (e: Exception) {
                            i++
                            null
                        }
                    }
                    return null
                }
            }, null
        )
    }
}