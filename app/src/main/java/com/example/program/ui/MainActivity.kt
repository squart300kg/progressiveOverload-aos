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
    // 2. 프로그래스바 넣기
    // 3. (운동기록페이지)운동 모든 세트 완료하고 'OOO운동을 완료했습니다' dialog띄워준 후, 확인 누르면 뒤로가게 하도록 하기
    // 4. (운동기록페이지)'중량'클릭시 키보드가 올라오는데, 이때 화면을 위로 밀 것

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