package com.yuanqi.yiwanjia.data

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:某个功能模块的数据仓库(使用了三级缓存的机制)
 * 每个功能模块都以自己的数据仓库
 */
class DataRepository(val remoteDataSource: IDataSource, val localDataSource: IDataSource) : IDataSource {

//    var cachedData : MutableMap<String,MutableList<Any>> = mutableMapOf()
    /**
     * 存入map的时候，key要写清楚数据类型
     * cachedData?.put("xxx_MutableListOf_String", mutableListOf<String>())
     * cachedData?.get("xxx_MutableListOf_String") as MutableList<String>
     */
    var cachedData: MutableMap<String, Any> = mutableMapOf()//使用map缓存所有使用过的数据

    override fun getData(callback: IDataSource.LoadDataCallback) {

    }

    override fun getData(dataId: String, callback: IDataSource.GetDataCallback) {
        if (dataId != null && dataId.isNotEmpty()) {
            var data = getDataWithId(dataId)
            data?.let {
                callback.onSuccess(dataId, it)
                return
            }
        }

        //缓存没有数据，从数据库获取
        localDataSource?.getData(dataId, object : IDataSource.GetDataCallback {
            override fun onSuccess(dataId: String, data: Any) {
                //处理数据

                //回调函数
                callback?.onSuccess(dataId, data)
            }

            override fun onFail() {
                remoteDataSource?.getData(object : IDataSource.LoadDataCallback {
                    override fun onSuccess(dataId: String, data: Any) {
                        //处理数据

                        //回调函数
                        callback?.onSuccess(dataId, data)
                    }

                    override fun onFail() {
                        callback?.onFail()
                    }


                })
            }

        })


    }

    private fun getDataWithId(dataId: String): Any? {
        var dataList: Any? = null
//        var dataList: MutableList<Any>? = null
        when (dataId) {
            "xxx_MutableListOf_String" -> {//这里的xxx_MutableListOf_String使用常量字符串代替
                var data = cachedData.get("xxx_MutableListOf_String")
                data?.let {
                    //有缓存数据
                    dataList = data
//                    dataList = data as MutableList<Any>
                }
            }
        }
        return dataList
    }

    override fun saveData() {
    }


}