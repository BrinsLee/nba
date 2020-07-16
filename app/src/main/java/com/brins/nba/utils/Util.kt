package com.brins.nba.utils

import android.content.Context
import android.content.Intent
import com.brins.nba.ui.activity.NewsInfoActivity

/**
 * @author lipeilin
 * @date 2020/7/10
 */


fun jumpToWebViewActivity(activity: Context, pos: Int) {
    val intent = Intent(activity, NewsInfoActivity::class.java)
    intent.putExtra(NewsInfoActivity.KEY_POS, pos)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(intent)
}