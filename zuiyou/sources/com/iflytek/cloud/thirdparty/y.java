package com.iflytek.cloud.thirdparty;

public class y implements Cloneable {
    public int a;
    public int b;
    public byte[] c;
    public int d;
    public ce e = null;

    public y(byte[] bArr, ce ceVar) {
        this.c = bArr;
        if (ceVar == null) {
            ceVar = new ce();
        }
        this.e = ceVar;
    }

    public y(byte[] bArr, String str) {
        this.c = bArr;
        this.e = ac.d(str);
    }

    public int a() {
        return this.c == null ? -1 : this.c.length;
    }

    public void a(String str, String str2, boolean z) {
        this.e.a(str, str2, z);
    }

    protected Object clone() {
        Object obj;
        CloneNotSupportedException e;
        try {
            obj = (y) super.clone();
            try {
                obj.c = (byte[]) this.c.clone();
                obj.e = this.e.b();
                obj.d = this.d;
                obj.a = this.a;
                obj.b = this.b;
            } catch (CloneNotSupportedException e2) {
                e = e2;
                e.printStackTrace();
                return obj;
            }
        } catch (CloneNotSupportedException e3) {
            CloneNotSupportedException cloneNotSupportedException = e3;
            obj = null;
            e = cloneNotSupportedException;
            e.printStackTrace();
            return obj;
        }
        return obj;
    }
}
