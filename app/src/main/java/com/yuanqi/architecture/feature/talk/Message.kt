package com.yuanqi.architecture.feature.talk

import cn.jiguang.imui.commons.models.IMessage
import cn.jiguang.imui.commons.models.IUser
import com.yuanqi.architecture.feature.talk.User
import java.util.HashMap

/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class Message : IMessage {

    var id = ""
    //默认消息状态为正在发送中
    var status = IMessage.MessageStatus.SEND_GOING
    var messageDuration: Long = 0L
    //存放一些自定义的参数
    var map = hashMapOf<String, String>()
    //默认消息类型为发送的文字信息
    var messageType = IMessage.MessageType.SEND_TEXT.ordinal
    //文字内容
    var content = ""
    //消息发送进度
    var messageProgress = ""
    //媒体文件路径
    var filePath = ""
    //发送时间
    var time = ""
    //
    var from: User? = null

    override fun getMsgId(): String {
        return id
    }

    override fun getMessageStatus(): IMessage.MessageStatus {
        return status
    }

    override fun getDuration(): Long {
        return messageDuration
    }

    override fun getExtras(): HashMap<String, String> {
        return map
    }

    override fun getType(): Int {
        return messageType
    }

    override fun getText(): String {
        return content
    }

    override fun getProgress(): String {
        return messageProgress
    }

    override fun getMediaFilePath(): String {
        return filePath
    }

    override fun getTimeString(): String {
        return time
    }

    override fun getFromUser(): IUser {
        return from!!
    }
}