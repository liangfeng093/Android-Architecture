package com.yuanqi.yiwanjia.feature.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.Toast
import com.yuanqi.yiwanjia.R
import com.yuanqi.yiwanjia.network.RetrofitService
import kotlinx.android.synthetic.main.activity_web.*


/**
 * Created by mzf on 2018/7/10.
 * Email:liangfeng093@gmail.com
 */
class WebActivity : AppCompatActivity() {

    val TAG = this.javaClass.name
    val testUrl = "http://t1451test.1451cn.com/smejhomepage/homepage.html?wqXCm8OAwqHCsnRtZmxiY3ZlbmxraGZ2a2FvaHhXwrLCl8KlwqfCu8KfwrTClnJswq3CgmlpZWtianJoZmRtb2h9W8KfwpnCnsKrbuWOpOa1oOeFqFrCuMKVwq5vZntxd25qbGxnZHNawqvCmMKqdWh7ZQ=="
    val baiDuUrl = "https://www.baidu.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        var url = ""
        var flag = intent?.getStringExtra(JumpFlags.FLAG)
        when (flag) {
            JumpFlags.HOSPITAL -> {
                url = RetrofitService.hospital
            }
            JumpFlags.RESERVATION_REGISTER -> {
                url = RetrofitService.one_level_reservation_register_url
            }
            JumpFlags.OUTPATIENT_PAYMENT -> {
                url = RetrofitService.one_outpatient_payment_url
            }
            JumpFlags.REPORT_CARD -> {
                url = RetrofitService.one_level_report_card_url
            }
            JumpFlags.INTELLIGENT_NAVIGATION -> {
                url = RetrofitService.secondary_intelligent_navigation_url
            }
            JumpFlags.HOSPITALIZATION_PAYMENT -> {
                url = RetrofitService.secondary_hospitalization_advance_payment_url
            }
            JumpFlags.INSPECT_REPORT_CARD -> {
                url = RetrofitService.secondary_inspect_report_card_url
            }
            JumpFlags.HOSPITAL_INTRODUCTION -> {
                url = RetrofitService.secondary_hospital_introduction_url
            }
            JumpFlags.ONE_DAY_LIST -> {
                url = RetrofitService.secondary_one_day_list_url
            }
            JumpFlags.LEAVE_HOSPITAL_LIST -> {
                url = RetrofitService.secondary_leave_hospital_demand_url
            }
            JumpFlags.PRICE_DEMAND -> {
                url = RetrofitService.secondary_price_demand_url
            }
            JumpFlags.TODAY_REGISTER -> {
                url = RetrofitService.one_level_today_register_url
            }
//            JumpFlags.->{}
        }

        web_view?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Log.e(TAG, ">>>>>>>网页链接:" + url)
                //拦截微信支付链接
                if (url?.contains("weixin://wap/pay")!!) {
                    Log.e(TAG, ">>>>>>>微信链接:")
                    if (isWxAvailable()) {
                        var intent = Intent()
                        intent.setAction(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(url))
                        startActivity(intent)
                        return true
                    } else {
                        Toast.makeText(this@WebActivity, "请安装微信后，再进行支付操作", Toast.LENGTH_LONG).show()
                    }
                }
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        web_view?.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress > 0 && newProgress < 100) {
                    progress_web?.visibility = View.VISIBLE
                    progress_web?.progress = newProgress?.toFloat()
                } else {
                    progress_web?.visibility = View.GONE
                }
            }
        }

        var settings = web_view?.settings
        settings?.javaScriptEnabled = true//支持js交互

        //LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
        //LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
        //LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
        //LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        settings?.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK//优先使用缓存

//        web_view?.loadUrl(baiDuUrl)
        Log.e(TAG, ">>>>>>>url:" + url)
        web_view?.loadUrl(url)
//        web_view?.loadUrl(testUrl)
//        web_view?.loadUrl("http://t1451test.1451cn.com/Micro/homepage.html?value=20170913180403@20170622001")

        btn_advance?.setOnClickListener {
            if (web_view?.canGoForward()!!) {
                web_view?.goForward()
            }
        }
        btn_back?.setOnClickListener {
            if (web_view?.canGoBack()!!) {
                web_view?.goBack()
            }
        }
        btn_refresh?.setOnClickListener {
            web_view?.reload()
//            web_view?.loadUrl(testUrl)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (web_view?.canGoBack()!!) {
                web_view?.goBack()
                return false
            } else {
                onBackPressed()
                return true
            }
        } else {
            return super.onKeyDown(keyCode, event)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }


    /**
     * 手机是否安装微信
     */
    fun isWxAvailable(): Boolean {
        var packages = packageManager.getInstalledPackages(0)
        if (packages != null) {
            packages?.forEach {
                var packageName = it?.packageName
                Log.e(TAG, ">>>>>>>packageName:" + packageName)
                if (packageName?.endsWith("com.tencent.mm", true)!!) {
                    Log.e(TAG, ">>>>>>>微信包名:" + packageName)
                    return true
                }
            }
        }

        return false
    }


}