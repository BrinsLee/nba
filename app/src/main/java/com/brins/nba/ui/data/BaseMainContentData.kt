package com.brins.nba.ui.data

import android.text.Spannable
import com.brins.nba.ui.adapter.ITEM_NEWS_CONTENT
import com.chad.library.adapter.base.model.BaseData

class BaseMainContentData : BaseData() {

    val content: Spannable? = null

    override fun isValidData(): Boolean {
        return false
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun isAutoIndex(): Boolean {
        return false
    }

    override fun getItemType(): Int {
        return ITEM_NEWS_CONTENT
    }
}