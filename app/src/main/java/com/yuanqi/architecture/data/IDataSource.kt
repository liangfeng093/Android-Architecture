package com.yuanqi.architecture.data

import com.yuanqi.architecture.network.RemoteDateManger

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:数据仓库，有两个数据管理器。分别管理远程数据和本地数据
 */
interface IDataSource {

//    fun getLocalDataManager()

//    fun getRemoteDataManager(): RemoteDateManger

    /**
     * 加载数据回调(web数据)
     */
    interface LoadDataCallback {

        /**
         * 根据dataId，将data强转为对应的MutableList集合
         */
        fun onSuccess(dataId: String, data: Any)


        fun onFail()
    }

    /**
     * 获取数据回调(本地数据)
     */
    interface GetDataCallback {
        /**
         * 根据dataId，将data强转为对应的MutableList集合
         */
        fun onSuccess(dataId: String, data: Any)

        fun onFail()
    }


    /**
     * 加载web端数据
     */
    fun getData(callback: LoadDataCallback)

    /**
     * 获取本地数据
     */
    fun getData(dataId: String, callback: GetDataCallback)

    /**
     * 保存数据
     */
    fun saveData()


}