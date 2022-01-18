package com.progressive.overload.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.firebase.database.FirebaseDatabase
import com.progressive.overload.base.BaseViewModel
import com.progressive.overload.model.entity.ProgramTable
import com.progressive.overload.model.model.AppUpdateModel

class SplashViewModel @ViewModelInject constructor(
    private val firebaseDatabase: FirebaseDatabase,
)  : BaseViewModel() {

    private val _appUpdateModel = MutableLiveData<AppUpdateModel>()
    val appUpdateModel: LiveData<AppUpdateModel>
        get() = _appUpdateModel

    private val TAG = "HomeViewModelLog"

    fun getSystem() {
        firebaseDatabase
            .getReference("versionInfo")
            .get()
            .addOnSuccessListener {
                Log.i("versionCheck", it.toString())
                _appUpdateModel.value = it.getValue(AppUpdateModel::class.java)
            }
    }
}