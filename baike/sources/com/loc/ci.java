package com.loc;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.qq.e.comm.constants.ErrorCode$OtherError;
import com.umeng.commonsdk.proguard.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.im.TimeUtils;
import qsbk.app.widget.RefreshTipView;

public final class ci {
    static long e = 0;
    static long f = 0;
    static long g = 0;
    static long h = 0;
    WifiManager a;
    Object b = new Object();
    ArrayList<ScanResult> c = new ArrayList();
    ArrayList<ScanResult> d = new ArrayList();
    Context i;
    boolean j = false;
    StringBuilder k = null;
    boolean l = true;
    boolean m = true;
    String n = "isScanAlwaysAvailable";
    String o = null;
    TreeMap<Integer, ScanResult> p = null;
    public boolean q = true;
    ConnectivityManager r = null;
    private volatile WifiInfo s = null;

    public ci(Context context, WifiManager wifiManager) {
        this.a = wifiManager;
        this.i = context;
    }

    private static boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (Throwable e) {
            cw.a(e, "APS", "wifiSigFine");
        }
        return i2 > 0;
    }

    public static boolean a(WifiInfo wifiInfo) {
        return (wifiInfo == null || TextUtils.isEmpty(wifiInfo.getSSID()) || !de.b(wifiInfo.getBSSID())) ? false : true;
    }

    public static String k() {
        return String.valueOf(de.b() - h);
    }

    private List<ScanResult> l() {
        if (this.a != null) {
            try {
                List<ScanResult> scanResults = this.a.getScanResults();
                this.o = null;
                return scanResults;
            } catch (SecurityException e) {
                this.o = e.getMessage();
            } catch (Throwable th) {
                this.o = null;
                cw.a(th, "WifiManagerWrapper", "getScanResults");
            }
        }
        return null;
    }

    private WifiInfo m() {
        try {
            if (this.a != null) {
                return this.a.getConnectionInfo();
            }
        } catch (Throwable th) {
            cw.a(th, "WifiManagerWrapper", "getConnectionInfo");
        }
        return null;
    }

    private boolean n() {
        boolean z = false;
        WifiManager wifiManager = this.a;
        if (wifiManager != null) {
            try {
                z = wifiManager.isWifiEnabled();
            } catch (Throwable th) {
                cw.a(th, "WifiManagerWrapper", "wifiEnabled1");
            }
            if (!z && de.c() > 17) {
                try {
                    z = String.valueOf(cz.a(wifiManager, this.n, new Object[0])).equals("true");
                } catch (Throwable th2) {
                    cw.a(th2, "WifiManagerWrapper", "wifiEnabled");
                }
            }
        }
        return z;
    }

    private void o() {
        if (this.c != null && !this.c.isEmpty()) {
            ScanResult scanResult;
            if (de.b() - h > TimeUtils.HOUR) {
                c();
                this.c.clear();
            }
            if (this.p == null) {
                this.p = new TreeMap(Collections.reverseOrder());
            }
            this.p.clear();
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                scanResult = (ScanResult) this.c.get(i);
                if (de.b(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        scanResult.SSID = "unkwn";
                    } else if (!"<unknown ssid>".equals(scanResult.SSID)) {
                        scanResult.SSID = String.valueOf(i);
                    }
                    this.p.put(Integer.valueOf((scanResult.level * 25) + i), scanResult);
                }
            }
            this.c.clear();
            for (ScanResult scanResult2 : this.p.values()) {
                this.c.add(scanResult2);
            }
            this.p.clear();
        }
    }

    private void p() {
        List list = this.c;
        Collection collection = this.d;
        list.clear();
        synchronized (this.b) {
            if (collection != null) {
                if (collection.size() > 0) {
                    list.addAll(collection);
                }
            }
        }
    }

    private void q() {
        if (r()) {
            try {
                boolean startScan;
                if (de.b() - e >= 4900) {
                    if (this.r == null) {
                        this.r = (ConnectivityManager) de.a(this.i, "connectivity");
                    }
                    if ((!a(this.r) || de.b() - e >= 9900) && this.a != null) {
                        e = de.b();
                        startScan = this.a.startScan();
                        if (startScan) {
                            g = de.b();
                        }
                    }
                }
                startScan = false;
                if (startScan) {
                    g = de.b();
                }
            } catch (Throwable th) {
                cw.a(th, "APS", "updateWifi");
            }
        }
    }

    private boolean r() {
        this.q = n();
        return (this.q && this.l) ? g == 0 ? true : (de.b() - g < 4900 || de.b() - h < 1500) ? false : de.b() - h > 4900 ? true : true : false;
    }

    public final String a() {
        return this.o;
    }

    public final void a(boolean z) {
        Context context = this.i;
        if (this.a != null && context != null && z && de.c() > 17) {
            ContentResolver contentResolver = context.getContentResolver();
            String str = "android.provider.Settings$Global";
            try {
                if (((Integer) cz.a(str, "getInt", new Object[]{contentResolver, "wifi_scan_always_enabled"}, new Class[]{ContentResolver.class, String.class})).intValue() == 0) {
                    cz.a(str, "putInt", new Object[]{contentResolver, "wifi_scan_always_enabled", Integer.valueOf(1)}, new Class[]{ContentResolver.class, String.class, Integer.TYPE});
                }
            } catch (Throwable th) {
                cw.a(th, "WifiManagerWrapper", "enableWifiAlwaysScan");
            }
        }
    }

    public final void a(boolean z, boolean z2) {
        this.l = z;
        this.m = z2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.net.ConnectivityManager r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = r4.a;
        if (r2 != 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r3 = r5.getActiveNetworkInfo();	 Catch:{ Throwable -> 0x001d }
        r3 = com.loc.de.a(r3);	 Catch:{ Throwable -> 0x001d }
        if (r3 != r0) goto L_0x0025;
    L_0x0011:
        r2 = r2.getConnectionInfo();	 Catch:{ Throwable -> 0x001d }
        r2 = a(r2);	 Catch:{ Throwable -> 0x001d }
        if (r2 == 0) goto L_0x0025;
    L_0x001b:
        r1 = r0;
        goto L_0x0006;
    L_0x001d:
        r0 = move-exception;
        r2 = "WifiManagerWrapper";
        r3 = "wifiAccess";
        com.loc.cw.a(r0, r2, r3);
    L_0x0025:
        r0 = r1;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ci.a(android.net.ConnectivityManager):boolean");
    }

    public final ArrayList<ScanResult> b() {
        return this.c;
    }

    public final void b(boolean z) {
        if (!z) {
            q();
        } else if (r()) {
            long b = de.b();
            if (b - f >= 10000) {
                synchronized (this.b) {
                    this.d.clear();
                }
            }
            q();
            if (b - f >= 10000) {
                for (int i = 20; i > 0 && this.d.isEmpty(); i--) {
                    try {
                        Thread.sleep(150);
                    } catch (Throwable th) {
                    }
                }
            }
            synchronized (this.b) {
            }
        }
        if (de.b() - h > RefreshTipView.FIRST_REFRESH_INTERVAL) {
            synchronized (this.b) {
                this.d.clear();
            }
        }
        f = de.b();
        if (this.d.isEmpty()) {
            h = de.b();
            Collection l = l();
            if (l != null) {
                synchronized (this.b) {
                    this.d.addAll(l);
                }
            }
            p();
            o();
        }
    }

    public final void c() {
        this.s = null;
        synchronized (this.b) {
            this.d.clear();
        }
    }

    public final void d() {
        if (this.a != null && de.b() - h > 4900) {
            Collection collection = null;
            try {
                collection = l();
            } catch (Throwable th) {
                cw.a(th, "APS", "onReceive part1");
            }
            if (collection != null) {
                synchronized (this.b) {
                    this.d.clear();
                    this.d.addAll(collection);
                    h = de.b();
                }
            } else {
                synchronized (this.b) {
                    this.d.clear();
                }
            }
            p();
            o();
        }
    }

    public final void e() {
        int i = 4;
        if (this.a != null) {
            try {
                if (this.a != null) {
                    i = this.a.getWifiState();
                }
            } catch (Throwable th) {
                cw.a(th, "APS", "onReceive part");
            }
            if (this.d == null) {
                this.d = new ArrayList();
            }
            switch (i) {
                case 0:
                    c();
                    return;
                case 1:
                    c();
                    return;
                case 4:
                    c();
                    return;
                default:
                    return;
            }
        }
    }

    public final boolean f() {
        return this.q;
    }

    public final WifiInfo g() {
        this.s = m();
        return this.s;
    }

    public final boolean h() {
        return this.j;
    }

    public final String i() {
        boolean z = false;
        if (this.k == null) {
            this.k = new StringBuilder(ErrorCode$OtherError.VIDEO_DOWNLOAD_ERROR);
        } else {
            this.k.delete(0, this.k.length());
        }
        this.j = false;
        String str = "";
        this.s = g();
        if (a(this.s)) {
            Object bssid = this.s.getBSSID();
        } else {
            String str2 = str;
        }
        int size = this.c.size();
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < size) {
            boolean z4;
            String str3;
            String str4 = ((ScanResult) this.c.get(i)).BSSID;
            if (!this.m) {
                if (!"<unknown ssid>".equals(((ScanResult) this.c.get(i)).SSID)) {
                    z4 = true;
                    str3 = "nb";
                    if (bssid.equals(str4)) {
                        str3 = g.P;
                        z2 = true;
                    }
                    this.k.append(String.format(Locale.US, "#%s,%s", new Object[]{str4, str3}));
                    i++;
                    z3 = z4;
                }
            }
            z4 = z3;
            str3 = "nb";
            if (bssid.equals(str4)) {
                str3 = g.P;
                z2 = true;
            }
            this.k.append(String.format(Locale.US, "#%s,%s", new Object[]{str4, str3}));
            i++;
            z3 = z4;
        }
        boolean z5 = this.c.size() == 0 ? true : z3;
        try {
            if (!(this.m || z5)) {
                List configuredNetworks = this.a != null ? this.a.getConfiguredNetworks() : null;
                i = 0;
                while (configuredNetworks != null && i < configuredNetworks.size()) {
                    z4 = this.k.toString().contains(((WifiConfiguration) configuredNetworks.get(i)).BSSID) ? true : z;
                    i++;
                    z = z4;
                }
            }
        } catch (Throwable th) {
        }
        if (!(this.m || z5 || r5)) {
            this.j = true;
        }
        if (!(z2 || TextUtils.isEmpty(bssid))) {
            this.k.append(MqttTopic.MULTI_LEVEL_WILDCARD).append(bssid);
            this.k.append(",access");
        }
        return this.k.toString();
    }

    public final void j() {
        c();
        this.c.clear();
    }
}
