package com.yuanqi.architecture.feature.demo.room

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.yuanqi.architecture.network.RemoteDateManger

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class ViewModelFactory(private val localDataSource: LocalDao, private val remoteDateManger: RemoteDateManger) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(localDataSource, remoteDateManger) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}