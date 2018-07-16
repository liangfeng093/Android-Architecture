package com.yuanqi.architecture

import android.os.AsyncTask.execute
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.yuanqi.architecture.feature.demo.User
import com.yuanqi.architecture.feature.demo.User_Table
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by mzf on 2018/7/16.
 * Email:liangfeng093@gmail.com
 * Desc:数据库DBFlow单元测试(api测试)
 */
@RunWith(AndroidJUnit4::class)
class DdTest {
    val TAG = this.javaClass.name
    @Test
    fun test_dbflow_insert() {

        for (i in 1..10) {
            var user = User()
            user?.userName = "userName_" + i
            user?.passWord = "passWord_" + i
            user?.insert()
        }


    }

    @Test
    fun test_dbflow_selectAll() {
        var list = SQLite.select()
                ?.from(User::class.java)
                ?.queryList()
        Log.e(TAG, ">>>>>>>size:" + list?.size)
        list?.forEach {
            Log.e(TAG, ">>>>>>>element:" + it)
        }
    }

    @Test
    fun test_dbflow_select_single() {
        var user = SQLite.select()
                ?.from(User::class.java)
                ?.where(User_Table.userName.eq("userName_4"))
                ?.querySingle()
        Log.e(TAG, ">>>>>>>user:" + user)
    }

    @Test
    fun test_dbflow_delete() {
        SQLite.delete()
                ?.from(User::class.java)
                ?.where(User_Table.userName.eq("userName_3"))
                ?.execute()
    }


    @Test
    fun test_dbflow_update() {
        SQLite.update(User::class.java)
                ?.set(User_Table.passWord.eq("passWord_444444"))
                ?.where(User_Table.userName?.eq("userName_4"))
                ?.execute()

    }


}