package com.brins.nba.ui.data

import com.brins.nba.ui.adapter.ITEM_NEWS
import com.chad.library.adapter.base.model.BaseData

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class BaseMainNewsData(
    var title: String, var commonCount: Int
    , var imgSrc: String, var date: String, var url: String
) : BaseData() {


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
        return ITEM_NEWS
    }


}