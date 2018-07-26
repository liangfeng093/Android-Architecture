package com.yuanqi.architecture.feature.home

import android.content.Context

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomePresenter : HomeContract.Presenter {

    val TAG = this.javaClass.name
    var context: Context? = null

    constructor(homeFragment: HomeFragment) {
        this.context = homeFragment?.activity
    }


    override fun start() {

    }

}