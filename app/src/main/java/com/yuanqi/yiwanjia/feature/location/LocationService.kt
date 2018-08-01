package com.yuanqi.yiwanjia.feature.location

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * Created by mzf on 2018/8/1.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LocationService : Service() {

    val TAG = "LocationService"
    override fun onCreate() {
        super.onCreate()
        startLocation()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

//    var startLatlng: LatLng? = null
//    var endLatlng: LatLng? = null
    var isFirst = true
    var tagDistance: Int? = null

    var isUpdate = true
    fun startLocation() {
        /**     初始化定位       **/
        launch {
            while (true) {
                isUpdate = true
                delay(5 * 1000)
            }
        }
        //声明AMapLocationClient类对象
        var mLocationClient: AMapLocationClient? = null
        //声明定位回调监听器
        var mLocationListener = object : AMapLocationListener {
            override fun onLocationChanged(p0: AMapLocation?) {
                Log.e(TAG, "address:" + p0?.address)
                Log.e(TAG, "latitude:" + p0?.latitude)
                Log.e(TAG, "longitude:" + p0?.longitude)

            }
        }
        //初始化定位
        mLocationClient = AMapLocationClient(getApplicationContext())
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener)

        //声明AMapLocationClientOption对象
        var mLocationOption: AMapLocationClientOption? = null
        //初始化AMapLocationClientOption对象
        mLocationOption = AMapLocationClientOption()
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        /* mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
         if (null != mLocationClient) {
             mLocationClient.setLocationOption(mLocationOption);
             //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
             mLocationClient.stopLocation();
             mLocationClient.startLocation();
         }*/
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(300000)
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5 * 1000)
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption)
        //启动定位
        mLocationClient.startLocation()


    }
}