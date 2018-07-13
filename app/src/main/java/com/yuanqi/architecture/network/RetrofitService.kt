package com.yuanqi.architecture.network

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:管理所有的接口请求
 */
interface RetrofitService {

    companion object {
        val BaseURL: String = "http://xxx.xx.xxx.xx:xxxx/XXXX/"//访问接口的ip和端口
        val videoUrl = BaseURL + "XXX/"//音频文件的加载路径
        val imgUrl = BaseURL + "img/"//图片的加载路径
    }


}