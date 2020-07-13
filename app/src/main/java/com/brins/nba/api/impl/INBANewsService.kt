package com.brins.nba.api.impl

import com.brins.nba.api.response.NewsListResponse
import com.brins.nba.api.result.NewsResultData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface INBANewsService {

    @GET("?")
    fun fetchNews(
        @Query("service") service: String, @Query("timestamp") time: Long
        , @Query("sign") sign: String
    ): Call<NewsListResponse>

}