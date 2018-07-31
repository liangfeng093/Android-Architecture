package com.yuanqi.architecture.im

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.yuanqi.architecture.R
import com.yuanqi.architecture.feature.register.IQBean
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import kotlinx.coroutines.experimental.launch
import org.jivesoftware.smack.*
import org.jivesoftware.smack.chat2.Chat
import org.jivesoftware.smack.chat2.ChatManager
import org.jivesoftware.smack.chat2.IncomingChatMessageListener
import org.jivesoftware.smack.chat2.OutgoingChatMessageListener
import org.jivesoftware.smack.filter.AndFilter
import org.jivesoftware.smack.filter.StanzaTypeFilter
import org.jivesoftware.smack.packet.IQ
import org.jivesoftware.smack.packet.Message
import org.jivesoftware.smack.packet.Presence
import org.jivesoftware.smack.packet.Stanza
import org.jivesoftware.smack.roster.Roster
import org.jivesoftware.smack.roster.RosterEntry
import org.jivesoftware.smack.roster.RosterListener
import org.jivesoftware.smack.tcp.XMPPTCPConnection
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
import org.jxmpp.jid.EntityBareJid
import org.jxmpp.jid.Jid
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
                    ?.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)//非安全模式
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

            //数据包监听器
            var stanzaListener = object : StanzaListener {
                override fun processStanza(packet: Stanza?) {
                    //xml转json
                    var xmlString = packet?.toXML()?.toString()
                    var xmlToJson = XmlToJson.Builder(xmlString!!).build()
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

            //添加名册(好友列表)监听
            connect?.let { con ->
                Roster.getInstanceFor(con)?.addRosterListener(object : RosterListener {
                    //删除好友回调
                    override fun entriesDeleted(addresses: MutableCollection<Jid>?) {
                        Log.e(TAG, "entriesUpdated>>>>>>>删除好友:" + addresses?.first())
                    }

                    override fun presenceChanged(presence: Presence?) {
                        Log.e(TAG, "presenceChanged>>>>>>>status:" + presence?.status)
                        Log.e(TAG, "presenceChanged>>>>>>>priority:" + presence?.priority)
                    }

                    //更新好友列表回调
                    override fun entriesUpdated(addresses: MutableCollection<Jid>?) {
                        Log.e(TAG, "entriesUpdated>>>>>>>好友列表更新:" + addresses?.first())

                    }

                    //添加好友
                    override fun entriesAdded(addresses: MutableCollection<Jid>?) {
                        Log.e(TAG, "entriesAdded>>>>>>>添加好友:" + addresses?.first())

                    }
                })
            }

            //添加会话监听器
            connect?.let { con ->
                //接收消息监听
                ChatManager.getInstanceFor(con)?.addIncomingListener(object : IncomingChatMessageListener {
                    override fun newIncomingMessage(from: EntityBareJid?, message: Message?, chat: Chat?) {
                        Log.e(TAG, "newIncomingMessage>>>>>>>接收消息，发送人:" + from)
                        Log.e(TAG, "newIncomingMessage>>>>>>>接收消息，消息内容_body:" + message?.body)
                        Log.e(TAG, "newIncomingMessage>>>>>>>接收消息，消息内容_bodyLanguages:" + message?.bodyLanguages)
                        Log.e(TAG, "newIncomingMessage>>>>>>>接收消息，消息内容_subject:" + message?.subject)

                    }
                })

                //发送消息监听
                ChatManager.getInstanceFor(con)?.addOutgoingListener(object : OutgoingChatMessageListener {
                    override fun newOutgoingMessage(to: EntityBareJid?, message: Message?, chat: Chat?) {
                        Log.e(TAG, "newOutgoingMessage>>>>>>>发送消息，接收人:" + to)
                        Log.e(TAG, "newOutgoingMessage>>>>>>>发送消息，发送内容_body:" + message?.body)
                        Log.e(TAG, "newOutgoingMessage>>>>>>>发送消息，发送内容_language:" + message?.language)
                        Log.e(TAG, "newOutgoingMessage>>>>>>>发送消息，发送内容_subject:" + message?.subject)
                    }
                })
                //
            }

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
        }

        /**
         * 注册账号
         *
         * name -- the user's name.
         * first -- the user's first name.
         * last -- the user's last name.
         * email -- the user's email address.
         * city -- the user's city.
         * state -- the user's state.
         * zip -- the user's ZIP code.
         * phone -- the user's phone number.
         * url -- the user's website.
         * date -- the date the registration took place.
         * misc -- other miscellaneous information to associate with the account.
         * text -- textual information to associate with the account.
         * remove -- empty flag to remove account.
         */
        fun register(userName: String, passWord: String) {
            try {
                if (connect?.isConnected!!) {
                    Log.e(TAG, ">>>>>>>连接成功_register:" + connect?.isConnected)
                    var accountManager = AccountManager.getInstance(connect)
                    Log.e(TAG, ">>>>>>>服务器是否支持注册:" + accountManager?.supportsAccountCreation())
                    if (accountManager?.supportsAccountCreation()!!) {
                        var attributes = mutableMapOf<String, String>()
                        attributes?.put("name", "小明")
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

        /**
         * 登录
         */
        fun login(userName: String, passWord: String) {
            if (connect?.isConnected!!) {
                connect?.login(userName, passWord)
            }
        }


        /**
         * Author: mzf
         * Date: 2018/7/31
         * Description:添加好友
         * param userName :用户名
         * param nickname :昵称
         */
        fun addFriend(userName: String, nickname: String): Boolean {
            connect?.let {
                try {
                    Roster.getInstanceFor(it)?.createEntry(JidCreate.bareFrom(userName), nickname, null)
                    return true
                } catch (e: Exception) {

                }
            }

            return false
        }


        /**
         * Author: mzf
         * Date: 2018/7/31
         * Description:删除好友
         * param entry :好友列表中的条目
         *
         */
        fun deleteFriend(entry: RosterEntry) {
            connect?.let {
                Roster.getInstanceFor(it)?.removeEntry(entry)
            }
        }

        /**
         * Author: mzf
         * Date: 2018/7/31
         * Description:获取好友列表
         */
        fun getFriends(): MutableList<RosterEntry>? {
//        fun getFriends(): MutableSet<RosterEntry>? {
            connect?.let { con ->
                Roster.getInstanceFor(con)?.entries?.let { entries ->
                    var list = mutableListOf<RosterEntry>()
                    entries?.forEach {
                        list?.add(it)
                    }
                    return list
                }
            }
            return null
        }

        /**
         * Author: mzf
         * Date: 2018/7/31
         * Description:发送消息
         * param jid :目标对象的用户名
         *
         */
        fun sendMessage(jid: EntityBareJid): Chat? {
//        fun sendMessage(jid: String): Chat? {
            Log.e(TAG, ">>>>>>>jid是不是isEmpty:" + jid?.isEmpty())
            connect?.let { con ->
                ChatManager.getInstanceFor(con)?.chatWith(jid)?.let { chat ->
                    //                ChatManager.getInstanceFor(con)?.chatWith(JidCreate.entityBareFrom(jid))?.let { chat ->
                    chat?.send("hello word")
                    return chat
                }
            }
            return null
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