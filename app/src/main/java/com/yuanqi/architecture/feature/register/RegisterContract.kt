package com.yuanqi.architecture.feature.register

import com.yuanqi.architecture.base.IBasePresenter

/**
 * Created by mzf on 2018/7/24.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
interface RegisterContract {
    /**
     * 定义注册界面界面（功能）中所有的UI状态情况
     */
    interface View {
        /**
         * 注册成功
         */
        fun registerSuccess()

        /**
         * 注册失败
         */

        fun registerFail()
        /**
         * 正在注册
         */

        /**
         * 展示密码
         */
        fun showPwd()

        /**
         * 隐藏密码
         */
        fun hidePwd()

    }

    /**
     * 定义注册界面（功能）中所有的用户操作事件
     */
    interface Presenter : IBasePresenter {
        /**
         * 返回登录页面
         */
        fun backLogin()

        /**
         * 注册
         */
        fun register()
    }
}