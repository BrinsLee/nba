package com.brins.nba.ui.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brins.nba.R
import com.brins.nba.api.data.CommentRequestData
import com.brins.nba.api.result.NewsResultData
import com.brins.nba.databinding.ActivityNewsInfoBinding
import com.brins.nba.ui.adapter.BaseMainAdapter
import com.brins.nba.ui.data.BaseMainContentData
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.news.NewsViewModel
import com.chad.library.adapter.base.OnLoadDataCompleteCallback
import com.chad.library.adapter.base.OnLoadDataListener
import com.chad.library.adapter.base.model.BaseData
import kotlinx.android.synthetic.main.activity_news_info.*

class NewsInfoActivity : BaseActivity() {

    private var mPos: Int = -1
    private var mNewsInfoViewModel: NewsViewModel =
        InjectorUtil.getNewsViewModelFactory().create(NewsViewModel::class.java)
    private var mNews: NewsResultData? = null
    private val mAdapter: BaseMainAdapter = BaseMainAdapter()

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityNewsInfoBinding>(
            this,
            R.layout.activity_news_info
        )
    }

    companion object {
        const val TAG = "WebViewActivity"
        const val KEY_URL = "KEY_URL"
        const val KEY_POS = "KEY_POS"


    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        initData()
        binding.viewModel = mNewsInfoViewModel
        binding.lifecycleOwner = this
        binding.mData = mNews

        if (mNews?.content == null) {
            mNewsInfoViewModel.parseHtml(mPos)
        } else {
            mAdapter.setOnLoadDataListener { _, _, onLoadDataCompleteCallback ->
                mNewsInfoViewModel.fetchNewsComment(CommentRequestData(mNews!!.docid))
                onLoadDataCompleteCallback.onLoadDataSuccess(mNews?.content)
            }
            recycler_content.adapter = mAdapter
            recycler_content.layoutManager = LinearLayoutManager(this)
        }

        mNewsInfoViewModel.mContent.observe(this,
            Observer<MutableList<BaseData>> { t ->
                mAdapter.setOnLoadDataListener { _, _, onLoadDataCompleteCallback ->
                    onLoadDataCompleteCallback.onLoadDataSuccess(
                        t
                    )
                }
                recycler_content.adapter = mAdapter
                recycler_content.layoutManager = LinearLayoutManager(this)
            })

        mNewsInfoViewModel.mNewsComment.observe(this,
            Observer<MutableList<BaseData>> { t ->
                mAdapter.addData(BaseMainContentData())
                mAdapter.addData(t)
            })
    }


    private fun initData() {
        val intent = intent
        if (!intent.hasExtra(KEY_POS)) {
            finish()
        } else {
            mPos = intent.getIntExtra(KEY_POS, -1)
            if (mPos == -1) {
                finish()
            } else {
                mNews = mNewsInfoViewModel.mNewsList.value?.get(mPos) ?: NewsResultData()
            }
        }
    }
}