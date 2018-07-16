package com.yuanqi.architecture

import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.yuanqi.architecture.feature.demo.User
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val TAG = this.javaClass.name

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_dbflowInsert() {
        var user = User()
        user?.userName = "123"
        user?.passWord = "321"
        user?.insert()
//        var isSave = user?.save()
//        Log.e(TAG, ">>>>>>>isSave:" + isSave)

        var list = SQLite.select()
                ?.from(User::class.java)
                ?.queryList()

        Log.e(TAG, ">>>>>>>list?.size:" + list?.size)
    }
}
