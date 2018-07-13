package com.yuanqi.architecture.feature.demo

import com.yuanqi.architecture.base.IContract
import com.yuanqi.architecture.data.DataRepository
import com.yuanqi.architecture.data.IDataSource

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class DemoPresenter(val fragment: DemoFragment) : IContract.Presenter {

    override fun start() {
        getData()
    }

    private fun getData() {

        var module = DemoModule()

        var dr = DataRepository(module?.remoteDataSource, module?.localDataSource)

        dr?.getData("666", object : IDataSource.GetDataCallback {
            override fun onSuccess(dataId: String, data: Any) {
                //处理数据

                //更改页面状态
                fragment?.loadSuccess()
                fragment?.content()
            }

            override fun onFail() {
                //更改页面状态
                fragment?.loadFail()
            }
        })
    }
}