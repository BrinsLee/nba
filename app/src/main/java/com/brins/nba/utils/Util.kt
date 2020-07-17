package com.brins.nba.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.app.ActivityOptionsCompat
import com.brins.nba.ui.activity.ImageActivity
import com.brins.nba.ui.activity.NewsInfoActivity
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream


/**
 * @author lipeilin
 * @date 2020/7/10
 */


fun jumpToWebViewActivity(activity: Context, pos: Int) {
    val intent = Intent(activity, NewsInfoActivity::class.java)
    intent.putExtra(NewsInfoActivity.KEY_POS, pos)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(intent)
}

fun jumpToImageActivity(activity: Context, options: ActivityOptionsCompat, name: String) {
    val intent = Intent(activity, ImageActivity::class.java)
    intent.putExtra(ImageActivity.TRANSITION_NAME, name)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(intent, options.toBundle())
}

fun bitmap2Stream(bitmap: Bitmap): InputStream? {
    val baos = ByteArrayOutputStream()
    bitmap.compress(CompressFormat.JPEG, 100, baos)
    return ByteArrayInputStream(baos.toByteArray())
}

fun drawable2Bitmap(drawable: Drawable?): Bitmap? {
    return if (drawable == null) null else (drawable as BitmapDrawable)
        .bitmap
}