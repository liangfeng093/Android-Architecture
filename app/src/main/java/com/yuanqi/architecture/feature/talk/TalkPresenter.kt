package com.yuanqi.architecture.feature.talk

import android.content.Context

/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class TalkPresenter(var fragment: TalkFragment) : TalkContract.Presenter {
    var context: Context? = null


    override fun start() {
        context = fragment?.activity
    }

}