package com.brins.nba.viewmodel.info

import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.data.BaseData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.result.CommentResultData
import com.brins.nba.api.result.NewsResultData
import com.brins.nba.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsViewModel(repository: NewsRepository) : BaseViewModel(repository) {


    val mNewsList: MutableLiveData<List<NewsResultData>> = MutableLiveData()
    val mNewsComment: MutableLiveData<List<CommentResultData>> = MutableLiveData()


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
}