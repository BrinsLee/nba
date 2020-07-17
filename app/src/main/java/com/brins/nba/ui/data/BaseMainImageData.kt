package com.brins.nba.ui.data

import com.brins.nba.ui.adapter.ITEM_NEWS_CONTENT
import com.brins.nba.ui.adapter.ITEM_NEWS_IMAGE
import com.chad.library.adapter.base.model.BaseData

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainImageData(var imageUrl: String) : BaseData() {


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
        return ITEM_NEWS_IMAGE
    }
}