package com.brins.nba.viewmodel.live

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

    suspend fun fetchLive(){

    }

}