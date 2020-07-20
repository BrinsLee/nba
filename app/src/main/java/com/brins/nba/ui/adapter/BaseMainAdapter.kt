package com.brins.nba.ui.adapter

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.brins.nba.R
import com.brins.nba.ui.data.BaseData
import com.brins.nba.ui.data.BaseMainCommentData
import com.brins.nba.ui.data.BaseMainImageData
import com.brins.nba.ui.data.BaseMainNewsData
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.brins.nba.utils.jumpToWebViewActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder


/**
 * @author lipeilin
 * @date 2020/7/13
 */
class BaseMainAdapter(list: MutableList<BaseMainNewsData>? = null) :
    BaseQuickAdapter<BaseMainNewsData, BaseViewHolder>(R.layout.item_news_list, list),
    LoadMoreModule {


    override fun convert(helper: BaseViewHolder, item: BaseMainNewsData) {
        helper.setText(R.id.title, item.title)
        helper.setText(R.id.comment_num, "${item.commonCount}")
        GlideHelper.setImageResource(helper.getView(R.id.image_view), item.imgSrc)
        helper.getView<ConstraintLayout>(R.id.view_root).setOnClickListener {

            jumpToWebViewActivity(context, helper.layoutPosition)
        }
    }


    /*override fun onCreateViewHolderByType(
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
    }*/


}