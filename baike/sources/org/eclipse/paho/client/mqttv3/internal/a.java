package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.BufferedMessage;
import org.eclipse.paho.client.mqttv3.MqttException;

class a implements IDisconnectedBufferCallback {
    final ClientComms a;

    a(ClientComms clientComms) {
        this.a = clientComms;
    }

    public void publishBufferedMessage(BufferedMessage bufferedMessage) throws MqttException {
        if (this.a.isConnected()) {
            while (ClientComms.d(this.a).getActualInFlight() >= ClientComms.d(this.a).getMaxInFlight() - 1) {
                Thread.yield();
            }
            ClientComms.a().fine(ClientComms.b(), "notifyReconnect", "510", new Object[]{bufferedMessage.getMessage().getKey()});
            this.a.a(bufferedMessage.getMessage(), bufferedMessage.getToken());
            ClientComms.d(this.a).unPersistBufferedMessage(bufferedMessage.getMessage());
            return;
        }
        ClientComms.a().fine(ClientComms.b(), "notifyReconnect", "208");
        throw ExceptionHelper.createMqttException(32104);
    }
}
