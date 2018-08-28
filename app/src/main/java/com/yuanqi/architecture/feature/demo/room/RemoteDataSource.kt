package com.yuanqi.architecture.feature.demo.room

import android.arch.persistence.room.Room
import android.content.Context
import com.yuanqi.architecture.data.IDataSource

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:某个功能模块的数据仓库(使用了三级缓存的机制)
 * 每个功能模块都以自己的数据仓库
 */
class RemoteDataSource : IDataSource {
//abstract class RemoteDataSource(val remoteDataSource: IDataSource) : IDataSource {


//    abstract fun remoteDataSource(): RemoteDataSource?

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = RemoteDataSource()


        fun getInstance(): RemoteDataSource? {
            return INSTANCE
        }

    }

    override fun getData(callback: IDataSource.LoadDataCallback) {

    }

    override fun getData(dataId: String, callback: IDataSource.GetDataCallback) {

        /*remoteDataSource?.getData(object : IDataSource.LoadDataCallback {
            override fun onSuccess(dataId: String, data: Any) {
                //处理数据(缓存到内存，数据库中)

                //回调函数
                callback?.onSuccess(dataId, data)
            }

            override fun onFail() {
                callback?.onFail()
            }
        })*/
    }

    override fun saveData() {

    }


}