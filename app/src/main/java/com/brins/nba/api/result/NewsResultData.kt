package com.brins.nba.api.result

class NewsResultData {

    var data: List<NewsData>? = null

    class NewsData() {

        var liveInfo: LiveResultData? = null

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
    }
}