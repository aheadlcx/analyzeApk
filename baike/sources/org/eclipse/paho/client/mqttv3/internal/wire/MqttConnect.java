package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttConnect extends MqttWireMessage {
    public static final String KEY = "Con";
    private String c;
    private boolean d;
    private MqttMessage e;
    private String f;
    private char[] g;
    private int h;
    private String i;
    private int j;

    public MqttConnect(byte b, byte[] bArr) throws IOException, MqttException {
        super((byte) 1);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        b(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        this.h = dataInputStream.readUnsignedShort();
        this.c = b(dataInputStream);
        dataInputStream.close();
    }

    public MqttConnect(String str, int i, boolean z, int i2, String str2, char[] cArr, MqttMessage mqttMessage, String str3) {
        super((byte) 1);
        this.c = str;
        this.d = z;
        this.h = i2;
        this.f = str2;
        this.g = cArr;
        this.e = mqttMessage;
        this.i = str3;
        this.j = i;
    }

    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" clientId ").append(this.c).append(" keepAliveInterval ").append(this.h).toString();
    }

    protected byte a() {
        return (byte) 0;
    }

    public boolean isCleanSession() {
        return this.d;
    }

    protected byte[] b() throws MqttException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (this.j == 3) {
                a(dataOutputStream, "MQIsdp");
            } else if (this.j == 4) {
                a(dataOutputStream, "MQTT");
            }
            dataOutputStream.write(this.j);
            int i = 0;
            if (this.d) {
                i = (byte) 2;
            }
            if (this.e != null) {
                i = (byte) (((byte) (i | 4)) | (this.e.getQos() << 3));
                if (this.e.isRetained()) {
                    i = (byte) (i | 32);
                }
            }
            if (this.f != null) {
                i = (byte) (i | 128);
                if (this.g != null) {
                    i = (byte) (i | 64);
                }
            }
            dataOutputStream.write(i);
            dataOutputStream.writeShort(this.h);
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
            a(dataOutputStream, this.c);
            if (this.e != null) {
                a(dataOutputStream, this.i);
                dataOutputStream.writeShort(this.e.getPayload().length);
                dataOutputStream.write(this.e.getPayload());
            }
            if (this.f != null) {
                a(dataOutputStream, this.f);
                if (this.g != null) {
                    a(dataOutputStream, new String(this.g));
                }
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new MqttException(e);
        }
    }

    public boolean isMessageIdRequired() {
        return false;
    }

    public String getKey() {
        return "Con";
    }
}
