package com.progressive.overload

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

/**
 * 4B:88:4F:EB:BA:9E:2E:9C:4E:A4:57:4E:BD:29:E3:78:36:89:6B:07
 */
@HiltAndroidApp
open class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        initContext()

        initFirebase()

        initAdMob()

    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }

    private fun initAdMob() {
        MobileAds.initialize(this) {
            Log.i("adMobLog", "초기화완료")
        }
    }

    private fun initContext() {
        instance = this
    }

    companion object {
        private const val Tag = "Sauce Application"
        var instance: com.progressive.overload.Application? = null

    }

}

