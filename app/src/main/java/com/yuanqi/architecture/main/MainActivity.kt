package com.yuanqi.architecture.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.yuanqi.architecture.R
import com.yuanqi.architecture.app.App
import com.yuanqi.architecture.feature.login.LoginFragment
import com.yuanqi.architecture.utils.EncUtil
import com.yuanqi.architecture.utils.PnbClientHelper
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var transaction = supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fl_fragment_container, LoginFragment())
        transaction?.commit()

//        launch {
//            testEnc()
//        }
    }

    override fun onResume() {
        super.onResume()

    }

    fun testEnc() {
        var json = "{\n" +
                "    \"body\": [\n" +
                "        {\n" +
                "            \"queryType\": \"3\", \n" +
                "            \"queryValue\": \"ac\", \n" +
                "            \"queryItemType\": \"1\", \n" +
                "            \"patientId\": \"\", \n" +
                "            \"patientName\": \"\", \n" +
                "            \"idCardNo\": \"\"\n" +
                "        }\n" +
                "    ], \n" +
                "    \"authorization\": {\n" +
                "        \"businessCode\": \"C20301\", \n" +
                "        \"medicalOrgCode\": \"1000000\", \n" +
                "        \"serialNumber\": \"123456789\", \n" +
                "        \"version\": \"100004\"\n" +
                "    }\n" +
                "}"
        //
        var sourceCode = "100003"
        //
        var comId = "0002000000"
        //
        Log.e(TAG, ">>>>>>>App.context:" + App.context)
//        var service = PnbClientHelper.getSQLCommandService(App.context, "pnbclient.xml")
        var service = PnbClientHelper.getSQLCommandService(App.context, "authAndLogPnb.properties")
//        var service = SQLCommandService()
        Log.e(TAG, ">>>>>>>service:" + service)
        //公钥加密
        var encryptionJson = EncUtil.GetEncryptMessagePublic(service, json, sourceCode, comId)
//        Log.e(TAG, ">>>>>>>encryptionJson:" + encryptionJson)
    }

}
