package com.yuanqi.architecture.feature.talk

import com.yuanqi.architecture.base.IBasePresenter

/**
 * Created by mzf on 2018/8/2.
 * Email:liangfeng093@gmail.com
 * Desc:聊天界面契约类
 */
interface TalkContract {
    /**
     * 定义该界面（功能）中所有的UI状态情况
     */
    interface View {

    }

    /**
     * 定义该界面（功能）中所有的用户操作事件
     */
    interface Presenter : IBasePresenter {

    }
}