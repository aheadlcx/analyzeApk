package org.eclipse.paho.client.mqttv3;

class b implements IMqttActionListener {
    final MqttAsyncClient a;

    b(MqttAsyncClient mqttAsyncClient) {
        this.a = mqttAsyncClient;
    }

    public void onSuccess(IMqttToken iMqttToken) {
        MqttAsyncClient.a().fine(MqttAsyncClient.b(), "attemptReconnect", "501", new Object[]{iMqttToken.getClient().getClientId()});
        this.a.a.setRestingState(false);
        MqttAsyncClient.c(this.a);
    }

    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        MqttAsyncClient.a().fine(MqttAsyncClient.b(), "attemptReconnect", "502", new Object[]{iMqttToken.getClient().getClientId()});
        if (MqttAsyncClient.c() < 128000) {
            MqttAsyncClient.a(MqttAsyncClient.c() * 2);
        }
        MqttAsyncClient.a(this.a, MqttAsyncClient.c());
    }
}
