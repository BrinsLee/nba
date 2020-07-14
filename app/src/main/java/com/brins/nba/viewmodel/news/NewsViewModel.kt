package com.brins.nba.viewmodel.news

import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.data.BaseData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.data.news.SingleNewsListData
import com.brins.nba.api.result.CommentResultData
import com.brins.nba.api.result.NewsResultData
import com.brins.nba.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class NewsViewModel(repository: NewsRepository) : BaseViewModel(repository) {


    val mNewsList: SingleNewsListData = SingleNewsListData.get()
    val mNewsComment: MutableLiveData<List<CommentResultData>> = MutableLiveData()
    val mTitle: MutableLiveData<String> = MutableLiveData()

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


    fun parseHtml(url: String) {
        launch({
            val title = parseHtmlTitle(url)
            val date = parseHtmlDate(url)
            mTitle.value = title
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
}