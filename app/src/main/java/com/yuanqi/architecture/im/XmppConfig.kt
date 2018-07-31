package com.yuanqi.architecture.im

/**
 * Created by mzf on 2018/7/24.
 * Email:liangfeng093@gmail.com
 * Desc:xmpp相关的属性配置
 */
class XmppConfig {
    companion object {

        /**
         * openfire服务器的服务器名称(主机名)
         * domain是配置openfire服务器时填写的域
         */
        val domain = "openfirewebsever"
        /**
         * openfire服务器的主机IP
         * 测试环境下为本机(电脑)的IP
         */
        val hostAddress = "192.168.0.135"
        /**
         * openfire端口号
         * 纯文本连接端口号
         */
        val portText = 5222
        /**
         * openfire端口号
         * 加密连接端口号
         */
        val portEncryption = 5223

        /**
         *
         */

    }
}
