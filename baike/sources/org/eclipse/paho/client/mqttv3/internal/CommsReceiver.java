package org.eclipse.paho.client.mqttv3.internal;

import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsReceiver implements Runnable {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private boolean d = false;
    private Object e = new Object();
    private ClientState f = null;
    private ClientComms g = null;
    private MqttInputStream h;
    private CommsTokenStore i = null;
    private Thread j = null;
    private volatile boolean k;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsReceiver");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        this.h = new MqttInputStream(clientState, inputStream);
        this.g = clientComms;
        this.f = clientState;
        this.i = commsTokenStore;
        c.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str) {
        c.fine(b, "start", "855");
        synchronized (this.e) {
            if (!this.d) {
                this.d = true;
                this.j = new Thread(this, str);
                this.j.start();
            }
        }
    }

    public void stop() {
        synchronized (this.e) {
            c.fine(b, "stop", "850");
            if (this.d) {
                this.d = false;
                this.k = false;
                if (!Thread.currentThread().equals(this.j)) {
                    try {
                        this.j.join();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        this.j = null;
        c.fine(b, "stop", "851");
    }

    public void run() {
        MqttToken mqttToken = null;
        while (this.d && this.h != null) {
            MqttToken mqttToken2;
            try {
                c.fine(b, "run", "852");
                this.k = this.h.available() > 0;
                MqttWireMessage readMqttWireMessage = this.h.readMqttWireMessage();
                this.k = false;
                if (readMqttWireMessage instanceof MqttAck) {
                    mqttToken = this.i.getToken(readMqttWireMessage);
                    if (mqttToken != null) {
                        synchronized (mqttToken) {
                            this.f.a((MqttAck) readMqttWireMessage);
                        }
                        mqttToken2 = mqttToken;
                        this.k = false;
                        mqttToken = mqttToken2;
                    } else {
                        throw new MqttException(6);
                    }
                }
                this.f.b(readMqttWireMessage);
                mqttToken2 = mqttToken;
                this.k = false;
                mqttToken = mqttToken2;
            } catch (Throwable e) {
                MqttToken mqttToken3 = mqttToken;
                c.fine(b, "run", "856", null, e);
                this.d = false;
                this.g.shutdownConnection(mqttToken3, e);
                mqttToken2 = mqttToken3;
            } catch (Throwable e2) {
                try {
                    c.fine(b, "run", "853");
                    this.d = false;
                    if (this.g.isDisconnecting()) {
                        mqttToken2 = mqttToken;
                    } else {
                        this.g.shutdownConnection(mqttToken, new MqttException(32109, e2));
                        mqttToken2 = mqttToken;
                    }
                } catch (Throwable th) {
                    this.k = false;
                }
            }
        }
        c.fine(b, "run", "854");
    }

    public boolean isRunning() {
        return this.d;
    }

    public boolean isReceiving() {
        return this.k;
    }
}
