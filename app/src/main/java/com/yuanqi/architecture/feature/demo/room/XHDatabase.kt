package com.yuanqi.architecture.feature.demo.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:创建数据库(数据库配置)
 */
@Database(entities = arrayOf(Student::class), version = 1)//创建的数据库包括Student表
abstract class XHDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

    companion object {
        @Volatile
        private var INSTANCE: XHDatabase? = null

        fun getInstance(context: Context): XHDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        XHDatabase::class.java, "XieHe.db")
                        .build()
    }

}