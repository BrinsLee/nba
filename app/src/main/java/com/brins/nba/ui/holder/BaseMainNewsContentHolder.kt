package com.brins.nba.ui.holder

import android.view.View
import android.widget.TextView
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainContentData
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainNewsContentHolder(view: View) : BaseViewHolder<BaseMainContentData>(view) {

    val tvContent = view.findViewById<TextView>(R.id.tv_content)

    override fun setData(data: BaseMainContentData) {
        super.setData(data)
        tvContent.text = data.content
    }
}