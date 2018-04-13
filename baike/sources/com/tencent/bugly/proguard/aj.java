package com.tencent.bugly.proguard;

public final class aj extends k implements Cloneable {
    private static byte[] d;
    private byte a = (byte) 0;
    private String b = "";
    private byte[] c = null;

    public aj(byte b, String str, byte[] bArr) {
        this.a = b;
        this.b = str;
        this.c = bArr;
    }

    public final void a(j jVar) {
        jVar.a(this.a, 0);
        jVar.a(this.b, 1);
        if (this.c != null) {
            jVar.a(this.c, 2);
        }
    }

    public final void a(i iVar) {
        byte[] bArr;
        this.a = iVar.a(this.a, 0, true);
        this.b = iVar.b(1, true);
        if (d == null) {
            bArr = new byte[1];
            d = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = d;
        this.c = iVar.c(2, false);
    }

    public final void a(StringBuilder stringBuilder, int i) {
    }
}
