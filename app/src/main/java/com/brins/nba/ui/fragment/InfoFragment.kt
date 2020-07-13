package com.brins.nba.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brins.nba.R
import com.brins.nba.api.result.NewsResultData
import com.brins.nba.ui.adapter.BaseMainAdapter
import com.brins.nba.ui.data.BaseMainNewsData
import com.brins.nba.ui.itemdecoration.ItemDecoration
import com.brins.nba.utils.InjectorUtil
import com.brins.nba.viewmodel.info.NewsViewModel
import com.brins.nba.viewmodel.live.LiveViewModel
import com.chad.library.adapter.base.OnLoadDataCompleteCallback
import com.chad.library.adapter.base.OnLoadDataListener
import com.chad.library.adapter.base.model.BaseData
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
        mNewsViewModel.mNewsList.observe(this,
            Observer {
                mNewsAdapter = BaseMainAdapter()
                mNewsAdapter!!.setOnLoadDataListener { _, _, onLoadDataCompleteCallback ->
                    run {
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
                                onLoadDataCompleteCallback.onLoadDataSuccess(list as List<BaseData>?)
                            } else {
                                onLoadDataCompleteCallback.onLoadDataFail()
                            }
                        }
                    }
                }

                recycler.adapter = mNewsAdapter
                recycler.layoutManager = LinearLayoutManager(activity)
            })

        mNewsViewModel.fetchNewsList()


    }

}