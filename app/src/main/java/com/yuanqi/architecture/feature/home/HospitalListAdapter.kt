package com.yuanqi.architecture.feature.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by mzf on 2018/7/26.
 * Email:liangfeng093@gmail.com
 * Desc:医院列表适配器
 */
class HospitalListAdapter : BaseQuickAdapter<Hospital, BaseViewHolder> {

    constructor(layoutResId: Int, data: MutableList<Hospital>?) : super(layoutResId, data) {
        this.setOnItemClickListener { adapter, view, position ->

        }
    }

    override fun convert(helper: BaseViewHolder?, item: Hospital?) {

    }

}