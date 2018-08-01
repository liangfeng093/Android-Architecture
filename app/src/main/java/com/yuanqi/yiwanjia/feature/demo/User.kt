package com.yuanqi.yiwanjia.feature.demo

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.yuanqi.yiwanjia.app.AppDatabase

/**
 * Created by mzf on 2018/7/16.
 * Email:liangfeng093@gmail.com
 * Desc:创建User表，主键为
 */
@Table(database = AppDatabase::class)
class User : BaseModel() {
    @PrimaryKey(autoincrement = true) // 主键自增长
    var id: Long = 0
    @Column
    var userName: String = ""
    @Column
    var passWord: String = ""

    override fun toString(): String {
        return "User(id=$id, userName='$userName', passWord='$passWord')"
    }


}