package com.brins.nba.ui.activity

import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebViewClient
import com.brins.nba.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.lang.Exception

class WebViewActivity : BaseActivity() {

    private var mUrl: String? = null


    override fun getLayoutResId(): Int {
        return R.layout.activity_web_view
    }

    companion object {
        const val TAG = "WebViewActivity"
        const val KEY_URL = "KEY_URL"


    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        initData()
        initWebView()
    }

    private fun initWebView() {

        web.loadUrl(mUrl)

    }

    private fun initData() {
        val intent = intent
        if (!intent.hasExtra(KEY_URL)) {
            finish()
        } else {
            mUrl = intent.getStringExtra(KEY_URL)
            if (TextUtils.isEmpty(mUrl)) {
                finish()
            }
        }
    }
}