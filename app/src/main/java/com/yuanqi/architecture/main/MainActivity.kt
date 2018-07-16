package com.yuanqi.architecture.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.yuanqi.architecture.R
import com.yuanqi.architecture.feature.demo.User

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()

    }
}
