package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscribe extends MqttWireMessage {
    private String[] c;
    private int[] d;
    private int e;

    public MqttSubscribe(byte b, byte[] bArr) throws IOException {
        int i = 0;
        super((byte) 8);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.a = dataInputStream.readUnsignedShort();
        this.e = 0;
        this.c = new String[10];
        this.d = new int[10];
        while (i == 0) {
            try {
                this.c[this.e] = b(dataInputStream);
                int[] iArr = this.d;
                int i2 = this.e;
                this.e = i2 + 1;
                iArr[i2] = dataInputStream.readByte();
            } catch (Exception e) {
                i = 1;
            }
        }
        dataInputStream.close();
    }

    public MqttSubscribe(String[] strArr, int[] iArr) {
        super((byte) 8);
        this.c = strArr;
        this.d = iArr;
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (int validateQos : iArr) {
            MqttMessage.validateQos(validateQos);
        }
    }

    public String toString() {
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" names:[");
        for (int i2 = 0; i2 < this.e; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append("\"").append(this.c[i2]).append("\"");
        }
        stringBuffer.append("] qos:[");
        while (i < this.e) {
            if (i > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(this.d[i]);
            i++;
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
            for (int i = 0; i < this.c.length; i++) {
                a(dataOutputStream, this.c[i]);
                dataOutputStream.writeByte(this.d[i]);
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
