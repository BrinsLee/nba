package com.brins.nba.api.impl

import com.brins.nba.api.response.LiveResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author lipeilin
 * @date 2020/7/10
 */
interface INBASchedule {

    @GET("?")
    fun getNbaSchedule(
        @Query("service") service: String, @Query("timestamp") time: Long
        , @Query("sign") sign: String
    ): Call<LiveResponse>
}