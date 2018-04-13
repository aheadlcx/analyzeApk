package org.eclipse.paho.client.mqttv3.internal;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsCallback implements Runnable {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private MqttCallback d;
    private MqttCallbackExtended e;
    private Hashtable f;
    private ClientComms g;
    private Vector h;
    private Vector i;
    private boolean j = false;
    private Object k = new Object();
    private Thread l;
    private Object m = new Object();
    private Object n = new Object();
    private ClientState o;
    private boolean p = false;
    public boolean running = false;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsCallback");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    CommsCallback(ClientComms clientComms) {
        this.g = clientComms;
        this.h = new Vector(10);
        this.i = new Vector(10);
        this.f = new Hashtable();
        c.setResourceName(clientComms.getClient().getClientId());
    }

    public void setClientState(ClientState clientState) {
        this.o = clientState;
    }

    public void start(String str) {
        synchronized (this.k) {
            if (!this.running) {
                this.h.clear();
                this.i.clear();
                this.running = true;
                this.j = false;
                this.l = new Thread(this, str);
                this.l.start();
            }
        }
    }

    public void stop() {
        synchronized (this.k) {
            if (this.running) {
                c.fine(b, "stop", "700");
                this.running = false;
                if (!Thread.currentThread().equals(this.l)) {
                    try {
                        synchronized (this.m) {
                            c.fine(b, "stop", "701");
                            this.m.notifyAll();
                        }
                        this.l.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
            this.l = null;
            c.fine(b, "stop", "703");
        }
    }

    public void setCallback(MqttCallback mqttCallback) {
        this.d = mqttCallback;
    }

    public void setReconnectCallback(MqttCallbackExtended mqttCallbackExtended) {
        this.e = mqttCallbackExtended;
    }

    public void setManualAcks(boolean z) {
        this.p = z;
    }

    public void run() {
        while (this.running) {
            try {
                synchronized (this.m) {
                    if (this.running && this.h.isEmpty() && this.i.isEmpty()) {
                        c.fine(b, "run", "704");
                        this.m.wait();
                    }
                }
            } catch (InterruptedException e) {
            }
            try {
                if (this.running) {
                    MqttToken mqttToken;
                    MqttPublish mqttPublish;
                    synchronized (this.i) {
                        if (this.i.isEmpty()) {
                            mqttToken = null;
                        } else {
                            mqttToken = (MqttToken) this.i.elementAt(0);
                            this.i.removeElementAt(0);
                        }
                    }
                    if (mqttToken != null) {
                        a(mqttToken);
                    }
                    synchronized (this.h) {
                        if (this.h.isEmpty()) {
                            mqttPublish = null;
                        } else {
                            mqttPublish = (MqttPublish) this.h.elementAt(0);
                            this.h.removeElementAt(0);
                        }
                    }
                    if (mqttPublish != null) {
                        a(mqttPublish);
                    }
                }
                if (this.j) {
                    this.o.f();
                }
            } catch (Throwable th) {
                try {
                    c.fine(b, "run", "714", null, th);
                    this.running = false;
                    this.g.shutdownConnection(null, new MqttException(th));
                } catch (Throwable th2) {
                    synchronized (this.n) {
                        c.fine(b, "run", "706");
                        this.n.notifyAll();
                    }
                }
            }
            synchronized (this.n) {
                c.fine(b, "run", "706");
                this.n.notifyAll();
            }
        }
    }

    private void a(MqttToken mqttToken) throws MqttException {
        synchronized (mqttToken) {
            c.fine(b, "handleActionComplete", "705", new Object[]{mqttToken.internalTok.getKey()});
            if (mqttToken.isComplete()) {
                this.o.a(mqttToken);
            }
            mqttToken.internalTok.c();
            if (!mqttToken.internalTok.isNotified()) {
                if (this.d != null && (mqttToken instanceof MqttDeliveryToken) && mqttToken.isComplete()) {
                    this.d.deliveryComplete((MqttDeliveryToken) mqttToken);
                }
                fireActionEvent(mqttToken);
            }
            if (mqttToken.isComplete() && ((mqttToken instanceof MqttDeliveryToken) || (mqttToken.getActionCallback() instanceof IMqttActionListener))) {
                mqttToken.internalTok.setNotified(true);
            }
        }
    }

    public void connectionLost(MqttException mqttException) {
        try {
            if (!(this.d == null || mqttException == null)) {
                c.fine(b, "connectionLost", "708", new Object[]{mqttException});
                this.d.connectionLost(mqttException);
            }
            if (this.e != null && mqttException != null) {
                this.e.connectionLost(mqttException);
            }
        } catch (Throwable th) {
            c.fine(b, "connectionLost", "720", new Object[]{th});
        }
    }

    public void fireActionEvent(MqttToken mqttToken) {
        if (mqttToken != null) {
            IMqttActionListener actionCallback = mqttToken.getActionCallback();
            if (actionCallback == null) {
                return;
            }
            if (mqttToken.getException() == null) {
                c.fine(b, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
                actionCallback.onSuccess(mqttToken);
                return;
            }
            c.fine(b, "fireActionEvent", "716", new Object[]{mqttToken.internalTok.getKey()});
            actionCallback.onFailure(mqttToken, mqttToken.getException());
        }
    }

    public void messageArrived(MqttPublish mqttPublish) {
        if (this.d != null || this.f.size() > 0) {
            synchronized (this.n) {
                while (this.running && !this.j && this.h.size() >= 10) {
                    try {
                        c.fine(b, "messageArrived", "709");
                        this.n.wait(200);
                    } catch (InterruptedException e) {
                    }
                }
            }
            if (!this.j) {
                this.h.addElement(mqttPublish);
                synchronized (this.m) {
                    c.fine(b, "messageArrived", "710");
                    this.m.notifyAll();
                }
            }
        }
    }

    public void quiesce() {
        this.j = true;
        synchronized (this.n) {
            c.fine(b, "quiesce", "711");
            this.n.notifyAll();
        }
    }

    public boolean isQuiesced() {
        if (this.j && this.i.size() == 0 && this.h.size() == 0) {
            return true;
        }
        return false;
    }

    private void a(MqttPublish mqttPublish) throws MqttException, Exception {
        c.fine(b, "handleMessage", "713", new Object[]{new Integer(mqttPublish.getMessageId()), mqttPublish.getTopicName()});
        a(r0, mqttPublish.getMessageId(), mqttPublish.getMessage());
        if (!this.p) {
            if (mqttPublish.getMessage().getQos() == 1) {
                this.g.a(new MqttPubAck(mqttPublish), new MqttToken(this.g.getClient().getClientId()));
            } else if (mqttPublish.getMessage().getQos() == 2) {
                this.g.a(mqttPublish);
                this.g.a(new MqttPubComp(mqttPublish), new MqttToken(this.g.getClient().getClientId()));
            }
        }
    }

    public void messageArrivedComplete(int i, int i2) throws MqttException {
        if (i2 == 1) {
            this.g.a(new MqttPubAck(i), new MqttToken(this.g.getClient().getClientId()));
        } else if (i2 == 2) {
            this.g.a(i);
            this.g.a(new MqttPubComp(i), new MqttToken(this.g.getClient().getClientId()));
        }
    }

    public void asyncOperationComplete(MqttToken mqttToken) {
        if (this.running) {
            this.i.addElement(mqttToken);
            synchronized (this.m) {
                c.fine(b, "asyncOperationComplete", "715", new Object[]{mqttToken.internalTok.getKey()});
                this.m.notifyAll();
            }
            return;
        }
        try {
            a(mqttToken);
        } catch (Throwable th) {
            c.fine(b, "asyncOperationComplete", "719", null, th);
            this.g.shutdownConnection(null, new MqttException(th));
        }
    }

    protected Thread a() {
        return this.l;
    }

    public void setMessageListener(String str, IMqttMessageListener iMqttMessageListener) {
        this.f.put(str, iMqttMessageListener);
    }

    public void removeMessageListener(String str) {
        this.f.remove(str);
    }

    public void removeMessageListeners() {
        this.f.clear();
    }

    protected boolean a(String str, int i, MqttMessage mqttMessage) throws Exception {
        Enumeration keys = this.f.keys();
        boolean z = false;
        while (keys.hasMoreElements()) {
            String str2 = (String) keys.nextElement();
            if (MqttTopic.isMatched(str2, str)) {
                mqttMessage.setId(i);
                ((IMqttMessageListener) this.f.get(str2)).messageArrived(str, mqttMessage);
                z = true;
            }
        }
        if (this.d == null || z) {
            return z;
        }
        mqttMessage.setId(i);
        this.d.messageArrived(str, mqttMessage);
        return true;
    }
}
