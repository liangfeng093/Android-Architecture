package com.yuanqi.architecture.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yuanqi.architecture.R
import com.yuanqi.architecture.app.App
import com.yuanqi.architecture.base.BaseFragment
import com.yuanqi.architecture.feature.login.LoginFragment
import com.yuanqi.architecture.feature.register.RegisterFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name

    var fragments = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        var transaction = supportFragmentManager?.beginTransaction()
        var loginFragment = LoginFragment()
        fragments?.add(loginFragment)
        transaction?.replace(R.id.fl_fragment_container, loginFragment)
        transaction?.commit()

//        launch {
//            testEnc()
//        }
    }

    @Subscribe
    fun changeFragment(event: ChangeFragmentEvent) {
        var transaction = supportFragmentManager?.beginTransaction()
        var registerFragment = RegisterFragment()
        fragments?.add(registerFragment)
        transaction?.replace(R.id.fl_fragment_container, registerFragment)
        transaction?.commit()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
