package com.yuanqi.architecture.feature.home

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:home主页医院列表对应的JavaBean
 */
class Hospital {
    /**
     *医院logo图片链接
     */
    var logoPicLink = ""
    /**
     * 医院名称
     */
    var name = ""
    /**
     * 医院级别
     */
    var level = ""
    /**
     * 医院地址
     */
    var address = ""
    /**
     * 定位距离
     */
    var distance = ""
    /**
     * 功能
     */
    var features = mutableListOf<String>()
}