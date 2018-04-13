package com.baidu.mobads.production.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.h;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class c {
    private Set<String> a;
    private Set<String> b;
    private Set<String> c;
    private CookieManager d;
    private IXAdSystemUtils e = XAdSDKFoundationFacade.getInstance().getSystemUtils();
    private IXAdCommonUtils f = XAdSDKFoundationFacade.getInstance().getCommonUtils();
    private Context g;
    private int h;
    private String i;
    private String j;

    public c(Context context, int i, String str) {
        this.g = context;
        this.h = i;
        this.i = str;
        this.j = null;
        b();
        c();
    }

    public c(Context context, String str, String str2) {
        this.g = context;
        this.j = str;
        this.i = str2;
        this.h = -1;
        b();
        c();
    }

    private void b() {
        try {
            CookieSyncManager.createInstance(this.g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            this.d = CookieManager.getInstance();
            this.d.setAcceptCookie(true);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public String a() {
        d();
        if (this.j != null) {
            return "https://cpu.baidu.com/block/app/" + this.i + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.j;
        }
        return "https://cpu.baidu.com/" + this.h + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.i;
    }

    private void c() {
        this.a = new HashSet();
        this.a.add("46000");
        this.a.add("46002");
        this.a.add("46007");
        this.b = new HashSet();
        this.b.add("46001");
        this.b.add("46006");
        this.c = new HashSet();
        this.c.add("46003");
        this.c.add("46005");
    }

    private void d() {
        int f;
        Object obj = null;
        int i = 0;
        Rect screenRect = this.f.getScreenRect(this.g);
        int height = screenRect.height();
        int width = screenRect.width();
        boolean e = e();
        Object a = e ? a(h()) : null;
        if (e) {
            f = f();
        } else {
            f = 0;
        }
        if (e) {
            obj = g();
        }
        if (e) {
            i = 1;
        }
        String cuid = this.e.getCUID(this.g);
        a("v", i());
        a("im", this.e.getIMEI(this.g));
        a("aid", this.e.getAndroidId(this.g));
        a("m", a(this.e.getMacAddress(this.g)));
        a("cuid", cuid);
        a(Config.EXCEPTION_CRASH_TYPE, Integer.valueOf(a.a(this.g)));
        a("oi", Integer.valueOf(j()));
        a("src", Integer.valueOf(1));
        a("h", Integer.valueOf(height));
        a("w", Integer.valueOf(width));
        a("apm", a);
        a("rssi", Integer.valueOf(f));
        a("apn", obj);
        a("isc", Integer.valueOf(i));
    }

    private void a(String str, Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("=");
        stringBuffer.append(obj);
        stringBuffer.append(h.b);
        try {
            this.d.setCookie("https://cpu.baidu.com/", stringBuffer.toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean e() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.g.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int f() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo == null ? 0 : connectionInfo.getRssi();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private String g() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService("wifi")).getConnectionInfo();
            String ssid = connectionInfo == null ? "" : connectionInfo.getSSID();
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                return ssid.substring(1, ssid.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String h() {
        try {
            WifiInfo connectionInfo = ((WifiManager) this.g.getSystemService("wifi")).getConnectionInfo();
            return connectionInfo == null ? null : connectionInfo.getMacAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String i() {
        try {
            PackageInfo packageInfo = this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0);
            String str = packageInfo == null ? null : packageInfo.versionName;
            if (str != null) {
                return str.replace(".", Constants.ACCEPT_TIME_SEPARATOR_SERVER);
            }
            return null;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int j() {
        String networkOperator = this.e.getNetworkOperator(this.g);
        if (networkOperator == null) {
            return 0;
        }
        if (this.a.contains(networkOperator)) {
            return 1;
        }
        if (this.c.contains(networkOperator)) {
            return 2;
        }
        if (this.b.contains(networkOperator)) {
            return 3;
        }
        return 99;
    }

    private String a(String str) {
        return str == null ? null : str.replace(Config.TRACE_TODAY_VISIT_SPLIT, Constants.ACCEPT_TIME_SEPARATOR_SERVER);
    }
}
