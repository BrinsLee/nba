package com.brins.nba.api

import com.brins.nba.api.impl.INBASchedule

object ServiceApi {

    val APPKEY = "6fc18957ce391f84a7ce34ce13cd99c4"
    val DOMAIN = "https://wapapi.it919.cn/"
    val NBA_SCHEDULE = "Nba.Schedule"


    private var msCheduleService: INBASchedule? = null

    fun getScheduleService(): INBASchedule {
        if (msCheduleService == null) {
            synchronized(INBASchedule::class.java) {
                if (msCheduleService == null) {
                    msCheduleService = RetrofitFactory.newRetrofit(DOMAIN)
                        .create(INBASchedule::class.java)
                }
            }
        }
        return msCheduleService!!
    }

}