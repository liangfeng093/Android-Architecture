package com.yuanqi.architecture.feature.talk

import cn.jiguang.imui.commons.models.IUser

/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class User : IUser {
    //用户id
    var userId = ""
    //用户昵称
    var nickName = ""
    //用户头像
    var headImgUrl = ""

    override fun getAvatarFilePath(): String {
        return headImgUrl
    }

    override fun getId(): String {
        return userId
    }

    override fun getDisplayName(): String {
        return nickName
    }
}