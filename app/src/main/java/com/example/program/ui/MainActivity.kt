package com.example.program.ui

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.program.R
import com.example.program.base.BaseActivity
import com.example.program.databinding.ActivityMainBinding
import com.example.program.ui.navigation.setupWithNavController
import com.example.program.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    // TODO 배포전
    // 1. 광고 아이디 넣기

    private var currentNavController: LiveData<NavController>? = null

    private val backButtonCloseHandler = BackButtonCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpBottomNavigationBar()

        initBannerAd(dataBinding.adView)

    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {
        when (currentNavController?.value?.currentDestination?.label) {
            getString(R.string.title_home) -> backButtonCloseHandler.onBackPressed()
            else -> super.onBackPressed()
        }
    }
    private fun setUpBottomNavigationBar() {

        val navGraphIds = listOf(R.navigation.log_record, R.navigation.log_look_up)
        val controller = dataBinding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent
        )

        currentNavController = controller
    }

}