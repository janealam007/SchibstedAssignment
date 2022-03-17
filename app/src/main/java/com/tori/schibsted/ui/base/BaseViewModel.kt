package com.tori.schibsted.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tori.schibsted.util.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    var forceLogOut = SingleLiveEvent<Boolean>()

    fun onLoading(isLoader: Boolean) {
        _isLoading.value = isLoader
    }

    fun forceLogOut(flag: Boolean) {
        forceLogOut.value = flag
    }
}