package com.yuanqi.architecture.feature.login

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment

/**
 * Created by mzf on 2018/7/17.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginFragment : BaseFragment<LoginContract.Presenter>(), LoginContract.View {

    var et_user_name: EditText? = null
    var et_pwd: EditText? = null
    var btn_login: Button? = null
    var btn_clear_pwd: Button? = null

    var isShowPwd = false

    override fun setPresenter(presenter: LoginContract.Presenter) {
        mPresenter = presenter
    }

    override fun onResume() {
        super.onResume()
        if (mPresenter == null) {
            setPresenter(LoginPresenter(this))
        }
    }


    override fun getLayout(): Int {
        return R.layout.fragment_login
    }

    override fun initView(view: View) {
        et_user_name = view?.findViewById(R.id.et_user_name)
        et_pwd = view?.findViewById(R.id.et_pwd)
        btn_login = view?.findViewById(R.id.btn_login)
        btn_clear_pwd = view?.findViewById(R.id.btn_clear_pwd)
    }

    override fun initData() {
        mPresenter?.start()
    }

    override fun initListener() {
        RxView.clicks(btn_login as Button)
                ?.subscribe {
                    var userName = et_user_name?.text?.trim()?.toString()
                    var pwd = et_pwd?.text?.trim()?.toString()
                    if (userName != null && pwd != null) {
                        if (userName?.isEmpty()!!) {//账号未填写，提示用户
                            Toast.makeText(context, context?.resources?.getString(R.string.login_user_name_tip), Toast.LENGTH_LONG).show()
                            return@subscribe
                        }
                        if (pwd?.isEmpty()!!) {//密码未填写，提示用户
                            Toast.makeText(context, context?.resources?.getString(R.string.login_pwd_tip), Toast.LENGTH_LONG).show()
                            return@subscribe
                        }
                        //对数据做好处理，避免后续逻辑做重复判断
                        mPresenter?.login(userName, pwd)
                    }
                }

        RxView.clicks(btn_clear_pwd as Button)
                ?.subscribe {
                    if (isShowPwd) {
                        hidePwd()
                    } else {
                        showPwd()
                    }
                }
        /*btn_login?.setOnClickListener {
            var userName = et_user_name?.text?.trim()?.toString()
            var pwd = et_pwd?.text?.trim()?.toString()
            if (userName != null && pwd != null) {
                if (userName?.isEmpty()!!) {//账号未填写，提示用户
                    Toast.makeText(context, context?.resources?.getString(R.string.login_user_name_tip), Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                if (pwd?.isEmpty()!!) {//密码未填写，提示用户
                    Toast.makeText(context, context?.resources?.getString(R.string.login_pwd_tip), Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                //对数据做好处理，避免后续逻辑做重复判断
                mPresenter?.login(userName, pwd)
            }
        }
*/
        /*btn_clear_pwd?.setOnClickListener {
            if (isShowPwd) {
                hidePwd()
            } else {
                showPwd()
            }
        }*/
    }

    override fun loginIng() {
    }


    override fun loginSuccess() {
        //登录成功，直接跳转到主界面
    }

    override fun loginFail() {
        //登录失败，提示用户

    }

    override fun showPwd() {
        isShowPwd = true
        et_pwd?.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    override fun hidePwd() {
        isShowPwd = false
        et_pwd?.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun rememberPwd(isRemember: Boolean) {

    }

    override fun clearPwd() {
    }


}