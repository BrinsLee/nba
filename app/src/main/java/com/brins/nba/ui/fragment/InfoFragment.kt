package com.brins.nba.ui.fragment

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brins.nba.R
import com.brins.nba.ui.adapter.BaseMainAdapter
import com.brins.nba.ui.data.BaseMainNewsData
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.news.NewsViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : BaseFragment() {

    private lateinit var mNewsViewModel: NewsViewModel
    private var mNewsAdapter: BaseMainAdapter = BaseMainAdapter()
    private var mCurrentPage = 0


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mNewsViewModel = InjectorUtil.getNewsViewModelFactory().create(NewsViewModel::class.java)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_info
    }

    override fun initEventAndData() {

        initRecyclerView()
        mNewsViewModel.mNewsList.observe(this,
            Observer {
                it?.let {
                    mCurrentPage++
                    val list: MutableList<BaseMainNewsData> = mutableListOf()
                    for (i in mNewsAdapter.data.size until it.size) {
                        val data = it[i]
                        list.add(
                            BaseMainNewsData(
                                data.title,
                                data.commentCount,
                                data.imgsrc,
                                data.ptime,
                                data.url
                            )
                        )
                    }
                    if (list.isNotEmpty()) {
                        mNewsAdapter.addData(list)
                        mNewsAdapter.loadMoreModule?.loadMoreComplete()
                    } else {
                        mNewsAdapter.loadMoreModule?.loadMoreEnd()
                        Toast.makeText(context, "暂无更多数据", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        mNewsViewModel.fetchNewsList(mCurrentPage)

    }

    private fun initRecyclerView() {

        mNewsAdapter.animationEnable = true
        mNewsAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
        recycler.adapter = mNewsAdapter
        recycler.layoutManager = LinearLayoutManager(activity)
        mNewsAdapter.loadMoreModule?.setOnLoadMoreListener {
            mNewsViewModel.fetchNewsList(
                mCurrentPage
            )
        }
        mNewsAdapter.loadMoreModule?.isAutoLoadMore = false
    }
}