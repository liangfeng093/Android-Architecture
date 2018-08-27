package com.yuanqi.architecture.feature.talk

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import cn.jiguang.imui.commons.models.IMessage
import cn.jiguang.imui.messages.MessageList
import cn.jiguang.imui.messages.MsgListAdapter
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment
import kotlinx.android.synthetic.main.im_input_actionbar.*

@SuppressLint("ValidFragment")
/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class TalkFragment : BaseFragment<TalkContract.Presenter>, TalkContract.View {

    var msg_list: MessageList? = null
    var iv_more: ImageView? = null
    var im_toolbar_more: LinearLayout? = null
    var adapter: MsgListAdapter<Message>? = null
    var et_input: EditText? = null


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
        iv_more = view?.findViewById(R.id.iv_more)
        et_input = view?.findViewById(R.id.et_input)
        im_toolbar_more = view?.findViewById(R.id.im_toolbar_more)
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
            message?.messageType = IMessage.MessageType.SEND_TEXT.ordinal
            adapter?.addToStart(message, true)
            i++
        }


    }

    override fun initData() {
    }

    var isShow = false
    @RequiresApi(Build.VERSION_CODES.O)
    override fun initListener() {
        et_input?.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {//有焦点
                    im_toolbar_more?.visibility = View.GONE
                } else {
                    im_toolbar_more?.visibility = View.VISIBLE
                }
            }
        })
        iv_more?.setOnClickListener {

            if (!isShow) {
                isShow = true
                im_toolbar_more?.visibility = View.VISIBLE
                showKeyBoard()
            } else {
                isShow = false
                im_toolbar_more?.visibility = View.GONE
                showKeyBoard()
            }
        }

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


    fun showKeyBoard() {
        var imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm?.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        imm?.showSoftInput(et_input, InputMethodManager.SHOW_FORCED)
    }

    fun hideKeyBoard() {
        var imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(et_input?.getWindowToken(), 0)

    }
}