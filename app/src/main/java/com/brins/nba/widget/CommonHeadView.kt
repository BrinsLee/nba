package com.brins.nba.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.brins.nba.R
import kotlinx.android.synthetic.main.common_head_view.view.*
import kotlinx.android.synthetic.main.item_news_content.view.*

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class CommonHeadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null, def: Int = 0
) : LinearLayout(context, attrs, def) {

    var listener: OnClickListener? = null
        set(value) {
            field = value
            iv_back.setOnClickListener(listener)
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.common_head_view, this)
        val a = context.obtainStyledAttributes(attrs, R.styleable.CommonHeadView)
        setText(a.getString(R.styleable.CommonHeadView_title_text))
        setTextColor(
            a.getColor(
                R.styleable.CommonHeadView_title_color,
                ContextCompat.getColor(context, R.color.white)
            )
        )
        a.recycle()
    }

    fun hideBackIcon() {
        iv_back.visibility = View.GONE
    }

    fun showBackIcon() {
        iv_back.visibility = View.VISIBLE
    }

    fun setText(title: String?) {
        title_tv.text = title
    }

    fun setTextColor(color: Int) {
        title_tv.setTextColor(color)
    }
}