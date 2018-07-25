package com.yuanqi.architecture.im

import android.util.Log
import kotlinx.coroutines.experimental.launch
import org.jivesoftware.smack.ConnectionConfiguration
import org.jivesoftware.smack.SmackException
import org.jivesoftware.smack.StanzaListener
import org.jivesoftware.smack.XMPPException
import org.jivesoftware.smack.packet.Stanza
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jivesoftware.smackx.iqregister.AccountManager
import org.jxmpp.jid.impl.JidCreate
import org.jxmpp.jid.parts.Localpart
import java.net.InetAddress

/**
 * Created by mzf on 2018/7/24.
 * Email:liangfeng093@gmail.com
 * Desc:xmpp连接管理者
 */
class XmppManager {


    /**
     * 私有构造单例模式
     */
    private constructor() {
        init()
    }


    /**
     * 初始化
     */
    fun init() {
        launch {
            var builder = XMPPTCPConnectionConfiguration.builder()
            var serviceName = JidCreate.domainBareFrom(XmppConfig.domain)
            builder?.setXmppDomain(serviceName) //设置openfire服务器名称
                    ?.setHostAddress(InetAddress.getByName(XmppConfig.hostAddress))//设置openfire主机IP
                    ?.setPort(XmppConfig.port)//设置端口
                    ?.setDebuggerEnabled(true)//
                    ?.setCompressionEnabled(true)//
                    ?.setSendPresence(true)
                    ?.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)//安全模式
                    ?.setDebuggerEnabled(true)

            var config = builder?.build()
            connect = XMPPTCPConnection(config)
            connect()
        }
    }

    companion object {
        private var connect: XMPPTCPConnection? = null
        var INSTANCE: XmppManager? = null
        val TAG = this.javaClass.name

        fun getInstance(): XmppManager {
            if (INSTANCE == null) {
                synchronized(XmppManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = XmppManager()
                    }
                }
            }
            return INSTANCE!!
        }

        /**
         * 连接openfire服务器
         */
        fun connect() {
            connect?.let {
                it?.connect()
            }
            if (connect?.isConnected!!) {
                Log.e(TAG, ">>>>>>>连接成功:" + connect?.isConnected)

            } else {//连接服务器失败
                Log.e(TAG, ">>>>>>>连接失败:" + connect?.isConnected)

            }
        }

        /**
         * 注册账号
         */
        fun register(userName: String, passWord: String) {
            try {
                if (connect?.isConnected!!) {
                    Log.e(TAG, ">>>>>>>连接成功_register:" + connect?.isConnected)
                    var accountManager = AccountManager.getInstance(connect)
                    Log.e(TAG, ">>>>>>>服务器是否支持注册:" + accountManager?.supportsAccountCreation())
                    if (accountManager?.supportsAccountCreation()!!) {
                        accountManager?.createAccount(Localpart.from(userName), passWord)
                        //数据包过滤器
//                        var filter=
                        //数据包监听器
                        var myListener = object : StanzaListener {
                            override fun processStanza(packet: Stanza?) {

                            }

                        }

                        connect?.addAsyncStanzaListener(myListener,)
                    } else {
                        Log.e(TAG, ">>>>>>>服务器不支持注册账号:")
                    }

                } else {//未连接openfire服务器

                }
            } catch (e: XMPPException.XMPPErrorException) {

            } catch (e: SmackException.NoResponseException) {

            } catch (e: SmackException.NotConnectedException) {

            } catch (e: Exception) {

            } finally {

            }
        }

        fun login(userName: String, passWord: String) {
            if (connect?.isConnected!!) {
                connect?.login(userName, passWord)
            }
        }
    }
}