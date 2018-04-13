package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnack extends MqttAck {
    public static final String KEY = "Con";
    private int c;
    private boolean d;

    public MqttConnack(byte b, byte[] bArr) throws IOException {
        boolean z = true;
        super((byte) 2);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        if ((dataInputStream.readUnsignedByte() & 1) != 1) {
            z = false;
        }
        this.d = z;
        this.c = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }

    public int getReturnCode() {
        return this.c;
    }

    protected byte[] b() throws MqttException {
        return new byte[0];
    }

    public boolean isMessageIdRequired() {
        return false;
    }

    public String getKey() {
        return "Con";
    }

    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" session present:").append(this.d).append(" return code: ").append(this.c).toString();
    }

    public boolean getSessionPresent() {
        return this.d;
    }
}
