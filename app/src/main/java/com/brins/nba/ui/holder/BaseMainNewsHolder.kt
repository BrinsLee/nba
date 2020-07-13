package com.brins.nba.ui.holder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainNewsData
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.brins.nba.utils.jumpToWebViewActivity
import com.brins.nba.widget.RoundedImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseViewHolder
import org.jsoup.Jsoup

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class BaseMainNewsHolder(view: View) : BaseViewHolder<BaseMainNewsData>(view) {


    var mTitle = view.findViewById<TextView>(R.id.title)
    var mCommentCount = view.findViewById<TextView>(R.id.comment_num)
    var mImage: ImageView = view.findViewById(R.id.image_view)
    var mRoot: ConstraintLayout = view.findViewById(R.id.view_root)


    override fun setData(data: BaseMainNewsData?, dataPosition: Int) {
        super.setData(data, dataPosition)
        data?.let {
            mTitle.text = it.title
            mCommentCount.text = it.commonCount.toString()
            GlideHelper.setImageResource(mImage, data.imgSrc)
        }
        mRoot.setOnClickListener {
            parseHtml(data!!.url)
            /*jumpToWebViewActivity(mContext, data!!.url)*/
        }
    }

    fun parseHtml(url: String): String {
        val doc = Jsoup.connect(url).get()
        val element = doc.select("div.head")
        val title = element.select("h1").attr("title")
        return title
    }
}