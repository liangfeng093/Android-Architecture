package com.yuanqi.architecture.feature.register

/**
 * Created by mzf on 2018/7/30.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
data class IQBean(var iq: Iq) {


    data class Iq(var from: String,
                  var `type`: String,
                  var id: String,
                  var to: String){
        override fun toString(): String {
            return "Iq(from='$from', `type`='$`type`', id='$id', to='$to')"
        }
    }

    override fun toString(): String {
        return "IQBean(iq=$iq)"
    }
}