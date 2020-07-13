package com.brins.nba.api

import com.brins.nba.api.impl.INBANewsService
import com.brins.nba.api.impl.INBASchedule

object ServiceApi {

    val APPKEY = "6fc18957ce391f84a7ce34ce13cd99c4"
    val DOMAIN = "https://wapapi.it919.cn/"
    val NBA_SCHEDULE = "Nba.Schedule"
    val NBA_NEWS_INFO = "Nba.News_info"
    val NBA_NEWS_LIST = "Nba.New_list"
    val NBA_NEWS_COMMENTS = "Nba.news_comments"



    private var mScheduleService: INBASchedule? = null
    private var mNewsListService: INBANewsService? = null

    fun getScheduleService(): INBASchedule {
        if (mScheduleService == null) {
            synchronized(INBASchedule::class.java) {
                if (mScheduleService == null) {
                    mScheduleService = RetrofitFactory.newRetrofit(DOMAIN)
                        .create(INBASchedule::class.java)
                }
            }
        }
        return mScheduleService!!
    }

    fun getNewsListService(): INBANewsService {
        if (mNewsListService == null) {
            synchronized(INBASchedule::class.java) {
                if (mNewsListService == null) {
                    mNewsListService = RetrofitFactory.newRetrofit(DOMAIN)
                        .create(INBANewsService::class.java)
                }
            }
        }
        return mNewsListService!!
    }

}