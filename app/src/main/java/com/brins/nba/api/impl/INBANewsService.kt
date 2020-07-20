package com.brins.nba.api.impl

import com.brins.nba.api.response.NewsCommentResponse
import com.brins.nba.api.response.NewsListResponse
import com.brins.nba.api.result.NewsResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INBANewsService {

    @GET("?")
    fun getNewsList(
        @Query("service") service: String, @Query("timestamp") time: Long
        , @Query("sign") sign: String, @Query("page") page: Int
    ): Call<NewsListResponse>


    @GET("?")
    fun getNewsComments(
        @Query("service") service: String, @Query("timestamp") time: Long
        , @Query("sign") sign: String, @Query("docid") docid: String
    ): Call<NewsCommentResponse>

}