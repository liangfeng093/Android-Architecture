package com.yuanqi.yiwanjia.base

import android.view.View

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:Fragment中必须实现的方法
 */
//interface IBaseFragmentView : IBaseView<IBasePresenter> {
interface IBaseFragmentView<T> {

    /**
     *
     */
    fun setPresenter(presenter: T)

    /**
     * 获取布局文件
     */
    fun getLayout(): Int

    /**
     * 初始化控件
     */
    fun initView(view: View)

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化控件的监听
     */
    fun initListener()
}