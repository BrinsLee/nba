package com.brins.nba.ui.adapter

import android.view.ViewGroup
import com.brins.nba.R
import com.brins.nba.ui.holder.BaseMainNewsHolder
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.model.BaseData

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class BaseMainAdapter : BaseQuickAdapter<BaseData, BaseViewHolder<out BaseData>>() {


    override fun onCreateViewHolderByType(
        parent: ViewGroup?,
        viewType: Int
    ): BaseViewHolder<out BaseData> {

        when (viewType) {
            ITEM_NEWS -> {
                return BaseMainNewsHolder(getItemView(R.layout.item_news_list, parent))
            }
            else -> {
                throw IllegalStateException("invalid view type")
            }
        }
    }


}