package com.yuanqi.architecture.feature.login

/**
 * Created by mzf on 2018/7/17.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginPresenter(var loginFragment: LoginFragment) : LoginContract.Presenter {
    override fun start() {
    }

    override fun login(userName: String, pwd: String) {
        //直接调用登录接口

        //正在登录
        loginFragment?.loginIng()

        //登录成功
        loginFragment?.loginSuccess()

        //登录失败
        loginFragment?.loginFail()
    }

    override fun register() {
    }

    override fun showOrHidePwd(isShow: Boolean) {
    }

    override fun rememberPwd(isRemember: Boolean) {
    }

    override fun clearPwd() {

    }
}