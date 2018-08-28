package com.yuanqi.architecture.feature.demo.room

import android.arch.lifecycle.ViewModel
import com.yuanqi.architecture.data.IDataSource
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by mzf on 2018/8/28.
 * Email:liangfeng093@gmail.com
 * Desc:main主界面的数据模型(持有本地和远程两个数据源)
 */
class MainViewModel(private val dataSource: LocalDao, private val remoteDataSource: IDataSource) : ViewModel() {

    fun getStudentName(id: String): Flowable<String> {
        return dataSource?.getStudentById("1")
                .map { student -> student?.name }//用map操作符对数据进行处理
    }

    fun updateStudentName(name: String): Completable {
        return Completable.fromAction {
            val student = Student("1", name, "11")
            dataSource?.insertStudent(student)
        }
    }
}