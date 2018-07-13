package com.yuanqi.architecture.data

import com.yuanqi.architecture.network.RemoteDateManger

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class DataRepository : IDataRepository {

    override fun getLocalDataManager() {

    }

    override fun getRemoteDataManager(): RemoteDateManger {
        return RemoteDateManger.getInstance()!!
    }
}