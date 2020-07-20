package com.brins.nba.ui.data

import com.brins.nba.api.result.CommentResultData
import com.brins.nba.ui.adapter.ITEM_NEWS_COMMENT
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainCommentData(var comment: CommentResultData) : BaseData() {
    override val itemType: Int
        get() = ITEM_NEWS_COMMENT


}