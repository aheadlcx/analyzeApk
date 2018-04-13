package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubRec extends MqttAck {
    public MqttPubRec(byte b, byte[] bArr) throws IOException {
        super((byte) 5);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.a = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    public MqttPubRec(MqttPublish mqttPublish) {
        super((byte) 5);
        this.a = mqttPublish.getMessageId();
    }

    protected byte[] b() throws MqttException {
        return c();
    }
}
