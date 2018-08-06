package com.yuanqi.yiwanjia.network

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:管理所有的接口请求
 */
interface RetrofitService {

    companion object {
        val BaseURL: String = "http://xxx.xx.xxx.xx:xxxx/XXXX/"//访问接口的ip和端口
        val testBaseURL: String = "http://v.juhe.cn/"//聚合数据测试接口
        val videoUrl = BaseURL + "XXX/"//音频文件的加载路径
        val imgUrl = BaseURL + "img/"//图片的加载路径

        val hospital = "http://t1451test.1451cn.com/smejhomepage/homepage.html?wqXCm8OAwqHCsnRtZmxiY3ZlbmxraGZ2a2FvaHhXwrLCl8KlwqfCu8KfwrTClnJswq3CgmlpZWtianJoZmRtb2h9W8KfwpnCnsKrbuWOpOa1oOeFqFrCuMKVwq5vZntxd25qbGxnZHNawqvCmMKqdWh7ZQ=="

        //主页一级功能链接
        //当日挂号
        val one_level_today_register_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAEC8E541D529A6C9590CD37BB9CDA12B00FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"
        //预约挂号
        val one_level_reservation_register_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAA2973905A6AC4DB8C7C6E1DC7F685EF280FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"
        //门诊缴费
        val one_outpatient_payment_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAEEA673BE9B612C26CD33D5A179FD9E650FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"
        //报告单
        val one_level_report_card_url = ""


        //主页二级功能链接
        //住院预缴
        val secondary_hospitalization_advance_payment_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAA21BF85E74653725EDE01E2106855A3220FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //门诊清单
        val secondary_outpatient_list_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAB4DE5F493ACEF0A11157B30D8627EAD30FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //一日清单
        val secondary_one_day_list_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAA7DC07FF38DD73600DB38B5A3C40BCB0D0FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //检查报告单
        val secondary_inspect_report_card_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAA5EEC39AEA30E3E02AC11662434975640FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //检验报告单
        val secondary_check_report_card_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAABD6785BA0158269610CE90FF9A23E6EF0FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //满意度评价
        val secondary_satisfaction_evaluation_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAA29343A3C62A88210EF7085B4106CBAF0FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"

        //排队候诊
        val secondary_queue_visit_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D46860DE04A4792FC4014D959F269A2D79AC9BA65702DF7D0AC8713EB0B64CE74B7C4D76C62F9BAB1C12A738339326C56E7F0C04BBD067EE7AFF08964E96463078923B005525FF6007E4CEBC90725A10FDEDEE8CF15E4A40BC0069CF5A8199068386C46100B909B30C68AFFB2DB2129AC24B5C74E01A5FCD072E28B1D6920C2BC"
        //物价查询
        val secondary_price_demand_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8DBB030D3C1BF65FEFFEE30F3B2BEC698291A9C50C3516DEA21F75514902DE4F69B7C4D76C62F9BAB1C12A738339326C56E7F0C04BBD067EE7AFF08964E96463078923B005525FF6007E4CEBC90725A10FDEDEE8CF15E4A40BC0069CF5A8199068386C46100B909B30C68AFFB2DB2129AC24B5C74E01A5FCD072E28B1D6920C2BC"

        //医院介绍
        val secondary_hospital_introduction_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D15523700B83D70DF3C8A91A9BB4FC18D5B2467D808B9E40B80225DE14C3F2B77B7C4D76C62F9BAB1C12A738339326C56E7F0C04BBD067EE7AFF08964E96463078923B005525FF6007E4CEBC90725A10FDEDEE8CF15E4A40BC0069CF5A8199068386C46100B909B30C68AFFB2DB2129AC24B5C74E01A5FCD072E28B1D6920C2BC"
        //智能导诊
        val secondary_intelligent_navigation_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8DBAEA199711295ED626A1D83C4F2D5198B667916447F907C4CA87F8FACFEB32C0B7C4D76C62F9BAB1C12A738339326C56E7F0C04BBD067EE7AFF08964E96463078923B005525FF6007E4CEBC90725A10FDEDEE8CF15E4A40BC0069CF5A8199068386C46100B909B30C68AFFB2DB2129AC24B5C74E01A5FCD072E28B1D6920C2BC"
        //出院查询
        val secondary_leave_hospital_demand_url = "http://orgineyun.1451cn.com:9190/JKHB/moduleLogin.html?7C50CC3BE22DB656258BF224FC5608D848305CABE59218599A0FECD3B9D7E99243B97C1D99B78BCFAB209DA92F627D8D20D1027EB70C7F614EB7E997126CDFAAEDD14F923497C5D9BA75FDD7A9ACFA270FB610E85BF70D2F0C99E39A79E1E5B180424BC5686BABB38DFD7ACFFF4480EB09955B970311953E4CE4B3E7684B6C5D8F16F9C825AB2B4822D634F9D5C528D157091FE9C248EA0ECA494BD5F10164FCAF8DF0075D6BADFF30CBDD5B4BCF28BF"
    }
}