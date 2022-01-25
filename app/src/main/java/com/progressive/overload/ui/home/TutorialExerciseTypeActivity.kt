package com.progressive.overload.ui.home

import android.os.Bundle
import androidx.core.view.isVisible
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityExerciseTypeTutotialBinding
import com.progressive.overload.util.GuideUtil
import com.securepreferences.SecurePreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TutorialExerciseTypeActivity :
    BaseActivity<ActivityExerciseTypeTutotialBinding>(R.layout.activity_exercise_type_tutotial){

    @Inject
    lateinit var securePreferences: SecurePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTutorial()
    }

    private fun initTutorial() {
        binding {

            // 튜토리얼 테스트
            GuideUtil.saveExerciseTypeGuideShown(securePreferences, false)

            if (!GuideUtil.isExerciseTypeGuideShown(securePreferences)) {
                layoutTopMenuGuide.root.isVisible = true
            }

            layoutTopMenuGuide.root.setOnClickListener {
                layoutTopMenuGuide.root.isVisible = false
                layoutReadyGuide.root.isVisible = true
            }

            layoutReadyGuide.root.setOnClickListener {
                layoutReadyGuide.root.isVisible = false
                finish()
            }
        }
    }

    override fun onBackPressed() {}

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }


}