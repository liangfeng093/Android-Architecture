package com.yuanqi.architecture.feature.demo.room

import android.arch.persistence.room.Room
import android.content.Context
import com.yuanqi.architecture.data.IDataSource
import com.yuanqi.architecture.network.RemoteDateManger

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:某个功能模块的数据仓库(使用了三级缓存的机制)
 * 每个功能模块都以自己的数据仓库
 */
class RemoteDataSource {

    fun remoteDataManager(): RemoteDateManger {
        return RemoteDateManger.getInstance()!!
    }

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null


        fun getInstance(): RemoteDataSource? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = RemoteDataSource()
            }
            return INSTANCE
        }

    }
}