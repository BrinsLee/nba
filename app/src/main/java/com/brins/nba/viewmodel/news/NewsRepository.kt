package com.brins.nba.viewmodel.news

import com.brins.nba.api.ServiceApi
import com.brins.nba.api.data.BaseRequestData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.data.NewsInfoRequestData
import com.brins.nba.api.response.NewsCommentResponse
import com.brins.nba.api.response.NewsListResponse
import com.brins.nba.repository.BaseRepository
import com.brins.nba.utils.MD5Util

class NewsRepository : BaseRepository() {


    companion object {
        private lateinit var instance: NewsRepository

        fun getInstance(): NewsRepository {
            if (!::instance.isInitialized) {
                synchronized(NewsRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance = NewsRepository()
                    }
                }
            }
            return instance
        }
    }

    suspend fun fetchNewsList(data: NewsInfoRequestData): NewsListResponse {
        data.sign = MD5Util.md5(ServiceApi.APPKEY + data.page + data.timestamp)
        return ServiceApi.getNewsListService()
            .getNewsList(ServiceApi.NBA_NEWS_LIST, data.timestamp, data.sign, data.page).await()
    }

    suspend fun fetchNewsComment(data: CommentRequestData): NewsCommentResponse {
        data.sign = MD5Util.md5(ServiceApi.APPKEY + data.docid + data.timestamp)
        return ServiceApi.getNewsListService().getNewsComments(
            ServiceApi.NBA_NEWS_COMMENTS
            , data.timestamp, data.sign, data.docid
        ).await()
    }
}