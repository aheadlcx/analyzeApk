package com.baidu.location.b;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.location.a.i;
import com.baidu.location.a.q;
import com.baidu.location.a.s;
import com.baidu.location.f;
import com.baidu.mobstat.Config;
import java.util.List;

public class h {
    public static long a = 0;
    private static h b = null;
    private WifiManager c = null;
    private a d = null;
    private g e = null;
    private long f = 0;
    private long g = 0;
    private boolean h = false;
    private Handler i = new Handler();

    private class a extends BroadcastReceiver {
        final /* synthetic */ h a;
        private long b;
        private boolean c;

        private a(h hVar) {
            this.a = hVar;
            this.b = 0;
            this.c = false;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                String action = intent.getAction();
                if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    h.a = System.currentTimeMillis() / 1000;
                    this.a.p();
                    i.c().h();
                    if (System.currentTimeMillis() - q.b() <= Config.BPLUS_DELAY_TIME) {
                        s.a(q.c(), this.a.l(), q.d(), q.a());
                    }
                } else if (action.equals("android.net.wifi.STATE_CHANGE") && ((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState().equals(State.CONNECTED) && System.currentTimeMillis() - this.b >= Config.BPLUS_DELAY_TIME) {
                    this.b = System.currentTimeMillis();
                    if (!this.c) {
                        this.c = true;
                    }
                }
            }
        }
    }

    private h() {
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    private String a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    public static boolean a(g gVar, g gVar2, float f) {
        if (gVar == null || gVar2 == null) {
            return false;
        }
        List list = gVar.a;
        List list2 = gVar2.a;
        if (list == list2) {
            return true;
        }
        if (list == null || list2 == null) {
            return false;
        }
        int size = list.size();
        int size2 = list2.size();
        float f2 = (float) (size + size2);
        if (size == 0 && size2 == 0) {
            return true;
        }
        if (size == 0 || size2 == 0) {
            return false;
        }
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            String str = ((ScanResult) list.get(i)).BSSID;
            if (str == null) {
                i3 = i2;
            } else {
                for (int i4 = 0; i4 < size2; i4++) {
                    if (str.equals(((ScanResult) list2.get(i4)).BSSID)) {
                        i3 = i2 + 1;
                        break;
                    }
                }
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return ((float) (i2 * 2)) > f2 * f;
    }

    public static boolean h() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) f.getServiceContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    private void p() {
        if (this.c != null) {
            try {
                List scanResults = this.c.getScanResults();
                if (scanResults != null) {
                    g gVar = new g(scanResults, System.currentTimeMillis());
                    if (this.e == null || !gVar.a(this.e)) {
                        this.e = gVar;
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public synchronized void b() {
        if (!this.h) {
            if (f.isServing) {
                this.c = (WifiManager) f.getServiceContext().getSystemService("wifi");
                this.d = new a();
                try {
                    f.getServiceContext().registerReceiver(this.d, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
                } catch (Exception e) {
                }
                this.h = true;
            }
        }
    }

    public synchronized void c() {
        if (this.h) {
            try {
                f.getServiceContext().unregisterReceiver(this.d);
                a = 0;
            } catch (Exception e) {
            }
            this.d = null;
            this.c = null;
            this.h = false;
        }
    }

    public boolean d() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.g > 0 && currentTimeMillis - this.g <= Config.BPLUS_DELAY_TIME) {
            return false;
        }
        this.g = currentTimeMillis;
        return e();
    }

    public boolean e() {
        if (this.c == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f > 0) {
            if (currentTimeMillis - this.f <= Config.BPLUS_DELAY_TIME || currentTimeMillis - (a * 1000) <= Config.BPLUS_DELAY_TIME) {
                return false;
            }
            if (h() && currentTimeMillis - this.f <= 10000) {
                return false;
            }
        }
        return g();
    }

    public String f() {
        String str = "";
        if (this.c == null) {
            return str;
        }
        try {
            return (this.c.isWifiEnabled() || (VERSION.SDK_INT > 17 && this.c.isScanAlwaysAvailable())) ? "&wifio=1" : str;
        } catch (NoSuchMethodError e) {
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    @SuppressLint({"NewApi"})
    public boolean g() {
        try {
            if (!this.c.isWifiEnabled() && (VERSION.SDK_INT <= 17 || !this.c.isScanAlwaysAvailable())) {
                return false;
            }
            this.c.startScan();
            this.f = System.currentTimeMillis();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    public WifiInfo i() {
        if (this.c == null) {
            return null;
        }
        try {
            WifiInfo connectionInfo = this.c.getConnectionInfo();
            if (connectionInfo == null || connectionInfo.getBSSID() == null) {
                return null;
            }
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                bssid = bssid.replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
                if ("000000000000".equals(bssid) || "".equals(bssid)) {
                    return null;
                }
            }
            return connectionInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public String j() {
        StringBuffer stringBuffer = new StringBuffer();
        WifiInfo i = a().i();
        if (i == null || i.getBSSID() == null) {
            return null;
        }
        String replace = i.getBSSID().replace(Config.TRACE_TODAY_VISIT_SPLIT, "");
        int rssi = i.getRssi();
        String k = a().k();
        if (rssi < 0) {
            rssi = -rssi;
        }
        if (replace == null) {
            return null;
        }
        stringBuffer.append("&wf=");
        stringBuffer.append(replace);
        stringBuffer.append(com.alipay.sdk.util.h.b);
        stringBuffer.append("" + rssi + com.alipay.sdk.util.h.b);
        stringBuffer.append(i.getSSID());
        stringBuffer.append("&wf_n=1");
        if (k != null) {
            stringBuffer.append("&wf_gt=");
            stringBuffer.append(k);
        }
        return stringBuffer.toString();
    }

    public String k() {
        if (this.c == null) {
            return null;
        }
        DhcpInfo dhcpInfo = this.c.getDhcpInfo();
        return dhcpInfo != null ? a((long) dhcpInfo.gateway) : null;
    }

    public g l() {
        return (this.e == null || !this.e.f()) ? n() : this.e;
    }

    public g m() {
        return (this.e == null || !this.e.g()) ? n() : this.e;
    }

    public g n() {
        if (this.c != null) {
            try {
                return new g(this.c.getScanResults(), this.f);
            } catch (Exception e) {
            }
        }
        return new g(null, 0);
    }

    public String o() {
        String str = null;
        try {
            WifiInfo connectionInfo = this.c.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getMacAddress();
            }
        } catch (Exception e) {
        }
        return str;
    }
}
