package com.brins.nba.utils

import android.content.Context
import android.content.Intent
import com.brins.nba.ui.activity.NewsInfoActivity

/**
 * @author lipeilin
 * @date 2020/7/10
 */

/*public static String md5(String content) {
    byte[] hash;
    try {
        hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException ("NoSuchAlgorithmException", e);
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException ("UnsupportedEncodingException", e);
    }

    StringBuilder hex = new StringBuilder(hash.length * 2);
    for (byte b : hash) {
        if ((b & 0xFF) < 0x10){
        hex.append("0");
    }
        hex.append(Integer.toHexString(b & 0xFF));
    }
    return hex.toString();
}*/


fun jumpToWebViewActivity(activity: Context, url: String) {
    val intent = Intent(activity, NewsInfoActivity::class.java)
    intent.putExtra(NewsInfoActivity.KEY_URL, url)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    activity.startActivity(intent)
}