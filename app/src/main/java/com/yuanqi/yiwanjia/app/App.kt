package com.yuanqi.yiwanjia.app

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager


/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc : APP最先执行的位置，主要进行一些初始化操作。不允许进行耗时操作
 */
class App : MultiDexApplication() {
//class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)//分包操作
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        CrashHandler.init(this, "544935678@qq.com")
        //初始化数据库
        FlowManager.init(FlowConfig.builder(this)
                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase::class.java)
                        .databaseName("AppDatabase")
                        .build())
                .build())


        //初始化
//        RxTool.init(this)
    }

    companion object {
        //全局开关
        var isDebug = true
        //
        var context: Context? = null
    }

}