package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientComms {
    public static String BUILD_LEVEL = "L${build.level}";
    public static String VERSION = "${project.version}";
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private IMqttAsyncClient d;
    private int e;
    private NetworkModule[] f;
    private CommsReceiver g;
    private CommsSender h;
    private CommsCallback i;
    private ClientState j;
    private MqttConnectOptions k;
    private MqttClientPersistence l;
    private MqttPingSender m;
    private CommsTokenStore n;
    private boolean o;
    private byte p;
    private Object q;
    private boolean r;
    private boolean s;
    private DisconnectedMessageBuffer t;

    private class a implements Runnable {
        ClientComms a = null;
        Thread b = null;
        MqttToken c;
        MqttConnect d;
        final ClientComms e;

        a(ClientComms clientComms, ClientComms clientComms2, MqttToken mqttToken, MqttConnect mqttConnect) {
            this.e = clientComms;
            this.a = clientComms2;
            this.c = mqttToken;
            this.d = mqttConnect;
            this.b = new Thread(this, new StringBuffer("MQTT Con: ").append(clientComms.getClient().getClientId()).toString());
        }

        void a() {
            this.b.start();
        }

        public void run() {
            MqttException mqttException = null;
            ClientComms.a().fine(ClientComms.b(), "connectBG:run", "220");
            try {
                MqttDeliveryToken[] outstandingDelTokens = ClientComms.a(this.e).getOutstandingDelTokens();
                for (MqttDeliveryToken mqttDeliveryToken : outstandingDelTokens) {
                    mqttDeliveryToken.internalTok.setException(null);
                }
                ClientComms.a(this.e).a(this.c, this.d);
                NetworkModule networkModule = ClientComms.b(this.e)[ClientComms.c(this.e)];
                networkModule.start();
                ClientComms.a(this.e, new CommsReceiver(this.a, ClientComms.d(this.e), ClientComms.a(this.e), networkModule.getInputStream()));
                ClientComms.e(this.e).start(new StringBuffer("MQTT Rec: ").append(this.e.getClient().getClientId()).toString());
                ClientComms.a(this.e, new CommsSender(this.a, ClientComms.d(this.e), ClientComms.a(this.e), networkModule.getOutputStream()));
                ClientComms.f(this.e).start(new StringBuffer("MQTT Snd: ").append(this.e.getClient().getClientId()).toString());
                ClientComms.g(this.e).start(new StringBuffer("MQTT Call: ").append(this.e.getClient().getClientId()).toString());
                this.e.a(this.d, this.c);
            } catch (Throwable e) {
                ClientComms.a().fine(ClientComms.b(), "connectBG:run", "212", null, e);
                Throwable th = e;
            } catch (Throwable e2) {
                ClientComms.a().fine(ClientComms.b(), "connectBG:run", "209", null, e2);
                mqttException = ExceptionHelper.createMqttException(e2);
            }
            if (mqttException != null) {
                this.e.shutdownConnection(this.c, mqttException);
            }
        }
    }

    private class b implements Runnable {
        Thread a = null;
        MqttDisconnect b;
        long c;
        MqttToken d;
        final ClientComms e;

        b(ClientComms clientComms, MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) {
            this.e = clientComms;
            this.b = mqttDisconnect;
            this.c = j;
            this.d = mqttToken;
        }

        void a() {
            this.a = new Thread(this, new StringBuffer("MQTT Disc: ").append(this.e.getClient().getClientId()).toString());
            this.a.start();
        }

        public void run() {
            ClientComms.a().fine(ClientComms.b(), "disconnectBG:run", "221");
            ClientComms.d(this.e).quiesce(this.c);
            try {
                this.e.a(this.b, this.d);
                this.d.internalTok.waitUntilSent();
            } catch (MqttException e) {
            } catch (Throwable th) {
                this.d.internalTok.a(null, null);
                this.e.shutdownConnection(this.d, null);
            }
            this.d.internalTok.a(null, null);
            this.e.shutdownConnection(this.d, null);
        }
    }

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientComms");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    static String b() {
        return b;
    }

    static Logger a() {
        return c;
    }

    static int c(ClientComms clientComms) {
        return clientComms.e;
    }

    static NetworkModule[] b(ClientComms clientComms) {
        return clientComms.f;
    }

    static void a(ClientComms clientComms, CommsReceiver commsReceiver) {
        clientComms.g = commsReceiver;
    }

    static CommsReceiver e(ClientComms clientComms) {
        return clientComms.g;
    }

    static void a(ClientComms clientComms, CommsSender commsSender) {
        clientComms.h = commsSender;
    }

    static CommsSender f(ClientComms clientComms) {
        return clientComms.h;
    }

    static CommsCallback g(ClientComms clientComms) {
        return clientComms.i;
    }

    static ClientState d(ClientComms clientComms) {
        return clientComms.j;
    }

    static CommsTokenStore a(ClientComms clientComms) {
        return clientComms.n;
    }

    public ClientComms(IMqttAsyncClient iMqttAsyncClient, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        this.o = false;
        this.p = (byte) 3;
        this.q = new Object();
        this.r = false;
        this.s = false;
        this.p = (byte) 3;
        this.d = iMqttAsyncClient;
        this.l = mqttClientPersistence;
        this.m = mqttPingSender;
        this.m.init(this);
        this.n = new CommsTokenStore(getClient().getClientId());
        this.i = new CommsCallback(this);
        this.j = new ClientState(mqttClientPersistence, this.n, this.i, this, mqttPingSender);
        this.i.setClientState(this.j);
        c.setResourceName(getClient().getClientId());
    }

    void a(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        c.fine(b, "internalSend", "200", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        if (mqttToken.getClient() == null) {
            mqttToken.internalTok.a(getClient());
            try {
                this.j.send(mqttWireMessage, mqttToken);
                return;
            } catch (MqttException e) {
                if (mqttWireMessage instanceof MqttPublish) {
                    this.j.a((MqttPublish) mqttWireMessage);
                }
                throw e;
            }
        }
        c.fine(b, "internalSend", "213", new Object[]{mqttWireMessage.getKey(), mqttWireMessage, mqttToken});
        throw new MqttException(32201);
    }

    public void sendNoWait(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (isConnected() || ((!isConnected() && (mqttWireMessage instanceof MqttConnect)) || (isDisconnecting() && (mqttWireMessage instanceof MqttDisconnect)))) {
            if (this.t == null || this.t.getMessageCount() == 0) {
                a(mqttWireMessage, mqttToken);
                return;
            }
            c.fine(b, "sendNoWait", "507", new Object[]{mqttWireMessage.getKey()});
            this.j.persistBufferedMessage(mqttWireMessage);
            this.t.putMessage(mqttWireMessage, mqttToken);
        } else if (this.t == null || !isResting()) {
            c.fine(b, "sendNoWait", "208");
            throw ExceptionHelper.createMqttException(32104);
        } else {
            c.fine(b, "sendNoWait", "508", new Object[]{mqttWireMessage.getKey()});
            this.j.persistBufferedMessage(mqttWireMessage);
            this.t.putMessage(mqttWireMessage, mqttToken);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws org.eclipse.paho.client.mqttv3.MqttException {
        /*
        r5 = this;
        r1 = r5.q;
        monitor-enter(r1);
        r0 = r5.isClosed();	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0066;
    L_0x0009:
        r0 = r5.isDisconnected();	 Catch:{ all -> 0x0028 }
        if (r0 != 0) goto L_0x0043;
    L_0x000f:
        r0 = c;	 Catch:{ all -> 0x0028 }
        r2 = b;	 Catch:{ all -> 0x0028 }
        r3 = "close";
        r4 = "224";
        r0.fine(r2, r3, r4);	 Catch:{ all -> 0x0028 }
        r0 = r5.isConnecting();	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x002b;
    L_0x0020:
        r0 = new org.eclipse.paho.client.mqttv3.MqttException;	 Catch:{ all -> 0x0028 }
        r2 = 32110; // 0x7d6e float:4.4996E-41 double:1.58644E-319;
        r0.<init>(r2);	 Catch:{ all -> 0x0028 }
        throw r0;	 Catch:{ all -> 0x0028 }
    L_0x0028:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        throw r0;
    L_0x002b:
        r0 = r5.isConnected();	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x0038;
    L_0x0031:
        r0 = 32100; // 0x7d64 float:4.4982E-41 double:1.58595E-319;
        r0 = org.eclipse.paho.client.mqttv3.internal.ExceptionHelper.createMqttException(r0);	 Catch:{ all -> 0x0028 }
        throw r0;	 Catch:{ all -> 0x0028 }
    L_0x0038:
        r0 = r5.isDisconnecting();	 Catch:{ all -> 0x0028 }
        if (r0 == 0) goto L_0x0043;
    L_0x003e:
        r0 = 1;
        r5.r = r0;	 Catch:{ all -> 0x0028 }
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
    L_0x0042:
        return;
    L_0x0043:
        r0 = 4;
        r5.p = r0;	 Catch:{ all -> 0x0028 }
        r0 = r5.j;	 Catch:{ all -> 0x0028 }
        r0.g();	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.j = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.i = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.l = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.h = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.m = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.g = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.f = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.k = r0;	 Catch:{ all -> 0x0028 }
        r0 = 0;
        r5.n = r0;	 Catch:{ all -> 0x0028 }
    L_0x0066:
        monitor-exit(r1);	 Catch:{ all -> 0x0028 }
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientComms.close():void");
    }

    public void connect(MqttConnectOptions mqttConnectOptions, MqttToken mqttToken) throws MqttException {
        synchronized (this.q) {
            if (!isDisconnected() || this.r) {
                c.fine(b, "connect", "207", new Object[]{new Byte(this.p)});
                if (isClosed() || this.r) {
                    throw new MqttException(32111);
                } else if (isConnecting()) {
                    throw new MqttException(32110);
                } else if (isDisconnecting()) {
                    throw new MqttException(32102);
                } else {
                    throw ExceptionHelper.createMqttException(32100);
                }
            }
            c.fine(b, "connect", "214");
            this.p = (byte) 1;
            this.k = mqttConnectOptions;
            MqttConnect mqttConnect = new MqttConnect(this.d.getClientId(), this.k.getMqttVersion(), this.k.isCleanSession(), this.k.getKeepAliveInterval(), this.k.getUserName(), this.k.getPassword(), this.k.getWillMessage(), this.k.getWillDestination());
            this.j.a((long) this.k.getKeepAliveInterval());
            this.j.a(this.k.isCleanSession());
            this.j.a(this.k.getMaxInflight());
            this.n.open();
            new a(this, this, mqttToken, mqttConnect).a();
        }
    }

    public void connectComplete(MqttConnack mqttConnack, MqttException mqttException) throws MqttException {
        int returnCode = mqttConnack.getReturnCode();
        synchronized (this.q) {
            if (returnCode == 0) {
                c.fine(b, "connectComplete", "215");
                this.p = (byte) 0;
                return;
            }
            c.fine(b, "connectComplete", "204", new Object[]{new Integer(returnCode)});
            throw mqttException;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void shutdownConnection(org.eclipse.paho.client.mqttv3.MqttToken r10, org.eclipse.paho.client.mqttv3.MqttException r11) {
        /*
        r9 = this;
        r1 = 1;
        r2 = 0;
        r4 = r9.q;
        monitor-enter(r4);
        r0 = r9.o;	 Catch:{ all -> 0x00e9 }
        if (r0 != 0) goto L_0x0013;
    L_0x0009:
        r0 = r9.r;	 Catch:{ all -> 0x00e9 }
        if (r0 != 0) goto L_0x0013;
    L_0x000d:
        r0 = r9.isClosed();	 Catch:{ all -> 0x00e9 }
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r4);	 Catch:{ all -> 0x00e9 }
    L_0x0014:
        return;
    L_0x0015:
        r0 = 1;
        r9.o = r0;	 Catch:{ all -> 0x00e9 }
        r0 = c;	 Catch:{ all -> 0x00e9 }
        r3 = b;	 Catch:{ all -> 0x00e9 }
        r5 = "shutdownConnection";
        r6 = "216";
        r0.fine(r3, r5, r6);	 Catch:{ all -> 0x00e9 }
        r0 = r9.isConnected();	 Catch:{ all -> 0x00e9 }
        if (r0 != 0) goto L_0x00e6;
    L_0x0029:
        r0 = r9.isDisconnecting();	 Catch:{ all -> 0x00e9 }
        if (r0 != 0) goto L_0x00e6;
    L_0x002f:
        r3 = r2;
    L_0x0030:
        r0 = 2;
        r9.p = r0;	 Catch:{ all -> 0x00e9 }
        monitor-exit(r4);	 Catch:{ all -> 0x00e9 }
        if (r10 == 0) goto L_0x0041;
    L_0x0036:
        r0 = r10.isComplete();
        if (r0 != 0) goto L_0x0041;
    L_0x003c:
        r0 = r10.internalTok;
        r0.setException(r11);
    L_0x0041:
        r0 = r9.i;
        if (r0 == 0) goto L_0x004a;
    L_0x0045:
        r0 = r9.i;
        r0.stop();
    L_0x004a:
        r0 = r9.f;	 Catch:{ Exception -> 0x00f9 }
        if (r0 == 0) goto L_0x0059;
    L_0x004e:
        r0 = r9.f;	 Catch:{ Exception -> 0x00f9 }
        r4 = r9.e;	 Catch:{ Exception -> 0x00f9 }
        r0 = r0[r4];	 Catch:{ Exception -> 0x00f9 }
        if (r0 == 0) goto L_0x0059;
    L_0x0056:
        r0.stop();	 Catch:{ Exception -> 0x00f9 }
    L_0x0059:
        r0 = r9.g;
        if (r0 == 0) goto L_0x0062;
    L_0x005d:
        r0 = r9.g;
        r0.stop();
    L_0x0062:
        r0 = r9.n;
        if (r0 == 0) goto L_0x0072;
    L_0x0066:
        r0 = r9.n;
        r4 = new org.eclipse.paho.client.mqttv3.MqttException;
        r5 = 32102; // 0x7d66 float:4.4984E-41 double:1.58605E-319;
        r4.<init>(r5);
        r0.a(r4);
    L_0x0072:
        r4 = r9.a(r10, r11);
        r0 = r9.j;	 Catch:{ Exception -> 0x00f7 }
        r0.disconnected(r11);	 Catch:{ Exception -> 0x00f7 }
        r0 = r9.j;	 Catch:{ Exception -> 0x00f7 }
        r0 = r0.b();	 Catch:{ Exception -> 0x00f7 }
        if (r0 == 0) goto L_0x0088;
    L_0x0083:
        r0 = r9.i;	 Catch:{ Exception -> 0x00f7 }
        r0.removeMessageListeners();	 Catch:{ Exception -> 0x00f7 }
    L_0x0088:
        r0 = r9.h;
        if (r0 == 0) goto L_0x0091;
    L_0x008c:
        r0 = r9.h;
        r0.stop();
    L_0x0091:
        r0 = r9.m;
        if (r0 == 0) goto L_0x009a;
    L_0x0095:
        r0 = r9.m;
        r0.stop();
    L_0x009a:
        r0 = r9.t;	 Catch:{ Exception -> 0x00f5 }
        if (r0 != 0) goto L_0x00a7;
    L_0x009e:
        r0 = r9.l;	 Catch:{ Exception -> 0x00f5 }
        if (r0 == 0) goto L_0x00a7;
    L_0x00a2:
        r0 = r9.l;	 Catch:{ Exception -> 0x00f5 }
        r0.close();	 Catch:{ Exception -> 0x00f5 }
    L_0x00a7:
        r5 = r9.q;
        monitor-enter(r5);
        r0 = c;	 Catch:{ all -> 0x00ec }
        r6 = b;	 Catch:{ all -> 0x00ec }
        r7 = "shutdownConnection";
        r8 = "217";
        r0.fine(r6, r7, r8);	 Catch:{ all -> 0x00ec }
        r0 = 3;
        r9.p = r0;	 Catch:{ all -> 0x00ec }
        r0 = 0;
        r9.o = r0;	 Catch:{ all -> 0x00ec }
        monitor-exit(r5);	 Catch:{ all -> 0x00ec }
        if (r4 == 0) goto L_0x00ef;
    L_0x00be:
        r0 = r1;
    L_0x00bf:
        r5 = r9.i;
        if (r5 == 0) goto L_0x00f1;
    L_0x00c3:
        r0 = r0 & r1;
        if (r0 == 0) goto L_0x00cb;
    L_0x00c6:
        r0 = r9.i;
        r0.asyncOperationComplete(r4);
    L_0x00cb:
        if (r3 == 0) goto L_0x00d6;
    L_0x00cd:
        r0 = r9.i;
        if (r0 == 0) goto L_0x00d6;
    L_0x00d1:
        r0 = r9.i;
        r0.connectionLost(r11);
    L_0x00d6:
        r1 = r9.q;
        monitor-enter(r1);
        r0 = r9.r;	 Catch:{ all -> 0x00e3 }
        if (r0 == 0) goto L_0x00e0;
    L_0x00dd:
        r9.close();	 Catch:{ Exception -> 0x00f3 }
    L_0x00e0:
        monitor-exit(r1);	 Catch:{ all -> 0x00e3 }
        goto L_0x0014;
    L_0x00e3:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x00e3 }
        throw r0;
    L_0x00e6:
        r3 = r1;
        goto L_0x0030;
    L_0x00e9:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x00e9 }
        throw r0;
    L_0x00ec:
        r0 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x00ec }
        throw r0;
    L_0x00ef:
        r0 = r2;
        goto L_0x00bf;
    L_0x00f1:
        r1 = r2;
        goto L_0x00c3;
    L_0x00f3:
        r0 = move-exception;
        goto L_0x00e0;
    L_0x00f5:
        r0 = move-exception;
        goto L_0x00a7;
    L_0x00f7:
        r0 = move-exception;
        goto L_0x0088;
    L_0x00f9:
        r0 = move-exception;
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientComms.shutdownConnection(org.eclipse.paho.client.mqttv3.MqttToken, org.eclipse.paho.client.mqttv3.MqttException):void");
    }

    private MqttToken a(MqttToken mqttToken, MqttException mqttException) {
        c.fine(b, "handleOldTokens", "222");
        MqttToken mqttToken2 = null;
        if (mqttToken != null) {
            try {
                if (this.n.getToken(mqttToken.internalTok.getKey()) == null) {
                    this.n.a(mqttToken, mqttToken.internalTok.getKey());
                }
            } catch (Exception e) {
                return null;
            }
        }
        Enumeration elements = this.j.resolveOldTokens(mqttException).elements();
        while (elements.hasMoreElements()) {
            try {
                MqttToken mqttToken3 = (MqttToken) elements.nextElement();
                if (mqttToken3.internalTok.getKey().equals(MqttDisconnect.KEY) || mqttToken3.internalTok.getKey().equals("Con")) {
                    mqttToken2 = mqttToken3;
                } else {
                    this.i.asyncOperationComplete(mqttToken3);
                }
            } catch (Exception e2) {
                return mqttToken2;
            }
        }
        return mqttToken2;
    }

    public void disconnect(MqttDisconnect mqttDisconnect, long j, MqttToken mqttToken) throws MqttException {
        synchronized (this.q) {
            if (isClosed()) {
                c.fine(b, "disconnect", "223");
                throw ExceptionHelper.createMqttException(32111);
            } else if (isDisconnected()) {
                c.fine(b, "disconnect", "211");
                throw ExceptionHelper.createMqttException(32101);
            } else if (isDisconnecting()) {
                c.fine(b, "disconnect", "219");
                throw ExceptionHelper.createMqttException(32102);
            } else if (Thread.currentThread() == this.i.a()) {
                c.fine(b, "disconnect", "210");
                throw ExceptionHelper.createMqttException(32107);
            } else {
                c.fine(b, "disconnect", "218");
                this.p = (byte) 2;
                new b(this, mqttDisconnect, j, mqttToken).a();
            }
        }
    }

    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.j.quiesce(j);
        MqttToken mqttToken = new MqttToken(this.d.getClientId());
        try {
            a(new MqttDisconnect(), mqttToken);
            mqttToken.waitForCompletion(j2);
        } catch (Exception e) {
        } catch (Throwable th) {
            mqttToken.internalTok.a(null, null);
            shutdownConnection(mqttToken, null);
        }
        mqttToken.internalTok.a(null, null);
        shutdownConnection(mqttToken, null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.q) {
            z = this.p == (byte) 0;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z = true;
        synchronized (this.q) {
            if (this.p != (byte) 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isDisconnected() {
        boolean z;
        synchronized (this.q) {
            z = this.p == (byte) 3;
        }
        return z;
    }

    public boolean isDisconnecting() {
        boolean z;
        synchronized (this.q) {
            z = this.p == (byte) 2;
        }
        return z;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this.q) {
            z = this.p == (byte) 4;
        }
        return z;
    }

    public boolean isResting() {
        boolean z;
        synchronized (this.q) {
            z = this.s;
        }
        return z;
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.i.setCallback(mqttCallback);
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.i.setReconnectCallback(mqttCallbackExtended);
    }

    public void setManualAcks(boolean z) {
        this.i.setManualAcks(z);
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.i.messageArrivedComplete(i, i2);
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.i.setMessageListener(str, iMqttMessageListener);
    }

    public void removeMessageListener(String str) {
        this.i.removeMessageListener(str);
    }

    public void setNetworkModuleIndex(int i) {
        this.e = i;
    }

    public int getNetworkModuleIndex() {
        return this.e;
    }

    public NetworkModule[] getNetworkModules() {
        return this.f;
    }

    public void setNetworkModules(NetworkModule[] networkModuleArr) {
        this.f = networkModuleArr;
    }

    public MqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.n.getOutstandingDelTokens();
    }

    protected void a(MqttPublish mqttPublish) throws MqttPersistenceException {
        this.j.b(mqttPublish);
    }

    protected void a(int i) throws MqttPersistenceException {
        this.j.b(i);
    }

    public IMqttAsyncClient getClient() {
        return this.d;
    }

    public long getKeepAlive() {
        return this.j.a();
    }

    public ClientState getClientState() {
        return this.j;
    }

    public MqttConnectOptions getConOptions() {
        return this.k;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("conState", new Integer(this.p));
        properties.put("serverURI", getClient().getServerURI());
        properties.put(com.alipay.sdk.authjs.a.c, this.i);
        properties.put("stoppingComms", new Boolean(this.o));
        return properties;
    }

    public MqttToken checkForActivity() {
        return checkForActivity(null);
    }

    public MqttToken checkForActivity(IMqttActionListener iMqttActionListener) {
        MqttToken mqttToken = null;
        try {
            mqttToken = this.j.checkForActivity(iMqttActionListener);
        } catch (Exception e) {
            a(e);
        } catch (Exception e2) {
            a(e2);
        }
        return mqttToken;
    }

    private void a(Exception exception) {
        c.fine(b, "handleRunException", "804", null, exception);
        if (exception instanceof MqttException) {
            MqttException mqttException = (MqttException) exception;
        } else {
            exception = new MqttException(32109, exception);
        }
        shutdownConnection(null, exception);
    }

    public void setRestingState(boolean z) {
        this.s = z;
    }

    public void setDisconnectedMessageBuffer(DisconnectedMessageBuffer disconnectedMessageBuffer) {
        this.t = disconnectedMessageBuffer;
    }

    public int getBufferedMessageCount() {
        return this.t.getMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return ((MqttPublish) this.t.getMessage(i).getMessage()).getMessage();
    }

    public void deleteBufferedMessage(int i) {
        this.t.deleteMessage(i);
    }

    public void notifyReconnect() {
        if (this.t != null) {
            c.fine(b, "notifyReconnect", "509");
            this.t.setPublishCallback(new a(this));
            new Thread(this.t).start();
        }
    }
}
