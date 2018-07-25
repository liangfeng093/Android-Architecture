package com.yuanqi.architecture.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:远程数据管理者。主要管理与web端的数据交互
 */
class RemoteDateManger {
    //私有构造,单例模式
    private constructor() {
        initRetrofit()
    }

    /**
     * 初始化Retrofit实例
     */
    private fun initRetrofit() {
        //拦截器（打印网络请求log）
        var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BASIC
//        var httpInterceptor = HttpInterceptor()
        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)//添加拦截器
//                .addInterceptor(httpInterceptor)//添加请求头
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(RetrofitService.testBaseURL)
//                .baseUrl(RetrofitService.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())//配置gson转换
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//配置rxjava转换
                .client(okHttpClient)
                .build()
                .create(RetrofitService::class.java)//创建接口实例
    }

    /**
     * 日志拦截器
     */
    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            Log.e("HttpLogInfo", message)
        }

    }

   /* class HttpInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): okhttp3.Response {
            Log.e("HttpInterceptor", "")
            var builder = chain?.request()?.newBuilder()
            //添加请求头
            var request = builder?.addHeader("content-type", "text/html;charset=UTF-8")?.build()
            return chain?.proceed(request)!!
        }
    }*/


    //相当于静态方法
    companion object {
        var retrofit: RetrofitService? = null
        var remoteDataManager: RemoteDateManger? = null
        val TAG = this.javaClass.name
        //使用单例模式，避免重复创建
        fun getInstance(): RemoteDateManger? {
            if (remoteDataManager == null) {
                synchronized(RemoteDateManger::class.java) {
                    //双锁
                    if (remoteDataManager == null) {
                        remoteDataManager = RemoteDateManger()
                    }
                }
            }
            return remoteDataManager
        }

    }

}