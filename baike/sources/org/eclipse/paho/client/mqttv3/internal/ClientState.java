package org.eclipse.paho.client.mqttv3.internal;

import android.support.v4.internal.view.SupportMenu;
import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttPingSender;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingReq;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPingResp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRel;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class ClientState {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private Hashtable A = null;
    private Hashtable B = null;
    private Hashtable C = null;
    private Hashtable D = null;
    private MqttPingSender E = null;
    private int d = 0;
    private Hashtable e;
    private volatile Vector f;
    private volatile Vector g;
    private CommsTokenStore h;
    private ClientComms i = null;
    private CommsCallback j = null;
    private long k;
    private boolean l;
    private MqttClientPersistence m;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private Object q = new Object();
    private Object r = new Object();
    private boolean s = false;
    private long t = 0;
    private long u = 0;
    private long v = 0;
    private MqttWireMessage w;
    private Object x = new Object();
    private int y = 0;
    private boolean z = false;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.ClientState");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    protected ClientState(MqttClientPersistence mqttClientPersistence, CommsTokenStore commsTokenStore, CommsCallback commsCallback, ClientComms clientComms, MqttPingSender mqttPingSender) throws MqttException {
        c.setResourceName(clientComms.getClient().getClientId());
        c.finer(b, "<Init>", "");
        this.e = new Hashtable();
        this.g = new Vector();
        this.A = new Hashtable();
        this.B = new Hashtable();
        this.C = new Hashtable();
        this.D = new Hashtable();
        this.w = new MqttPingReq();
        this.p = 0;
        this.o = 0;
        this.m = mqttClientPersistence;
        this.j = commsCallback;
        this.h = commsTokenStore;
        this.i = clientComms;
        this.E = mqttPingSender;
        d();
    }

    protected void a(int i) {
        this.n = i;
        this.f = new Vector(this.n);
    }

    protected void a(long j) {
        this.k = 1000 * j;
    }

    protected long a() {
        return this.k;
    }

    protected void a(boolean z) {
        this.l = z;
    }

    protected boolean b() {
        return this.l;
    }

    private String c(MqttWireMessage mqttWireMessage) {
        return new StringBuffer("s-").append(mqttWireMessage.getMessageId()).toString();
    }

    private String d(MqttWireMessage mqttWireMessage) {
        return new StringBuffer("sc-").append(mqttWireMessage.getMessageId()).toString();
    }

    private String e(MqttWireMessage mqttWireMessage) {
        return new StringBuffer("r-").append(mqttWireMessage.getMessageId()).toString();
    }

    private String c(int i) {
        return new StringBuffer("r-").append(i).toString();
    }

    private String f(MqttWireMessage mqttWireMessage) {
        return new StringBuffer("sb-").append(mqttWireMessage.getMessageId()).toString();
    }

    protected void c() throws MqttException {
        c.fine(b, "clearState", ">");
        this.m.clear();
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.A.clear();
        this.B.clear();
        this.C.clear();
        this.D.clear();
        this.h.clear();
    }

    private MqttWireMessage a(String str, MqttPersistable mqttPersistable) throws MqttException {
        MqttWireMessage createWireMessage;
        try {
            createWireMessage = MqttWireMessage.createWireMessage(mqttPersistable);
        } catch (Throwable e) {
            c.fine(b, "restoreMessage", "602", new Object[]{str}, e);
            if (!(e.getCause() instanceof EOFException)) {
                throw e;
            } else if (str != null) {
                this.m.remove(str);
                createWireMessage = null;
            } else {
                createWireMessage = null;
            }
        }
        c.fine(b, "restoreMessage", "601", new Object[]{str, createWireMessage});
        return createWireMessage;
    }

    private void a(Vector vector, MqttWireMessage mqttWireMessage) {
        int messageId = mqttWireMessage.getMessageId();
        for (int i = 0; i < vector.size(); i++) {
            if (((MqttWireMessage) vector.elementAt(i)).getMessageId() > messageId) {
                vector.insertElementAt(mqttWireMessage, i);
                return;
            }
        }
        vector.addElement(mqttWireMessage);
    }

    private Vector a(Vector vector) {
        int i = 0;
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < vector.size()) {
            int messageId = ((MqttWireMessage) vector.elementAt(i2)).getMessageId();
            if (messageId - i5 > i4) {
                i4 = messageId - i5;
                i3 = i2;
            }
            i2++;
            i5 = messageId;
        }
        if (((MqttWireMessage) vector.elementAt(0)).getMessageId() + (SupportMenu.USER_MASK - i5) > i4) {
            i3 = 0;
        }
        for (int i6 = i3; i6 < vector.size(); i6++) {
            vector2.addElement(vector.elementAt(i6));
        }
        while (i < i3) {
            vector2.addElement(vector.elementAt(i));
            i++;
        }
        return vector2;
    }

    protected void d() throws MqttException {
        Enumeration keys = this.m.keys();
        int i = this.d;
        Vector vector = new Vector();
        c.fine(b, "restoreState", "600");
        int i2 = i;
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            MqttWireMessage a = a(str, this.m.get(str));
            if (a != null) {
                if (str.startsWith("r-")) {
                    c.fine(b, "restoreState", "604", new Object[]{str, a});
                    this.D.put(new Integer(a.getMessageId()), a);
                } else if (str.startsWith("s-")) {
                    r1 = (MqttPublish) a;
                    int max = Math.max(r1.getMessageId(), i2);
                    if (this.m.containsKey(d((MqttWireMessage) r1))) {
                        MqttPubRel mqttPubRel = (MqttPubRel) a(str, this.m.get(d((MqttWireMessage) r1)));
                        if (mqttPubRel != null) {
                            c.fine(b, "restoreState", "605", new Object[]{str, a});
                            this.A.put(new Integer(mqttPubRel.getMessageId()), mqttPubRel);
                        } else {
                            c.fine(b, "restoreState", "606", new Object[]{str, a});
                        }
                    } else {
                        r1.setDuplicate(true);
                        if (r1.getMessage().getQos() == 2) {
                            c.fine(b, "restoreState", "607", new Object[]{str, a});
                            this.A.put(new Integer(r1.getMessageId()), r1);
                        } else {
                            c.fine(b, "restoreState", "608", new Object[]{str, a});
                            this.B.put(new Integer(r1.getMessageId()), r1);
                        }
                    }
                    this.h.a(r1).internalTok.a(this.i.getClient());
                    this.e.put(new Integer(r1.getMessageId()), new Integer(r1.getMessageId()));
                    i2 = max;
                } else if (str.startsWith("sb-")) {
                    r1 = (MqttPublish) a;
                    i2 = Math.max(r1.getMessageId(), i2);
                    if (r1.getMessage().getQos() == 2) {
                        c.fine(b, "restoreState", "607", new Object[]{str, a});
                        this.A.put(new Integer(r1.getMessageId()), r1);
                    } else if (r1.getMessage().getQos() == 1) {
                        c.fine(b, "restoreState", "608", new Object[]{str, a});
                        this.B.put(new Integer(r1.getMessageId()), r1);
                    } else {
                        c.fine(b, "restoreState", "511", new Object[]{str, a});
                        this.C.put(new Integer(r1.getMessageId()), r1);
                        this.m.remove(str);
                    }
                    this.h.a(r1).internalTok.a(this.i.getClient());
                    this.e.put(new Integer(r1.getMessageId()), new Integer(r1.getMessageId()));
                } else if (str.startsWith("sc-")) {
                    if (!this.m.containsKey(c((MqttPubRel) a))) {
                        vector.addElement(str);
                    }
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            c.fine(b, "restoreState", "609", new Object[]{(String) elements.nextElement()});
            this.m.remove(str);
        }
        this.d = i2;
    }

    private void h() {
        this.f = new Vector(this.n);
        this.g = new Vector();
        Enumeration keys = this.A.keys();
        while (keys.hasMoreElements()) {
            MqttWireMessage mqttWireMessage = (MqttWireMessage) this.A.get(keys.nextElement());
            if (mqttWireMessage instanceof MqttPublish) {
                c.fine(b, "restoreInflightMessages", "610", new Object[]{r2});
                mqttWireMessage.setDuplicate(true);
                a(this.f, (MqttPublish) mqttWireMessage);
            } else if (mqttWireMessage instanceof MqttPubRel) {
                c.fine(b, "restoreInflightMessages", "611", new Object[]{r2});
                a(this.g, (MqttPubRel) mqttWireMessage);
            }
        }
        keys = this.B.keys();
        while (keys.hasMoreElements()) {
            mqttWireMessage = (MqttPublish) this.B.get(keys.nextElement());
            mqttWireMessage.setDuplicate(true);
            c.fine(b, "restoreInflightMessages", "612", new Object[]{r2});
            a(this.f, mqttWireMessage);
        }
        keys = this.C.keys();
        while (keys.hasMoreElements()) {
            mqttWireMessage = (MqttPublish) this.C.get(keys.nextElement());
            c.fine(b, "restoreInflightMessages", "512", new Object[]{keys.nextElement()});
            a(this.f, mqttWireMessage);
        }
        this.g = a(this.g);
        this.f = a(this.f);
    }

    public void send(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        if (mqttWireMessage.isMessageIdRequired() && mqttWireMessage.getMessageId() == 0) {
            mqttWireMessage.setMessageId(j());
        }
        if (mqttToken != null) {
            try {
                mqttToken.internalTok.setMessageID(mqttWireMessage.getMessageId());
            } catch (Exception e) {
            }
        }
        if (mqttWireMessage instanceof MqttPublish) {
            synchronized (this.q) {
                if (this.o >= this.n) {
                    c.fine(b, "send", "613", new Object[]{new Integer(this.o)});
                    throw new MqttException(32202);
                }
                c.fine(b, "send", "628", new Object[]{new Integer(mqttWireMessage.getMessageId()), new Integer(((MqttPublish) mqttWireMessage).getMessage().getQos()), mqttWireMessage});
                switch (((MqttPublish) mqttWireMessage).getMessage().getQos()) {
                    case 1:
                        this.B.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.m.put(c(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                    case 2:
                        this.A.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
                        this.m.put(c(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        break;
                }
                this.h.a(mqttToken, mqttWireMessage);
                this.f.addElement(mqttWireMessage);
                this.q.notifyAll();
            }
            return;
        }
        c.fine(b, "send", "615", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (mqttWireMessage instanceof MqttConnect) {
            synchronized (this.q) {
                this.h.a(mqttToken, mqttWireMessage);
                this.g.insertElementAt(mqttWireMessage, 0);
                this.q.notifyAll();
            }
            return;
        }
        if (mqttWireMessage instanceof MqttPingReq) {
            this.w = mqttWireMessage;
        } else if (mqttWireMessage instanceof MqttPubRel) {
            this.A.put(new Integer(mqttWireMessage.getMessageId()), mqttWireMessage);
            this.m.put(d(mqttWireMessage), (MqttPubRel) mqttWireMessage);
        } else if (mqttWireMessage instanceof MqttPubComp) {
            this.m.remove(e(mqttWireMessage));
        }
        synchronized (this.q) {
            if (!(mqttWireMessage instanceof MqttAck)) {
                this.h.a(mqttToken, mqttWireMessage);
            }
            this.g.addElement(mqttWireMessage);
            this.q.notifyAll();
        }
    }

    public void persistBufferedMessage(MqttWireMessage mqttWireMessage) {
        String f = f(mqttWireMessage);
        try {
            mqttWireMessage.setMessageId(j());
            try {
                this.m.put(f, (MqttPublish) mqttWireMessage);
            } catch (MqttPersistenceException e) {
                c.fine(b, "persistBufferedMessage", "515");
                this.m.open(this.i.getClient().getClientId(), this.i.getClient().getClientId());
                this.m.put(f, (MqttPublish) mqttWireMessage);
            }
            c.fine(b, "persistBufferedMessage", "513", new Object[]{f});
        } catch (MqttException e2) {
            c.warning(b, "persistBufferedMessage", "513", new Object[]{f});
        }
    }

    public void unPersistBufferedMessage(MqttWireMessage mqttWireMessage) throws MqttPersistenceException {
        c.fine(b, "unPersistBufferedMessage", "513", new Object[]{mqttWireMessage.getKey()});
        this.m.remove(f(mqttWireMessage));
    }

    protected void a(MqttPublish mqttPublish) throws MqttPersistenceException {
        synchronized (this.q) {
            c.fine(b, "undo", "618", new Object[]{new Integer(mqttPublish.getMessageId()), new Integer(mqttPublish.getMessage().getQos())});
            if (mqttPublish.getMessage().getQos() == 1) {
                this.B.remove(new Integer(mqttPublish.getMessageId()));
            } else {
                this.A.remove(new Integer(mqttPublish.getMessageId()));
            }
            this.f.removeElement(mqttPublish);
            this.m.remove(c((MqttWireMessage) mqttPublish));
            this.h.removeToken((MqttWireMessage) mqttPublish);
            f();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.eclipse.paho.client.mqttv3.MqttToken checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener r15) throws org.eclipse.paho.client.mqttv3.MqttException {
        /*
        r14 = this;
        r0 = c;
        r1 = b;
        r2 = "checkForActivity";
        r3 = "616";
        r4 = 0;
        r4 = new java.lang.Object[r4];
        r0.fine(r1, r2, r3, r4);
        r1 = r14.r;
        monitor-enter(r1);
        r0 = r14.s;	 Catch:{ all -> 0x008a }
        if (r0 == 0) goto L_0x0018;
    L_0x0015:
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        r0 = 0;
    L_0x0017:
        return r0;
    L_0x0018:
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        r0 = 0;
        r14.a();
        r1 = r14.z;
        if (r1 == 0) goto L_0x0017;
    L_0x0021:
        r2 = r14.k;
        r4 = 0;
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 <= 0) goto L_0x0017;
    L_0x0029:
        r2 = java.lang.System.currentTimeMillis();
        r1 = 100;
        r4 = r14.x;
        monitor-enter(r4);
        r5 = r14.y;	 Catch:{ all -> 0x0087 }
        if (r5 <= 0) goto L_0x008d;
    L_0x0036:
        r6 = r14.u;	 Catch:{ all -> 0x0087 }
        r6 = r2 - r6;
        r8 = r14.k;	 Catch:{ all -> 0x0087 }
        r10 = (long) r1;	 Catch:{ all -> 0x0087 }
        r8 = r8 + r10;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 < 0) goto L_0x008d;
    L_0x0042:
        r0 = c;	 Catch:{ all -> 0x0087 }
        r1 = b;	 Catch:{ all -> 0x0087 }
        r5 = "checkForActivity";
        r6 = "619";
        r7 = 5;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0087 }
        r8 = 0;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.k;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 1;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.t;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 2;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.u;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 3;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r9.<init>(r2);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r2 = 4;
        r3 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r8 = r14.v;	 Catch:{ all -> 0x0087 }
        r3.<init>(r8);	 Catch:{ all -> 0x0087 }
        r7[r2] = r3;	 Catch:{ all -> 0x0087 }
        r0.severe(r1, r5, r6, r7);	 Catch:{ all -> 0x0087 }
        r0 = 32000; // 0x7d00 float:4.4842E-41 double:1.581E-319;
        r0 = org.eclipse.paho.client.mqttv3.internal.ExceptionHelper.createMqttException(r0);	 Catch:{ all -> 0x0087 }
        throw r0;	 Catch:{ all -> 0x0087 }
    L_0x0087:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0087 }
        throw r0;
    L_0x008a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x008a }
        throw r0;
    L_0x008d:
        r5 = r14.y;	 Catch:{ all -> 0x0087 }
        if (r5 != 0) goto L_0x00e3;
    L_0x0091:
        r6 = r14.t;	 Catch:{ all -> 0x0087 }
        r6 = r2 - r6;
        r8 = 2;
        r10 = r14.k;	 Catch:{ all -> 0x0087 }
        r8 = r8 * r10;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 < 0) goto L_0x00e3;
    L_0x009e:
        r0 = c;	 Catch:{ all -> 0x0087 }
        r1 = b;	 Catch:{ all -> 0x0087 }
        r5 = "checkForActivity";
        r6 = "642";
        r7 = 5;
        r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0087 }
        r8 = 0;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.k;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 1;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.t;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 2;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r10 = r14.u;	 Catch:{ all -> 0x0087 }
        r9.<init>(r10);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r8 = 3;
        r9 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r9.<init>(r2);	 Catch:{ all -> 0x0087 }
        r7[r8] = r9;	 Catch:{ all -> 0x0087 }
        r2 = 4;
        r3 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r8 = r14.v;	 Catch:{ all -> 0x0087 }
        r3.<init>(r8);	 Catch:{ all -> 0x0087 }
        r7[r2] = r3;	 Catch:{ all -> 0x0087 }
        r0.severe(r1, r5, r6, r7);	 Catch:{ all -> 0x0087 }
        r0 = 32002; // 0x7d02 float:4.4844E-41 double:1.5811E-319;
        r0 = org.eclipse.paho.client.mqttv3.internal.ExceptionHelper.createMqttException(r0);	 Catch:{ all -> 0x0087 }
        throw r0;	 Catch:{ all -> 0x0087 }
    L_0x00e3:
        r5 = r14.y;	 Catch:{ all -> 0x0087 }
        if (r5 != 0) goto L_0x00f3;
    L_0x00e7:
        r6 = r14.u;	 Catch:{ all -> 0x0087 }
        r6 = r2 - r6;
        r8 = r14.k;	 Catch:{ all -> 0x0087 }
        r10 = (long) r1;	 Catch:{ all -> 0x0087 }
        r8 = r8 - r10;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 >= 0) goto L_0x00ff;
    L_0x00f3:
        r6 = r14.t;	 Catch:{ all -> 0x0087 }
        r6 = r2 - r6;
        r8 = r14.k;	 Catch:{ all -> 0x0087 }
        r10 = (long) r1;	 Catch:{ all -> 0x0087 }
        r8 = r8 - r10;
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 < 0) goto L_0x0176;
    L_0x00ff:
        r0 = c;	 Catch:{ all -> 0x0087 }
        r1 = b;	 Catch:{ all -> 0x0087 }
        r2 = "checkForActivity";
        r3 = "620";
        r5 = 3;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x0087 }
        r6 = 0;
        r7 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r8 = r14.k;	 Catch:{ all -> 0x0087 }
        r7.<init>(r8);	 Catch:{ all -> 0x0087 }
        r5[r6] = r7;	 Catch:{ all -> 0x0087 }
        r6 = 1;
        r7 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r8 = r14.t;	 Catch:{ all -> 0x0087 }
        r7.<init>(r8);	 Catch:{ all -> 0x0087 }
        r5[r6] = r7;	 Catch:{ all -> 0x0087 }
        r6 = 2;
        r7 = new java.lang.Long;	 Catch:{ all -> 0x0087 }
        r8 = r14.u;	 Catch:{ all -> 0x0087 }
        r7.<init>(r8);	 Catch:{ all -> 0x0087 }
        r5[r6] = r7;	 Catch:{ all -> 0x0087 }
        r0.fine(r1, r2, r3, r5);	 Catch:{ all -> 0x0087 }
        r2 = new org.eclipse.paho.client.mqttv3.MqttToken;	 Catch:{ all -> 0x0087 }
        r0 = r14.i;	 Catch:{ all -> 0x0087 }
        r0 = r0.getClient();	 Catch:{ all -> 0x0087 }
        r0 = r0.getClientId();	 Catch:{ all -> 0x0087 }
        r2.<init>(r0);	 Catch:{ all -> 0x0087 }
        if (r15 == 0) goto L_0x013f;
    L_0x013c:
        r2.setActionCallback(r15);	 Catch:{ all -> 0x0087 }
    L_0x013f:
        r0 = r14.h;	 Catch:{ all -> 0x0087 }
        r1 = r14.w;	 Catch:{ all -> 0x0087 }
        r0.a(r2, r1);	 Catch:{ all -> 0x0087 }
        r0 = r14.g;	 Catch:{ all -> 0x0087 }
        r1 = r14.w;	 Catch:{ all -> 0x0087 }
        r3 = 0;
        r0.insertElementAt(r1, r3);	 Catch:{ all -> 0x0087 }
        r0 = r14.a();	 Catch:{ all -> 0x0087 }
        r14.notifyQueueLock();	 Catch:{ all -> 0x0087 }
        r12 = r0;
        r0 = r2;
        r2 = r12;
    L_0x0158:
        monitor-exit(r4);	 Catch:{ all -> 0x0087 }
        r1 = c;
        r4 = b;
        r5 = "checkForActivity";
        r6 = "624";
        r7 = 1;
        r7 = new java.lang.Object[r7];
        r8 = 0;
        r9 = new java.lang.Long;
        r9.<init>(r2);
        r7[r8] = r9;
        r1.fine(r4, r5, r6, r7);
        r1 = r14.E;
        r1.schedule(r2);
        goto L_0x0017;
    L_0x0176:
        r1 = c;	 Catch:{ all -> 0x0087 }
        r5 = b;	 Catch:{ all -> 0x0087 }
        r6 = "checkForActivity";
        r7 = "634";
        r8 = 0;
        r1.fine(r5, r6, r7, r8);	 Catch:{ all -> 0x0087 }
        r6 = 1;
        r8 = r14.a();	 Catch:{ all -> 0x0087 }
        r10 = r14.t;	 Catch:{ all -> 0x0087 }
        r2 = r2 - r10;
        r2 = r8 - r2;
        r2 = java.lang.Math.max(r6, r2);	 Catch:{ all -> 0x0087 }
        goto L_0x0158;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.ClientState.checkForActivity(org.eclipse.paho.client.mqttv3.IMqttActionListener):org.eclipse.paho.client.mqttv3.MqttToken");
    }

    protected MqttWireMessage e() throws MqttException {
        synchronized (this.q) {
            MqttWireMessage mqttWireMessage = null;
            while (mqttWireMessage == null) {
                if ((this.f.isEmpty() && this.g.isEmpty()) || (this.g.isEmpty() && this.o >= this.n)) {
                    try {
                        c.fine(b, "get", "644");
                        this.q.wait();
                        c.fine(b, "get", "647");
                    } catch (InterruptedException e) {
                    }
                }
                if (!this.z && (this.g.isEmpty() || !(((MqttWireMessage) this.g.elementAt(0)) instanceof MqttConnect))) {
                    c.fine(b, "get", "621");
                    return null;
                } else if (!this.g.isEmpty()) {
                    r0 = (MqttWireMessage) this.g.remove(0);
                    if (r0 instanceof MqttPubRel) {
                        this.p++;
                        c.fine(b, "get", "617", new Object[]{new Integer(this.p)});
                    }
                    f();
                    mqttWireMessage = r0;
                } else if (!this.f.isEmpty()) {
                    if (this.o < this.n) {
                        r0 = (MqttWireMessage) this.f.elementAt(0);
                        this.f.removeElementAt(0);
                        this.o++;
                        c.fine(b, "get", "623", new Object[]{new Integer(this.o)});
                        mqttWireMessage = r0;
                    } else {
                        c.fine(b, "get", "622");
                    }
                }
            }
            return mqttWireMessage;
        }
    }

    public void setKeepAliveInterval(long j) {
        this.k = j;
    }

    public void notifySentBytes(int i) {
        if (i > 0) {
            this.t = System.currentTimeMillis();
        }
        c.fine(b, "notifySentBytes", "643", new Object[]{new Integer(i)});
    }

    protected void a(MqttWireMessage mqttWireMessage) {
        this.t = System.currentTimeMillis();
        c.fine(b, "notifySent", "625", new Object[]{mqttWireMessage.getKey()});
        MqttToken token = this.h.getToken(mqttWireMessage);
        token.internalTok.d();
        if (mqttWireMessage instanceof MqttPingReq) {
            synchronized (this.x) {
                long currentTimeMillis = System.currentTimeMillis();
                synchronized (this.x) {
                    this.v = currentTimeMillis;
                    this.y++;
                }
                c.fine(b, "notifySent", "635", new Object[]{new Integer(this.y)});
            }
        } else if ((mqttWireMessage instanceof MqttPublish) && ((MqttPublish) mqttWireMessage).getMessage().getQos() == 0) {
            token.internalTok.a(null, null);
            this.j.asyncOperationComplete(token);
            i();
            d(mqttWireMessage.getMessageId());
            this.h.removeToken(mqttWireMessage);
            f();
        }
    }

    private void i() {
        synchronized (this.q) {
            this.o--;
            c.fine(b, "decrementInFlight", "646", new Object[]{new Integer(this.o)});
            if (!f()) {
                this.q.notifyAll();
            }
        }
    }

    protected boolean f() {
        int count = this.h.count();
        if (!this.s || count != 0 || this.g.size() != 0 || !this.j.isQuiesced()) {
            return false;
        }
        c.fine(b, "checkQuiesceLock", "626", new Object[]{new Boolean(this.s), new Integer(this.o), new Integer(this.g.size()), new Integer(this.p), Boolean.valueOf(this.j.isQuiesced()), new Integer(count)});
        synchronized (this.r) {
            this.r.notifyAll();
        }
        return true;
    }

    public void notifyReceivedBytes(int i) {
        if (i > 0) {
            this.u = System.currentTimeMillis();
        }
        c.fine(b, "notifyReceivedBytes", "630", new Object[]{new Integer(i)});
    }

    protected void a(MqttAck mqttAck) throws MqttException {
        this.u = System.currentTimeMillis();
        c.fine(b, "notifyReceivedAck", "627", new Object[]{new Integer(mqttAck.getMessageId()), mqttAck});
        MqttToken token = this.h.getToken((MqttWireMessage) mqttAck);
        if (token == null) {
            c.fine(b, "notifyReceivedAck", "662", new Object[]{new Integer(mqttAck.getMessageId())});
        } else if (mqttAck instanceof MqttPubRec) {
            send(new MqttPubRel((MqttPubRec) mqttAck), token);
        } else if ((mqttAck instanceof MqttPubAck) || (mqttAck instanceof MqttPubComp)) {
            a(mqttAck, token, null);
        } else if (mqttAck instanceof MqttPingResp) {
            synchronized (this.x) {
                this.y = Math.max(0, this.y - 1);
                a(mqttAck, token, null);
                if (this.y == 0) {
                    this.h.removeToken((MqttWireMessage) mqttAck);
                }
            }
            c.fine(b, "notifyReceivedAck", "636", new Object[]{new Integer(this.y)});
        } else if (mqttAck instanceof MqttConnack) {
            int returnCode = ((MqttConnack) mqttAck).getReturnCode();
            if (returnCode == 0) {
                synchronized (this.q) {
                    if (this.l) {
                        c();
                        this.h.a(token, (MqttWireMessage) mqttAck);
                    }
                    this.p = 0;
                    this.o = 0;
                    h();
                    connected();
                }
                this.i.connectComplete((MqttConnack) mqttAck, null);
                a(mqttAck, token, null);
                this.h.removeToken((MqttWireMessage) mqttAck);
                synchronized (this.q) {
                    this.q.notifyAll();
                }
            } else {
                throw ExceptionHelper.createMqttException(returnCode);
            }
        } else {
            a(mqttAck, token, null);
            d(mqttAck.getMessageId());
            this.h.removeToken((MqttWireMessage) mqttAck);
        }
        f();
    }

    protected void b(MqttWireMessage mqttWireMessage) throws MqttException {
        this.u = System.currentTimeMillis();
        c.fine(b, "notifyReceivedMsg", "651", new Object[]{new Integer(mqttWireMessage.getMessageId()), mqttWireMessage});
        if (!this.s) {
            MqttPublish mqttPublish;
            if (mqttWireMessage instanceof MqttPublish) {
                mqttPublish = (MqttPublish) mqttWireMessage;
                switch (mqttPublish.getMessage().getQos()) {
                    case 0:
                    case 1:
                        if (this.j != null) {
                            this.j.messageArrived(mqttPublish);
                            return;
                        }
                        return;
                    case 2:
                        this.m.put(e(mqttWireMessage), (MqttPublish) mqttWireMessage);
                        this.D.put(new Integer(mqttPublish.getMessageId()), mqttPublish);
                        send(new MqttPubRec(mqttPublish), null);
                        return;
                    default:
                        return;
                }
            } else if (mqttWireMessage instanceof MqttPubRel) {
                mqttPublish = (MqttPublish) this.D.get(new Integer(mqttWireMessage.getMessageId()));
                if (mqttPublish == null) {
                    send(new MqttPubComp(mqttWireMessage.getMessageId()), null);
                } else if (this.j != null) {
                    this.j.messageArrived(mqttPublish);
                }
            }
        }
    }

    protected void a(MqttToken mqttToken) throws MqttException {
        MqttWireMessage wireMessage = mqttToken.internalTok.getWireMessage();
        if (wireMessage != null && (wireMessage instanceof MqttAck)) {
            c.fine(b, "notifyComplete", "629", new Object[]{new Integer(wireMessage.getMessageId()), mqttToken, wireMessage});
            MqttAck mqttAck = (MqttAck) wireMessage;
            if (mqttAck instanceof MqttPubAck) {
                this.m.remove(c(wireMessage));
                this.B.remove(new Integer(mqttAck.getMessageId()));
                i();
                d(wireMessage.getMessageId());
                this.h.removeToken(wireMessage);
                c.fine(b, "notifyComplete", "650", new Object[]{new Integer(mqttAck.getMessageId())});
            } else if (mqttAck instanceof MqttPubComp) {
                this.m.remove(c(wireMessage));
                this.m.remove(d(wireMessage));
                this.A.remove(new Integer(mqttAck.getMessageId()));
                this.p--;
                i();
                d(wireMessage.getMessageId());
                this.h.removeToken(wireMessage);
                c.fine(b, "notifyComplete", "645", new Object[]{new Integer(mqttAck.getMessageId()), new Integer(this.p)});
            }
            f();
        }
    }

    protected void a(MqttWireMessage mqttWireMessage, MqttToken mqttToken, MqttException mqttException) {
        mqttToken.internalTok.a(mqttWireMessage, mqttException);
        mqttToken.internalTok.c();
        if (!(mqttWireMessage == null || !(mqttWireMessage instanceof MqttAck) || (mqttWireMessage instanceof MqttPubRec))) {
            c.fine(b, "notifyResult", "648", new Object[]{mqttToken.internalTok.getKey(), mqttWireMessage, mqttException});
            this.j.asyncOperationComplete(mqttToken);
        }
        if (mqttWireMessage == null) {
            c.fine(b, "notifyResult", "649", new Object[]{mqttToken.internalTok.getKey(), mqttException});
            this.j.asyncOperationComplete(mqttToken);
        }
    }

    public void connected() {
        c.fine(b, "connected", "631");
        this.z = true;
        this.E.start();
    }

    public Vector resolveOldTokens(MqttException mqttException) {
        c.fine(b, "resolveOldTokens", "632", new Object[]{mqttException});
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector outstandingTokens = this.h.getOutstandingTokens();
        Enumeration elements = outstandingTokens.elements();
        while (elements.hasMoreElements()) {
            MqttToken mqttToken = (MqttToken) elements.nextElement();
            synchronized (mqttToken) {
                if (!(mqttToken.isComplete() || mqttToken.internalTok.a() || mqttToken.getException() != null)) {
                    mqttToken.internalTok.setException(mqttException);
                }
            }
            if (!(mqttToken instanceof MqttDeliveryToken)) {
                this.h.removeToken(mqttToken.internalTok.getKey());
            }
        }
        return outstandingTokens;
    }

    public void disconnected(MqttException mqttException) {
        c.fine(b, "disconnected", "633", new Object[]{mqttException});
        this.z = false;
        try {
            if (this.l) {
                c();
            }
            this.f.clear();
            this.g.clear();
            synchronized (this.x) {
                this.y = 0;
            }
        } catch (MqttException e) {
        }
    }

    private synchronized void d(int i) {
        this.e.remove(new Integer(i));
    }

    private synchronized int j() throws MqttException {
        int i = this.d;
        int i2 = 0;
        do {
            this.d++;
            if (this.d > SupportMenu.USER_MASK) {
                this.d = 1;
            }
            if (this.d == i) {
                i2++;
                if (i2 == 2) {
                    throw ExceptionHelper.createMqttException(32001);
                }
            }
        } while (this.e.containsKey(new Integer(this.d)));
        Integer num = new Integer(this.d);
        this.e.put(num, num);
        return this.d;
    }

    public void quiesce(long j) {
        if (j > 0) {
            c.fine(b, "quiesce", "637", new Object[]{new Long(j)});
            synchronized (this.q) {
                this.s = true;
            }
            this.j.quiesce();
            notifyQueueLock();
            synchronized (this.r) {
                try {
                    if (this.h.count() > 0 || this.g.size() > 0 || !this.j.isQuiesced()) {
                        c.fine(b, "quiesce", "639", new Object[]{new Integer(this.o), new Integer(this.g.size()), new Integer(this.p), new Integer(r0)});
                        this.r.wait(j);
                    }
                } catch (InterruptedException e) {
                }
            }
            synchronized (this.q) {
                this.f.clear();
                this.g.clear();
                this.s = false;
                this.o = 0;
            }
            c.fine(b, "quiesce", "640");
        }
    }

    public void notifyQueueLock() {
        synchronized (this.q) {
            c.fine(b, "notifyQueueLock", "638");
            this.q.notifyAll();
        }
    }

    protected void b(MqttPublish mqttPublish) throws MqttPersistenceException {
        c.fine(b, "deliveryComplete", "641", new Object[]{new Integer(mqttPublish.getMessageId())});
        this.m.remove(e(mqttPublish));
        this.D.remove(new Integer(mqttPublish.getMessageId()));
    }

    protected void b(int i) throws MqttPersistenceException {
        c.fine(b, "deliveryComplete", "641", new Object[]{new Integer(i)});
        this.m.remove(c(i));
        this.D.remove(new Integer(i));
    }

    public int getActualInFlight() {
        return this.o;
    }

    public int getMaxInFlight() {
        return this.n;
    }

    protected void g() {
        this.e.clear();
        this.f.clear();
        this.g.clear();
        this.A.clear();
        this.B.clear();
        this.C.clear();
        this.D.clear();
        this.h.clear();
        this.e = null;
        this.f = null;
        this.g = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.h = null;
        this.j = null;
        this.i = null;
        this.m = null;
        this.w = null;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("In use msgids", this.e);
        properties.put("pendingMessages", this.f);
        properties.put("pendingFlows", this.g);
        properties.put("maxInflight", new Integer(this.n));
        properties.put("nextMsgID", new Integer(this.d));
        properties.put("actualInFlight", new Integer(this.o));
        properties.put("inFlightPubRels", new Integer(this.p));
        properties.put("quiescing", Boolean.valueOf(this.s));
        properties.put("pingoutstanding", new Integer(this.y));
        properties.put("lastOutboundActivity", new Long(this.t));
        properties.put("lastInboundActivity", new Long(this.u));
        properties.put("outboundQoS2", this.A);
        properties.put("outboundQoS1", this.B);
        properties.put("outboundQoS0", this.C);
        properties.put("inboundQoS2", this.D);
        properties.put("tokens", this.h);
        return properties;
    }
}
