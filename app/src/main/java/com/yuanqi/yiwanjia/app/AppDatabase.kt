package com.yuanqi.yiwanjia.app

import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by mzf on 2018/7/16.
 * Email:liangfeng093@gmail.com
 * Desc:DBFlow数据库配置
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
class AppDatabase {
//class AppDatabase(val NAME: String, val VERSION: Int) {

    companion object {
        const val NAME = "AppDatabase"
        const val VERSION = 1
    }
}