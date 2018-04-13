package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.zip.CRC32;

@SuppressLint({"NewApi"})
public final class cu {
    public static String K = null;
    public String A = null;
    public String B = null;
    public ArrayList<ce> C = new ArrayList();
    public String D = null;
    public String E = null;
    public ArrayList<ScanResult> F = new ArrayList();
    public ArrayList<cb> G = new ArrayList();
    public String H = null;
    public String I = null;
    public byte[] J = null;
    public String L = null;
    private byte[] M = null;
    private int N = 0;
    public String a = "1";
    public short b = (short) 0;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public String p = null;
    public String q = null;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;
    public String x = null;
    public String y = null;
    public int z = 0;

    private String a(String str, int i) {
        String[] split = this.B.split("\\*")[i].split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        return str.equals("lac") ? split[0] : str.equals("cellid") ? split[1] : str.equals("signal") ? split[2] : null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] a(java.lang.String r8) {
        /*
        r7 = this;
        r6 = 2;
        r4 = 6;
        r2 = 0;
        r0 = ":";
        r0 = r8.split(r0);
        r1 = new byte[r4];
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 == r4) goto L_0x001e;
    L_0x0010:
        r0 = 6;
        r0 = new java.lang.String[r0];	 Catch:{ Throwable -> 0x0043 }
        r3 = r2;
    L_0x0014:
        r4 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r3 >= r4) goto L_0x001e;
    L_0x0017:
        r4 = "0";
        r0[r3] = r4;	 Catch:{ Throwable -> 0x0043 }
        r3 = r3 + 1;
        goto L_0x0014;
    L_0x001e:
        r3 = r0.length;	 Catch:{ Throwable -> 0x0043 }
        if (r2 >= r3) goto L_0x0041;
    L_0x0021:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r3 = r3.length();	 Catch:{ Throwable -> 0x0043 }
        if (r3 <= r6) goto L_0x0033;
    L_0x0029:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 0;
        r5 = 2;
        r3 = r3.substring(r4, r5);	 Catch:{ Throwable -> 0x0043 }
        r0[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
    L_0x0033:
        r3 = r0[r2];	 Catch:{ Throwable -> 0x0043 }
        r4 = 16;
        r3 = java.lang.Integer.parseInt(r3, r4);	 Catch:{ Throwable -> 0x0043 }
        r3 = (byte) r3;	 Catch:{ Throwable -> 0x0043 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0043 }
        r2 = r2 + 1;
        goto L_0x001e;
    L_0x0041:
        r0 = r1;
    L_0x0042:
        return r0;
    L_0x0043:
        r0 = move-exception;
        r1 = "Req";
        r2 = new java.lang.StringBuilder;
        r3 = "getMacBa ";
        r2.<init>(r3);
        r2 = r2.append(r8);
        r2 = r2.toString();
        com.loc.cw.a(r0, r1, r2);
        r0 = "00:00:00:00:00:00";
        r0 = r7.a(r0);
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cu.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        if (!this.A.contains(str + ">")) {
            return "0";
        }
        int indexOf = this.A.indexOf(str + ">");
        return this.A.substring((indexOf + str.length()) + 1, this.A.indexOf("</" + str));
    }

    public final void a(Context context, boolean z, boolean z2, cf cfVar, ci ciVar, ConnectivityManager connectivityManager, cc ccVar, String str, String str2, String str3) {
        String str4;
        int i;
        String str5 = "0";
        String str6 = "0";
        String str7 = "0";
        String str8 = "0";
        String str9 = "0";
        String f = k.f(context);
        int f2 = de.f();
        String str10 = "";
        String str11 = "";
        String str12 = "";
        this.L = str3;
        String str13 = "api_serverSDK_130905";
        String str14 = "S128DF1572465B890OE3F7A13167KLEI";
        if (z2) {
            str4 = str13;
            str13 = str14;
        } else {
            str4 = "UC_nlp_20131029";
            str13 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        int d = cfVar.d();
        int e = cfVar.e();
        TelephonyManager f3 = cfVar.f();
        ArrayList a = cf.a();
        ArrayList b = cf.b();
        ArrayList b2 = ciVar.b();
        String str15 = e == 2 ? "1" : str5;
        if (f3 != null) {
            if (TextUtils.isEmpty(cw.c)) {
                try {
                    cw.c = n.q(context);
                } catch (Throwable th) {
                    cw.a(th, "APS", "getApsReq part4");
                }
            }
            if (TextUtils.isEmpty(cw.c)) {
                cw.c = "888888888888888";
            }
            if (TextUtils.isEmpty(cw.d)) {
                try {
                    cw.d = f3.getSubscriberId();
                } catch (Throwable th2) {
                    cw.a(th2, "APS", "getApsReq part2");
                }
            }
            if (TextUtils.isEmpty(cw.d)) {
                cw.d = "888888888888888";
            }
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Throwable th3) {
            cw.a(th3, "APS", "getApsReq part");
        }
        WifiInfo g = ciVar.g();
        boolean a2 = ci.a(g);
        if (de.a(networkInfo) != -1) {
            str11 = de.b(f3);
            if (a2 && ciVar.f()) {
                str10 = str11;
                str11 = "2";
            } else {
                str10 = str11;
                str11 = "1";
            }
        }
        if (a.isEmpty()) {
            str5 = str12;
        } else {
            StringBuilder stringBuilder3 = new StringBuilder();
            ce ceVar;
            switch (e) {
                case 1:
                    ceVar = (ce) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(ceVar.a).append("</mcc>");
                    stringBuilder3.append("<mnc>").append(ceVar.b).append("</mnc>");
                    stringBuilder3.append("<lac>").append(ceVar.c).append("</lac>");
                    stringBuilder3.append("<cellid>").append(ceVar.d);
                    stringBuilder3.append("</cellid>");
                    stringBuilder3.append("<signal>").append(ceVar.j);
                    stringBuilder3.append("</signal>");
                    str5 = stringBuilder3.toString();
                    for (i = 1; i < a.size(); i++) {
                        ceVar = (ce) a.get(i);
                        stringBuilder.append(ceVar.c).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        stringBuilder.append(ceVar.d).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                        stringBuilder.append(ceVar.j);
                        if (i < a.size() - 1) {
                            stringBuilder.append("*");
                        }
                    }
                    str14 = str5;
                    break;
                case 2:
                    ceVar = (ce) a.get(0);
                    stringBuilder3.delete(0, stringBuilder3.length());
                    stringBuilder3.append("<mcc>").append(ceVar.a).append("</mcc>");
                    stringBuilder3.append("<sid>").append(ceVar.g).append("</sid>");
                    stringBuilder3.append("<nid>").append(ceVar.h).append("</nid>");
                    stringBuilder3.append("<bid>").append(ceVar.i).append("</bid>");
                    if (ceVar.f > 0 && ceVar.e > 0) {
                        stringBuilder3.append("<lon>").append(ceVar.f).append("</lon>");
                        stringBuilder3.append("<lat>").append(ceVar.e).append("</lat>");
                    }
                    stringBuilder3.append("<signal>").append(ceVar.j).append("</signal>");
                    str14 = stringBuilder3.toString();
                    break;
                default:
                    str14 = str12;
                    break;
            }
            stringBuilder3.delete(0, stringBuilder3.length());
            str5 = str14;
        }
        if ((d & 4) != 4 || b.isEmpty()) {
            this.C.clear();
        } else {
            this.C.clear();
            this.C.addAll(b);
        }
        if (ciVar.f()) {
            if (a2) {
                stringBuilder2.append(ciVar.g().getBSSID()).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                int rssi = ciVar.g().getRssi();
                if (rssi < -128) {
                    rssi = 0;
                } else if (rssi > 127) {
                    rssi = 0;
                }
                stringBuilder2.append(rssi).append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                str14 = g.getSSID();
                i = 32;
                try {
                    i = g.getSSID().getBytes("UTF-8").length;
                } catch (Exception e2) {
                }
                if (i >= 32) {
                    str14 = "unkwn";
                }
                stringBuilder2.append(str14.replace("*", "."));
            }
            if (!(b2 == null || this.F == null)) {
                this.F.clear();
                this.F.addAll(b2);
            }
        } else {
            ciVar.c();
            if (this.F != null) {
                this.F.clear();
            }
        }
        if (z) {
            this.b = (short) 0;
        } else {
            this.b = (short) 2;
        }
        this.c = str4;
        this.d = str13;
        this.f = de.d();
        this.g = "android" + de.e();
        this.h = de.b(context);
        this.i = str15;
        this.j = str6;
        this.k = "0";
        this.l = str7;
        this.m = str8;
        this.n = str9;
        this.o = f;
        this.p = cw.c;
        this.q = cw.d;
        this.s = String.valueOf(f2);
        this.t = str;
        this.v = "3.4.0";
        this.w = str2;
        this.u = "";
        this.x = str10;
        this.y = str11;
        this.z = d;
        this.A = str5;
        this.B = stringBuilder.toString();
        this.D = cfVar.i();
        this.H = ci.k();
        this.E = stringBuilder2.toString();
        if (ccVar != null) {
            this.G = ccVar.c();
        }
        try {
            if (TextUtils.isEmpty(K)) {
                K = n.f(context);
            }
        } catch (Throwable th4) {
        }
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder2.delete(0, stringBuilder2.length());
    }

    public final byte[] a() {
        byte[] bArr;
        Object e;
        int length;
        Object e2;
        int length2;
        ArrayList arrayList;
        int i;
        Object a;
        String[] split;
        ArrayList arrayList2;
        int min;
        Object obj;
        long j;
        int i2;
        ScanResult scanResult;
        Object a2;
        boolean isEmpty;
        ArrayList arrayList3;
        byte[] bArr2;
        cb cbVar;
        byte[] bArr3;
        CRC32 crc32;
        Object a3;
        if (TextUtils.isEmpty(this.a)) {
            this.a = "";
        }
        if (TextUtils.isEmpty(this.c)) {
            this.c = "";
        }
        if (TextUtils.isEmpty(this.d)) {
            this.d = "";
        }
        if (TextUtils.isEmpty(this.e)) {
            this.e = "";
        }
        if (TextUtils.isEmpty(this.f)) {
            this.f = "";
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = "";
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = "";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = "";
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "0";
        } else if (!(this.j.equals("1") || this.j.equals("2"))) {
            this.j = "0";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "0";
        } else if (!(this.k.equals("0") || this.k.equals("1"))) {
            this.k = "0";
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = "";
        }
        if (TextUtils.isEmpty(this.m)) {
            this.m = "";
        }
        if (TextUtils.isEmpty(this.n)) {
            this.n = "";
        }
        if (TextUtils.isEmpty(this.o)) {
            this.o = "";
        }
        if (TextUtils.isEmpty(this.p)) {
            this.p = "";
        }
        if (TextUtils.isEmpty(this.q)) {
            this.q = "";
        }
        if (TextUtils.isEmpty(this.r)) {
            this.r = "";
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = "";
        }
        if (TextUtils.isEmpty(this.t)) {
            this.t = "";
        }
        if (TextUtils.isEmpty(this.u)) {
            this.u = "";
        }
        if (TextUtils.isEmpty(this.v)) {
            this.v = "";
        }
        if (TextUtils.isEmpty(this.w)) {
            this.w = "";
        }
        if (TextUtils.isEmpty(this.x)) {
            this.x = "";
        }
        if (TextUtils.isEmpty(this.y)) {
            this.y = "0";
        } else if (!(this.y.equals("1") || this.y.equals("2"))) {
            this.y = "0";
        }
        if (!cf.a(this.z)) {
            this.z = 0;
        }
        if (TextUtils.isEmpty(this.A)) {
            this.A = "";
        }
        if (TextUtils.isEmpty(this.B)) {
            this.B = "";
        }
        if (TextUtils.isEmpty(this.E)) {
            this.E = "";
        }
        if (TextUtils.isEmpty(this.H)) {
            this.H = "";
        }
        if (TextUtils.isEmpty(this.I)) {
            this.I = "";
        }
        if (TextUtils.isEmpty(K)) {
            K = "";
        }
        if (this.J == null) {
            this.J = new byte[0];
        }
        byte[] bArr4 = new byte[2];
        byte[] bArr5 = new byte[4];
        int i3 = 4096;
        if (this.J != null) {
            i3 = (this.J.length + 1) + 4096;
        }
        if (this.M == null || i3 > this.N) {
            bArr = new byte[i3];
            this.M = bArr;
            this.N = i3;
        } else {
            bArr = this.M;
        }
        bArr[0] = Byte.parseByte(this.a);
        Object a4 = de.a(this.b, null);
        System.arraycopy(a4, 0, bArr, 1, a4.length);
        int length3 = a4.length + 1;
        try {
            a4 = this.c.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th) {
            cw.a(th, "Req", "buildV4Dot2");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.d.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th2) {
            cw.a(th2, "Req", "buildV4Dot21");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.o.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th22) {
            cw.a(th22, "Req", "buildV4Dot22");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.e.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th222) {
            cw.a(th222, "Req", "buildV4Dot23");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.f.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th2222) {
            cw.a(th2222, "Req", "buildV4Dot24");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.g.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th22222) {
            cw.a(th22222, "Req", "buildV4Dot25");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.u.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th222222) {
            cw.a(th222222, "Req", "buildV4Dot26");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.h.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th2222222) {
            cw.a(th2222222, "Req", "buildV4Dot27");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.p.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th22222222) {
            cw.a(th22222222, "Req", "buildV4Dot28");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.q.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th222222222) {
            cw.a(th222222222, "Req", "buildV4Dot29");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            if (TextUtils.isEmpty(this.t)) {
                bArr[length3] = (byte) 0;
                length3++;
            } else {
                a4 = a(this.t);
                bArr[length3] = (byte) a4.length;
                length3++;
                System.arraycopy(a4, 0, bArr, length3, a4.length);
                length3 += a4.length;
            }
        } catch (Throwable th2222222222) {
            cw.a(th2222222222, "Req", "buildV4Dot219");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.v.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th22222222222) {
            cw.a(th22222222222, "Req", "buildV4Dot211");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.w.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
        } catch (Throwable th222222222222) {
            cw.a(th222222222222, "Req", "buildV4Dot212");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            if (TextUtils.isEmpty(K)) {
                bArr[length3] = (byte) 0;
                length3++;
            } else {
                a4 = K.getBytes("GBK");
                bArr[length3] = (byte) a4.length;
                length3++;
                System.arraycopy(a4, 0, bArr, length3, a4.length);
                length3 += a4.length;
            }
        } catch (Throwable th2222222222222) {
            cw.a(th2222222222222, "Req", "buildV4Dot212");
            bArr[length3] = (byte) 0;
            length3++;
        }
        try {
            a4 = this.x.getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            i3 = a4.length + length3;
        } catch (Throwable th22222222222222) {
            cw.a(th22222222222222, "Req", "buildV4Dot213");
            bArr[length3] = (byte) 0;
            i3 = length3 + 1;
        }
        bArr[i3] = Byte.parseByte(this.y);
        i3++;
        bArr[i3] = Byte.parseByte(this.j);
        i3++;
        length3 = this.z & 3;
        bArr[i3] = (byte) this.z;
        i3++;
        if (length3 == 1) {
            e = de.e(b("mcc"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.e(b("mnc"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.e(b("lac"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.f(b("cellid"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            length3 = e.length + i3;
            i3 = Integer.parseInt(b("signal"));
            if (i3 > 127) {
                i3 = 0;
            } else if (i3 < -128) {
                i3 = 0;
            }
            bArr[length3] = (byte) i3;
            i3 = length3 + 1;
            e = de.a(0, bArr4);
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += 2;
            if (this.B.length() == 0) {
                bArr[i3] = (byte) 0;
                i3++;
            } else {
                length = this.B.split("\\*").length;
                bArr[i3] = (byte) length;
                i3++;
                length3 = 0;
                while (length3 < length) {
                    e2 = de.e(a("lac", length3));
                    System.arraycopy(e2, 0, bArr, i3, e2.length);
                    i3 += e2.length;
                    e2 = de.f(a("cellid", length3));
                    System.arraycopy(e2, 0, bArr, i3, e2.length);
                    length2 = e2.length + i3;
                    i3 = Integer.parseInt(a("signal", length3));
                    if (i3 > 127) {
                        i3 = 0;
                    } else if (i3 < -128) {
                        i3 = 0;
                    }
                    bArr[length2] = (byte) i3;
                    length3++;
                    i3 = length2 + 1;
                }
            }
        } else if (length3 == 2) {
            e = de.e(b("mcc"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.e(b("sid"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.e(b("nid"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.e(b("bid"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.f(b("lon"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += e.length;
            e = de.f(b("lat"));
            System.arraycopy(e, 0, bArr, i3, e.length);
            length3 = e.length + i3;
            i3 = Integer.parseInt(b("signal"));
            if (i3 > 127) {
                i3 = 0;
            } else if (i3 < -128) {
                i3 = 0;
            }
            bArr[length3] = (byte) i3;
            i3 = length3 + 1;
            e = de.a(0, bArr4);
            System.arraycopy(e, 0, bArr, i3, e.length);
            i3 += 2;
            bArr[i3] = (byte) 0;
            i3++;
        }
        String str = this.D;
        if (str != null && (this.z & 8) == 8) {
            try {
                e = str.getBytes("GBK");
                length2 = Math.min(e.length, 60);
                bArr[i3] = (byte) length2;
                i3++;
                System.arraycopy(e, 0, bArr, i3, length2);
                length3 = i3 + length2;
            } catch (Exception e3) {
            }
            arrayList = this.C;
            length2 = arrayList.size();
            if ((this.z & 4) == 4 || length2 <= 0) {
                bArr[length3] = (byte) 0;
                length = length3 + 1;
            } else {
                if (!((ce) arrayList.get(0)).o) {
                    length2--;
                }
                bArr[length3] = (byte) length2;
                length = length3 + 1;
                i = 0;
                while (i < length2) {
                    ce ceVar = (ce) arrayList.get(i);
                    if (ceVar.o) {
                        byte b;
                        if (ceVar.k == 1 || ceVar.k == 3 || ceVar.k == 4) {
                            b = (byte) ceVar.k;
                            if (ceVar.n) {
                                b = (byte) (b | 8);
                            }
                            bArr[length] = b;
                            length3 = length + 1;
                            a = de.a(ceVar.a, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.b, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.c, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.d, bArr5);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length = a.length + length3;
                            length3 = ceVar.j;
                            if (length3 > 127) {
                                length3 = 99;
                            } else if (length3 < -128) {
                                length3 = 99;
                            }
                            bArr[length] = (byte) length3;
                            length3 = length + 1;
                            a4 = de.a(ceVar.l, bArr4);
                            System.arraycopy(a4, 0, bArr, length3, a4.length);
                            i3 = a4.length + length3;
                            i++;
                            length = i3;
                        } else if (ceVar.k == 2) {
                            b = (byte) ceVar.k;
                            if (ceVar.n) {
                                b = (byte) (b | 8);
                            }
                            bArr[length] = b;
                            length3 = length + 1;
                            a = de.a(ceVar.a, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.g, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.h, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.a(ceVar.i, bArr4);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.b(ceVar.f, bArr5);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length3 += a.length;
                            a = de.b(ceVar.e, bArr5);
                            System.arraycopy(a, 0, bArr, length3, a.length);
                            length = a.length + length3;
                            length3 = ceVar.j;
                            if (length3 > 127) {
                                length3 = 99;
                            } else if (length3 < -128) {
                                length3 = 99;
                            }
                            bArr[length] = (byte) length3;
                            length3 = length + 1;
                            a4 = de.a(ceVar.l, bArr4);
                            System.arraycopy(a4, 0, bArr, length3, a4.length);
                            i3 = a4.length + length3;
                            i++;
                            length = i3;
                        }
                    }
                    i3 = length;
                    i++;
                    length = i3;
                }
            }
            if (this.E.length() != 0) {
                bArr[length] = (byte) 0;
                i3 = length + 1;
            } else {
                bArr[length] = (byte) 1;
                length3 = length + 1;
                try {
                    split = this.E.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    a4 = a(split[0]);
                    System.arraycopy(a4, 0, bArr, length3, a4.length);
                    length3 += a4.length;
                    a4 = split[2].getBytes("GBK");
                    bArr[length3] = (byte) a4.length;
                    length3++;
                    System.arraycopy(a4, 0, bArr, length3, a4.length);
                    length3 += a4.length;
                } catch (Throwable th222222222222222) {
                    cw.a(th222222222222222, "Req", "buildV4Dot216");
                    a4 = a("00:00:00:00:00:00");
                    System.arraycopy(a4, 0, bArr, length3, a4.length);
                    i3 = a4.length + length3;
                    bArr[i3] = (byte) 0;
                    i3++;
                    bArr[i3] = Byte.parseByte("0");
                    i3++;
                }
                i3 = Integer.parseInt(split[1]);
                if (i3 > 127) {
                    i3 = 0;
                } else if (i3 < -128) {
                    i3 = 0;
                }
                bArr[length3] = Byte.parseByte(String.valueOf(i3));
                i3 = length3 + 1;
            }
            arrayList2 = this.F;
            min = Math.min(arrayList2.size(), 25);
            if (min != 0) {
                bArr[i3] = (byte) 0;
                i3++;
            } else {
                bArr[i3] = (byte) min;
                length = i3 + 1;
                obj = de.c() < 17 ? 1 : null;
                j = 0;
                if (obj != null) {
                    j = de.b() / 1000;
                }
                for (i2 = 0; i2 < min; i2++) {
                    scanResult = (ScanResult) arrayList2.get(i2);
                    a2 = a(scanResult.BSSID);
                    System.arraycopy(a2, 0, bArr, length, a2.length);
                    length += a2.length;
                    try {
                        a2 = scanResult.SSID.getBytes("GBK");
                        bArr[length] = (byte) a2.length;
                        length++;
                        System.arraycopy(a2, 0, bArr, length, a2.length);
                        length += a2.length;
                    } catch (Exception e4) {
                        bArr[length] = (byte) 0;
                        length++;
                    }
                    i = scanResult.level;
                    if (i > 127) {
                        i = 0;
                    } else if (i < -128) {
                        i = 0;
                    }
                    bArr[length] = Byte.parseByte(String.valueOf(i));
                    i = length + 1;
                    if (obj != null) {
                        length = (int) (((j - scanResult.timestamp) / 1000000) + 1);
                        if (length >= 0) {
                            a = de.a(length, bArr4);
                            System.arraycopy(a, 0, bArr, i, a.length);
                            length = a.length + i;
                            a4 = de.a(scanResult.frequency, bArr4);
                            System.arraycopy(a4, 0, bArr, length, a4.length);
                            length += a4.length;
                        }
                    }
                    length = 0;
                    a = de.a(length, bArr4);
                    System.arraycopy(a, 0, bArr, i, a.length);
                    length = a.length + i;
                    a4 = de.a(scanResult.frequency, bArr4);
                    System.arraycopy(a4, 0, bArr, length, a4.length);
                    length += a4.length;
                }
                a4 = de.a(Integer.parseInt(this.H), bArr4);
                System.arraycopy(a4, 0, bArr, length, a4.length);
                i3 = a4.length + length;
            }
            bArr[i3] = (byte) 0;
            i3++;
            e = this.I.getBytes("GBK");
            if (e.length > 127) {
                e = null;
            }
            if (e != null) {
                bArr[i3] = (byte) 0;
                i3++;
            } else {
                bArr[i3] = (byte) e.length;
                i3++;
                System.arraycopy(e, 0, bArr, i3, e.length);
                i3 += e.length;
            }
            e = new byte[]{(byte) 0, (byte) 0};
            isEmpty = TextUtils.isEmpty(this.L);
            if (!isEmpty) {
                e = de.a(this.L.length(), bArr4);
            }
            System.arraycopy(e, 0, bArr, i3, 2);
            i3 += 2;
            if (!isEmpty) {
                try {
                    e = this.L.getBytes("GBK");
                    System.arraycopy(e, 0, bArr, i3, e.length);
                    i3 += e.length;
                } catch (Throwable th3) {
                }
            }
            arrayList3 = this.G;
            i2 = Math.min(arrayList3.size(), 65536);
            bArr2 = new byte[]{(byte) 0, (byte) 0};
            if (i2 != 0) {
                try {
                    System.arraycopy(de.a(0, bArr4), 0, bArr, i3, 2);
                    i3 += 2;
                } catch (Throwable th4) {
                    i3 += 2;
                }
            } else {
                try {
                    System.arraycopy(de.a(i2, bArr4), 0, bArr, i3, 2);
                    i3 += 2;
                } catch (Throwable th5) {
                    i3 += 2;
                }
                length2 = 0;
                length3 = i3;
                while (length2 < i2) {
                    cbVar = (cb) arrayList3.get(length2);
                    try {
                        a = cbVar.c;
                        System.arraycopy(a, 0, bArr, length3, a.length);
                        length = length3 + a.length;
                    } catch (Throwable th6) {
                        length = length3 + 6;
                    }
                    try {
                        obj = cbVar.a.getBytes("GBK");
                        System.arraycopy(obj, 0, bArr, length, obj.length <= 32 ? 32 : obj.length);
                        length3 = length + 32;
                    } catch (Throwable th7) {
                        length3 = length + 32;
                    }
                    try {
                        bArr3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                        a = cbVar.d.getBytes("GBK");
                        System.arraycopy(a, 0, bArr, length3, a.length);
                        length3 += 4;
                    } catch (Throwable th8) {
                        length3 += 4;
                    }
                    try {
                        bArr3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                        a = cbVar.e.getBytes("GBK");
                        System.arraycopy(a, 0, bArr, length3, a.length);
                        length3 += 4;
                    } catch (Throwable th9) {
                        length3 += 4;
                    }
                    try {
                        if (cbVar.g > 127) {
                            cbVar.g = 0;
                        } else if (cbVar.g < -128) {
                            cbVar.g = 0;
                        }
                        bArr[length3] = (byte) cbVar.g;
                        length3++;
                    } catch (Throwable th10) {
                        length3++;
                    }
                    try {
                        a = de.b(cbVar.f, new byte[4]);
                        System.arraycopy(a, 0, bArr, length3, a.length);
                        length3 += 4;
                    } catch (Throwable th11) {
                        length3 += 4;
                    }
                    try {
                        a4 = de.b(cbVar.j, new byte[4]);
                        System.arraycopy(a4, 0, bArr, length3, a4.length);
                        i3 = length3 + 4;
                    } catch (Throwable th12) {
                        i3 = length3 + 4;
                    }
                    length2++;
                    length3 = i3;
                }
                i3 = length3;
            }
            length3 = 0;
            if (this.J != null) {
                length3 = this.J.length;
            }
            e2 = de.a(length3, null);
            System.arraycopy(e2, 0, bArr, i3, e2.length);
            i3 += e2.length;
            if (length3 > 0) {
                System.arraycopy(this.J, 0, bArr, i3, this.J.length);
                i3 += this.J.length;
            }
            e = new byte[i3];
            System.arraycopy(bArr, 0, e, 0, i3);
            crc32 = new CRC32();
            crc32.update(e);
            a3 = de.a(crc32.getValue());
            e2 = new byte[(a3.length + i3)];
            System.arraycopy(e, 0, e2, 0, i3);
            System.arraycopy(a3, 0, e2, i3, a3.length);
            return e2;
        }
        bArr[i3] = (byte) 0;
        length3 = i3 + 1;
        arrayList = this.C;
        length2 = arrayList.size();
        if ((this.z & 4) == 4) {
        }
        bArr[length3] = (byte) 0;
        length = length3 + 1;
        if (this.E.length() != 0) {
            bArr[length] = (byte) 1;
            length3 = length + 1;
            split = this.E.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            a4 = a(split[0]);
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
            a4 = split[2].getBytes("GBK");
            bArr[length3] = (byte) a4.length;
            length3++;
            System.arraycopy(a4, 0, bArr, length3, a4.length);
            length3 += a4.length;
            i3 = Integer.parseInt(split[1]);
            if (i3 > 127) {
                i3 = 0;
            } else if (i3 < -128) {
                i3 = 0;
            }
            bArr[length3] = Byte.parseByte(String.valueOf(i3));
            i3 = length3 + 1;
        } else {
            bArr[length] = (byte) 0;
            i3 = length + 1;
        }
        arrayList2 = this.F;
        min = Math.min(arrayList2.size(), 25);
        if (min != 0) {
            bArr[i3] = (byte) min;
            length = i3 + 1;
            if (de.c() < 17) {
            }
            j = 0;
            if (obj != null) {
                j = de.b() / 1000;
            }
            for (i2 = 0; i2 < min; i2++) {
                scanResult = (ScanResult) arrayList2.get(i2);
                a2 = a(scanResult.BSSID);
                System.arraycopy(a2, 0, bArr, length, a2.length);
                length += a2.length;
                a2 = scanResult.SSID.getBytes("GBK");
                bArr[length] = (byte) a2.length;
                length++;
                System.arraycopy(a2, 0, bArr, length, a2.length);
                length += a2.length;
                i = scanResult.level;
                if (i > 127) {
                    i = 0;
                } else if (i < -128) {
                    i = 0;
                }
                bArr[length] = Byte.parseByte(String.valueOf(i));
                i = length + 1;
                if (obj != null) {
                    length = (int) (((j - scanResult.timestamp) / 1000000) + 1);
                    if (length >= 0) {
                        a = de.a(length, bArr4);
                        System.arraycopy(a, 0, bArr, i, a.length);
                        length = a.length + i;
                        a4 = de.a(scanResult.frequency, bArr4);
                        System.arraycopy(a4, 0, bArr, length, a4.length);
                        length += a4.length;
                    }
                }
                length = 0;
                a = de.a(length, bArr4);
                System.arraycopy(a, 0, bArr, i, a.length);
                length = a.length + i;
                a4 = de.a(scanResult.frequency, bArr4);
                System.arraycopy(a4, 0, bArr, length, a4.length);
                length += a4.length;
            }
            a4 = de.a(Integer.parseInt(this.H), bArr4);
            System.arraycopy(a4, 0, bArr, length, a4.length);
            i3 = a4.length + length;
        } else {
            bArr[i3] = (byte) 0;
            i3++;
        }
        bArr[i3] = (byte) 0;
        i3++;
        try {
            e = this.I.getBytes("GBK");
            if (e.length > 127) {
                e = null;
            }
            if (e != null) {
                bArr[i3] = (byte) e.length;
                i3++;
                System.arraycopy(e, 0, bArr, i3, e.length);
                i3 += e.length;
            } else {
                bArr[i3] = (byte) 0;
                i3++;
            }
        } catch (Throwable th13) {
            bArr[i3] = (byte) 0;
            i3++;
        }
        e = new byte[]{(byte) 0, (byte) 0};
        try {
            isEmpty = TextUtils.isEmpty(this.L);
            if (isEmpty) {
                e = de.a(this.L.length(), bArr4);
            }
            System.arraycopy(e, 0, bArr, i3, 2);
            i3 += 2;
            if (isEmpty) {
                e = this.L.getBytes("GBK");
                System.arraycopy(e, 0, bArr, i3, e.length);
                i3 += e.length;
            }
        } catch (Throwable th14) {
            i3 += 2;
        }
        arrayList3 = this.G;
        i2 = Math.min(arrayList3.size(), 65536);
        bArr2 = new byte[]{(byte) 0, (byte) 0};
        if (i2 != 0) {
            System.arraycopy(de.a(i2, bArr4), 0, bArr, i3, 2);
            i3 += 2;
            length2 = 0;
            length3 = i3;
            while (length2 < i2) {
                cbVar = (cb) arrayList3.get(length2);
                a = cbVar.c;
                System.arraycopy(a, 0, bArr, length3, a.length);
                length = length3 + a.length;
                obj = cbVar.a.getBytes("GBK");
                if (obj.length <= 32) {
                }
                System.arraycopy(obj, 0, bArr, length, obj.length <= 32 ? 32 : obj.length);
                length3 = length + 32;
                bArr3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                a = cbVar.d.getBytes("GBK");
                System.arraycopy(a, 0, bArr, length3, a.length);
                length3 += 4;
                bArr3 = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                a = cbVar.e.getBytes("GBK");
                System.arraycopy(a, 0, bArr, length3, a.length);
                length3 += 4;
                if (cbVar.g > 127) {
                    cbVar.g = 0;
                } else if (cbVar.g < -128) {
                    cbVar.g = 0;
                }
                bArr[length3] = (byte) cbVar.g;
                length3++;
                a = de.b(cbVar.f, new byte[4]);
                System.arraycopy(a, 0, bArr, length3, a.length);
                length3 += 4;
                a4 = de.b(cbVar.j, new byte[4]);
                System.arraycopy(a4, 0, bArr, length3, a4.length);
                i3 = length3 + 4;
                length2++;
                length3 = i3;
            }
            i3 = length3;
        } else {
            System.arraycopy(de.a(0, bArr4), 0, bArr, i3, 2);
            i3 += 2;
        }
        length3 = 0;
        if (this.J != null) {
            length3 = this.J.length;
        }
        e2 = de.a(length3, null);
        System.arraycopy(e2, 0, bArr, i3, e2.length);
        i3 += e2.length;
        if (length3 > 0) {
            System.arraycopy(this.J, 0, bArr, i3, this.J.length);
            i3 += this.J.length;
        }
        e = new byte[i3];
        System.arraycopy(bArr, 0, e, 0, i3);
        crc32 = new CRC32();
        crc32.update(e);
        a3 = de.a(crc32.getValue());
        e2 = new byte[(a3.length + i3)];
        System.arraycopy(e, 0, e2, 0, i3);
        System.arraycopy(a3, 0, e2, i3, a3.length);
        return e2;
    }
}
