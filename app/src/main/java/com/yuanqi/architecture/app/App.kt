package com.yuanqi.architecture.app

import android.app.Application

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc : APP最先执行的位置，主要进行一些初始化操作。不允许进行耗时操作
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        //全局开关
        var isDebug = true
    }

}