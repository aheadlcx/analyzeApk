package org.eclipse.paho.client.mqttv3;

public class MqttDeliveryToken extends MqttToken implements IMqttDeliveryToken {
    public MqttDeliveryToken(String str) {
        super(str);
    }

    public MqttMessage getMessage() throws MqttException {
        return this.internalTok.getMessage();
    }

    protected void a(MqttMessage mqttMessage) {
        this.internalTok.setMessage(mqttMessage);
    }
}
