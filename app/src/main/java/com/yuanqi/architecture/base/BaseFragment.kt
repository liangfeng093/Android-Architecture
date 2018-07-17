package com.yuanqi.architecture.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuanqi.architecture.feature.demo.DemoPresenter

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:Fragment的基类，抽取一些重复操作
 */
abstract class BaseFragment<T> : Fragment(), IBaseFragmentView<T> {
    var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(getLayout(), container, false)
        initView(view)
        initData()
        initListener()
        return view
    }


    /*override fun onResume() {
        super.onResume()
        if (mPresenter == null) {
            setPresenter(T())
        }
    }*/

}