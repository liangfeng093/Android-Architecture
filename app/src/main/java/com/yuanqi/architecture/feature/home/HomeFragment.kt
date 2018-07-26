package com.yuanqi.architecture.feature.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import cn.levey.bannerlib.RxBanner
import com.yuanqi.architecture.R
import com.yuanqi.architecture.base.BaseFragment

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class HomeFragment : BaseFragment<HomeContract.Presenter>(), HomeContract.View {

    //h5链接(测试)
    val testUrl = "http://t1451test.1451cn.com/smejhomepage/homepage.html?wqXCm8OAwqHCsnRtZmxiY3ZlbmxraGZ2a2FvaHhXwrLCl8KlwqfCu8KfwrTClnJswq3CgmlpZWtianJoZmRtb2h9W8KfwpnCnsKrbuWOpOa1oOeFqFrCuMKVwq5vZntxd25qbGxnZHNawqvCmMKqdWh7ZQ=="
    val picUrl1 = "http://t1451test.1451cn.com/smejhomepage/style1/images/k1_index/1.jpg"
    val picUrl2 = "http://t1451test.1451cn.com/smejhomepage/style1/images/k1_index/2.jpg"
    val picUrl3 = "http://t1451test.1451cn.com/smejhomepage/style1/images/k1_index/3.jpg"
    val picUrl4 = "http://t1451test.1451cn.com/smejhomepage/style1/images/k1_index/4.jpg"

    val testPics = arrayListOf<String>(picUrl1, picUrl2, picUrl3, picUrl4)

    //一级功能
    var ll_reservation_register: LinearLayout? = null
    var ll_today_register: LinearLayout? = null
    var ll_outpatient_payment: LinearLayout? = null
    var ll_report_card: LinearLayout? = null


    //二级功能
    var ll_intelligent_navigation: LinearLayout? = null
    var ll_hospitalization_payment: LinearLayout? = null
    var ll_inspect_report_card: LinearLayout? = null
    var ll_hospital_introduction: LinearLayout? = null
    var ll_one_day_list: LinearLayout? = null
    var ll_leave_hospital_list: LinearLayout? = null
    var ll_price_demand: LinearLayout? = null
    var ll_more: LinearLayout? = null
    var rv_hospital: RecyclerView? = null

    var rx_banner: RxBanner? = null

    override fun setPresenter(presenter: HomeContract.Presenter) {
        mPresenter = presenter
    }

    override fun getLayout(): Int {
        return R.layout.fragment_home
    }

    override fun onResume() {
        super.onResume()
//        zBanner?.star(1000, 2000)
        if (mPresenter == null) {
            setPresenter(HomePresenter(this))
        }
    }

    override fun initView(view: View) {
        ll_reservation_register = view?.findViewById(R.id.ll_reservation_register)
        ll_today_register = view?.findViewById(R.id.ll_today_register)
        ll_outpatient_payment = view?.findViewById(R.id.ll_outpatient_payment)
        ll_report_card = view?.findViewById(R.id.ll_report_card)

        ll_intelligent_navigation = view?.findViewById(R.id.ll_intelligent_navigation)
        ll_hospitalization_payment = view?.findViewById(R.id.ll_hospitalization_payment)
        ll_inspect_report_card = view?.findViewById(R.id.ll_inspect_report_card)
        ll_hospital_introduction = view?.findViewById(R.id.ll_hospital_introduction)
        ll_one_day_list = view?.findViewById(R.id.ll_one_day_list)
        ll_leave_hospital_list = view?.findViewById(R.id.ll_leave_hospital_list)
        ll_price_demand = view?.findViewById(R.id.ll_price_demand)
        ll_more = view?.findViewById(R.id.ll_more)

        rx_banner = view?.findViewById(R.id.rx_banner)
        rx_banner?.setLoader(GlideLoader())
//                ?.setConfig()
                ?.setDatas(testPics)
                ?.start()
        rv_hospital = view?.findViewById(R.id.rv_hospital)

        var hospitals = mutableListOf<Hospital>()

        var i = 0
        while (i < 10) {
            var hospital = Hospital()
            hospital?.htmlLink = testUrl
            hospitals?.add(hospital)
            i++
        }

        rv_hospital?.layoutManager = LinearLayoutManager(this.activity)
        rv_hospital?.adapter = HospitalListAdapter(this.activity, R.layout.item_home_hospital, hospitals)
    }

    override fun initData() {
        mPresenter?.start()
    }

    override fun initListener() {
        ll_reservation_register?.setOnClickListener { }
        ll_today_register?.setOnClickListener { }
        ll_outpatient_payment?.setOnClickListener { }
        ll_report_card?.setOnClickListener { }
        ll_intelligent_navigation?.setOnClickListener { }
        ll_hospitalization_payment?.setOnClickListener { }
        ll_inspect_report_card?.setOnClickListener { }
        ll_hospital_introduction?.setOnClickListener { }
        ll_one_day_list?.setOnClickListener { }
        ll_leave_hospital_list?.setOnClickListener { }
        ll_price_demand?.setOnClickListener { }
        ll_more?.setOnClickListener { }
    }
}