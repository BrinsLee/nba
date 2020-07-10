package com.brins.nba.ui.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.brins.nba.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.bottom_fragment) as NavHostFragment
        val controller = host.navController
        NavigationUI.setupWithNavController(bottom_nav, controller)
        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_live -> {
                    title_tv.text = getString(R.string.app_name)
                }
                R.id.fragment_info -> {
                    title_tv.text = it.title
                }
                R.id.fragment_record -> {
                    title_tv.text = it.title
                }
                R.id.fragment_statistics -> {
                    title_tv.text = it.title
                }
            }
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.bottom_fragment).navigateUp()
    }
}