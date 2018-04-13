package org.eclipse.paho.client.mqttv3.internal;

import com.alipay.sdk.util.h;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsTokenStore {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private Hashtable d;
    private String e;
    private MqttException f = null;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsTokenStore");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public CommsTokenStore(String str) {
        c.setResourceName(str);
        this.d = new Hashtable();
        this.e = str;
        c.fine(b, "<Init>", "308");
    }

    public MqttToken getToken(MqttWireMessage mqttWireMessage) {
        return (MqttToken) this.d.get(mqttWireMessage.getKey());
    }

    public MqttToken getToken(String str) {
        return (MqttToken) this.d.get(str);
    }

    public MqttToken removeToken(MqttWireMessage mqttWireMessage) {
        if (mqttWireMessage != null) {
            return removeToken(mqttWireMessage.getKey());
        }
        return null;
    }

    public MqttToken removeToken(String str) {
        c.fine(b, "removeToken", "306", new Object[]{str});
        if (str != null) {
            return (MqttToken) this.d.remove(str);
        }
        return null;
    }

    protected MqttDeliveryToken a(MqttPublish mqttPublish) {
        MqttDeliveryToken mqttDeliveryToken;
        synchronized (this.d) {
            String num = new Integer(mqttPublish.getMessageId()).toString();
            if (this.d.containsKey(num)) {
                mqttDeliveryToken = (MqttDeliveryToken) this.d.get(num);
                c.fine(b, "restoreToken", "302", new Object[]{num, mqttPublish, mqttDeliveryToken});
            } else {
                mqttDeliveryToken = new MqttDeliveryToken(this.e);
                mqttDeliveryToken.internalTok.setKey(num);
                this.d.put(num, mqttDeliveryToken);
                c.fine(b, "restoreToken", "303", new Object[]{num, mqttPublish, mqttDeliveryToken});
            }
        }
        return mqttDeliveryToken;
    }

    protected void a(MqttToken mqttToken, MqttWireMessage mqttWireMessage) throws MqttException {
        synchronized (this.d) {
            if (this.f == null) {
                c.fine(b, "saveToken", "300", new Object[]{mqttWireMessage.getKey(), mqttWireMessage});
                a(mqttToken, r0);
            } else {
                throw this.f;
            }
        }
    }

    protected void a(MqttToken mqttToken, String str) {
        synchronized (this.d) {
            c.fine(b, "saveToken", "307", new Object[]{str, mqttToken.toString()});
            mqttToken.internalTok.setKey(str);
            this.d.put(str, mqttToken);
        }
    }

    protected void a(MqttException mqttException) {
        synchronized (this.d) {
            c.fine(b, "quiesce", "309", new Object[]{mqttException});
            this.f = mqttException;
        }
    }

    public void open() {
        synchronized (this.d) {
            c.fine(b, "open", "310");
            this.f = null;
        }
    }

    public MqttDeliveryToken[] getOutstandingDelTokens() {
        MqttDeliveryToken[] mqttDeliveryTokenArr;
        synchronized (this.d) {
            c.fine(b, "getOutstandingDelTokens", "311");
            Vector vector = new Vector();
            Enumeration elements = this.d.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (!(mqttToken == null || !(mqttToken instanceof MqttDeliveryToken) || mqttToken.internalTok.isNotified())) {
                    vector.addElement(mqttToken);
                }
            }
            mqttDeliveryTokenArr = (MqttDeliveryToken[]) vector.toArray(new MqttDeliveryToken[vector.size()]);
        }
        return mqttDeliveryTokenArr;
    }

    public Vector getOutstandingTokens() {
        Vector vector;
        synchronized (this.d) {
            c.fine(b, "getOutstandingTokens", "312");
            vector = new Vector();
            Enumeration elements = this.d.elements();
            while (elements.hasMoreElements()) {
                MqttToken mqttToken = (MqttToken) elements.nextElement();
                if (mqttToken != null) {
                    vector.addElement(mqttToken);
                }
            }
        }
        return vector;
    }

    public void clear() {
        c.fine(b, "clear", "305", new Object[]{new Integer(this.d.size())});
        synchronized (this.d) {
            this.d.clear();
        }
    }

    public int count() {
        int size;
        synchronized (this.d) {
            size = this.d.size();
        }
        return size;
    }

    public String toString() {
        String stringBuffer;
        String property = System.getProperty("line.separator", "\n");
        StringBuffer stringBuffer2 = new StringBuffer();
        synchronized (this.d) {
            Enumeration elements = this.d.elements();
            while (elements.hasMoreElements()) {
                stringBuffer2.append(new StringBuffer("{").append(((MqttToken) elements.nextElement()).internalTok).append(h.d).append(property).toString());
            }
            stringBuffer = stringBuffer2.toString();
        }
        return stringBuffer;
    }
}
