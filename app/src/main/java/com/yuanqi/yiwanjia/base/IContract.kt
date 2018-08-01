package com.yuanqi.yiwanjia.base

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:契约类:用来管理View和Presenter
 *
 * 一般都是命名为XxxContract,内部的View和Presenter命名类似
 * XxxView,XxxPresenter
 */
interface IContract {

    /**
     * 定义该界面（功能）中所有的UI状态情况
     */
    interface View :IBaseView<Presenter>{

    }

    /**
     * 定义该界面（功能）中所有的用户操作事件
     */
    interface Presenter : IBasePresenter {

    }
}