package com.brins.nba.ui.holder

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.brins.nba.R
import com.brins.nba.ui.data.BaseMainImageData
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.brins.nba.utils.jumpToImageActivity
import com.chad.library.adapter.base.BaseViewHolder

/**
 * @author lipeilin
 * @date 2020/7/17
 */
class BaseMainNewsImageHolder(view: View) : BaseViewHolder<BaseMainImageData>(view) {


    val image: ImageView = view.findViewById(R.id.image_view)

    override fun setData(data: BaseMainImageData, dataPosition: Int) {
        super.setData(data, dataPosition)
        GlideHelper.setImageResource(image, data.imageUrl)
        ViewCompat.setTransitionName(image, data.imageUrl)
        image.setOnClickListener {
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mContext as Activity,
                image,
                ViewCompat.getTransitionName(image)!!
            )
            jumpToImageActivity(mContext, options, image.transitionName)
        }
    }
}