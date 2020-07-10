package com.brins.nba.viewmodel.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brins.nba.repository.BaseRepository

/**
 * @author lipeilin
 * @date 2020/7/10
 */
class LiveModelFactory(private val repository: LiveRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LiveViewModel(repository) as T
    }
}