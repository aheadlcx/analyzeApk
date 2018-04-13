package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttUnsubscribe extends MqttWireMessage {
    private String[] c;
    private int d;

    public MqttUnsubscribe(String[] strArr) {
        super((byte) 10);
        this.c = strArr;
    }

    public MqttUnsubscribe(byte b, byte[] bArr) throws IOException {
        int i = 0;
        super((byte) 10);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.a = dataInputStream.readUnsignedShort();
        this.d = 0;
        this.c = new String[10];
        while (i == 0) {
            try {
                this.c[this.d] = b(dataInputStream);
            } catch (Exception e) {
                i = 1;
            }
        }
        dataInputStream.close();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i = 0; i < this.d; i++) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(new StringBuffer("\"").append(this.c[i]).append("\"").toString());
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    protected byte a() {
        return (byte) ((this.b ? 8 : 0) | 2);
    }

    protected byte[] b() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(this.a);
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public byte[] getPayload() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            for (String a : this.c) {
                a(dataOutputStream, a);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isRetryable() {
        return true;
    }
}
