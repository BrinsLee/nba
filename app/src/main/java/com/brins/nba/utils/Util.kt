package com.brins.nba.utils

import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

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