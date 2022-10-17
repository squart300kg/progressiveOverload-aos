package com.progressive.overload.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityMainBinding
import com.progressive.overload.ui.navigation.setupWithNavController
import com.progressive.overload.util.BackButtonCloseHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

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

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }

}