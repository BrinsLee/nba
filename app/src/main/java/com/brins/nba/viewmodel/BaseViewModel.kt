package com.brins.nba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brins.nba.repository.BaseRepository
import kotlinx.coroutines.launch

/**
 * @author lipeilin
 * @date 2020/7/10
 */
open class BaseViewModel(protected val respository: BaseRepository) : ViewModel() {

    protected val Tag = this::class.java.simpleName


    companion object {
    }

    protected fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
}