package org.eclipse.paho.client.mqttv3.internal;

import com.tencent.bugly.Bugly;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttConnack;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSuback;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class Token {
    static Class b;
    private static final String c;
    private static final Logger d = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, c);
    protected MqttMessage a = null;
    private volatile boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private Object h = new Object();
    private Object i = new Object();
    private MqttWireMessage j = null;
    private MqttException k = null;
    private String[] l = null;
    private String m;
    private IMqttAsyncClient n = null;
    private IMqttActionListener o = null;
    private Object p = null;
    private int q = 0;
    private boolean r = false;

    static {
        Class cls = b;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.Token");
                b = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        c = cls.getName();
    }

    public Token(String str) {
        d.setResourceName(str);
    }

    public int getMessageID() {
        return this.q;
    }

    public void setMessageID(int i) {
        this.q = i;
    }

    public boolean checkResult() throws MqttException {
        if (getException() == null) {
            return true;
        }
        throw getException();
    }

    public MqttException getException() {
        return this.k;
    }

    public boolean isComplete() {
        return this.e;
    }

    protected boolean a() {
        return this.f;
    }

    protected boolean b() {
        return (getClient() == null || isComplete()) ? false : true;
    }

    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.o = iMqttActionListener;
    }

    public IMqttActionListener getActionCallback() {
        return this.o;
    }

    public void waitForCompletion() throws MqttException {
        waitForCompletion(-1);
    }

    public void waitForCompletion(long j) throws MqttException {
        d.fine(c, "waitForCompletion", "407", new Object[]{getKey(), new Long(j), this});
        if (a(j) != null || this.e) {
            checkResult();
            return;
        }
        d.fine(c, "waitForCompletion", "406", new Object[]{getKey(), this});
        this.k = new MqttException(32000);
        throw this.k;
    }

    protected MqttWireMessage a(long j) throws MqttException {
        synchronized (this.h) {
            Logger logger = d;
            String str = c;
            String str2 = "waitForResponse";
            String str3 = "400";
            Object[] objArr = new Object[7];
            objArr[0] = getKey();
            objArr[1] = new Long(j);
            objArr[2] = new Boolean(this.g);
            objArr[3] = new Boolean(this.e);
            objArr[4] = this.k == null ? Bugly.SDK_IS_DEV : "true";
            objArr[5] = this.j;
            objArr[6] = this;
            logger.fine(str, str2, str3, objArr, this.k);
            while (!this.e) {
                if (this.k == null) {
                    try {
                        d.fine(c, "waitForResponse", "408", new Object[]{getKey(), new Long(j)});
                        if (j <= 0) {
                            this.h.wait();
                        } else {
                            this.h.wait(j);
                        }
                    } catch (Throwable e) {
                        this.k = new MqttException(e);
                    }
                }
                if (!this.e) {
                    if (this.k != null) {
                        d.fine(c, "waitForResponse", "401", null, this.k);
                        throw this.k;
                    } else if (j > 0) {
                        break;
                    }
                }
            }
        }
        d.fine(c, "waitForResponse", "402", new Object[]{getKey(), this.j});
        return this.j;
    }

    protected void a(MqttWireMessage mqttWireMessage, MqttException mqttException) {
        d.fine(c, "markComplete", "404", new Object[]{getKey(), mqttWireMessage, mqttException});
        synchronized (this.h) {
            if (mqttWireMessage instanceof MqttAck) {
                this.a = null;
            }
            this.f = true;
            this.j = mqttWireMessage;
            this.k = mqttException;
        }
    }

    protected void c() {
        d.fine(c, "notifyComplete", "404", new Object[]{getKey(), this.j, this.k});
        synchronized (this.h) {
            if (this.k == null && this.f) {
                this.e = true;
                this.f = false;
            } else {
                this.f = false;
            }
            this.h.notifyAll();
        }
        synchronized (this.i) {
            this.g = true;
            this.i.notifyAll();
        }
    }

    public void waitUntilSent() throws MqttException {
        synchronized (this.i) {
            synchronized (this.h) {
                if (this.k != null) {
                    throw this.k;
                }
            }
            while (!this.g) {
                try {
                    d.fine(c, "waitUntilSent", "409", new Object[]{getKey()});
                    this.i.wait();
                } catch (InterruptedException e) {
                }
            }
            if (this.g) {
            } else if (this.k == null) {
                throw ExceptionHelper.createMqttException(6);
            } else {
                throw this.k;
            }
        }
    }

    protected void d() {
        d.fine(c, "notifySent", "403", new Object[]{getKey()});
        synchronized (this.h) {
            this.j = null;
            this.e = false;
        }
        synchronized (this.i) {
            this.g = true;
            this.i.notifyAll();
        }
    }

    public IMqttAsyncClient getClient() {
        return this.n;
    }

    protected void a(IMqttAsyncClient iMqttAsyncClient) {
        this.n = iMqttAsyncClient;
    }

    public void reset() throws MqttException {
        if (b()) {
            throw new MqttException(32201);
        }
        d.fine(c, "reset", "410", new Object[]{getKey()});
        this.n = null;
        this.e = false;
        this.j = null;
        this.g = false;
        this.k = null;
        this.p = null;
    }

    public MqttMessage getMessage() {
        return this.a;
    }

    public MqttWireMessage getWireMessage() {
        return this.j;
    }

    public void setMessage(MqttMessage mqttMessage) {
        this.a = mqttMessage;
    }

    public String[] getTopics() {
        return this.l;
    }

    public void setTopics(String[] strArr) {
        this.l = strArr;
    }

    public Object getUserContext() {
        return this.p;
    }

    public void setUserContext(Object obj) {
        this.p = obj;
    }

    public void setKey(String str) {
        this.m = str;
    }

    public String getKey() {
        return this.m;
    }

    public void setException(MqttException mqttException) {
        synchronized (this.h) {
            this.k = mqttException;
        }
    }

    public boolean isNotified() {
        return this.r;
    }

    public void setNotified(boolean z) {
        this.r = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(getKey());
        stringBuffer.append(" ,topics=");
        if (getTopics() != null) {
            for (String append : getTopics()) {
                stringBuffer.append(append).append(", ");
            }
        }
        stringBuffer.append(" ,usercontext=").append(getUserContext());
        stringBuffer.append(" ,isComplete=").append(isComplete());
        stringBuffer.append(" ,isNotified=").append(isNotified());
        stringBuffer.append(" ,exception=").append(getException());
        stringBuffer.append(" ,actioncallback=").append(getActionCallback());
        return stringBuffer.toString();
    }

    public int[] getGrantedQos() {
        int[] iArr = new int[0];
        if (this.j instanceof MqttSuback) {
            return ((MqttSuback) this.j).getGrantedQos();
        }
        return iArr;
    }

    public boolean getSessionPresent() {
        if (this.j instanceof MqttConnack) {
            return ((MqttConnack) this.j).getSessionPresent();
        }
        return false;
    }

    public MqttWireMessage getResponse() {
        return this.j;
    }
}
