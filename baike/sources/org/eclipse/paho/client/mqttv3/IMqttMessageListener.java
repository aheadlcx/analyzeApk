package org.eclipse.paho.client.mqttv3;

public interface IMqttMessageListener {
    void messageArrived(String str, MqttMessage mqttMessage) throws Exception;
}
