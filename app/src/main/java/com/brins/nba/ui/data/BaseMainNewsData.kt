package com.brins.nba.ui.data

import com.brins.nba.ui.adapter.ITEM_NEWS

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class BaseMainNewsData(
    var title: String, var commonCount: Int
    , var imgSrc: String, var date: String, var url: String
) : BaseData() {
    override val itemType: Int
        get() = ITEM_NEWS
}