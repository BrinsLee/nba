package com.brins.nba.api.result

import com.brins.nba.ui.data.BaseData


class NewsResultData {

    var liveInfo: LiveResultData? = null

    var commentCount: Int = 0

    var docid: String = ""

    var source: String = ""

    var title: String = ""

    var priority: Int = 0

    var hasImg: Int = 0

    var url: String = ""

    var stitle = ""

    var digest: String = ""

    var imgsrc: String = ""

    var ptime: String = ""

    var content: MutableList<BaseData>? = null


}