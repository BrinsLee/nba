package com.brins.nba.api.data.news

import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.result.NewsResultData

/**
 * @author lipeilin
 * @date 2020/7/14
 */
class SingleNewsListData : MutableLiveData<List<NewsResultData>>() {

    companion object {
        private lateinit var sInstance: SingleNewsListData

        fun get(): SingleNewsListData {
            sInstance = if (::sInstance.isInitialized) sInstance else SingleNewsListData()
            return sInstance
        }
    }
}