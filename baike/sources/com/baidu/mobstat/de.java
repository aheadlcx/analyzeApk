package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.live.ui.NetworkDiagnosisActivity;

public class de {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static final Pattern d = Pattern.compile("\\s*|\t|\r|\n");

    public static String a(Context context, String str) {
        String str2 = "";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return str2;
            }
            Object obj = null;
            if (applicationInfo.metaData != null) {
                obj = applicationInfo.metaData.get(str);
            }
            if (obj != null) {
                return obj.toString();
            }
            db.a("null,can't find information for key:" + str);
            return str2;
        } catch (Exception e) {
            return str2;
        }
    }

    public static String a(int i, Context context) {
        try {
            return ct.c(i, a(context).getBytes());
        } catch (Throwable e) {
            db.a(e);
            return "";
        }
    }

    public static String a(Context context) {
        return d.matcher(dg.a(context)).replaceAll("");
    }

    public static int b(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = d(context);
        } catch (Throwable e) {
            db.a(e);
        }
        return displayMetrics.widthPixels;
    }

    public static int c(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            displayMetrics = d(context);
        } catch (Throwable e) {
            db.a(e);
        }
        return displayMetrics.heightPixels;
    }

    public static DisplayMetrics d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int e(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            db.b("Get app version code exception");
            return 1;
        }
    }

    public static String f(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            db.b("get app version name exception");
            return "";
        }
    }

    public static String g(Context context) {
        String str = "%s_%s_%s";
        String format = String.format("%s_%s_%s", new Object[]{Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)});
        try {
            if (cu.e(context, "android.permission.ACCESS_FINE_LOCATION") || cu.e(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
                db.a(cellLocation + "");
                if (cellLocation == null) {
                    return format;
                }
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    r3 = new Object[3];
                    r3[0] = String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getCid())});
                    r3[1] = String.format("%d", new Object[]{Integer.valueOf(gsmCellLocation.getLac())});
                    r3[2] = Integer.valueOf(0);
                    return String.format("%s_%s_%s", r3);
                }
                String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                return String.format("%s_%s_%s", new Object[]{split[0], split[3], split[4]});
            }
        } catch (Throwable e) {
            db.a("Get Location", e);
        }
        return format;
    }

    public static String h(Context context) {
        String str = "";
        try {
            if (cu.e(context, "android.permission.ACCESS_FINE_LOCATION")) {
                Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps");
                db.b("location: " + lastKnownLocation);
                if (lastKnownLocation != null) {
                    return String.format("%s_%s_%s", new Object[]{Long.valueOf(lastKnownLocation.getTime()), Double.valueOf(lastKnownLocation.getLongitude()), Double.valueOf(lastKnownLocation.getLatitude())});
                }
            }
        } catch (Throwable e) {
            db.b(e);
        }
        return str;
    }

    public static String b(int i, Context context) {
        Object j = j(context);
        return TextUtils.isEmpty(j) ? "" : ct.c(i, j.getBytes());
    }

    public static String i(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23) {
            return j(context);
        }
        return c();
    }

    public static String j(Context context) {
        try {
            if (cu.e(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                if (connectionInfo != null) {
                    Object macAddress = connectionInfo.getMacAddress();
                    if (!TextUtils.isEmpty(macAddress)) {
                        return macAddress;
                    }
                }
            }
            db.c("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add: android.permission.ACCESS_WIFI_STATE");
        } catch (Throwable e) {
            db.b(e);
        }
        return "";
    }

    @TargetApi(9)
    private static String c() {
        if (VERSION.SDK_INT < 9) {
            return "";
        }
        try {
            String str = "wlan0";
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase(str)) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Throwable th) {
            db.b(th);
        }
        return "";
    }

    private static String a(byte b) {
        String str = "00" + Integer.toHexString(b) + Config.TRACE_TODAY_VISIT_SPLIT;
        return str.substring(str.length() - 3);
    }

    public static String c(int i, Context context) {
        Object d = d(i, context);
        String str = null;
        if (!TextUtils.isEmpty(d)) {
            str = ct.c(i, d.getBytes());
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    public static String d(int i, Context context) {
        String a = a();
        if (TextUtils.isEmpty(a)) {
            a = e(i, context);
        }
        if (TextUtils.isEmpty(a)) {
            return "";
        }
        return a;
    }

    @SuppressLint({"NewApi"})
    public static String e(int i, Context context) {
        byte[] bArr = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!(inetAddress.isAnyLocalAddress() || !(inetAddress instanceof Inet4Address) || inetAddress.isLoopbackAddress())) {
                        byte[] hardwareAddress;
                        if (inetAddress.isSiteLocalAddress()) {
                            hardwareAddress = networkInterface.getHardwareAddress();
                        } else if (!inetAddress.isLinkLocalAddress()) {
                            bArr = networkInterface.getHardwareAddress();
                            break;
                        } else {
                            hardwareAddress = bArr;
                        }
                        bArr = hardwareAddress;
                    }
                }
            }
        } catch (Throwable e) {
            db.a(e);
        }
        if (bArr != null) {
            for (byte a : bArr) {
                stringBuffer.append(a(a));
            }
            return stringBuffer.substring(0, stringBuffer.length() - 1).replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        String b = b(i, context);
        if (b != null) {
            return b.replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
        }
        return b;
    }

    public static String a() {
        Throwable e;
        Throwable th;
        String str = null;
        StringBuffer stringBuffer = new StringBuffer();
        Reader inputStreamReader;
        try {
            char[] cArr = new char[20];
            inputStreamReader = new InputStreamReader(new FileInputStream("/sys/class/net/eth0/address"));
            while (true) {
                try {
                    int read = inputStreamReader.read(cArr);
                    if (read == -1) {
                        break;
                    } else if (read != cArr.length || cArr[cArr.length - 1] == TokenParser.CR) {
                        for (int i = 0; i < read; i++) {
                            if (cArr[i] != TokenParser.CR) {
                                stringBuffer.append(cArr[i]);
                            }
                        }
                        continue;
                    } else {
                        System.out.print(cArr);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            str = stringBuffer.toString().trim().replaceAll(Config.TRACE_TODAY_VISIT_SPLIT, "");
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (Throwable e3) {
                    db.a(e3);
                }
            }
        } catch (Exception e4) {
            e3 = e4;
            inputStreamReader = null;
            try {
                db.a(e3);
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (Throwable e32) {
                        db.a(e32);
                    }
                }
                return str;
            } catch (Throwable th2) {
                th = th2;
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (Throwable e322) {
                        db.a(e322);
                    }
                }
                throw th;
            }
        } catch (Throwable e3222) {
            inputStreamReader = null;
            th = e3222;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        return str;
    }

    public static String a(Context context, int i) {
        Object u = u(context);
        return TextUtils.isEmpty(u) ? "" : ct.c(i, u.getBytes());
    }

    private static String u(Context context) {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null) {
                String name = defaultAdapter.getName();
                if (name != null) {
                    return name;
                }
            }
        } catch (Throwable e) {
            db.b(e);
        }
        return "";
    }

    public static String f(int i, Context context) {
        Object k = k(context);
        return TextUtils.isEmpty(k) ? "" : ct.c(i, k.getBytes());
    }

    @SuppressLint({"NewApi"})
    public static String k(Context context) {
        String str = Build.BRAND;
        if ("4.1.1".equals(VERSION.RELEASE) && "TCT".equals(str)) {
            return "";
        }
        try {
            if (cu.e(context, "android.permission.BLUETOOTH")) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    str = defaultAdapter.getAddress();
                    if (str != null) {
                        return str;
                    }
                }
            }
        } catch (Throwable e) {
            db.b(e);
        }
        return "";
    }

    public static String l(Context context) {
        Object m = m(context);
        if (TextUtils.isEmpty(m)) {
            return "";
        }
        return cs.a(m.getBytes());
    }

    public static String g(int i, Context context) {
        Object m = m(context);
        if (TextUtils.isEmpty(m)) {
            return "";
        }
        return ct.d(i, m.getBytes());
    }

    public static String m(Context context) {
        boolean z;
        WifiInfo connectionInfo;
        List scanResults;
        JSONArray jSONArray;
        JSONObject jSONObject;
        StringBuilder stringBuilder;
        int i = 0;
        if (context == null) {
            return "";
        }
        if (!cu.e(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        WifiInfo wifiInfo;
        int i2;
        ScanResult scanResult;
        StringBuilder stringBuilder2;
        String replaceAll;
        int i3;
        try {
            boolean isProviderEnabled;
            if (cu.e(context, "android.permission.ACCESS_FINE_LOCATION")) {
                isProviderEnabled = ((LocationManager) context.getSystemService("location")).isProviderEnabled("gps");
            } else {
                isProviderEnabled = false;
            }
            z = isProviderEnabled;
        } catch (Throwable e) {
            Throwable e2;
            db.a(e2);
            z = false;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            connectionInfo = wifiManager.getConnectionInfo();
            try {
                scanResults = wifiManager.getScanResults();
                wifiInfo = connectionInfo;
            } catch (Throwable th) {
                e2 = th;
                db.a(e2);
                scanResults = null;
                wifiInfo = connectionInfo;
                Collections.sort(scanResults, new df());
                jSONArray = new JSONArray();
                i2 = 0;
                while (scanResults != null) {
                    try {
                        scanResult = (ScanResult) scanResults.get(i2);
                        stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(scanResult.BSSID);
                        stringBuilder2.append("|");
                        replaceAll = scanResult.SSID.replaceAll("\\|", "");
                        if (replaceAll.length() > 30) {
                            replaceAll = replaceAll.substring(0, 30);
                        }
                        stringBuilder2.append(replaceAll);
                        stringBuilder2.append("|");
                        stringBuilder2.append(scanResult.level);
                        stringBuilder2.append("|");
                        if (wifiInfo == null) {
                        }
                        stringBuilder2.append(i3);
                        jSONArray.put(stringBuilder2.toString());
                    } catch (Throwable e22) {
                        db.a(e22);
                    }
                    i2++;
                }
                if (jSONArray.length() != 0) {
                    return null;
                }
                jSONObject = new JSONObject();
                try {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(System.currentTimeMillis());
                    stringBuilder.append("|");
                    if (z) {
                        i = 1;
                    }
                    stringBuilder.append(i);
                    stringBuilder.append("|");
                    stringBuilder.append(h(context));
                    jSONObject.put("ap-list", jSONArray);
                    jSONObject.put("meta-data", stringBuilder.toString());
                    return jSONObject.toString();
                } catch (Throwable e222) {
                    db.a(e222);
                    return "";
                }
            }
        } catch (Throwable th2) {
            e222 = th2;
            connectionInfo = null;
            db.a(e222);
            scanResults = null;
            wifiInfo = connectionInfo;
            Collections.sort(scanResults, new df());
            jSONArray = new JSONArray();
            i2 = 0;
            while (scanResults != null) {
                scanResult = (ScanResult) scanResults.get(i2);
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(scanResult.BSSID);
                stringBuilder2.append("|");
                replaceAll = scanResult.SSID.replaceAll("\\|", "");
                if (replaceAll.length() > 30) {
                    replaceAll = replaceAll.substring(0, 30);
                }
                stringBuilder2.append(replaceAll);
                stringBuilder2.append("|");
                stringBuilder2.append(scanResult.level);
                stringBuilder2.append("|");
                if (wifiInfo == null) {
                }
                stringBuilder2.append(i3);
                jSONArray.put(stringBuilder2.toString());
                i2++;
            }
            if (jSONArray.length() != 0) {
                return null;
            }
            jSONObject = new JSONObject();
            stringBuilder = new StringBuilder();
            stringBuilder.append(System.currentTimeMillis());
            stringBuilder.append("|");
            if (z) {
                i = 1;
            }
            stringBuilder.append(i);
            stringBuilder.append("|");
            stringBuilder.append(h(context));
            jSONObject.put("ap-list", jSONArray);
            jSONObject.put("meta-data", stringBuilder.toString());
            return jSONObject.toString();
        }
        if (!(scanResults == null || scanResults.size() == 0)) {
            Collections.sort(scanResults, new df());
        }
        jSONArray = new JSONArray();
        i2 = 0;
        while (scanResults != null && i2 < scanResults.size() && i2 < 30) {
            scanResult = (ScanResult) scanResults.get(i2);
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(scanResult.BSSID);
            stringBuilder2.append("|");
            replaceAll = scanResult.SSID.replaceAll("\\|", "");
            if (replaceAll.length() > 30) {
                replaceAll = replaceAll.substring(0, 30);
            }
            stringBuilder2.append(replaceAll);
            stringBuilder2.append("|");
            stringBuilder2.append(scanResult.level);
            stringBuilder2.append("|");
            i3 = (wifiInfo == null && scanResult.BSSID.equals(wifiInfo.getBSSID())) ? 1 : 0;
            stringBuilder2.append(i3);
            jSONArray.put(stringBuilder2.toString());
            i2++;
        }
        if (jSONArray.length() != 0) {
            return null;
        }
        jSONObject = new JSONObject();
        stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append("|");
        if (z) {
            i = 1;
        }
        stringBuilder.append(i);
        stringBuilder.append("|");
        stringBuilder.append(h(context));
        jSONObject.put("ap-list", jSONArray);
        jSONObject.put("meta-data", stringBuilder.toString());
        return jSONObject.toString();
    }

    public static boolean n(Context context) {
        if (context == null) {
            return false;
        }
        try {
            boolean z;
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } catch (Throwable e) {
            db.a(e);
            return false;
        }
    }

    public static String o(Context context) {
        Throwable e;
        String str = "";
        String typeName;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return str;
            }
            typeName = activeNetworkInfo.getTypeName();
            try {
                if (typeName.equals(NetworkDiagnosisActivity.NETWORKTYPE_WIFI) || activeNetworkInfo.getSubtypeName() == null) {
                    return typeName;
                }
                return activeNetworkInfo.getSubtypeName();
            } catch (Exception e2) {
                e = e2;
                db.a(e);
                return typeName;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            typeName = str;
            e = th;
            db.a(e);
            return typeName;
        }
    }

    public static String p(Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return "";
    }

    public static String h(int i, Context context) {
        Object p = p(context);
        if (!TextUtils.isEmpty(p)) {
            try {
                return ct.c(i, p.getBytes());
            } catch (Throwable e) {
                db.b(e);
            }
        }
        return "";
    }

    private static String v(Context context) {
        String str = a;
        if (str != null) {
            return str;
        }
        String str2;
        try {
            List runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            int i = 0;
            while (runningAppProcesses != null && i < runningAppProcesses.size()) {
                RunningAppProcessInfo runningAppProcessInfo = (RunningAppProcessInfo) runningAppProcesses.get(i);
                if (runningAppProcessInfo != null && runningAppProcessInfo.pid == Process.myPid()) {
                    str2 = runningAppProcessInfo.processName;
                    break;
                }
                i++;
            }
        } catch (Throwable e) {
            db.b(e);
        }
        str2 = str;
        if (str2 == null) {
            str2 = "";
        }
        a = str2;
        return str2;
    }

    private static String b(Context context, String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf <= 0 || lastIndexOf + 1 >= str.length()) {
            return null;
        }
        return str.substring(lastIndexOf + 1);
    }

    private static String c(Context context, String str) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        String str2 = applicationInfo.processName;
        if (str2 == null || str2.equals(str)) {
            str = null;
        }
        return str;
    }

    public static String q(Context context) {
        String str = b;
        if (str == null) {
            String v = v(context);
            str = b(context, v);
            if (TextUtils.isEmpty(str)) {
                str = c(context, v);
            }
            if (str == null) {
                str = "";
            }
            b = str;
        }
        return str;
    }

    public static String r(Context context) {
        String str = "";
        String v = v(context);
        if (v == null) {
            return str;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
        } catch (NameNotFoundException e) {
        }
        if (packageInfo == null) {
            return str;
        }
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr == null) {
            return str;
        }
        for (ServiceInfo serviceInfo : serviceInfoArr) {
            if (v.equals(serviceInfo.processName)) {
                str = serviceInfo.name;
                break;
            }
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean s(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        } catch (Throwable e) {
            db.b(e);
            return false;
        }
    }

    public static String t(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("m", memoryInfo.availMem);
            jSONObject.put("l", memoryInfo.lowMemory);
            jSONObject.put("t", memoryInfo.threshold);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_mem", jSONArray);
            jSONObject2.put("meta-data", stringBuilder.toString());
            return cs.a(jSONObject2.toString().getBytes());
        } catch (Throwable e) {
            db.a(e);
            return "";
        }
    }

    public static String b() {
        if (c != null) {
            return c;
        }
        String str = "";
        if (!TextUtils.isEmpty(a("ro.miui.ui.version.name"))) {
            str = "miui";
        } else if (!TextUtils.isEmpty(a("ro.build.version.opporom"))) {
            str = "coloros";
        } else if (!TextUtils.isEmpty(a("ro.build.version.emui"))) {
            str = "emui";
        } else if (!TextUtils.isEmpty(a("ro.vivo.os.version"))) {
            str = "funtouch";
        } else if (!TextUtils.isEmpty(a("ro.smartisan.version"))) {
            str = "smartisan";
        }
        if (TextUtils.isEmpty(str)) {
            Object a = a("ro.build.display.id");
            if (!TextUtils.isEmpty(a) && a.contains("Flyme")) {
                str = "flyme";
            }
        }
        c = str;
        return c;
    }

    private static String a(String str) {
        Process exec;
        BufferedReader bufferedReader;
        Throwable th;
        Object obj;
        String str2 = null;
        try {
            exec = Runtime.getRuntime().exec("getprop " + str);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()), 1024);
                try {
                    str2 = bufferedReader.readLine();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e) {
                        }
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                } catch (Exception e2) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e4) {
                        }
                    }
                    if (exec != null) {
                        exec.destroy();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                obj = str2;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                return str2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = str2;
                th = th4;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
        } catch (Exception e6) {
            exec = str2;
            bufferedReader = str2;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            return str2;
        } catch (Throwable th5) {
            bufferedReader = str2;
            String str3 = str2;
            th = th5;
            exec = str3;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            throw th;
        }
        return str2;
    }
}
