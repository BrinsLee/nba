package com.brins.nba.ui.itemdecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class ItemDecoration : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable =
        ColorDrawable(Color.GRAY)
    private var mDividerHeight = 0
    private var mLeftPadding = 0
    private var mRightPadding = 0
    private var mItemViewType = -1
    private var mDrawFirstDivide = true


    fun setDivider(divider: Drawable): ItemDecoration {
        mDivider = divider
        return this
    }

    fun setDividerHeight(dividerHeight: Int): ItemDecoration {
        mDividerHeight = dividerHeight
        return this
    }

    fun setPadding(padding: Int): ItemDecoration {
        mLeftPadding = padding
        mRightPadding = padding
        return this
    }

    fun setItemViewType(itemViewType: Int): ItemDecoration {
        mItemViewType = itemViewType
        return this
    }

    fun setDrawFirstDivide(drawFirstDivide: Boolean): ItemDecoration {
        mDrawFirstDivide = drawFirstDivide
        return this
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        drawHorizontalLines(c, parent)

    }

    private fun drawHorizontalLines(c: Canvas, parent: RecyclerView) {
        val itemCount = parent.childCount
        val left = parent.paddingLeft + mLeftPadding
        val right = parent.width - parent.paddingRight - mRightPadding
        val startRange = if (mDrawFirstDivide) 0 else 1
        for (i in startRange until itemCount - 1) {
            val child = parent.getChildAt(i) ?: return
            if (-1 == mItemViewType || (parent.getChildViewHolder(child).itemViewType === mItemViewType
                        && parent.getChildViewHolder(parent.getChildAt(i + 1))
                    .itemViewType === mItemViewType)) {
                val top = child.bottom - mDividerHeight
                val bottom = child.bottom
                mDivider.setBounds(left, top, right, bottom)
                mDivider.draw(c)
            }
        }
    }
}