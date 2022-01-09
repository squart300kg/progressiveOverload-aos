package com.progressive.overload.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.progressive.overload.R
import com.progressive.overload.ui.MainActivity
import com.progressive.overload.util.Ad.FullScreenAdCallback
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

open class BaseActivity<T: ViewDataBinding>(
    private val layoutRes: Int
): AppCompatActivity() {

    protected lateinit var dataBinding: T

    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this
    }

    protected fun binding(action: T.() -> Unit) {
        dataBinding.run(action)
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun goMain() {
        Intent(this,
            MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    fun initFullScreenAd(callback: FullScreenAdCallback) {

        // 전면광고 초기화
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,getString(R.string.main_full_screen_ad), adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                // 전면광고 콜백함수 등록
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    val TAG = "fullScreenLog"
                    override fun onAdDismissedFullScreenContent() {
                        callback.onCloseFullScreenAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        Log.d(TAG, "Ad failed to show.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Log.d(TAG, "Ad showed fullscreen content.")
                        mInterstitialAd = null
                    }
                }
            }
        })
    }

    fun startFullScreenAd() {
        val TAG = "fullScreenLog"

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d(TAG, "Ad showed fullscreen content.")
        }
    }

    fun initBannerAd(adView: AdView) {
        MobileAds.initialize(this) {
            Log.i("구글광고", "시작!")
        }

        AdRequest.Builder().build().apply {
            adView.loadAd(this)
        }
    }
}