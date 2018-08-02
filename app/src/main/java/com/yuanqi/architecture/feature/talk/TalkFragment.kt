package com.yuanqi.architecture.feature.talk

import android.annotation.SuppressLint
import android.view.View
import cn.jiguang.imui.commons.models.IMessage
import cn.jiguang.imui.messages.MessageList
import cn.jiguang.imui.messages.MsgListAdapter
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment

@SuppressLint("ValidFragment")
/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class TalkFragment : BaseFragment<TalkContract.Presenter>, TalkContract.View {

    var msg_list: MessageList? = null
    var adapter: MsgListAdapter<Message>? = null


    var headImgUrl = "https://raw.githubusercontent.com/liangfeng093/MarkdownBlogs/master/res/2018-8/headImg_1.pngg"
    /**
     * 消息发送者的id
     */
    var senderId = ""

    constructor(senderId: String) : super() {
        this.senderId = senderId
    }


    override fun setPresenter(presenter: TalkContract.Presenter) {
        mPresenter = presenter
    }

    override fun getLayout(): Int {
        return R.layout.fragment_im
    }

    override fun onResume() {
        super.onResume()
        if (mPresenter == null) {
            setPresenter(TalkPresenter(this))
        }
    }


    override fun initView(view: View) {
        msg_list = view?.findViewById(R.id.msg_list)
        adapter = MsgListAdapter(senderId, ImgLoader(activity))
        msg_list?.setAdapter(adapter)

        var testMessages = mutableListOf<Message>()
        var i = 0
        while (i < 10) {
            var message = Message()
            message?.content = i?.toString()
            message?.from = User()
            message?.from?.headImgUrl = headImgUrl
            adapter?.addToStart(message, true)
            i++
        }

        while (i < 20) {
            var message = Message()
            message?.content = i?.toString()
            message?.from = User()
            message?.from?.headImgUrl = headImgUrl
            message?.status = IMessage.MessageStatus.RECEIVE_GOING
            message?.messageType =
            adapter?.addToStart(message, true)
            i++
        }



    }

    override fun initData() {
    }

    override fun initListener() {
        //消息点击事件
        adapter?.setOnMsgClickListener {

        }
        //头像点击事件(消息对应的头像)
        adapter?.setOnAvatarClickListener {

        }
        //消息长按事件
        adapter?.setMsgLongClickListener(object : MsgListAdapter.OnMsgLongClickListener<Message> {
            override fun onMessageLongClick(view: View?, message: Message?) {

            }
        })
        //点击消息状态按钮触发
        adapter?.setMsgStatusViewClickListener(object : MsgListAdapter.OnMsgStatusViewClickListener<Message> {
            override fun onStatusViewClick(message: Message?) {

            }
        })
    }
}