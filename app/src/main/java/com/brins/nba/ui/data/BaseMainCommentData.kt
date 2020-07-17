package com.brins.nba.ui.data

import com.brins.nba.api.result.CommentResultData
import com.brins.nba.ui.adapter.ITEM_NEWS_COMMENT
import com.chad.library.adapter.base.model.BaseData

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainCommentData(var comment: CommentResultData) : BaseData() {


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
        return ITEM_NEWS_COMMENT
    }
}