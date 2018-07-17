package com.yuanqi.architecture.feature.demo

import android.view.View
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class DemoFragment : BaseFragment<DemoContract.Presenter>(), DemoContract.View {


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

    private var presenter: DemoContract.Presenter? = null

    override fun setPresenter(presenter: DemoContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        if (presenter == null) {
            setPresenter(DemoPresenter(this))
        }
    }

    override fun emptyContent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadFail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun content() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}