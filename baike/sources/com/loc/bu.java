package com.loc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationProtocol;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import qsbk.app.widget.RefreshTipView;

@SuppressLint({"NewApi"})
public final class bu {
    static int G = -1;
    private static boolean M = false;
    private static int O = -1;
    StringBuilder A = null;
    boolean B = false;
    public boolean C = false;
    int D = 12;
    ca E = null;
    boolean F = false;
    by H = null;
    String I = null;
    IntentFilter J = null;
    private int K = 0;
    private String L = null;
    private String N = null;
    Context a = null;
    ConnectivityManager b = null;
    ci c = null;
    cf d = null;
    ch e = null;
    cg f = null;
    ck g = null;
    cm h = null;
    bv i = null;
    cc j = null;
    ArrayList<ScanResult> k = new ArrayList();
    a l = null;
    AMapLocationClientOption m = new AMapLocationClientOption();
    AMapLocationServer n = null;
    long o = 0;
    String p = "00:00:00:00:00:00";
    cu q = null;
    boolean r = false;
    cr s = null;
    StringBuilder t = new StringBuilder();
    boolean u = true;
    boolean v = true;
    boolean w = true;
    boolean x = false;
    WifiInfo y = null;
    boolean z = true;

    class a extends BroadcastReceiver {
        final /* synthetic */ bu a;

        a(bu buVar) {
            this.a = buVar;
        }

        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (this.a.c != null) {
                                this.a.c.d();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                            if (this.a.c != null) {
                                this.a.c.e();
                            }
                        } else if (action.equals("android.intent.action.SCREEN_ON")) {
                            if (this.a.f != null) {
                                this.a.f.a(true);
                            }
                        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                            if (this.a.f != null) {
                                this.a.f.a(false);
                                this.a.f.c();
                            }
                        } else if (action.equals("android.net.conn.CONNECTIVITY_CHANGE") && this.a.f != null) {
                            this.a.f.d();
                        }
                    }
                } catch (Throwable th) {
                    cw.a(th, "APS", "onReceive");
                }
            }
        }
    }

    private static AMapLocationServer a(int i, String str) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        aMapLocationServer.setErrorCode(i);
        aMapLocationServer.setLocationDetail(str);
        if (i == 15) {
            db.a(null, 2151);
        }
        return aMapLocationServer;
    }

    private AMapLocationServer a(AMapLocationServer aMapLocationServer, bo boVar) {
        if (boVar != null) {
            try {
                if (!(boVar.a == null || boVar.a.length == 0)) {
                    ct ctVar = new ct();
                    String str = new String(boVar.a, "UTF-8");
                    if (str.contains("\"status\":\"0\"")) {
                        aMapLocationServer = ctVar.a(str, this.a, boVar);
                        aMapLocationServer.g(this.A.toString());
                        return aMapLocationServer;
                    } else if (!str.contains("</body></html>")) {
                        return null;
                    } else {
                        aMapLocationServer.setErrorCode(5);
                        if (this.c == null || !this.c.a(this.b)) {
                            this.t.append("request may be intercepted");
                            db.a(null, 2052);
                        } else {
                            this.t.append("make sure you are logged in to the network");
                            db.a(null, 2051);
                        }
                        aMapLocationServer.setLocationDetail(this.t.toString());
                        return aMapLocationServer;
                    }
                }
            } catch (Throwable th) {
                cw.a(th, "APS", "checkResponseEntity");
                this.t.append("check response exception ex is" + th.getMessage());
                aMapLocationServer.setLocationDetail(this.t.toString());
                return aMapLocationServer;
            }
        }
        aMapLocationServer.setErrorCode(4);
        this.t.append("please check the network");
        aMapLocationServer.g(this.A.toString());
        aMapLocationServer.setLocationDetail(this.t.toString());
        if (boVar == null) {
            return aMapLocationServer;
        }
        db.a(boVar.d, 2041);
        return aMapLocationServer;
    }

    @SuppressLint({"NewApi"})
    private AMapLocationServer a(boolean z, boolean z2) {
        AMapLocationServer aMapLocationServer = new AMapLocationServer("");
        ct ctVar = new ct();
        try {
            if (this.q == null) {
                this.q = new cu();
            }
            if (this.m == null) {
                this.m = new AMapLocationClientOption();
            }
            this.q.a(this.a, this.m.isNeedAddress(), this.m.isOffset(), this.d, this.c, this.b, this.j, this.p, this.f.f(), this.I);
            this.e.a(this.d);
            byte[] a = this.q.a();
            this.o = de.b();
            try {
                AMapLocationServer aMapLocationServer2;
                cs a2 = this.s.a(this.a, a, cw.a(), z2);
                cp.a(this.a).a(a2);
                bo a3 = this.s.a(a2);
                String str = "";
                if (a3 != null) {
                    cp.a(this.a).a();
                    aMapLocationServer.a((long) this.s.a());
                    if (!TextUtils.isEmpty(a3.c)) {
                        this.t.append(" #csid:" + a3.c);
                    }
                    str = a3.d;
                    aMapLocationServer.g(this.A.toString());
                }
                if (z) {
                    aMapLocationServer2 = aMapLocationServer;
                } else {
                    AMapLocationServer a4 = a(aMapLocationServer, a3);
                    if (a4 != null) {
                        return a4;
                    }
                    byte[] a5 = cj.a(a3.a);
                    if (a5 == null) {
                        aMapLocationServer.setErrorCode(5);
                        this.t.append("decrypt response data error");
                        aMapLocationServer.setLocationDetail(this.t.toString());
                        db.a(str, 2053);
                        return aMapLocationServer;
                    }
                    a4 = ctVar.a(aMapLocationServer, a5);
                    if (de.a(a4)) {
                        if (this.j != null) {
                            cc ccVar = this.j;
                            String d = a4.d();
                            float accuracy = a4.getAccuracy();
                            try {
                                if (!"-1".equals(d) || accuracy > 5.0f) {
                                    ccVar.a();
                                } else {
                                    ccVar.b();
                                }
                            } catch (Throwable th) {
                                cw.a(th, "BeaconManager", "checkLocationType");
                            }
                        }
                        if (a4.getErrorCode() == 0 && a4.getLocationType() == 0) {
                            if ("-5".equals(a4.d()) || "1".equals(a4.d()) || "2".equals(a4.d()) || Constants.VIA_REPORT_TYPE_MAKE_FRIEND.equals(a4.d()) || "24".equals(a4.d()) || "-1".equals(a4.d())) {
                                a4.setLocationType(5);
                            } else {
                                a4.setLocationType(6);
                            }
                        }
                        a4.setOffset(this.v);
                        a4.a(this.u);
                        aMapLocationServer2 = a4;
                    } else {
                        this.L = a4.b();
                        if (TextUtils.isEmpty(this.L)) {
                            db.a(str, 2061);
                        } else {
                            db.a(str, 2062);
                        }
                        a4.setErrorCode(6);
                        this.t.append("location faile retype:" + a4.d() + " rdesc:" + (TextUtils.isEmpty(this.L) ? "" : this.L));
                        a4.g(this.A.toString());
                        a4.setLocationDetail(this.t.toString());
                        return a4;
                    }
                }
                aMapLocationServer2.e("new");
                aMapLocationServer2.setLocationDetail(this.t.toString());
                this.I = aMapLocationServer2.a();
                return aMapLocationServer2;
            } catch (Throwable th2) {
                cp.a(this.a).b();
                cw.a(th2, "APS", "getApsLoc req");
                db.a("/mobile/binary", th2);
                this.t.append("request error, please check the network");
                aMapLocationServer = a(4, this.t.toString());
                aMapLocationServer.g(this.A.toString());
                return aMapLocationServer;
            }
        } catch (Throwable th22) {
            this.t.append("get parames error:" + th22.getMessage());
            db.a(null, 2031);
            aMapLocationServer = a(3, this.t.toString());
            aMapLocationServer.g(this.A.toString());
            return aMapLocationServer;
        }
    }

    private StringBuilder a(StringBuilder stringBuilder) {
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder(ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        } else {
            stringBuilder.delete(0, stringBuilder.length());
        }
        stringBuilder.append(this.d.j());
        stringBuilder.append(this.c.i());
        return stringBuilder;
    }

    public static void b(Context context) {
        try {
            if (O == -1 || cv.h(context)) {
                O = 1;
                cv.a(context);
            }
        } catch (Throwable th) {
            cw.a(th, "APS", "initAuth");
        }
    }

    private void b(AMapLocationServer aMapLocationServer) {
        if (this.e != null) {
            if (aMapLocationServer != null) {
                this.n = aMapLocationServer;
                this.e.a(this.n.toJson(1));
            }
            this.e.c();
        }
    }

    private void k() {
        if (this.t.length() > 0) {
            this.t.delete(0, this.t.length());
        }
    }

    private void l() {
        try {
            if (this.l == null) {
                this.l = new a(this);
            }
            if (this.J == null) {
                this.J = new IntentFilter();
                this.J.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                this.J.addAction("android.net.wifi.SCAN_RESULTS");
                this.J.addAction("android.intent.action.SCREEN_ON");
                this.J.addAction("android.intent.action.SCREEN_OFF");
                this.J.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            this.a.registerReceiver(this.l, this.J);
        } catch (Throwable th) {
            cw.a(th, "APS", "initBroadcastListener");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m() {
        /*
        r10 = this;
        r9 = 12;
        r8 = 0;
        r7 = 2;
        r3 = 1;
        r2 = 0;
        r4 = "";
        r5 = "network";
        r0 = r10.d;
        r0 = r0.e();
        r1 = r10.d;
        r1 = r1.c();
        if (r1 != 0) goto L_0x005e;
    L_0x0018:
        r6 = r10.k;
        if (r6 == 0) goto L_0x0024;
    L_0x001c:
        r6 = r10.k;
        r6 = r6.isEmpty();
        if (r6 == 0) goto L_0x005e;
    L_0x0024:
        r0 = r10.d;
        r0 = r0.h();
        r1 = r10.c;
        r1 = r1.a();
        if (r0 != 0) goto L_0x0034;
    L_0x0032:
        if (r1 == 0) goto L_0x0040;
    L_0x0034:
        r10.D = r9;
        r2 = r10.t;
        if (r0 == 0) goto L_0x003e;
    L_0x003a:
        r2.append(r0);
    L_0x003d:
        return r4;
    L_0x003e:
        r0 = r1;
        goto L_0x003a;
    L_0x0040:
        r0 = r10.a;
        r0 = com.loc.de.f(r0);
        if (r0 != 0) goto L_0x0052;
    L_0x0048:
        r0 = r10.t;
        r1 = "locationpermission has not been granted";
        r0.append(r1);
        r10.D = r9;
        goto L_0x003d;
    L_0x0052:
        r0 = r10.t;
        r1 = "no cgi & no wifis";
        r0.append(r1);
        r0 = 13;
        r10.D = r0;
        goto L_0x003d;
    L_0x005e:
        r6 = r10.c;
        r6 = r6.g();
        r10.y = r6;
        r6 = r10.c;
        r6 = r10.y;
        r6 = com.loc.ci.a(r6);
        r10.z = r6;
        switch(r0) {
            case 0: goto L_0x016a;
            case 1: goto L_0x00b3;
            case 2: goto L_0x0109;
            default: goto L_0x0073;
        };
    L_0x0073:
        r0 = 11;
        r10.D = r0;
        r0 = r10.t;
        r1 = "get cgi failure";
        r0.append(r1);
    L_0x007e:
        r0 = r4;
    L_0x007f:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x00b1;
    L_0x0085:
        r1 = "#";
        r1 = r0.startsWith(r1);
        if (r1 != 0) goto L_0x009c;
    L_0x008d:
        r1 = new java.lang.StringBuilder;
        r2 = "#";
        r1.<init>(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
    L_0x009c:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = com.loc.de.h();
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
    L_0x00b1:
        r4 = r0;
        goto L_0x003d;
    L_0x00b3:
        if (r1 == 0) goto L_0x007e;
    L_0x00b5:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r1.a;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.b;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.c;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.d;
        r0 = r2.append(r0);
        r1 = "#";
        r0.append(r1);
        r0 = r2.append(r5);
        r1 = "#";
        r0.append(r1);
        r0 = r10.k;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x00fb;
    L_0x00f7:
        r0 = r10.z;
        if (r0 == 0) goto L_0x0106;
    L_0x00fb:
        r0 = "cgiwifi";
    L_0x00fd:
        r2.append(r0);
        r0 = r2.toString();
        goto L_0x007f;
    L_0x0106:
        r0 = "cgi";
        goto L_0x00fd;
    L_0x0109:
        if (r1 == 0) goto L_0x007e;
    L_0x010b:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r1.a;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.b;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.g;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.h;
        r0 = r2.append(r0);
        r3 = "#";
        r0.append(r3);
        r0 = r1.i;
        r0 = r2.append(r0);
        r1 = "#";
        r0.append(r1);
        r0 = r2.append(r5);
        r1 = "#";
        r0.append(r1);
        r0 = r10.k;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x015c;
    L_0x0158:
        r0 = r10.z;
        if (r0 == 0) goto L_0x0167;
    L_0x015c:
        r0 = "cgiwifi";
    L_0x015e:
        r2.append(r0);
        r0 = r2.toString();
        goto L_0x007f;
    L_0x0167:
        r0 = "cgi";
        goto L_0x015e;
    L_0x016a:
        r0 = r10.k;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0176;
    L_0x0172:
        r0 = r10.z;
        if (r0 == 0) goto L_0x020e;
    L_0x0176:
        r1 = r3;
    L_0x0177:
        r0 = r10.z;
        if (r0 == 0) goto L_0x01b5;
    L_0x017b:
        r0 = r10.k;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x01b5;
    L_0x0183:
        r10.D = r7;
        r0 = r10.t;
        r1 = "no around wifi(s) & has access wifi";
        r0.append(r1);
        r0 = 2021; // 0x7e5 float:2.832E-42 double:9.985E-321;
        com.loc.db.a(r8, r0);
        r1 = r2;
    L_0x0192:
        r0 = java.util.Locale.US;
        r4 = "#%s#";
        r3 = new java.lang.Object[r3];
        r3[r2] = r5;
        r0 = java.lang.String.format(r0, r4, r3);
        if (r1 == 0) goto L_0x01f9;
    L_0x01a0:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = "wifi";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x007f;
    L_0x01b5:
        r0 = r10.k;
        r0 = r0.size();
        if (r0 != r3) goto L_0x0192;
    L_0x01bd:
        r10.D = r7;
        r0 = r10.z;
        if (r0 != 0) goto L_0x01d1;
    L_0x01c3:
        r0 = r10.t;
        r1 = "no access wifi & around wifi 1";
        r0.append(r1);
        r0 = 2022; // 0x7e6 float:2.833E-42 double:9.99E-321;
        com.loc.db.a(r8, r0);
        r1 = r2;
        goto L_0x0192;
    L_0x01d1:
        r0 = r10.k;
        r0 = r0.get(r2);
        r0 = (android.net.wifi.ScanResult) r0;
        r0 = r0.BSSID;
        r4 = r10.c;
        r4 = r4.g();
        r4 = r4.getBSSID();
        r0 = r4.equals(r0);
        if (r0 == 0) goto L_0x0192;
    L_0x01eb:
        r0 = r10.t;
        r1 = "same access wifi & around wifi 1";
        r0.append(r1);
        r0 = 2021; // 0x7e5 float:2.832E-42 double:9.985E-321;
        com.loc.db.a(r8, r0);
        r1 = r2;
        goto L_0x0192;
    L_0x01f9:
        r1 = "network";
        r1 = r5.equals(r1);
        if (r1 == 0) goto L_0x007f;
    L_0x0201:
        r0 = "";
        r10.D = r7;
        r1 = r10.t;
        r2 = "is network & no wifi";
        r1.append(r2);
        goto L_0x007f;
    L_0x020e:
        r1 = r2;
        goto L_0x0177;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bu.m():java.lang.String");
    }

    public final AMapLocationServer a(double d, double d2) {
        try {
            String a = this.s.a(("output=json&radius=1000&extensions=all&location=" + d2 + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP + d).getBytes("UTF-8"), this.a, "http://restapi.amap.com/v3/geocode/regeo");
            ct ctVar = new ct();
            if (a.contains("\"status\":\"1\"")) {
                AMapLocationServer a2 = ct.a(a);
                a2.setLatitude(d);
                a2.setLongitude(d2);
                return a2;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer, String... strArr) {
        this.H.a(this.w);
        if (strArr == null || strArr.length == 0) {
            return this.H.a(aMapLocationServer);
        }
        if (strArr[0].equals("shake")) {
            return this.H.a(aMapLocationServer);
        }
        if (!strArr[0].equals("fusion")) {
            return aMapLocationServer;
        }
        by byVar = this.H;
        return aMapLocationServer;
    }

    public final AMapLocationServer a(boolean z) {
        if (this.a == null) {
            this.t.append("context is null");
            db.a(null, 2011);
            return a(1, this.t.toString());
        } else if (this.c.h()) {
            return a(15, "networkLocation has been mocked!");
        } else {
            a();
            if (TextUtils.isEmpty(this.N)) {
                return a(this.D, this.t.toString());
            }
            AMapLocationServer a = a(false, z);
            if (de.a(a)) {
                this.g.a(this.A.toString());
                this.g.a(this.d.c());
                b(a);
                return a;
            }
            String stringBuilder = this.A.toString();
            this.e.a(this.a);
            ch chVar = this.e;
            ck ckVar = this.g;
            String str = this.N;
            AMapLocationClientOption aMapLocationClientOption = this.m;
            Context context = this.a;
            return chVar.a(ckVar, str, stringBuilder, aMapLocationClientOption, m(), a);
        }
    }

    public final void a() {
        this.s = cr.a(this.a);
        if (this.s != null) {
            try {
                this.s.a(this.m.getHttpTimeOut(), this.m.getLocationProtocol().equals(AMapLocationProtocol.HTTPS));
            } catch (Throwable th) {
            }
        }
        if (this.b == null) {
            this.b = (ConnectivityManager) de.a(this.a, "connectivity");
        }
        if (this.q == null) {
            this.q = new cu();
        }
    }

    public final void a(Context context) {
        try {
            if (this.a == null) {
                this.H = new by();
                this.a = context.getApplicationContext();
                cv.e(this.a);
                de.b(this.a);
                if (this.c == null) {
                    this.c = new ci(this.a, (WifiManager) de.a(this.a, "wifi"));
                }
                if (this.d == null) {
                    this.d = new cf(this.a);
                }
                if (this.e == null) {
                    this.e = new ch();
                }
                if (this.f == null) {
                    this.f = new cg();
                }
                if (this.g == null) {
                    this.g = new ck();
                }
                if (this.h == null) {
                    this.h = new cm(context);
                }
            }
        } catch (Throwable th) {
            cw.a(th, "APS", "initBase");
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        boolean isNeedAddress;
        boolean isLocationCacheEnable;
        boolean z = true;
        this.m = aMapLocationClientOption;
        if (this.m == null) {
            this.m = new AMapLocationClientOption();
        }
        if (this.c != null) {
            ci ciVar = this.c;
            this.m.isWifiActiveScan();
            ciVar.a(this.m.isWifiScan(), this.m.isMockEnable());
        }
        if (this.s != null) {
            this.s.a(this.m.getHttpTimeOut(), this.m.getLocationProtocol().equals(AMapLocationProtocol.HTTPS));
        }
        if (this.g != null) {
            this.g.a(this.m);
        }
        try {
            isNeedAddress = this.m.isNeedAddress();
            try {
                boolean isOffset = this.m.isOffset();
                try {
                    isLocationCacheEnable = this.m.isLocationCacheEnable();
                    try {
                        this.x = this.m.isOnceLocationLatest();
                        this.F = this.m.isSensorEnable();
                        if (!(isOffset == this.v && isNeedAddress == this.u && isLocationCacheEnable == this.w)) {
                            if (this.g != null) {
                                this.g.a();
                            }
                            b(null);
                            this.o = 0;
                            if (this.H != null) {
                                this.H.a();
                            }
                            z = isNeedAddress;
                            isNeedAddress = isOffset;
                            this.v = isNeedAddress;
                            this.u = z;
                            this.w = isLocationCacheEnable;
                        }
                    } catch (Throwable th) {
                        z = isNeedAddress;
                        isNeedAddress = isOffset;
                    }
                    z = isNeedAddress;
                    isNeedAddress = isOffset;
                } catch (Throwable th2) {
                    isLocationCacheEnable = true;
                    z = isNeedAddress;
                    isNeedAddress = isOffset;
                }
            } catch (Throwable th3) {
                isLocationCacheEnable = true;
                boolean z2 = isNeedAddress;
                isNeedAddress = true;
                z = z2;
            }
        } catch (Throwable th4) {
            isLocationCacheEnable = true;
            isNeedAddress = true;
        }
        this.v = isNeedAddress;
        this.u = z;
        this.w = isLocationCacheEnable;
    }

    public final void a(AMapLocationServer aMapLocationServer) {
        if (de.a(aMapLocationServer)) {
            this.g.a(this.N, this.A, aMapLocationServer, this.a, true);
        }
    }

    public final void b() {
        if (this.j == null) {
            this.j = new cc(this.a);
        }
        if (this.E == null) {
            this.E = new ca(this.a);
        }
        if (this.i == null) {
            this.i = new bv(this.a);
        }
        this.e.a(this.a);
        l();
        this.c.b(false);
        this.d.a(false);
        this.g.a(this.a);
        this.h.a(this.a);
        this.i.b();
        try {
            if (this.a.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.r = true;
            }
        } catch (Throwable th) {
        }
        this.C = true;
    }

    public final AMapLocationServer c() throws Throwable {
        Object obj = null;
        k();
        if (this.a == null) {
            this.t.append("context is null");
            return a(1, this.t.toString());
        }
        boolean z;
        Context context;
        Object obj2;
        SharedPreferences sharedPreferences;
        AMapLocationServer a;
        this.K++;
        if (this.K == 1) {
            String str;
            this.f.e();
            if (this.c != null) {
                this.c.a(this.r);
            }
            this.f.a();
            this.e.a();
            if (TextUtils.isEmpty(this.p) || this.p.equals("00:00:00:00:00:00")) {
                this.p = dd.a(this.a);
                str = obj;
            } else {
                str = this.p;
            }
            this.p = str;
        }
        if (de.b() - this.o < 800) {
            if ((de.a(this.n) ? de.a() - this.n.getTime() : 0) <= 10000) {
                z = true;
                if (z || !de.a(this.n)) {
                    if (this.E != null) {
                        if (this.F) {
                            this.E.b();
                        } else {
                            this.E.a();
                        }
                    }
                    this.d.a(false);
                    this.c.b(true);
                    this.k = this.c.b();
                    this.N = m();
                    if (TextUtils.isEmpty(this.N)) {
                        this.e.a(this.m, this.N);
                        if ((TextUtils.isEmpty(this.p) || this.p.equals("00:00:00:00:00:00")) && this.c.g() != null) {
                            this.p = n.i(this.a);
                            context = this.a;
                            obj2 = this.p;
                            if (!M) {
                                if (!(context == null || TextUtils.isEmpty(obj2))) {
                                    sharedPreferences = context.getSharedPreferences("pref", 0);
                                    try {
                                        obj = o.a(obj2.getBytes("UTF-8"));
                                    } catch (Throwable th) {
                                        cw.a(th, "SPUtil", "setSmac");
                                    }
                                    if (!TextUtils.isEmpty(obj)) {
                                        sharedPreferences.edit().putString("smac", obj).commit();
                                    }
                                }
                                M = true;
                            }
                            if (TextUtils.isEmpty(this.p)) {
                                this.p = "00:00:00:00:00:00";
                            }
                        }
                        this.A = a(this.A);
                        if (this.c.h()) {
                            return a(15, "networkLocation has been mocked!");
                        }
                        boolean z2 = this.o != 0 ? true : de.b() - this.o <= RefreshTipView.FIRST_REFRESH_INTERVAL;
                        a = this.g.a(this.d, z2, this.n, this.c, this.A, this.N, this.a);
                        if (de.a(a)) {
                            this.n = a(false, true);
                            if (de.a(this.n)) {
                                this.n.e("new");
                                this.g.a(this.A.toString());
                                this.g.a(this.d.c());
                                this.e.a(this.n.toJson(1));
                                this.e.c();
                            }
                        } else {
                            b(a);
                        }
                        this.g.a(this.N, this.A, this.n, this.a, true);
                        this.h.a(this.a, this.N, this.n);
                        if (!de.a(this.n)) {
                            ch chVar = this.e;
                            ck ckVar = this.g;
                            String str2 = this.N;
                            String stringBuilder = this.A.toString();
                            AMapLocationClientOption aMapLocationClientOption = this.m;
                            Context context2 = this.a;
                            this.n = chVar.a(ckVar, str2, stringBuilder, aMapLocationClientOption, m(), this.n);
                        }
                        this.A.delete(0, this.A.length());
                        if (this.F || this.E == null) {
                            this.n.setAltitude(0.0d);
                            this.n.setBearing(0.0f);
                            this.n.setSpeed(0.0f);
                        } else {
                            this.n.setAltitude(this.E.c());
                            this.n.setBearing(this.E.d());
                            this.n.setSpeed((float) this.E.e());
                        }
                        return this.n;
                    }
                    this.n = this.i.e();
                    return this.n == null ? this.n : a(this.D, this.t.toString());
                } else {
                    if (this.w && cv.b(this.n.getTime())) {
                        this.n.setLocationType(2);
                    }
                    return this.n;
                }
            }
        }
        z = false;
        if (z) {
        }
        if (this.E != null) {
            if (this.F) {
                this.E.b();
            } else {
                this.E.a();
            }
        }
        try {
            this.d.a(false);
        } catch (Throwable th2) {
            cw.a(th2, "APS", "getLocation getCgiListParam");
        }
        try {
            this.c.b(true);
            this.k = this.c.b();
        } catch (Throwable th22) {
            cw.a(th22, "APS", "getLocation getScanResultsParam");
        }
        this.N = m();
        if (TextUtils.isEmpty(this.N)) {
            this.e.a(this.m, this.N);
            this.p = n.i(this.a);
            context = this.a;
            obj2 = this.p;
            if (M) {
                sharedPreferences = context.getSharedPreferences("pref", 0);
                obj = o.a(obj2.getBytes("UTF-8"));
                if (TextUtils.isEmpty(obj)) {
                    sharedPreferences.edit().putString("smac", obj).commit();
                }
                M = true;
            }
            if (TextUtils.isEmpty(this.p)) {
                this.p = "00:00:00:00:00:00";
            }
            this.A = a(this.A);
            if (this.c.h()) {
                return a(15, "networkLocation has been mocked!");
            }
            if (this.o != 0) {
                if (de.b() - this.o <= RefreshTipView.FIRST_REFRESH_INTERVAL) {
                }
            }
            a = this.g.a(this.d, z2, this.n, this.c, this.A, this.N, this.a);
            if (de.a(a)) {
                this.n = a(false, true);
                if (de.a(this.n)) {
                    this.n.e("new");
                    this.g.a(this.A.toString());
                    this.g.a(this.d.c());
                    this.e.a(this.n.toJson(1));
                    this.e.c();
                }
            } else {
                b(a);
            }
            this.g.a(this.N, this.A, this.n, this.a, true);
            this.h.a(this.a, this.N, this.n);
            if (de.a(this.n)) {
                ch chVar2 = this.e;
                ck ckVar2 = this.g;
                String str22 = this.N;
                String stringBuilder2 = this.A.toString();
                AMapLocationClientOption aMapLocationClientOption2 = this.m;
                Context context22 = this.a;
                this.n = chVar2.a(ckVar2, str22, stringBuilder2, aMapLocationClientOption2, m(), this.n);
            }
            this.A.delete(0, this.A.length());
            if (this.F) {
            }
            this.n.setAltitude(0.0d);
            this.n.setBearing(0.0f);
            this.n.setSpeed(0.0f);
            return this.n;
        }
        this.n = this.i.e();
        if (this.n == null) {
        }
    }

    public final void d() {
        try {
            a(this.a);
            a(this.m);
            Context context = this.a;
            h();
            a(a(true, true));
        } catch (Throwable th) {
            cw.a(th, "APS", "doFusionLocation");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"NewApi"})
    public final void e() {
        /*
        r4 = this;
        r0 = 0;
        r3 = 0;
        r4.I = r3;
        r4.B = r0;
        r4.C = r0;
        r0 = r4.f;
        if (r0 == 0) goto L_0x0011;
    L_0x000c:
        r0 = r4.f;
        r0.b();
    L_0x0011:
        r0 = r4.i;
        if (r0 == 0) goto L_0x001a;
    L_0x0015:
        r0 = r4.i;
        r0.a();
    L_0x001a:
        r0 = r4.g;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r4.g;
        r1 = r4.a;
        r0.b(r1);
    L_0x0025:
        r0 = r4.H;
        if (r0 == 0) goto L_0x002e;
    L_0x0029:
        r0 = r4.H;
        r0.a();
    L_0x002e:
        com.loc.de.g();
        r0 = r4.a;	 Catch:{ Throwable -> 0x0088 }
        if (r0 == 0) goto L_0x0040;
    L_0x0035:
        r0 = r4.l;	 Catch:{ Throwable -> 0x0088 }
        if (r0 == 0) goto L_0x0040;
    L_0x0039:
        r0 = r4.a;	 Catch:{ Throwable -> 0x0088 }
        r1 = r4.l;	 Catch:{ Throwable -> 0x0088 }
        r0.unregisterReceiver(r1);	 Catch:{ Throwable -> 0x0088 }
    L_0x0040:
        r4.l = r3;
    L_0x0042:
        r0 = r4.d;
        if (r0 == 0) goto L_0x004b;
    L_0x0046:
        r0 = r4.d;
        r0.g();
    L_0x004b:
        r0 = r4.h;
        if (r0 == 0) goto L_0x0054;
    L_0x004f:
        r0 = r4.h;
        r0.a();
    L_0x0054:
        r0 = r4.c;
        if (r0 == 0) goto L_0x005d;
    L_0x0058:
        r0 = r4.c;
        r0.j();
    L_0x005d:
        r0 = r4.k;
        if (r0 == 0) goto L_0x0066;
    L_0x0061:
        r0 = r4.k;
        r0.clear();
    L_0x0066:
        r0 = r4.E;
        if (r0 == 0) goto L_0x006f;
    L_0x006a:
        r0 = r4.E;
        r0.f();
    L_0x006f:
        r4.n = r3;
        r4.a = r3;
        r0 = r4.e;
        if (r0 == 0) goto L_0x007c;
    L_0x0077:
        r0 = r4.e;
        r0.b();
    L_0x007c:
        r4.A = r3;
        r0 = r4.j;
        if (r0 == 0) goto L_0x0087;
    L_0x0082:
        r0 = r4.j;
        r0.d();
    L_0x0087:
        return;
    L_0x0088:
        r0 = move-exception;
        r1 = "APS";
        r2 = "destroy";
        com.loc.cw.a(r0, r1, r2);	 Catch:{ all -> 0x0093 }
        r4.l = r3;
        goto L_0x0042;
    L_0x0093:
        r0 = move-exception;
        r4.l = r3;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bu.e():void");
    }

    public final void f() {
        try {
            if (this.i != null) {
                this.i.c();
            }
        } catch (Throwable th) {
            cw.a(th, "APS", "bindAMapService");
        }
    }

    public final void g() {
        try {
            if (this.i != null) {
                this.i.d();
            }
        } catch (Throwable th) {
            cw.a(th, "APS", "bindOtherService");
        }
    }

    public final void h() {
        try {
            if (!this.B) {
                if (this.N != null) {
                    this.N = null;
                }
                if (this.A != null) {
                    this.A.delete(0, this.A.length());
                }
                if (this.x) {
                    l();
                }
                this.d.a(true);
                this.c.b(this.x);
                this.k = this.c.b();
                this.N = m();
                if (!TextUtils.isEmpty(this.N)) {
                    this.A = a(this.A);
                }
                this.B = true;
            }
        } catch (Throwable th) {
            cw.a(th, "APS", "initFirstLocateParam");
        }
    }

    public final AMapLocationServer i() {
        k();
        if (this.c.h()) {
            return a(15, "networkLocation has been mocked!");
        }
        if (TextUtils.isEmpty(this.N)) {
            return a(this.D, this.t.toString());
        }
        AMapLocationServer a = this.g.a(this.a, this.N, this.A, true);
        if (!de.a(a)) {
            return a;
        }
        b(a);
        return a;
    }

    public final void j() {
        this.f.a(this.a, this.s, this.c, this.m, this.b);
    }
}
