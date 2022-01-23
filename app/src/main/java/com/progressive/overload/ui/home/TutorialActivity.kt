package com.progressive.overload.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityExcerciseTypeBinding
import com.progressive.overload.databinding.ActivityTutotialBinding
import com.progressive.overload.ui.dialog.CancelDialog
import com.progressive.overload.ui.dialog.Input1RMDialog
import com.progressive.overload.ui.dialog.TitleDialog
import com.progressive.overload.util.Ad.AdUtil
import com.progressive.overload.util.Ad.FullScreenAdCallback
import com.progressive.overload.util.DateUtil
import com.progressive.overload.util.GuideUtil
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TutorialActivity :
    BaseActivity<ActivityTutotialBinding>(R.layout.activity_tutotial){

    @Inject
    lateinit var securePreferences: SecurePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTutorial()
    }

    private fun initTutorial() {
        GuideUtil.saveMainGuideShown(securePreferences, false)

        binding {
            GuideUtil.saveMainGuideShown(securePreferences, false)

            if (!GuideUtil.isMainGuideShown(securePreferences)) {
                layoutMainFirstGuide.root.isVisible = true
            }
            layoutMainFirstGuide.tvNext.setOnClickListener {
                layoutMainFirstGuide.root.isVisible = false
                layoutMainSecondGuide.root.isVisible = true
            }
            layoutMainSecondGuide.tvNext.setOnClickListener {
                layoutMainSecondGuide.root.isVisible = false
                layoutMainThirdGuide.root.isVisible = true
            }
            layoutMainSecondGuide.tvPrev.setOnClickListener {
                layoutMainSecondGuide.root.isVisible = false
                layoutMainFirstGuide.root.isVisible = true
            }
            layoutMainThirdGuide.tvNext.setOnClickListener {
                layoutMainFourthGuide.root.isVisible = true
                layoutMainThirdGuide.root.isVisible = false
            }
            layoutMainThirdGuide.tvPrev.setOnClickListener {
                layoutMainThirdGuide.root.isVisible = false
                layoutMainSecondGuide.root.isVisible = true
            }
            layoutMainFourthGuide.tvNext.setOnClickListener {
                layoutMainFifthGuide.root.isVisible = true
                layoutMainFourthGuide.root.isVisible = false
            }
            layoutMainFourthGuide.tvPrev.setOnClickListener {
                layoutMainFourthGuide.root.isVisible = false
                layoutMainThirdGuide.root.isVisible = true
            }
            layoutMainFifthGuide.tvPrev.setOnClickListener {
                layoutMainFourthGuide.root.isVisible = true
                layoutMainFifthGuide.root.isVisible = false
            }
            layoutMainFifthGuide.tvLetsStart.setOnClickListener {
                GuideUtil.saveMainGuideShown(securePreferences, true)
                layoutMainFifthGuide.root.isVisible = false
                finish()
            }
        }
    }

    override fun onBackPressed() {}

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }


}