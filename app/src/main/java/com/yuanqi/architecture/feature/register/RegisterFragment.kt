package com.yuanqi.architecture.feature.register

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment
import com.yuanqi.architecture.im.XmppManager

/**
 * Created by mzf on 2018/7/24.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class RegisterFragment : BaseFragment<RegisterContract.Presenter>(), RegisterContract.View {


    var et_real_name: EditText? = null
    var et_user_name: EditText? = null
    var et_pwd_one: EditText? = null
    var et_pwd_two: EditText? = null
    var btn_register: Button? = null

    override fun setPresenter(presenter: RegisterContract.Presenter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayout(): Int {
        return R.layout.fragment_register
    }

    override fun registerFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView(view: View) {
        et_real_name = view?.findViewById(R.id.et_real_name)
        et_user_name = view?.findViewById(R.id.et_user_name)
        et_pwd_one = view?.findViewById(R.id.et_pwd_one)
        et_pwd_two = view?.findViewById(R.id.et_pwd_two)
        btn_register = view?.findViewById(R.id.btn_register)
    }

    override fun initData() {

    }

    override fun showPwd() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initListener() {
        btn_register?.setOnClickListener {
            XmppManager.register("rzf", "123")
        }
    }

    override fun hidePwd() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}