package com.brins.nba.ui.fragment

import android.content.Context
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brins.nba.R
import com.brins.nba.ui.adapter.BaseMainAdapter
import com.brins.nba.ui.data.BaseMainNewsData
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.news.NewsViewModel
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : BaseFragment() {

    private lateinit var mNewsViewModel: NewsViewModel
    private var mNewsAdapter: BaseMainAdapter? = null


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
                    val list: MutableList<BaseMainNewsData> = mutableListOf()
                    it.forEach { it ->
                        list.add(
                            BaseMainNewsData(
                                it.title,
                                it.commentCount,
                                it.imgsrc,
                                it.ptime,
                                it.url
                            )
                        )
                    }
                    if (list.isNotEmpty()) {
                        mNewsAdapter?.addData(list)
                    }
                }
            })
        mNewsViewModel.fetchNewsList()

    }

    private fun initRecyclerView() {
        mNewsAdapter = BaseMainAdapter()
        mNewsAdapter!!.animationEnable = true
        recycler.adapter = mNewsAdapter
        recycler.layoutManager = LinearLayoutManager(activity)

    }
}