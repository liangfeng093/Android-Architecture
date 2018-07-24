package com.yuanqi.architecture.feature.login

import com.yuanqi.architecture.base.IBasePresenter

/**
 * Created by mzf on 2018/7/17.
 * Email:liangfeng093@gmail.com
 * Desc:登录功能契约类
 */
interface LoginContract {
    /**
     * 定义该界面（功能）中所有的UI状态情况
     */
    interface View {
        //    interface View : IBaseView<Presenter> {
        fun setPresenter(presenter: Presenter)

        /**
         * 登录成功
         */
        fun loginSuccess()

        /**
         * 正在登录
         */
        fun loginIng()

        /**
         * 登录失败
         */
        fun loginFail()

        /**
         * 展示密码
         */
        fun showPwd()

        /**
         * 隐藏密码
         */
        fun hidePwd()

        /**
         * 记住密码
         */
        fun rememberPwd(isRemember: Boolean)

        /**
         * 清空密码
         */
        fun clearPwd()

    }

    /**
     * 定义该界面（功能）中所有的用户操作事件
     */
    interface Presenter : IBasePresenter {
        /**
         * 登录
         */
        fun login(userName: String, pwd: String)

        /**
         * 注册
         */
        fun register()

        /**
         * 明文显示密码/隐藏明文密码
         */
        fun showOrHidePwd(isShow: Boolean)

        /**
         * 第三方登录
         */
//        fun thirdLogin()
        /**
         * 记住密码
         */
        fun rememberPwd(isRemember: Boolean)

        /**
         * 清空密码
         */
        fun clearPwd()

        /**
         *
         */
    }
}