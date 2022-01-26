package com.progressive.overload.ui.record

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
import com.progressive.overload.databinding.ActivityRecordTutotialBinding
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
class TutorialRecordActivity :
    BaseActivity<ActivityRecordTutotialBinding>(R.layout.activity_record_tutotial){

    @Inject
    lateinit var securePreferences: SecurePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTutorial()
    }

    private fun initTutorial() {
        binding {
            // 튜토리얼테스트
//            GuideUtil.saveRecordDetailGuideShown(securePreferences, false)

            Log.i("tuto", "recordDetail: ${!GuideUtil.isRecordDetailGuideShown(securePreferences)}")
            if (!GuideUtil.isRecordDetailGuideShown(securePreferences)) {
                layoutRecordSecondGuide.root.isVisible = true
            }
            layoutRecordSecondGuide.tvNext.setOnClickListener {
                layoutRecordThirdGuide.root.isVisible = true
                layoutRecordSecondGuide.root.isVisible = false
            }
            layoutRecordThirdGuide.tvPrev.setOnClickListener {
                layoutRecordThirdGuide.root.isVisible = false
                layoutRecordSecondGuide.root.isVisible = true
            }
            layoutRecordThirdGuide.ok.setOnClickListener {
                GuideUtil.saveRecordDetailGuideShown(securePreferences, true)
                layoutRecordThirdGuide.root.isVisible = false
                finish()
            }
        }
    }

    override fun onBackPressed() {}

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }


}