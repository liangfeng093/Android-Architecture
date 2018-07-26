package com.yuanqi.architecture.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yuanqi.architecture.R
import com.yuanqi.architecture.app.App
import com.yuanqi.architecture.feature.home.HomeFragment
import com.yuanqi.architecture.feature.login.LoginFragment

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = supportFragmentManager?.beginTransaction()
//        transaction?.replace(R.id.fl_fragment_container, LoginFragment())
        transaction?.replace(R.id.fl_fragment_container, HomeFragment())
        transaction?.commit()

//        launch {
//            testEnc()
//        }
    }

    override fun onResume() {
        super.onResume()

    }


}
