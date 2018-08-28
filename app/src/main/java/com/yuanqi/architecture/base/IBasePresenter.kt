package com.yuanqi.architecture.base

import android.arch.lifecycle.DefaultLifecycleObserver

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
interface IBasePresenter : DefaultLifecycleObserver {

    /**
     * 该方法的作用是Presenter开始获取数据并调用View的方法来刷新界面，其调用时机是在Fragment类的onResume方法中
     */
    fun start()
}