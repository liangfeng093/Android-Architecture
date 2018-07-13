package com.yuanqi.architecture.feature.demo

import android.view.View
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment
import com.yuanqi.architecture.base.IContract

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class DemoFragment : BaseFragment(), IContract.View {
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView(view: View) {
    }

    override fun initData() {
        presenter?.start()
    }

    override fun initListener() {
    }

    private var presenter: IContract.Presenter? = null

    override fun setPresenter(presenter: IContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        if (presenter == null) {
            setPresenter(DemoPresenter(this))
        }
    }

    override fun emptyContent() {
    }

    override fun loading() {
    }

    override fun loadSuccess() {
    }

    override fun loadFail() {
    }

    override fun content() {
    }
}