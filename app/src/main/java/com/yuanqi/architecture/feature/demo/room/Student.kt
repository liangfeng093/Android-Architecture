package com.yuanqi.architecture.feature.demo.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:数据库中对应student表
 */
@Entity(tableName = "students")
data class Student(@PrimaryKey//主键标识
                   @ColumnInfo(name = "id")//数据库对应字段
                   val id: String = UUID.randomUUID().toString(),
                   @ColumnInfo(name = "name")//数据库对应字段
                   val name: String,
                   @ColumnInfo(name = "age")//数据库对应字段
                   val age: String) {

}