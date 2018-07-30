package com.yuanqi.architecture.im

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.yuanqi.architecture.R
import com.yuanqi.architecture.feature.register.IQBean
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.coroutines.experimental.launch
import org.jivesoftware.smack.*
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.PacketIDFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.packet.IQ
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.packet.Stanza
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.jid.impl.JidCreate
import org.jxmpp.jid.parts.Localpart
import java.io.IOException
import java.io.InputStream
import java.net.InetAddress
import java.security.*
import java.security.cert.CertificateException
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import com.yuanqi.architecture.im.AccountManager

/**
 * Created by mzf on 2018/7/24.
 * Email:liangfeng093@gmail.com
 * Desc:xmpp连接管理者
 */
class XmppManager {


    /**
     * 私有构造单例模式
     */
    private constructor(context: Context) {
        init(context)
    }


    /**
     * 初始化
     */
    fun init(context: Context) {
        launch {
            var builder = XMPPTCPConnectionConfiguration.builder()
            var serviceName = JidCreate.domainBareFrom(XmppConfig.domain)
            builder?.setXmppDomain(serviceName) //设置openfire服务器名称
                    ?.setHostAddress(InetAddress.getByName(XmppConfig.hostAddress))//设置openfire主机IP
                    ?.setPort(XmppConfig.portText)//设置端口
                    ?.setDebuggerEnabled(true)//
                    ?.setCompressionEnabled(true)//
                    ?.setSendPresence(true)
//                    ?.setSecurityMode(ConnectionConfiguration.SecurityMode.required)//
//                    ?.setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)//
                    ?.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)//安全模式
                    ?.setDebuggerEnabled(true)


            var sslContext: SSLContext? = null
            try {
//                sslContext = createSSLContext(context)
            } catch (e: KeyStoreException) {
                Log.e(TAG, ">>>>>>>KeyStoreException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>KeyStoreException:" + it?.toString())
                }
            } catch (e: NoSuchAlgorithmException) {
                Log.e(TAG, ">>>>>>>NoSuchAlgorithmException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>NoSuchAlgorithmException:" + it?.toString())
                }
            } catch (e: KeyManagementException) {
                Log.e(TAG, ">>>>>>>KeyManagementException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>KeyManagementException:" + it?.toString())
                }
            } catch (e: IOException) {
                Log.e(TAG, ">>>>>>>IOException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>IOException:" + it?.toString())
                }
            } catch (e: CertificateException) {
                Log.e(TAG, ">>>>>>>CertificateException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>CertificateException:" + it?.toString())
                }
            } finally {

            }
//            builder?.setCustomSSLContext(sslContext)

            var config = builder?.build()
            connect = XMPPTCPConnection(config)

            //数据包过滤器
            var messageFilter = AndFilter(StanzaTypeFilter(Message::class.java))
            var iqFilter = AndFilter(StanzaTypeFilter(IQ::class.java))
            var presenceFilter = AndFilter(StanzaTypeFilter(Presence::class.java))

            //
//                        var myCollector =
            //注册监听器
            var registerListener = object : StanzaListener {
                override fun processStanza(packet: Stanza?) {
                    //xml转json
                    var xmlString = packet?.toXML()?.toString()
                    var xmlToJson = XmlToJson.Builder(xmlString!!).build()
                    //json转对象
                    var result = Gson().fromJson(xmlToJson?.toString(), IQBean::class.java)
                    if (result?.iq?.type?.equals(IQ.Type.result)!!) {//响应成功
                        Log.e(TAG, "registerListener>>>>>>>注册成功")
                    }

                }
            }

            //数据包监听器
            var stanzaListener = object : StanzaListener {
                override fun processStanza(packet: Stanza?) {
                    //xml转json
                    var xmlString = packet?.toXML()?.toString()
                    var xmlToJson = XmlToJson.Builder(xmlString!!).build()
//                    Log.e(TAG, "connect>>>>>>>数据包监听器,监听到数据包xmlToJson:" + xmlToJson)
                    //json转对象
                    var result = Gson().fromJson(xmlToJson?.toString(), IQBean::class.java)
                    Log.e(TAG, "connect>>>>>>>数据包监听器,监听到数据包xmlString:" + xmlString)
                    Log.e(TAG, "connect>>>>>>>数据包监听器,监听到数据包result:" + result)
                    if (result?.iq?.type?.equals(IQ.Type.result.toString())!! && result?.iq?.id?.equals(registerId)) {//响应成功
                        Log.e(TAG, ">>>>>>>注册成功:")
                    }
                }

            }

            connect?.addAsyncStanzaListener(stanzaListener, messageFilter)
            connect?.addAsyncStanzaListener(stanzaListener, presenceFilter)
            connect?.addAsyncStanzaListener(stanzaListener, iqFilter)
//            connect?.addAsyncStanzaListener(registerListener, registerFilter)


            //
//            connect?.addPacketSendingListener(object :)


            //添加连接监听器
            connect?.addConnectionListener(object : ConnectionListener {
                override fun connected(connection: XMPPConnection?) {
                    Log.e(TAG, ">>>>>>>连接成功:" + connection?.isConnected)
                }

                override fun connectionClosed() {
                    Log.e(TAG, ">>>>>>>连接关闭:")
                }

                override fun connectionClosedOnError(e: java.lang.Exception?) {
                    Log.e(TAG, ">>>>>>>出现异常，连接关闭:" + e?.cause)
                    e?.stackTrace?.forEach {
                        Log.e(TAG, "connectionClosedOnError>>>>>>>Exception:" + it?.toString())
                    }
                }

                //重连成功
                override fun reconnectionSuccessful() {
                    Log.e(TAG, "reconnectionSuccessful>>>>>>>重连成功")

                }

                //已认证
                override fun authenticated(connection: XMPPConnection?, resumed: Boolean) {
                    Log.e(TAG, "authenticated>>>>>>>已认证:")
                }

                //重连失败
                override fun reconnectionFailed(e: java.lang.Exception?) {
                    Log.e(TAG, "reconnectionFailed>>>>>>>重连失败:" + e?.cause)
                    e?.stackTrace?.forEach {
                        Log.e(TAG, "reconnectionFailed>>>>>>>Exception:" + it?.toString())
                    }
                }

                //在指定的秒数内重新连接
                override fun reconnectingIn(seconds: Int) {
                    Log.e(TAG, "reconnectingIn>>>>>>>正在重连中_seconds:" + seconds)
                }
            })


            connect()
        }
    }

    companion object {
        private var connect: XMPPTCPConnection? = null
        var INSTANCE: XmppManager? = null
        val TAG = this.javaClass.name
        /**
         * 注册账号时发送的数据包id
         */
        var registerId = ""


        fun getInstance(context: Context): XmppManager {
            if (INSTANCE == null) {
                synchronized(XmppManager::class) {
                    if (INSTANCE == null) {
                        INSTANCE = XmppManager(context)
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
            /*if (connect?.isConnected!!) {
                Log.e(TAG, "connect>>>>>>>连接成功:" + connect?.isConnected)

            } else {//连接服务器失败
                Log.e(TAG, ">>>>>>>连接失败:" + connect?.isConnected)

            }*/
        }

        /**
         * 注册账号
         *
         * name -- the user's name.
        first -- the user's first name.
        last -- the user's last name.
        email -- the user's email address.
        city -- the user's city.
        state -- the user's state.
        zip -- the user's ZIP code.
        phone -- the user's phone number.
        url -- the user's website.
        date -- the date the registration took place.
        misc -- other miscellaneous information to associate with the account.
        text -- textual information to associate with the account.
        remove -- empty flag to remove account.
         */
        fun register(userName: String, passWord: String) {
            try {
                if (connect?.isConnected!!) {
                    Log.e(TAG, ">>>>>>>连接成功_register:" + connect?.isConnected)
                    var accountManager = AccountManager.getInstance(connect)
                    Log.e(TAG, ">>>>>>>服务器是否支持注册:" + accountManager?.supportsAccountCreation())
                    if (accountManager?.supportsAccountCreation()!!) {
                        var attributes = mutableMapOf<String, String>()
                        attributes?.put("username", userName)
                        attributes?.put("password", passWord)
                        attributes?.put("name", "戎泽峰")
                        attributes?.put("name", "戎泽峰")
                        attributes?.put("email", "123@qq.com")
                        attributes?.put("first", "first")
                        attributes?.put("last", "last")
                        attributes?.put("city", "city")
                        attributes?.put("state", "state")
                        attributes?.put("zip", "zip")
                        attributes?.put("phone", "phone")
                        attributes?.put("url", "url")
                        attributes?.put("date", "date")
                        attributes?.put("misc", "misc")
                        attributes?.put("text", "text")
                        attributes?.put("remove", "remove")
                        accountManager?.sensitiveOperationOverInsecureConnection(true)//允许在不安全连接创建账号
                        var reg = accountManager?.createAccount(Localpart.from(userName), passWord)
                        registerId = reg?.packetID!!
//                        accountManager?.createAccount(Localpart.from(userName), passWord, attributes)
                        //创建字节收集器
                        var collector = connect?.createStanzaCollector(PacketIDFilter(reg?.packetID))
                        Log.e(TAG, ">>>>>>>注册操作数据包packetID:" + reg?.packetID)
                        Log.e(TAG, ">>>>>>>注册操作数据包stanzaId:" + reg?.stanzaId)


                    } else {
                        Log.e(TAG, ">>>>>>>服务器不支持注册账号:")
                    }

                } else {//未连接openfire服务器

                }
            } catch (e: XMPPException.XMPPErrorException) {
                Log.e(TAG, ">>>>>>>XMPPErrorException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>XMPPErrorException:" + it?.toString())
                }
            } catch (e: SmackException.NoResponseException) {
                Log.e(TAG, ">>>>>>>NoResponseException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>NoResponseException:" + it?.toString())
                }
            } catch (e: SmackException.NotConnectedException) {
                Log.e(TAG, ">>>>>>>NotConnectedException:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>NotConnectedException:" + it?.toString())
                }
            } catch (e: Exception) {
                Log.e(TAG, ">>>>>>>Exception:" + e?.cause)
                e?.stackTrace?.forEach {
                    Log.e(TAG, ">>>>>>>Exception:" + it?.toString())
                }
            } finally {

            }
        }

        fun login(userName: String, passWord: String) {
            if (connect?.isConnected!!) {
                connect?.login(userName, passWord)
            }
        }
    }


    @Throws(KeyStoreException::class, NoSuchAlgorithmException::class, KeyManagementException::class, IOException::class, CertificateException::class)
    fun createSSLContext(context: Context): SSLContext {
        var trustStore: KeyStore
        var inputStream: InputStream? = null
        trustStore = KeyStore.getInstance("BKS")

        inputStream = context?.resources?.openRawResource(R.raw.keystore)

        trustStore?.load(inputStream, "123456".toCharArray())
        var trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        trustManagerFactory?.init(trustStore)
        var sslContext = SSLContext.getInstance("TLS")
        sslContext?.init(null, trustManagerFactory?.trustManagers, SecureRandom())
        return sslContext
    }
}