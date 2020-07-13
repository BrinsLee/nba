package com.brins.nba.viewmodel.info

import com.brins.nba.api.ServiceApi
import com.brins.nba.api.data.BaseData
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.response.LiveResponse
import com.brins.nba.api.response.NewsCommentResponse
import com.brins.nba.api.response.NewsListResponse
import com.brins.nba.repository.BaseRepository
import com.brins.nba.viewmodel.live.LiveRepository

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

    suspend fun fetchNewsList(data: BaseData): NewsListResponse {
        return ServiceApi.getNewsListService()
            .getNewsList(ServiceApi.NBA_NEWS_LIST, data.timestamp, data.sign).await()
    }

    suspend fun fetchNewsComment(data: CommentRequestData): NewsCommentResponse {
        return ServiceApi.getNewsListService().getNewsComments(
            ServiceApi.NBA_NEWS_COMMENTS
            , data.timestamp, data.sign, data.docid
        ).await()
    }
}