package com.yuanqi.architecture.feature.demo

import com.yuanqi.architecture.data.IDataSource

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class DemoModule {
    var remoteDataSource = object : IDataSource {
        override fun getData(callback: IDataSource.LoadDataCallback) {
            //网络操作获取数据(在子线程操作，避免阻塞UI)

            //是否请求成功
            var isRequest = true

            if (isRequest) {
                //回传数据
//                callback?.onSuccess()
            } else {
                callback?.onFail()
            }
        }

        override fun getData(dataId: String, callback: IDataSource.GetDataCallback) {
        }

        override fun saveData() {
        }

    }
    var localDataSource = object : IDataSource {
        override fun getData(callback: IDataSource.LoadDataCallback) {
            //数据库操作(在子线程操作，避免阻塞UI)

            //是否有数据
            var isData = true


            if (isData) {//数据库有数据
                //回传数据
//                callback?.onSuccess()
            } else {
                //数据库有数据
                callback?.onFail()
            }
        }

        override fun getData(dataId: String, callback: IDataSource.GetDataCallback) {
        }

        override fun saveData() {
        }

    }

}