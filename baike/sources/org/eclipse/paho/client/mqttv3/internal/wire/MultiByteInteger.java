package org.eclipse.paho.client.mqttv3.internal.wire;

public class MultiByteInteger {
    private long a;
    private int b;

    public MultiByteInteger(long j) {
        this(j, -1);
    }

    public MultiByteInteger(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public int getEncodedLength() {
        return this.b;
    }

    public long getValue() {
        return this.a;
    }
}
