package com.progressive.overload.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.androidadvance.topsnackbar.TSnackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.progressive.overload.R
import com.progressive.overload.base.BaseActivity
import com.progressive.overload.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModels()

    private lateinit var appUpdateManager: AppUpdateManager

    val TAG = "SplashActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appUpdateManager = AppUpdateManagerFactory.create(this)

        observing {
            splashViewModel.appUpdateModel.observe(this@SplashActivity, {
                if (it.maintenanceMode) {
                    showFinishDialog(it.maintenanceMessage, "종료")
                } else {
                    checkForUpdate(it.forceUpdateVersion)
                }
            })
        }
    }

    private fun checkForUpdate(forceUpdateVersion: String) {
        try {
            val pInfo = packageManager.getPackageInfo(packageName, 0)

            val currentVersion = pInfo.versionName

            val compareResult = compareVersionNames(currentVersion, forceUpdateVersion)

            if (compareResult == -1) {
                showUpdateDialog()
            } else {
                showFlexibleUpdate()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun compareVersionNames(oldVersionName: String, newVersionName: String): Int {
        var res = 0

        val oldNumbers =
            oldVersionName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val newNumbers =
            newVersionName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        // To avoid IndexOutOfBounds
        val maxIndex = oldNumbers.size.coerceAtMost(newNumbers.size)

        for (i in 0 until maxIndex) {
            val oldVersionPart = Integer.valueOf(oldNumbers[i])
            val newVersionPart = Integer.valueOf(newNumbers[i])

            if (oldVersionPart < newVersionPart) {
                res = -1
                break
            } else if (oldVersionPart > newVersionPart) {
                res = 1
                break
            }
        }

        // If versions are the same so far, but they have different length...
        if (res == 0 && oldNumbers.size != newNumbers.size) {
            res = if (oldNumbers.size > newNumbers.size) 1 else -1
        }

        return res
    }

    private fun showFinishDialog(msg: String, btnText: String) {
        val errorDialog = AlertDialog.Builder(this@SplashActivity)
            .setCancelable(false)
            .setMessage(msg)
            .setPositiveButton(btnText) { dialog, _ ->
                dialog?.dismiss()
                finish()
            }
            .create()
        errorDialog.show()
    }

    private fun showUpdateDialog() {
        val errorDialog = AlertDialog.Builder(this@SplashActivity)
            .setCancelable(false)
            .setMessage("업데이트 사항이 있습니다.\n최신 버전으로 업데이트해주세요.")
            .setPositiveButton("확인") { dialog, _ ->
                val url =
                    when (packageManager?.getInstallerPackageName(packageName)?.toLowerCase()) {
                        "com.samsung.android.mateagent",
                        "com.sec.android.app.samsungapps",
                        "com.sec.android.easyMover.Agent" -> "samsungapps://ProductDetail/${packageName}"

                        "com.skt.skaf.A000Z00040", // skt
                        "com.kt.olleh.storefront",
                        "android.lgt.appstore" -> "onestore://common/product/0000741533"

                        else -> "market://details?id=${packageName}"
                    }

                try {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                } catch (exception: android.content.ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=${packageName}")
                        )
                    )
                }

                dialog?.dismiss()
                finish()
            }
            .create()
        errorDialog.show()
    }

    private fun showFlexibleUpdate() {
        val appUpdateInfo = appUpdateManager.appUpdateInfo

        val appUpdateListener = InstallStateUpdatedListener {
            Log.d(
                "InstallStatus",
                "status = ${it.installStatus()} / error = ${it.installErrorCode()}"
            )
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackBarForCompleteUpdate()
            }
        }

        appUpdateManager.registerListener(appUpdateListener)

        appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.FLEXIBLE, // or AppUpdateType.IMMEDIATE
                    this,
                    REQUEST_CODE_FLEXIBLE_UPDATE
                )
            } else {
                showMainActivity()
            }
        }.addOnFailureListener { exception ->
            Log.d("InstallStatus", "exception = ${exception.message}")
            showMainActivity()
        }
    }

    private fun popupSnackBarForCompleteUpdate() {
        val snackBar = TSnackbar.make(
            window.decorView.rootView,
            "업데이트가 완료되었습니다.",
            TSnackbar.LENGTH_INDEFINITE
        )

        snackBar.setAction("설치/재시작") {
            appUpdateManager.completeUpdate()
        }

        val snackbarView = snackBar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.green_first))

        val messageTextView =
            snackbarView.findViewById<View>(com.androidadvance.topsnackbar.R.id.snackbar_text) as TextView
        messageTextView.apply {
            setTextColor(Color.parseColor("#ffffff"))
        }
        snackBar.show()
    }

    private fun showMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({ goMain() }, 1500)
    }

    private fun observing(action: SplashViewModel.() -> Unit) {
        splashViewModel.run(action)
    }

    companion object {
        const val REQUEST_CODE_FLEXIBLE_UPDATE = 1
        const val REQUEST_CODE_IMMEDIATE_UPDATE = 2
    }

    override fun showInternetDisconnectedView(disconnected: Boolean) {
        Log.i("splashNetwork", disconnected.toString())
        dataBinding.viewNetworkNotConnected.root.isVisible = disconnected

        if (!disconnected) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                showFinishDialog("지원하지 않는 안드로이드 버전입니다.", "종료")
            } else {
                splashViewModel.getSystem()
            }
        }
    }

}