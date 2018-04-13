package org.eclipse.paho.client.mqttv3.internal;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class DisconnectedMessageBuffer implements Runnable {
    private static final Logger a = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, "DisconnectedMessageBuffer");
    private DisconnectedBufferOptions b;
    private ArrayList c;
    private Object d = new Object();
    private IDisconnectedBufferCallback e;

    public DisconnectedMessageBuffer(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.b = disconnectedBufferOptions;
        this.c = new ArrayList();
    }

    public void putMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) throws MqttException {
        BufferedMessage bufferedMessage = new BufferedMessage(mqttWireMessage, mqttToken);
        synchronized (this.d) {
            if (this.c.size() < this.b.getBufferSize()) {
                this.c.add(bufferedMessage);
            } else if (this.b.isDeleteOldestMessages()) {
                this.c.remove(0);
                this.c.add(bufferedMessage);
            } else {
                throw new MqttException(32203);
            }
        }
    }

    public BufferedMessage getMessage(int i) {
        BufferedMessage bufferedMessage;
        synchronized (this.d) {
            bufferedMessage = (BufferedMessage) this.c.get(i);
        }
        return bufferedMessage;
    }

    public void deleteMessage(int i) {
        synchronized (this.d) {
            this.c.remove(i);
        }
    }

    public int getMessageCount() {
        int size;
        synchronized (this.d) {
            size = this.c.size();
        }
        return size;
    }

    public void run() {
        a.fine("DisconnectedMessageBuffer", "run", "516");
        while (getMessageCount() > 0) {
            try {
                this.e.publishBufferedMessage(getMessage(0));
                deleteMessage(0);
            } catch (MqttException e) {
                a.warning("DisconnectedMessageBuffer", "run", "517");
                return;
            }
        }
    }

    public void setPublishCallback(IDisconnectedBufferCallback iDisconnectedBufferCallback) {
        this.e = iDisconnectedBufferCallback;
    }
}
