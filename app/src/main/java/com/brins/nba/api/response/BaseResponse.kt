package com.brins.nba.api.response

/**
 * @author lipeilin
 * @date 2020/7/10
 */
open class BaseResponse<T> {

    var ret: Int = 0

    var msg: String = ""

    var data: T? = null

    fun isSuccess() : Boolean = ret == 200

}