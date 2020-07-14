package com.brins.nba.ui.activity

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.os.Looper
import android.os.Process
import android.util.Log
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.brins.nba.AppManager
import java.util.concurrent.ScheduledExecutorService
import kotlin.system.exitProcess

abstract class BaseActivity : AppCompatActivity() {

    protected open val TAG = this::class.java.simpleName
    private val mMainThread = Looper.getMainLooper().thread
    private var mScheduledExecutorService: ScheduledExecutorService? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateBeforeBinding(savedInstanceState)
        val resId = getLayoutResId()
        if (resId != 0) {
            setContentView(resId)
        }
        onCreateAfterBinding(savedInstanceState)
        setStatusBarTranslucent()

    }


    private fun isInForeground(): Boolean {
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = manager.runningAppProcesses
        val processInfo = info[0]
        return applicationInfo.packageName == processInfo.processName && processInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    protected open fun killApp() {
        AppManager.getAppManager().finishAllActivity()
        Process.killProcess(Process.myPid())
        exitProcess(0)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    protected open fun onCreateBeforeBinding(@Nullable savedInstanceState: Bundle?) {}


    protected open fun getLayoutResId(): Int{
        return 0
    }

    protected open fun onCreateAfterBinding(@Nullable savedInstanceState: Bundle?) {
    }


    private fun setStatusBarTranslucent() {
        /*setTranslucent(this)
        if (isDarkTheme())
            setTextDark(this.window, false)
        else
        setTextDark(this.window, true)*/
    }


    fun isDarkTheme(): Boolean {
        val flag = this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }
}
