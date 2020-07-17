package com.brins.nba.ui.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.core.view.ViewCompat
import com.brins.nba.R
import com.brins.nba.utils.GlideHelper.GlideHelper
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : BaseActivity() {

    companion object {
        val TRANSITION_NAME = "TRANSITION_NAME"
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_image
    }

    override fun onCreateAfterBinding(savedInstanceState: Bundle?) {
        super.onCreateAfterBinding(savedInstanceState)
        postponeEnterTransition()
        val ex = intent.extras
        val name = ex!!.getString(TRANSITION_NAME)
        ViewCompat.setTransitionName(image_view, name)
        GlideHelper.setImageResource(image_view, name, object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                image_view.setImageDrawable(resource)
                startPostponedEnterTransition()
                return true
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                startPostponedEnterTransition()
                return false
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}