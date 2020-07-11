package com.brins.nba.viewmodel.live

import com.brins.nba.api.ServiceApi
import com.brins.nba.api.ServiceApi.NBA_SCHEDULE
import com.brins.nba.api.data.BaseData
import com.brins.nba.api.response.LiveResponse
import com.brins.nba.api.result.LiveResultData
import com.brins.nba.repository.BaseRepository

/**
 * @author lipeilin
 * @date 2020/7/10
 */
class LiveRepository : BaseRepository() {


    companion object {
        private lateinit var instance: LiveRepository

        fun getInstance(): LiveRepository {
            if (!::instance.isInitialized) {
                synchronized(LiveRepository::class.java) {
                    if (!::instance.isInitialized) {
                        instance = LiveRepository()
                    }
                }
            }
            return instance
        }
    }

    suspend fun fetchSchedule(data: BaseData): LiveResponse {
        return ServiceApi.getScheduleService()
            .getNbaSchedule(NBA_SCHEDULE, data.timestamp, data.sign).await()
    }

}