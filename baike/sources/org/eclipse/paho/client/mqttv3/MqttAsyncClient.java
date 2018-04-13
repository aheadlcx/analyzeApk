package org.eclipse.paho.client.mqttv3;

import android.support.v4.internal.view.SupportMenu;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import kotlin.jvm.internal.CharCompanionObject;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttAsyncClient implements IMqttAsyncClient {
    static Class b;
    private static final String c;
    private static final Logger d = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, c);
    private static int m = 1000;
    protected ClientComms a;
    private String e;
    private String f;
    private Hashtable g;
    private MqttClientPersistence h;
    private MqttCallback i;
    private MqttConnectOptions j;
    private Object k;
    private Timer l;
    private boolean n;

    private class a extends TimerTask {
        final MqttAsyncClient a;

        private a(MqttAsyncClient mqttAsyncClient) {
            this.a = mqttAsyncClient;
        }

        a(MqttAsyncClient mqttAsyncClient, a aVar) {
            this(mqttAsyncClient);
        }

        public void run() {
            MqttAsyncClient.a().fine(MqttAsyncClient.b(), "ReconnectTask.run", "506");
            MqttAsyncClient.a(this.a);
        }
    }

    static {
        Class cls = b;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.MqttAsyncClient");
                b = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        c = cls.getName();
    }

    static String b() {
        return c;
    }

    static Logger a() {
        return d;
    }

    static void a(int i) {
        m = i;
    }

    static int c() {
        return m;
    }

    static void a(MqttAsyncClient mqttAsyncClient, boolean z) {
        mqttAsyncClient.n = z;
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.n = false;
        d.setResourceName(str2);
        if (str2 == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i = 0;
        int i2 = 0;
        while (i < str2.length() - 1) {
            if (a(str2.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        if (i2 > SupportMenu.USER_MASK) {
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        MqttConnectOptions.a(str);
        this.f = str;
        this.e = str2;
        this.h = mqttClientPersistence;
        if (this.h == null) {
            this.h = new MemoryPersistence();
        }
        d.fine(c, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
        this.h.open(str2, str);
        this.a = new ClientComms(this, this.h, mqttPingSender);
        this.h.close();
        this.g = new Hashtable();
    }

    protected static boolean a(char c) {
        return c >= '?' && c <= CharCompanionObject.MAX_HIGH_SURROGATE;
    }

    protected NetworkModule[] a(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        int i = 0;
        d.fine(c, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null) {
            serverURIs = new String[]{str};
        } else if (serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        while (i < serverURIs.length) {
            networkModuleArr[i] = b(serverURIs[i], mqttConnectOptions);
            i++;
        }
        d.fine(c, "createNetworkModules", "108");
        return networkModuleArr;
    }

    private NetworkModule b(String str, MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        d.fine(c, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        String substring;
        NetworkModule tCPNetworkModule;
        String b;
        int a;
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        Properties sSLProperties;
        String[] enabledCipherSuites;
        SocketFactory socketFactory2;
        switch (MqttConnectOptions.a(str)) {
            case 0:
                substring = str.substring(6);
                String b2 = b(substring);
                int a2 = a(substring, 1883);
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                tCPNetworkModule = new TCPNetworkModule(socketFactory, b2, a2, this.e);
                ((TCPNetworkModule) tCPNetworkModule).setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return tCPNetworkModule;
            case 1:
                SSLSocketFactoryFactory sSLSocketFactoryFactory2;
                substring = str.substring(6);
                b = b(substring);
                a = a(substring, 8883);
                if (socketFactory == null) {
                    sSLSocketFactoryFactory = new SSLSocketFactoryFactory();
                    sSLProperties = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties != null) {
                        sSLSocketFactoryFactory.initialize(sSLProperties, null);
                    }
                    sSLSocketFactoryFactory2 = sSLSocketFactoryFactory;
                    socketFactory = sSLSocketFactoryFactory.createSocketFactory(null);
                } else if (socketFactory instanceof SSLSocketFactory) {
                    sSLSocketFactoryFactory2 = null;
                } else {
                    throw ExceptionHelper.createMqttException(32105);
                }
                tCPNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, b, a, this.e);
                ((SSLNetworkModule) tCPNetworkModule).setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory2 == null) {
                    return tCPNetworkModule;
                }
                enabledCipherSuites = sSLSocketFactoryFactory2.getEnabledCipherSuites(null);
                if (enabledCipherSuites == null) {
                    return tCPNetworkModule;
                }
                ((SSLNetworkModule) tCPNetworkModule).setEnabledCiphers(enabledCipherSuites);
                return tCPNetworkModule;
            case 2:
                return new LocalNetworkModule(str.substring(8));
            case 3:
                substring = str.substring(5);
                b = b(substring);
                a = a(substring, 80);
                if (socketFactory == null) {
                    socketFactory2 = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                } else {
                    socketFactory2 = socketFactory;
                }
                WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory2, str, b, a, this.e);
                webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return webSocketNetworkModule;
            case 4:
                SSLSocketFactory createSocketFactory;
                SSLSocketFactoryFactory sSLSocketFactoryFactory3;
                substring = str.substring(6);
                b = b(substring);
                a = a(substring, 443);
                if (socketFactory == null) {
                    sSLSocketFactoryFactory = new SSLSocketFactoryFactory();
                    sSLProperties = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties != null) {
                        sSLSocketFactoryFactory.initialize(sSLProperties, null);
                    }
                    createSocketFactory = sSLSocketFactoryFactory.createSocketFactory(null);
                    sSLSocketFactoryFactory3 = sSLSocketFactoryFactory;
                } else if (socketFactory instanceof SSLSocketFactory) {
                    sSLSocketFactoryFactory3 = null;
                    socketFactory2 = socketFactory;
                } else {
                    throw ExceptionHelper.createMqttException(32105);
                }
                WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule(createSocketFactory, str, b, a, this.e);
                webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory3 != null) {
                    enabledCipherSuites = sSLSocketFactoryFactory3.getEnabledCipherSuites(null);
                    if (enabledCipherSuites != null) {
                        webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites);
                        return webSocketSecureNetworkModule;
                    }
                }
                return webSocketSecureNetworkModule;
            default:
                return null;
        }
    }

    private int a(String str, int i) {
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf == -1) {
            return i;
        }
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return Integer.parseInt(str.substring(lastIndexOf + 1, indexOf));
    }

    private String b(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    public IMqttToken connect() throws MqttException, MqttSecurityException {
        return connect(null, null);
    }

    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException, MqttSecurityException {
        return connect(mqttConnectOptions, null, null);
    }

    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttSecurityException {
        if (this.a.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        } else if (this.a.isConnecting()) {
            throw new MqttException(32110);
        } else if (this.a.isDisconnecting()) {
            throw new MqttException(32102);
        } else if (this.a.isClosed()) {
            throw new MqttException(32111);
        } else {
            this.j = mqttConnectOptions;
            this.k = obj;
            boolean isAutomaticReconnect = mqttConnectOptions.isAutomaticReconnect();
            Logger logger = d;
            String str = c;
            String str2 = "connect";
            String str3 = "103";
            Object[] objArr = new Object[8];
            objArr[0] = Boolean.valueOf(mqttConnectOptions.isCleanSession());
            objArr[1] = new Integer(mqttConnectOptions.getConnectionTimeout());
            objArr[2] = new Integer(mqttConnectOptions.getKeepAliveInterval());
            objArr[3] = mqttConnectOptions.getUserName();
            objArr[4] = mqttConnectOptions.getPassword() == null ? "[null]" : "[notnull]";
            objArr[5] = mqttConnectOptions.getWillMessage() == null ? "[null]" : "[notnull]";
            objArr[6] = obj;
            objArr[7] = iMqttActionListener;
            logger.fine(str, str2, str3, objArr);
            this.a.setNetworkModules(a(this.f, mqttConnectOptions));
            this.a.setReconnectCallback(new a(this, isAutomaticReconnect));
            IMqttToken mqttToken = new MqttToken(getClientId());
            ConnectActionListener connectActionListener = new ConnectActionListener(this, this.h, this.a, mqttConnectOptions, mqttToken, obj, iMqttActionListener, this.n);
            mqttToken.setActionCallback(connectActionListener);
            mqttToken.setUserContext(this);
            if (this.i instanceof MqttCallbackExtended) {
                connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) this.i);
            }
            this.a.setNetworkModuleIndex(0);
            connectActionListener.connect();
            return mqttToken;
        }
    }

    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(i.MIN_UPLOAD_INTERVAL, obj, iMqttActionListener);
    }

    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        d.fine(c, "disconnect", "104", new Object[]{new Long(j), obj, iMqttActionListener});
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.a.disconnect(new MqttDisconnect(), j, mqttToken);
            d.fine(c, "disconnect", "108");
            return mqttToken;
        } catch (Throwable e) {
            d.fine(c, "disconnect", "105", null, e);
            throw e;
        }
    }

    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(i.MIN_UPLOAD_INTERVAL, 10000);
    }

    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(i.MIN_UPLOAD_INTERVAL, j);
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.a.disconnectForcibly(j, j2);
    }

    public boolean isConnected() {
        return this.a.isConnected();
    }

    public String getClientId() {
        return this.e;
    }

    public String getServerURI() {
        return this.f;
    }

    public String getCurrentServerURI() {
        return this.a.getNetworkModules()[this.a.getNetworkModuleIndex()].getServerURI();
    }

    protected MqttTopic a(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.g.get(str);
        if (mqttTopic != null) {
            return mqttTopic;
        }
        mqttTopic = new MqttTopic(str, this.a);
        this.g.put(str, mqttTopic);
        return mqttTopic;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        d.fine(c, "ping", "117");
        IMqttToken checkForActivity = this.a.checkForActivity();
        d.fine(c, "ping", "118");
        return checkForActivity;
    }

    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, null, null);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, null, null);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (String removeMessageListener : strArr) {
            String removeMessageListener2;
            this.a.removeMessageListener(removeMessageListener2);
        }
        Object obj2 = "";
        int i = 0;
        while (i < strArr.length) {
            if (i > 0) {
                obj2 = new StringBuffer(String.valueOf(obj2)).append(", ").toString();
            }
            removeMessageListener2 = new StringBuffer(String.valueOf(obj2)).append("topic=").append(strArr[i]).append(" qos=").append(iArr[i]).toString();
            MqttTopic.validate(strArr[i], true);
            i++;
            String str = removeMessageListener2;
        }
        d.fine(c, "subscribe", "106", new Object[]{obj2, obj, iMqttActionListener});
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.a.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        d.fine(c, "subscribe", "109");
        return mqttToken;
    }

    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, null, null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        return subscribe(strArr, iArr, null, null, iMqttMessageListenerArr);
    }

    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        if (iMqttMessageListenerArr.length == iArr.length && iArr.length == strArr.length) {
            IMqttToken subscribe = subscribe(strArr, iArr, obj, iMqttActionListener);
            for (int i = 0; i < strArr.length; i++) {
                this.a.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
            }
            return subscribe;
        }
        throw new IllegalArgumentException();
    }

    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, null, null);
    }

    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, null, null);
    }

    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        int i = 0;
        Object obj2 = "";
        int i2 = 0;
        while (i2 < strArr.length) {
            if (i2 > 0) {
                obj2 = new StringBuffer(String.valueOf(obj2)).append(", ").toString();
            }
            String stringBuffer = new StringBuffer(String.valueOf(obj2)).append(strArr[i2]).toString();
            MqttTopic.validate(strArr[i2], true);
            i2++;
            String str = stringBuffer;
        }
        d.fine(c, "unsubscribe", "107", new Object[]{obj2, obj, iMqttActionListener});
        while (i < strArr.length) {
            this.a.removeMessageListener(strArr[i]);
            i++;
        }
        IMqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.a.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        d.fine(c, "unsubscribe", "110");
        return mqttToken;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.i = mqttCallback;
        this.a.setCallback(mqttCallback);
    }

    public void setManualAcks(boolean z) {
        this.a.setManualAcks(z);
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.a.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return new StringBuffer("paho").append(System.nanoTime()).toString();
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.a.getPendingDeliveryTokens();
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        return publish(str, bArr, i, z, null, null);
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        return publish(str, mqttMessage, null, null);
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, MqttPersistenceException {
        d.fine(c, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        Object mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.a(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.a.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        d.fine(c, "publish", "112");
        return mqttDeliveryToken;
    }

    public void reconnect() throws MqttException {
        d.fine(c, "reconnect", "500", new Object[]{this.e});
        if (this.a.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        } else if (this.a.isConnecting()) {
            throw new MqttException(32110);
        } else if (this.a.isDisconnecting()) {
            throw new MqttException(32102);
        } else if (this.a.isClosed()) {
            throw new MqttException(32111);
        } else {
            f();
            d();
        }
    }

    static void a(MqttAsyncClient mqttAsyncClient) {
        mqttAsyncClient.d();
    }

    private void d() {
        d.fine(c, "attemptReconnect", "500", new Object[]{this.e});
        try {
            connect(this.j, this.k, new b(this));
        } catch (Throwable e) {
            d.fine(c, "attemptReconnect", "804", null, e);
        } catch (Throwable e2) {
            d.fine(c, "attemptReconnect", "804", null, e2);
        }
    }

    static void b(MqttAsyncClient mqttAsyncClient) {
        mqttAsyncClient.e();
    }

    private void e() {
        d.fine(c, "startReconnectCycle", "503", new Object[]{this.e, new Long((long) m)});
        this.l = new Timer(new StringBuffer("MQTT Reconnect: ").append(this.e).toString());
        this.l.schedule(new a(this, null), (long) m);
    }

    static void c(MqttAsyncClient mqttAsyncClient) {
        mqttAsyncClient.f();
    }

    private void f() {
        d.fine(c, "stopReconnectCycle", "504", new Object[]{this.e});
        this.l.cancel();
        m = 1000;
    }

    static void a(MqttAsyncClient mqttAsyncClient, int i) {
        mqttAsyncClient.b(i);
    }

    private void b(int i) {
        d.fine(c, "rescheduleReconnectCycle", "505", new Object[]{this.e, new Long((long) m)});
        this.l.schedule(new a(this, null), (long) m);
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.a.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    public int getBufferedMessageCount() {
        return this.a.getBufferedMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.a.getBufferedMessage(i);
    }

    public void deleteBufferedMessage(int i) {
        this.a.deleteBufferedMessage(i);
    }

    public void close() throws MqttException {
        d.fine(c, "close", "113");
        this.a.close();
        d.fine(c, "close", "114");
    }

    public Debug getDebug() {
        return new Debug(this.e, this.a);
    }
}
