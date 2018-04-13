package org.eclipse.paho.client.mqttv3;

class a implements MqttCallbackExtended {
    final MqttAsyncClient a;
    private final boolean b;

    a(MqttAsyncClient mqttAsyncClient, boolean z) {
        this.a = mqttAsyncClient;
        this.b = z;
    }

    public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public void connectComplete(boolean z, String str) {
    }

    public void connectionLost(Throwable th) {
        if (this.b) {
            this.a.a.setRestingState(true);
            MqttAsyncClient.a(this.a, true);
            MqttAsyncClient.b(this.a);
        }
    }
}
