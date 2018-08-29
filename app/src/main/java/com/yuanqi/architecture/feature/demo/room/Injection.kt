package com.yuanqi.architecture.feature.demo.room

import android.content.Context

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:启用数据源注入
 */
object Injection {//单例，类名直接调用

    fun provideLocalSource(context: Context): LocalDao {
        val database = XHDatabase.getInstance(context)
        return database.localDao()
    }

    fun provideRemoteSource(context: Context): RemoteDataSource {
        val database = RemoteDataSource.getInstance()
        return database!!
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val localDataSource = provideLocalSource(context)
        val remoteDataSource = provideRemoteSource(context)
        return ViewModelFactory(localDataSource, remoteDataSource)
    }
}