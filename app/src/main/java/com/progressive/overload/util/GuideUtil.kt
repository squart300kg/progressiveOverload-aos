package com.progressive.overload.util

import com.securepreferences.SecurePreferences
import javax.inject.Inject

object GuideUtil {

    fun saveMainGuideShown(securePreferences: SecurePreferences, status: Boolean) {
        securePreferences.edit().putBoolean("mainGuide", status).commit()
    }

    fun isMainGuideShown(securePreferences: SecurePreferences) : Boolean {
        return securePreferences.getBoolean("mainGuide", false)
    }

    fun saveReadyGuideShown(securePreferences: SecurePreferences, status: Boolean) {
        securePreferences.edit().putBoolean("readyGuide", status).commit()
    }

    fun isReadyGuideShown(securePreferences: SecurePreferences) : Boolean {
        return securePreferences.getBoolean("readyGuide", false)
    }

    fun saveStartGuideShown(securePreferences: SecurePreferences, status: Boolean) {
        securePreferences.edit().putBoolean("startGuide", status).commit()
    }

    fun isStartGuideShown(securePreferences: SecurePreferences) : Boolean {
        return securePreferences.getBoolean("startGuide", false)
    }

    fun saveOneSetClearGuideShown(securePreferences: SecurePreferences, status: Boolean) {
        securePreferences.edit().putBoolean("oneSetClearGuide", status).commit()
    }

    fun isOneSetClearGuideShown(securePreferences: SecurePreferences) : Boolean {
        return securePreferences.getBoolean("oneSetClearGuide", false)
    }
}