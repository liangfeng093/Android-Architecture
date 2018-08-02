package com.yuanqi.yiwanjia.feature.scancode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.yuanqi.yiwanjia.R
import kotlinx.android.synthetic.main.activity_scan_code.*

/**
 * Created by mzf on 2018/8/1.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class ScanCodeActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_code)
        //设置扫描二维码的代理
        zxingview?.setDelegate(object : QRCodeView.Delegate {
            override fun onScanQRCodeSuccess(result: String?) {
                Log.e(TAG, ">>>>>>>result:" + result)
                Toast.makeText(this@ScanCodeActivity, result, Toast.LENGTH_LONG).show()
            }

            override fun onScanQRCodeOpenCameraError() {
//                Log.e(TAG, ">>>>>>>result:" + result)

            }
        })

        btn_back?.setOnClickListener {
            finish()
        }
        btn_close_light?.setOnClickListener {
            zxingview?.closeFlashlight()
        }
        btn_open_light?.setOnClickListener {
            zxingview?.openFlashlight()
        }
    }

    override fun onStart() {
        super.onStart()
        zxingview?.startSpotAndShowRect()

    }

    override fun onStop() {
        zxingview?.stopCamera()
        super.onStop()

    }

    override fun onDestroy() {
        zxingview?.onDestroy()
        super.onDestroy()
    }
}