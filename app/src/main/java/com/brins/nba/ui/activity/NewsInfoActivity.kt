package com.brins.nba.ui.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import com.brins.nba.R
import com.brins.nba.databinding.ActivityNewsInfoBinding
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.news.NewsViewModel
import kotlinx.android.synthetic.main.activity_news_info.*

class NewsInfoActivity : BaseActivity() {

    private var mUrl: String? = null
    private var mNewsInfoViewModel: NewsViewModel =
        InjectorUtil.getNewsViewModelFactory().create(NewsViewModel::class.java)

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityNewsInfoBinding>(
            this,
            R.layout.activity_news_info
        )
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_news_info
    }

    companion object {
        const val TAG = "WebViewActivity"
        const val KEY_URL = "KEY_URL"


    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        initData()
        binding.viewModel = mNewsInfoViewModel
        binding.lifecycleOwner = this
        mNewsInfoViewModel.parseHtml(mUrl!!)
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