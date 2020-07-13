package com.brins.nba.api.result

/**
 * @author lipeilin
 * @date 2020/7/13
 */
class CommentResultData {

    var against: Int = 0
    var anonymous: Boolean = false
    var buildLevel = 0
    var commentId: Long = 0
    var commentType: Int = 0
    var content = ""
    var createTime = ""
    var ip = ""
    var isDel = false
    var isFake = false
    var postId = ""
    var productKey = ""
    var shareCount = 0
    var siteName = ""
    var source = ""
    var unionState = false
    var user: UserInfo? = null
    var vote: Int = 0

    class UserInfo {
        var avatar = ""
        var location = ""
        var nickname = ""
        var titleInfo: TitleInfo? = null
        var userId: Long = 0
        var userType = 1

    }

    class TitleInfo {
        var title = ""
        var titleIcon = ""
        var titleUrl = ""

    }
}