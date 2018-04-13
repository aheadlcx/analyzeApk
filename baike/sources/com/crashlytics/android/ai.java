package com.crashlytics.android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

final class ai {
    private final byte[] a;
    private volatile int b = 0;

    private ai(byte[] bArr) {
        this.a = bArr;
    }

    public final int a() {
        return this.a.length;
    }

    static {
        ai aiVar = new ai(new byte[0]);
    }

    public static ai a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, 0, obj, 0, i2);
        return new ai(obj);
    }

    public static ai a(String str) {
        try {
            return new ai(str.getBytes("UTF-8"));
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported.", e);
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.a, i, bArr, i2, i3);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ai)) {
            return false;
        }
        ai aiVar = (ai) obj;
        int length = this.a.length;
        if (length != aiVar.a.length) {
            return false;
        }
        byte[] bArr = this.a;
        byte[] bArr2 = aiVar.a;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = this.b;
        if (i == 0) {
            byte[] bArr = this.a;
            int length = this.a.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
            if (i == 0) {
                i = 1;
            }
            this.b = i;
        }
        return i;
    }

    public final InputStream b() {
        return new ByteArrayInputStream(this.a);
    }
}
