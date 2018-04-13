package com.loc;

import java.util.HashMap;
import java.util.Map;

public final class y extends bn {
    private byte[] a;
    private String b = "1";

    public y(byte[] bArr) {
        this.a = (byte[]) bArr.clone();
    }

    public y(byte[] bArr, String str) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    public final Map<String, String> b() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    public final Map<String, String> b_() {
        return null;
    }

    public final String c() {
        String str = u.b;
        Object[] objArr = new Object[5];
        objArr[0] = "1";
        objArr[1] = this.b;
        objArr[2] = "1";
        objArr[3] = "open";
        Object a = t.a(u.a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        objArr[4] = p.a(bArr);
        return String.format(str, objArr);
    }

    public final byte[] d() {
        return this.a;
    }
}
