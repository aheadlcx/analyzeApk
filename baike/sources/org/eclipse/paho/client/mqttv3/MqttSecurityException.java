package org.eclipse.paho.client.mqttv3;

public class MqttSecurityException extends MqttException {
    public MqttSecurityException(int i) {
        super(i);
    }

    public MqttSecurityException(Throwable th) {
        super(th);
    }

    public MqttSecurityException(int i, Throwable th) {
        super(i, th);
    }
}
