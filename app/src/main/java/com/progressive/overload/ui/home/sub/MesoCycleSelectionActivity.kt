package com.progressive.overload.ui.home.sub

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivityMesoCycleSplitSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MesoCycleSelectionActivity :
    BaseActivity<ActivityMesoCycleSplitSelectionBinding>(R.layout.activity_meso_cycle_split_selection) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding {
            rvSplitSelection.apply {
                setHasFixedSize(true)
                adapter = MesoCycleSelectionAdapter { split ->
                    Intent(this@MesoCycleSelectionActivity, MicroCycleSelectionActivity::class.java).apply {
                        putExtra("mesoSplitCount", split.count)
                        putExtra("mesoSplitText", split.text)
                        startActivity(this)
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    }
                }
            }
        }

        initBannerAd(dataBinding.adView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected
    }
}