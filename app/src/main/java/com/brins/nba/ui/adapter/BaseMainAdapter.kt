package com.brins.nba.ui.adapter

import android.view.ViewGroup
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainCommentData
import com.brins.nba.ui.data.BaseMainImageData
import com.brins.nba.ui.holder.BaseMainCommentHolder
import com.brins.nba.ui.holder.BaseMainNewsContentHolder
import com.brins.nba.ui.holder.BaseMainNewsHolder
import com.brins.nba.ui.holder.BaseMainNewsImageHolder
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
            ITEM_NEWS_CONTENT -> {
                return BaseMainNewsContentHolder(getItemView(R.layout.item_news_content, parent))
            }
            ITEM_NEWS_IMAGE -> {
                return BaseMainNewsImageHolder(getItemView(R.layout.item_news_image, parent))
            }
            ITEM_NEWS_COMMENT -> {
                return BaseMainCommentHolder(getItemView(R.layout.item_news_comment, parent))
            }
            else -> {
                throw IllegalStateException("invalid view type")
            }
        }
    }


}