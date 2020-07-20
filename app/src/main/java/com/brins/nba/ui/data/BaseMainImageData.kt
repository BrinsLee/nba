package com.brins.nba.ui.data

import com.brins.nba.ui.adapter.ITEM_NEWS_CONTENT
import com.brins.nba.ui.adapter.ITEM_NEWS_IMAGE
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainImageData(var imageUrl: String) : BaseData() {
    override val itemType: Int
        get() = ITEM_NEWS_IMAGE


}