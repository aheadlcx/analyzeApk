package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public class BufferedMessage {
    private MqttWireMessage a;
    private MqttToken b;

    public BufferedMessage(MqttWireMessage mqttWireMessage, MqttToken mqttToken) {
        this.a = mqttWireMessage;
        this.b = mqttToken;
    }

    public MqttWireMessage getMessage() {
        return this.a;
    }

    public MqttToken getToken() {
        return this.b;
    }
}
