package com.brins.nba.ui.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainCommentData
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_news_comment.view.*

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainCommentHolder(view: View) : BaseViewHolder<BaseMainCommentData>(view) {

    val tvName: TextView = view.findViewById(R.id.nickname)
    val imageView: ImageView = view.findViewById(R.id.avatar)
    val tvDate: TextView = view.findViewById(R.id.date)
    val tvContent: TextView = view.findViewById(R.id.content)
    val tvLocation: TextView = view.findViewById(R.id.location)

    override fun setData(data: BaseMainCommentData) {
        super.setData(data)
        GlideHelper.setCircleImageResource(imageView, data.comment.user?.avatar)
        tvName.text = data.comment.user?.nickname
        tvDate.text = data.comment.createTime
        tvContent.text = data.comment.content
        tvLocation.text = data.comment.user?.location
    }
}