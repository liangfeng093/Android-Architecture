package com.yuanqi.architecture.base

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:页面加载数据的5种状态
 */
interface IBaseView<T> {

    // 规定View必须要实现setPresenter方法，则View中保持对Presenter的引用。
    fun setPresenter(presenter: T)
//    var presenter: T
    /**
     * 空视图
     */
    fun emptyContent()

    /**
     * 正在加载
     */
    fun loading()

    /**
     * 加载成功
     */
    fun loadSuccess()

    /**
     * 加载失败
     */
    fun loadFail()

    /**
     * 内容视图
     */
    fun content()

}