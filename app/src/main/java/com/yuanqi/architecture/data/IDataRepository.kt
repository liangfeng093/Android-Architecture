package com.yuanqi.architecture.data

import com.yuanqi.architecture.network.RemoteDateManger

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:数据仓库，有两个数据管理器。分别管理远程数据和本地数据
 */
interface IDataRepository {

    fun getLocalDataManager()

    fun getRemoteDataManager(): RemoteDateManger
}