package com.yuanqi.architecture.feature.login

import android.content.Context
import android.util.Log

/**
 * Created by mzf on 2018/7/17.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginPresenter(var loginFragment: LoginFragment) : LoginContract.Presenter {

    val TAG = this.javaClass.name
    var context: Context? = null

    override fun start() {
        context = loginFragment?.activity
    }

    override fun login(userName: String, pwd: String) {
        Log.e(TAG, ">>>>>>>登录成功:")
        //直接调用登录接口
//        Toast.makeText(context,context?.resources?.getString(R.string.login_success),Toast.LENGTH_LONG ).show()
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