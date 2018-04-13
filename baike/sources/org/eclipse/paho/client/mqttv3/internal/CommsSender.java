package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsSender implements Runnable {
    static Class a;
    private static final String b;
    private static final Logger c = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, b);
    private boolean d = false;
    private Object e = new Object();
    private ClientState f = null;
    private MqttOutputStream g;
    private ClientComms h = null;
    private CommsTokenStore i = null;
    private Thread j = null;

    static {
        Class cls = a;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsSender");
                a = cls;
            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        b = cls.getName();
    }

    public CommsSender(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, OutputStream outputStream) {
        this.g = new MqttOutputStream(clientState, outputStream);
        this.h = clientComms;
        this.f = clientState;
        this.i = commsTokenStore;
        c.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str) {
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
            c.fine(b, "stop", "800");
            if (this.d) {
                this.d = false;
                if (!Thread.currentThread().equals(this.j)) {
                    while (this.j.isAlive()) {
                        try {
                            this.f.notifyQueueLock();
                            this.j.join(100);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
            this.j = null;
            c.fine(b, "stop", "801");
        }
    }

    public void run() {
        MqttWireMessage mqttWireMessage = null;
        while (this.d && this.g != null) {
            try {
                mqttWireMessage = this.f.e();
                if (mqttWireMessage != null) {
                    c.fine(b, "run", "802", new Object[]{mqttWireMessage.getKey(), mqttWireMessage});
                    if (mqttWireMessage instanceof MqttAck) {
                        this.g.write(mqttWireMessage);
                        this.g.flush();
                    } else {
                        MqttToken token = this.i.getToken(mqttWireMessage);
                        if (token != null) {
                            synchronized (token) {
                                this.g.write(mqttWireMessage);
                                this.g.flush();
                                this.f.a(mqttWireMessage);
                            }
                        } else {
                            continue;
                        }
                    }
                } else {
                    c.fine(b, "run", "803");
                    this.d = false;
                }
            } catch (IOException e) {
                if (!(mqttWireMessage instanceof MqttDisconnect)) {
                    throw e;
                }
            } catch (Exception e2) {
                a(mqttWireMessage, e2);
            } catch (Exception e22) {
                a(mqttWireMessage, e22);
            }
        }
        c.fine(b, "run", "805");
    }

    private void a(MqttWireMessage mqttWireMessage, Exception exception) {
        c.fine(b, "handleRunException", "804", null, exception);
        if (exception instanceof MqttException) {
            MqttException mqttException = (MqttException) exception;
        } else {
            exception = new MqttException(32109, exception);
        }
        this.d = false;
        this.h.shutdownConnection(null, exception);
    }
}
