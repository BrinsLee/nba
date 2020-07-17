package com.brins.nba.ui.data

import android.text.Spanned
import com.brins.nba.ui.adapter.ITEM_NEWS_CONTENT
import com.chad.library.adapter.base.model.BaseData

class BaseMainContentData(var content: Spanned? = null) : BaseData() {


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