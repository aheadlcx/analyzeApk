package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttPersistable;

public class MqttPersistentData implements MqttPersistable {
    private String a = null;
    private byte[] b = null;
    private int c = 0;
    private int d = 0;
    private byte[] e = null;
    private int f = 0;
    private int g = 0;

    public MqttPersistentData(String str, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.a = str;
        this.b = bArr;
        this.c = i;
        this.d = i2;
        this.e = bArr2;
        this.f = i3;
        this.g = i4;
    }

    public String getKey() {
        return this.a;
    }

    public byte[] getHeaderBytes() {
        return this.b;
    }

    public int getHeaderLength() {
        return this.d;
    }

    public int getHeaderOffset() {
        return this.c;
    }

    public byte[] getPayloadBytes() {
        return this.e;
    }

    public int getPayloadLength() {
        if (this.e == null) {
            return 0;
        }
        return this.g;
    }

    public int getPayloadOffset() {
        return this.f;
    }
}
