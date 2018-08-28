package com.yuanqi.architecture.feature.demo.room

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:数据库操作(整个APP中的数据库操作都写在这里)
 */
interface LocalDao {

    /**
     * 通过id查询Student表
     */
    @Query("select * from Student where id = :sId")
    fun getStudentById(sId: String): Flowable<Student>

    /**
     * 将student插入数据库
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    /**
     * 删除Student表所有内容
     */
    @Query("DELETE FROM Users")
    fun deleteAllStudents()

}