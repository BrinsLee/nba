package com.brins.nba.viewmodel.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class NewsModelFactory(private val repository: NewsRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(repository) as T
    }
}