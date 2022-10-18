package com.progressive.overload.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({ goMain() }, 1500)

    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {

    }

}