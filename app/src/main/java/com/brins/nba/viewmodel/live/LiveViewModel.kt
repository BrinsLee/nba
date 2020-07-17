package com.brins.nba.viewmodel.live

import androidx.lifecycle.MutableLiveData
import com.brins.nba.api.data.BaseRequestData
import com.brins.nba.api.result.LiveResultData
import com.brins.nba.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author lipeilin
 * @date 2020/7/10
 */
class LiveViewModel(repository: LiveRepository) : BaseViewModel(repository) {

    val mSchedule: MutableLiveData<LiveResultData>? = null


    fun fetchSchedule() {
        launch(
            {
                val result = fetchSchedule(BaseRequestData())
                mSchedule!!.value = result.data
            }, {

            }
        )
    }

    private suspend fun fetchSchedule(data: BaseRequestData) =
        withContext(Dispatchers.Main) {
            val resultData = (respository as LiveRepository).fetchSchedule(data)
            resultData
        }
}