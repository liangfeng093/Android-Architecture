package com.yuanqi.yiwanjia.feature.home

import android.content.Context
import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:医院列表适配器
 */
class HospitalListAdapter : BaseQuickAdapter<Hospital, BaseViewHolder> {

    var context: Context? = null


    constructor(context: Context?, layoutResId: Int, data: MutableList<Hospital>?) : super(layoutResId, data) {
        this.context = context
        this.setOnItemClickListener { adapter, view, position ->
            context?.startActivity(Intent(context, WebActivity::class.java))
        }
    }

    override fun convert(helper: BaseViewHolder?, item: Hospital?) {

    }

}