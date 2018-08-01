package com.yuanqi.yiwanjia.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import com.yuanqi.yiwanjia.R
import com.yuanqi.yiwanjia.`interface`.Actions
import com.yuanqi.yiwanjia.`interface`.Actions.Companion.LOCATION_REQUEST_CODE
import com.yuanqi.yiwanjia.feature.home.HomeFragment
import com.yuanqi.yiwanjia.feature.location.LocationService
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.search_bar.*

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_container?.setOnClickListener {
            et_search?.focusable = EditText.FOCUSABLE

        }
        var transaction = supportFragmentManager?.beginTransaction()
//        transaction?.replace(R.id.fl_fragment_container, LoginFragment())
        transaction?.replace(R.id.fl_fragment_container, HomeFragment())
        transaction?.commit()


        if (ContextCompat.checkSelfPermission(this!!, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this!!, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION), Actions.LOCATION_REQUEST_CODE)//自定义的code
        } else {
            var intent = Intent(this, LocationService::class.java)
//            startService(intent)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            Log.e(TAG, "onRequestPermissionsResult")
            var intent = Intent(this@MainActivity, LocationService::class.java)
//            startService(intent)
        }
    }


}
