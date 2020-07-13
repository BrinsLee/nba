package com.brins.nba.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.brins.nba.R
import com.brins.nba.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var liveFragment: LiveFragment? = null
    var newsListFragment: InfoFragment? = null
    var recordFragment: RecordFragment? = null
    var statisticsFragment: StatisticsFragment? = null
    var currentFragment: Fragment? = null


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {

    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.bottom_fragment) as NavHostFragment
        val controller = host.navController
        NavigationUI.setupWithNavController(bottom_nav, controller)
        if (liveFragment == null) {
            liveFragment = LiveFragment()
        }
        fragmentAdd(R.id.bottom_fragment, liveFragment!!)
        currentFragment = liveFragment
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_live -> {
                    title_tv.text = getString(R.string.app_name)
                    if (liveFragment == null) {
                        liveFragment = LiveFragment()
                    }
                    settingFragment(liveFragment!!)

                }
                R.id.fragment_info -> {
                    title_tv.text = it.title
                    if (newsListFragment == null) {
                        newsListFragment = InfoFragment()
                    }
                    settingFragment(newsListFragment!!)
                }
                R.id.fragment_record -> {
                    title_tv.text = it.title
                    if (recordFragment == null) {
                        recordFragment = RecordFragment()
                    }
                    settingFragment(recordFragment!!)
                }
                R.id.fragment_statistics -> {
                    title_tv.text = it.title
                    if (statisticsFragment == null) {
                        statisticsFragment = StatisticsFragment()
                    }
                    settingFragment(statisticsFragment!!)
                }
            }
            true
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.bottom_fragment).navigateUp()
    }

    private fun settingFragment(fragment: Fragment) {
        if (currentFragment != fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            if (!fragment.isAdded) {
                transaction.hide(currentFragment!!)
                    .add(R.id.bottom_fragment, fragment, fragment.javaClass.name)
                transaction.show(fragment)
            } else {
                transaction.hide(currentFragment!!).show(fragment)
            }
            transaction.commitAllowingStateLoss()
            currentFragment = fragment
        }
    }

    fun fragmentAdd(viewId: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(viewId, fragment)
            .commitAllowingStateLoss()
    }
}