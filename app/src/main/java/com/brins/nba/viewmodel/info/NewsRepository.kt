package com.brins.nba.viewmodel.info

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
}