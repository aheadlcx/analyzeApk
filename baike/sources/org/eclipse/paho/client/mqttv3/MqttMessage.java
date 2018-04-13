package org.eclipse.paho.client.mqttv3;

public class MqttMessage {
    private boolean a = true;
    private byte[] b;
    private int c = 1;
    private boolean d = false;
    private boolean e = false;
    private int f;

    public static void validateQos(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException();
        }
    }

    public MqttMessage() {
        setPayload(new byte[0]);
    }

    public MqttMessage(byte[] bArr) {
        setPayload(bArr);
    }

    public byte[] getPayload() {
        return this.b;
    }

    public void clearPayload() {
        a();
        this.b = new byte[0];
    }

    public void setPayload(byte[] bArr) {
        a();
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.b = bArr;
    }

    public boolean isRetained() {
        return this.d;
    }

    public void setRetained(boolean z) {
        a();
        this.d = z;
    }

    public int getQos() {
        return this.c;
    }

    public void setQos(int i) {
        a();
        validateQos(i);
        this.c = i;
    }

    public String toString() {
        return new String(this.b);
    }

    protected void a(boolean z) {
        this.a = z;
    }

    protected void a() throws IllegalStateException {
        if (!this.a) {
            throw new IllegalStateException();
        }
    }

    protected void setDuplicate(boolean z) {
        this.e = z;
    }

    public boolean isDuplicate() {
        return this.e;
    }

    public void setId(int i) {
        this.f = i;
    }

    public int getId() {
        return this.f;
    }
}
