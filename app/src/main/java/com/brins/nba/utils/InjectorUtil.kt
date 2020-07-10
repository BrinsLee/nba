package com.brins.nba.utils

import com.brins.nba.viewmodel.live.LiveModelFactory
import com.brins.nba.viewmodel.live.LiveRepository

/**
 * @author lipeilin
 * @date 2020/7/10
 */
object InjectorUtil {

    private fun getLiveRepository() = LiveRepository.getInstance()

    fun getLiveModelFactory() = LiveModelFactory(getLiveRepository())
}
