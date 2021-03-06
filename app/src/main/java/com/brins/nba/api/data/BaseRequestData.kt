package com.brins.nba.api.data

import com.brins.nba.api.ServiceApi.APPKEY
import com.brins.nba.utils.MD5Util


/**
 * @author lipeilin
 * @date 2020/7/10
 */
open class BaseRequestData {
    val timestamp: Long = System.currentTimeMillis()
    var sign: String = MD5Util.md5(APPKEY + timestamp.toString())
}