package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import java.io.InputStream;

public class MultiByteArrayInputStream extends InputStream {
    private byte[] a;
    private int b;
    private int c;
    private byte[] d;
    private int e;
    private int f;
    private int g = 0;

    public MultiByteArrayInputStream(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.a = bArr;
        this.d = bArr2;
        this.b = i;
        this.e = i3;
        this.c = i2;
        this.f = i4;
    }

    public int read() throws IOException {
        int i;
        if (this.g < this.c) {
            i = this.a[this.b + this.g];
        } else if (this.g >= this.c + this.f) {
            return -1;
        } else {
            i = this.d[(this.e + this.g) - this.c];
        }
        if (i < 0) {
            i += 256;
        }
        this.g++;
        return i;
    }
}
