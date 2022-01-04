package com.example.program.util

import android.util.Log
import com.securepreferences.SecurePreferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AdUtil {

    fun isTurnToExposeAd(securePreferences: SecurePreferences): Boolean {
        val currentCount = getFullScreenAdExposeCount(securePreferences)
        Log.i("FullScreenAdExposeCount", "$currentCount")
        return if (currentCount % 5 == 0) {
            initExposeCountToZero(securePreferences)
            plusFullScreenExposeCount(securePreferences)
            true
        } else {
            plusFullScreenExposeCount(securePreferences)
            false
        }
    }

    private fun getFullScreenAdExposeCount(securePreferences: SecurePreferences): Int {
        return securePreferences.getInt("FULL_SCREEN_AD_EXPOSE_COUNT", 0)
    }

    private fun plusFullScreenExposeCount(securePreferences: SecurePreferences) {
        var currentCount = getFullScreenAdExposeCount(securePreferences)
        securePreferences.edit().putInt("FULL_SCREEN_AD_EXPOSE_COUNT", ++currentCount).apply()
    }

    private fun initExposeCountToZero(securePreferences: SecurePreferences) {
        securePreferences.edit().putInt("FULL_SCREEN_AD_EXPOSE_COUNT", 0).apply()
    }
}