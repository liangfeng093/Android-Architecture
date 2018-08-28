package com.yuanqi.architecture.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yuanqi.architecture.R
import com.yuanqi.architecture.feature.demo.room.MainViewModel
import com.yuanqi.architecture.feature.demo.room.Injection
import com.yuanqi.architecture.feature.demo.room.ViewModelFactory
import com.yuanqi.architecture.feature.login.LoginFragment

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name

    private lateinit var viewModelFactory: ViewModelFactory

    private lateinit var dataRepository: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fl_fragment_container, LoginFragment())
        transaction?.commit()

        viewModelFactory = Injection.provideViewModelFactory(this)
        dataRepository = ViewModelProviders.of(this, viewModelFactory)?.get(MainViewModel::class.java)


        dataRepository?.getStudentName("1")

    }
}
