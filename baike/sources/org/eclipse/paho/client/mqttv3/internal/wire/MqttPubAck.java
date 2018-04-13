package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPubAck extends MqttAck {
    public MqttPubAck(byte b, byte[] bArr) throws IOException {
        super((byte) 4);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.a = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    public MqttPubAck(MqttPublish mqttPublish) {
        super((byte) 4);
        this.a = mqttPublish.getMessageId();
    }

    public MqttPubAck(int i) {
        super((byte) 4);
        this.a = i;
    }

    protected byte[] b() throws MqttException {
        return c();
    }
}
