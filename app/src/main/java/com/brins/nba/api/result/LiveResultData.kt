package com.brins.nba.api.result

import com.google.gson.annotations.SerializedName

/**
 * @author lipeilin
 * @date 2020/7/10
 */
class LiveResultData {

    var data: LiveInfo? = null

    class LiveInfo {
        @SerializedName("cur_date")
        var currentDate: String = ""

        @SerializedName("pre_date")
        var preDate: String = ""

        @SerializedName("next_date")
        var nextDate: String = ""
    }

    var msg = ""

    var code = 0
}