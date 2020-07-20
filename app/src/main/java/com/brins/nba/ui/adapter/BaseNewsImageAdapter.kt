package com.brins.nba.ui.adapter

import android.app.Activity
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainContentData
import com.brins.nba.ui.data.BaseMainImageData
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.brins.nba.utils.jumpToImageActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author lipeilin
 * @date 2020/7/20
 */
class BaseNewsImageAdapter(list: MutableList<BaseMainImageData>) :
    BaseQuickAdapter<BaseMainImageData, BaseViewHolder>(R.layout.item_news_content, list) {

    override fun convert(helper: BaseViewHolder, item: BaseMainImageData) {
        
    }
}