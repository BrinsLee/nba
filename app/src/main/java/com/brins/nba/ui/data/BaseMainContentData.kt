package com.brins.nba.ui.data

import android.text.SpannableString
import android.text.Spanned
import com.brins.nba.ui.adapter.ITEM_NEWS_CONTENT
import com.chad.library.adapter.base.entity.MultiItemEntity

class BaseMainContentData(var content: Spanned? = SpannableString("评论")) : BaseData(){

    override val itemType: Int
        get() = ITEM_NEWS_CONTENT


}