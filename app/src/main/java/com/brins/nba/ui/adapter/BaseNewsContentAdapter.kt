package com.brins.nba.ui.adapter

import android.app.Activity
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.brins.nba.R
import com.brins.nba.ui.data.*
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.brins.nba.utils.jumpToImageActivity
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author lipeilin
 * @date 2020/7/20
 */
class BaseNewsContentAdapter(list: MutableList<BaseData>? = null) :
    BaseMultiItemQuickAdapter<BaseData, BaseViewHolder>(list) {

    init {
        addItemType(ITEM_NEWS_IMAGE, R.layout.item_news_image)
        addItemType(ITEM_NEWS_CONTENT, R.layout.item_news_content)
        addItemType(ITEM_NEWS_COMMENT, R.layout.item_news_comment)
    }

    override fun convert(helper: BaseViewHolder, item: BaseData) {
        when (helper.itemViewType) {
            ITEM_NEWS_CONTENT -> {
                helper.setText(R.id.tv_content, (item as BaseMainContentData).content)
            }

            ITEM_NEWS_COMMENT -> {
                val image: ImageView = helper.getView(R.id.avatar)
                val it = item as BaseMainCommentData
                GlideHelper.setCircleImageResource(image, it.comment.user?.avatar)
                helper.setText(R.id.nickname, it.comment.user?.nickname)
                helper.setText(R.id.date, it.comment.createTime)
                helper.setText(R.id.content, it.comment.content)
                helper.setText(R.id.location, it.comment.user?.location)
            }
            ITEM_NEWS_IMAGE -> {
                val image: ImageView = helper.getView(R.id.image_view)
                GlideHelper.setImageResource(image, (item as BaseMainImageData).imageUrl)
                ViewCompat.setTransitionName(image, item.imageUrl)
                image.setOnClickListener {
                    val options: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context as Activity,
                            image,
                            ViewCompat.getTransitionName(image)!!
                        )
                    jumpToImageActivity(context, options, image.transitionName)
                }
            }
        }
    }
}