package com.loc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.util.h;
import com.baidu.mobstat.Config;
import java.io.File;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class n {
    private static String a = "";
    private static boolean b = false;
    private static String c = null;
    private static String d = "";
    private static String e = "";
    private static String f = "";
    private static String g = "";

    static class a extends DefaultHandler {
        a() {
        }

        public final void characters(char[] cArr, int i, int i2) throws SAXException {
            if (n.b) {
                n.a = new String(cArr, i, i2);
            }
        }

        public final void endElement(String str, String str2, String str3) throws SAXException {
            n.b = false;
        }

        public final void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                n.b = true;
            }
        }
    }

    public static String a() {
        return c;
    }

    public static String a(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                if (((ScanResult) list.get(i2 - 1)).level > ((ScanResult) list.get(i2)).level) {
                    ScanResult scanResult = (ScanResult) list.get(i2 - 1);
                    list.set(i2 - 1, list.get(i2));
                    list.set(i2, scanResult);
                }
            }
        }
        return list;
    }

    public static void a(String str) {
        c = str;
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String b(Context context) {
        String str = "";
        try {
            String str2 = "";
            String r = r(context);
            return (r == null || r.length() < 5) ? str2 : r.substring(3, 5);
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }

    public static void b() {
        try {
            if (VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(40964)});
            }
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "setTraficTag");
        }
    }

    public static int c(Context context) {
        try {
            return x(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int d(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    private static String d() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] bArr = null;
                    if (VERSION.SDK_INT >= 9) {
                        bArr = networkInterface.getHardwareAddress();
                    }
                    if (bArr == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b : bArr) {
                        String toUpperCase = Integer.toHexString(b & 255).toUpperCase();
                        if (toUpperCase.length() == 1) {
                            stringBuilder.append("0");
                        }
                        stringBuilder.append(toUpperCase).append(Config.TRACE_TODAY_VISIT_SPLIT);
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Throwable e) {
            w.a(e, "DeviceInfo", "getMacAddr");
        }
        return "";
    }

    public static String e(Context context) {
        try {
            return t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        try {
            if (a != null && !"".equals(a)) {
                return a;
            }
            if (a(context, "android.permission.WRITE_SETTINGS")) {
                a = System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
            }
            if (!(a == null || "".equals(a))) {
                return a;
            }
            try {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                    if (file.exists()) {
                        SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                    }
                }
            } catch (Throwable th) {
            }
            return a == null ? "" : a;
        } catch (Throwable th2) {
        }
    }

    static String g(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        try {
            if (!a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return str;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return str;
            }
            String bssid;
            if (wifiManager.isWifiEnabled()) {
                bssid = wifiManager.getConnectionInfo().getBSSID();
                return bssid;
            }
            bssid = str;
            return bssid;
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getWifiMacs");
        }
    }

    static String h(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        if (context != null) {
            try {
                if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    if (wifiManager == null) {
                        return "";
                    }
                    if (wifiManager.isWifiEnabled()) {
                        List scanResults = wifiManager.getScanResults();
                        if (scanResults == null || scanResults.size() == 0) {
                            return stringBuilder.toString();
                        }
                        List a = a(scanResults);
                        Object obj = 1;
                        int i = 0;
                        while (i < a.size() && i < 7) {
                            ScanResult scanResult = (ScanResult) a.get(i);
                            if (obj != null) {
                                obj = null;
                            } else {
                                stringBuilder.append(h.b);
                            }
                            stringBuilder.append(scanResult.BSSID);
                            i++;
                        }
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable th) {
                w.a(th, "DeviceInfo", "getWifiMacs");
            }
        }
        return stringBuilder.toString();
    }

    public static String i(Context context) {
        try {
            if (d != null && !"".equals(d)) {
                return d;
            }
            if (!a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return d;
            }
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            d = wifiManager.getConnectionInfo().getMacAddress();
            if (Config.DEF_MAC_ID.equals(d) || "00:00:00:00:00:00".equals(d)) {
                d = d();
            }
            return d;
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getDeviceMac");
        }
    }

    static String[] j(Context context) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE") && a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager == null) {
                    return new String[]{"", ""};
                }
                CellLocation cellLocation = telephonyManager.getCellLocation();
                int cid;
                int lac;
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    cid = gsmCellLocation.getCid();
                    lac = gsmCellLocation.getLac();
                    return new String[]{lac + "||" + cid, "gsm"};
                }
                if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    cid = cdmaCellLocation.getSystemId();
                    lac = cdmaCellLocation.getNetworkId();
                    int baseStationId = cdmaCellLocation.getBaseStationId();
                    return new String[]{cid + "||" + lac + "||" + baseStationId, "cdma"};
                }
                return new String[]{"", ""};
            }
            return new String[]{"", ""};
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "cellInfo");
        }
    }

    static String k(Context context) {
        String str = "";
        try {
            if (!a(context, "android.permission.READ_PHONE_STATE")) {
                return str;
            }
            TelephonyManager y = y(context);
            if (y == null) {
                return "";
            }
            String networkOperator = y.getNetworkOperator();
            return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? str : networkOperator.substring(3);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getMNC");
            return str;
        }
    }

    public static int l(Context context) {
        try {
            return x(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getNetWorkType");
            return -1;
        }
    }

    public static int m(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getActiveNetWorkType");
            return -1;
        }
    }

    public static NetworkInfo n(Context context) {
        if (!a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return null;
        }
        ConnectivityManager w = w(context);
        return w != null ? w.getActiveNetworkInfo() : null;
    }

    static String o(Context context) {
        String str = null;
        try {
            NetworkInfo n = n(context);
            if (n != null) {
                str = n.getExtraInfo();
            }
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getNetworkExtraInfo");
        }
        return str;
    }

    static String p(Context context) {
        try {
            if (e != null && !"".equals(e)) {
                return e;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return "";
            }
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            e = i2 > i ? i + "*" + i2 : i2 + "*" + i;
            return e;
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getReslution");
        }
    }

    public static String q(Context context) {
        try {
            if (f != null && !"".equals(f)) {
                return f;
            }
            if (!a(context, "android.permission.READ_PHONE_STATE")) {
                return f;
            }
            TelephonyManager y = y(context);
            if (y == null) {
                return "";
            }
            String deviceId = y.getDeviceId();
            f = deviceId;
            if (deviceId == null) {
                f = "";
            }
            return f;
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getDeviceID");
        }
    }

    public static String r(Context context) {
        String str = "";
        try {
            str = t(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getSubscriberId");
        }
        return str;
    }

    static String s(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    private static String t(Context context) {
        if (g != null && !"".equals(g)) {
            return g;
        }
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return g;
        }
        TelephonyManager y = y(context);
        if (y == null) {
            return "";
        }
        String subscriberId = y.getSubscriberId();
        g = subscriberId;
        if (subscriberId == null) {
            g = "";
        }
        return g;
    }

    private static String u(Context context) {
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelephonyManager y = y(context);
        if (y == null) {
            return "";
        }
        Object simOperatorName = y.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? y.getNetworkOperatorName() : simOperatorName;
    }

    private static int v(Context context) {
        if (context == null || !a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return -1;
        }
        ConnectivityManager w = w(context);
        if (w == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = w.getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getType() : -1;
    }

    private static ConnectivityManager w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static int x(Context context) {
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return -1;
        }
        TelephonyManager y = y(context);
        return y != null ? y.getNetworkType() : -1;
    }

    private static TelephonyManager y(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}
