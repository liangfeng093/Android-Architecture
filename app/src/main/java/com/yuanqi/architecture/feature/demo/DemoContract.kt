package com.yuanqi.architecture.feature.demo

import com.yuanqi.architecture.base.IBasePresenter
import com.yuanqi.architecture.base.IBaseView

/**
 * Created by mzf on 2018/7/16.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
interface DemoContract {
    /**
     * 定义该界面（功能）中所有的UI状态情况
     */
    interface View : IBaseView<Presenter> {

    }

    /**
     * 定义该界面（功能）中所有的用户操作事件
     */
    interface Presenter : IBasePresenter {

    }
}