package com.yuanqi.architecture.feature.home

import com.yuanqi.architecture.base.IBasePresenter

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
interface HomeContract {
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