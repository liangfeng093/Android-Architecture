package com.yuanqi.architecture.network

import com.yuanqi.architecture.feature.demo.TestBean_IdiomDictionary
import com.yuanqi.architecture.feature.demo.TestBody
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:管理所有的接口请求
 */
interface RetrofitService {

    companion object {
        val BaseURL: String = "http://xxx.xx.xxx.xx:xxxx/XXXX/"//访问接口的ip和端口
        val testBaseURL: String = "http://v.juhe.cn/"//聚合数据测试接口
        val videoUrl = BaseURL + "XXX/"//音频文件的加载路径
        val imgUrl = BaseURL + "img/"//图片的加载路径
    }

    /**
     * 测试接口
     * 聚合数据接口:成语词典
     */
    @POST("chengyu/query")
    fun idiomDictionary(@Body body: TestBody): Observable<TestBean_IdiomDictionary>

    @FormUrlEncoded
    @POST("chengyu/query")
    fun idiomDictionary1(@Field("word") word: String,@Field("key") key: String): Observable<TestBean_IdiomDictionary>

}