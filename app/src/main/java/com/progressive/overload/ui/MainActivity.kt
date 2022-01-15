package com.progressive.overload.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
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

        initActionBar()

        initBottomNavBar()

        initBannerAd(dataBinding.appBarMain.contentMain.adView)

    }

    private fun initActionBar() {
        setSupportActionBar(dataBinding.appBarMain.toolbar)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    // 툴바 메뉴 버튼이 클릭 됐을 때 콜백
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 클릭된 메뉴 아이템의 아이디 마다 when 구절로 클릭시 동작을 설정한다.
        when(item.itemId){
            android.R.id.home->{ // 메뉴 버튼
                dataBinding.drawerLayout.openDrawer(GravityCompat.START)    // 네비게이션 드로어 열기
            }
            R.id.nav_login-> Snackbar.make(dataBinding.appBarMain.toolbar,"Search menu pressed",Snackbar.LENGTH_SHORT).show()
            R.id.nav_no_ads-> Snackbar.make(dataBinding.appBarMain.toolbar,"Logout menu pressed",Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onBackPressed() {

        // 네비게이션 드로워 닫은 후, 뒤로가기 시행
        if (dataBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            dataBinding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            when (currentNavController?.value?.currentDestination?.label) {
                getString(R.string.title_home) -> backButtonCloseHandler.onBackPressed()
                else -> super.onBackPressed()
            }
        }
    }

    private fun initBottomNavBar() {

        val navGraphIds = listOf(R.navigation.log_record, R.navigation.log_look_up)
        val controller = dataBinding.appBarMain.contentMain.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent
        )

        currentNavController = controller
    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.appBarMain.contentMain.viewNetworkNotConnected.root.isVisible = disconnected
    }

}