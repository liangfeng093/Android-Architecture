package com.yuanqi.architecture.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuanqi.architecture.R
import com.yuanqi.architecture.feature.login.LoginFragment

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fl_fragment_container, LoginFragment())
        transaction?.commit()

    }

    override fun onResume() {
        super.onResume()

    }
}
