package com.loc;

import java.util.Locale;

public final class cb implements Comparable<cb> {
    public String a = null;
    public String b = null;
    public byte[] c = null;
    public String d = null;
    public String e = null;
    public int f = 0;
    public int g = 0;
    public String h = null;
    public long i = 0;
    public int j = 0;

    public cb(String str, String str2, byte[] bArr, String str3, int i, int i2, int i3, int i4, long j) {
        this.a = str;
        this.b = str2;
        this.c = bArr;
        this.d = Integer.toHexString(i).trim().toUpperCase(Locale.CHINA);
        if (this.d.length() < 4) {
            this.d += "00000";
            this.d = this.d.substring(0, 4);
        }
        this.e = Integer.toHexString(i2).trim().toUpperCase(Locale.CHINA);
        if (this.e.length() < 4) {
            this.e += "00000";
            this.e = this.e.substring(0, 4);
        }
        this.f = i3;
        this.g = i4;
        this.i = j;
        this.h = str3;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        cb cbVar = (cb) obj;
        return this.g < cbVar.g ? 1 : (this.g == cbVar.g || this.g <= cbVar.g) ? 0 : -1;
    }

    public final String toString() {
        return "name = " + this.b + ",uuid = " + this.a + ",major = " + this.d + ",minor = " + this.e + ",TxPower = " + this.f + ",rssi = " + this.g + ",time = " + this.i;
    }
}
