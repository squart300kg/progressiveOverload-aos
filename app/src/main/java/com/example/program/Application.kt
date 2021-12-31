package com.example.program

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 4B:88:4F:EB:BA:9E:2E:9C:4E:A4:57:4E:BD:29:E3:78:36:89:6B:07
 */
@HiltAndroidApp
open class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        contextInit()

    }

    private fun contextInit() {
        instance = this
    }

    companion object {
        private const val Tag = "Sauce Application"
        var instance: com.example.program.Application? = null

    }

}

