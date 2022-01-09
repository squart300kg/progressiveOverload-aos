package com.progressive.overload.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel: ViewModel() {
    protected val _errorCode = MutableLiveData<String>()
    val errorCode: LiveData<String>
        get() = _errorCode

    protected val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    protected val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}