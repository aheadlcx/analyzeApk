package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.live.ui.NetworkDiagnosisActivity;

public final class b {
    private static String a = null;
    private static String b = null;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        if (AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            String deviceId;
            try {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId == null) {
                    return deviceId;
                }
                try {
                    return deviceId.toLowerCase();
                } catch (Throwable th) {
                    x.a("Failed to get IMEI.", new Object[0]);
                    return deviceId;
                }
            } catch (Throwable th2) {
                deviceId = null;
                x.a("Failed to get IMEI.", new Object[0]);
                return deviceId;
            }
        }
        x.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
        return null;
    }

    public static String b(Context context) {
        String subscriberId;
        if (context == null) {
            return null;
        }
        if (AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            try {
                subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (subscriberId == null) {
                    return subscriberId;
                }
                try {
                    return subscriberId.toLowerCase();
                } catch (Throwable th) {
                    x.a("Failed to get IMSI.", new Object[0]);
                    return subscriberId;
                }
            } catch (Throwable th2) {
                subscriberId = null;
                x.a("Failed to get IMSI.", new Object[0]);
                return subscriberId;
            }
        }
        x.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
        return null;
    }

    public static String c(Context context) {
        Throwable th;
        String str = "fail";
        if (context == null) {
            return str;
        }
        try {
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (string != null) {
                return string.toLowerCase();
            }
            try {
                return "null";
            } catch (Throwable th2) {
                Throwable th3 = th2;
                str = string;
                th = th3;
                if (!x.a(th)) {
                    return str;
                }
                x.a("Failed to get Android ID.", new Object[0]);
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            if (!x.a(th)) {
                return str;
            }
            x.a("Failed to get Android ID.", new Object[0]);
            return str;
        }
    }

    public static String d(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        String toLowerCase;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    str = connectionInfo.getMacAddress();
                    toLowerCase = str == null ? "null" : str.toLowerCase();
                    return toLowerCase;
                }
            }
            toLowerCase = str;
        } catch (Throwable th) {
            Throwable th2 = th;
            toLowerCase = str;
            Throwable th3 = th2;
            if (!x.a(th3)) {
                th3.printStackTrace();
            }
        }
        return toLowerCase;
    }

    private static boolean o() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String p() {
        /*
        r6 = 2;
        r1 = 0;
        r0 = "/system/build.prop";
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0065, all -> 0x0093 }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x0065, all -> 0x0093 }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2.<init>(r3, r0);	 Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
    L_0x0010:
        r0 = r2.readLine();	 Catch:{ Throwable -> 0x00bf }
        if (r0 == 0) goto L_0x00c1;
    L_0x0016:
        r4 = "=";
        r5 = 2;
        r0 = r0.split(r4, r5);	 Catch:{ Throwable -> 0x00bf }
        r4 = r0.length;	 Catch:{ Throwable -> 0x00bf }
        if (r4 != r6) goto L_0x0010;
    L_0x0020:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00bf }
        r5 = "ro.product.cpu.abilist";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00bf }
        if (r4 == 0) goto L_0x0040;
    L_0x002b:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00bf }
    L_0x002e:
        if (r0 == 0) goto L_0x0039;
    L_0x0030:
        r4 = ",";
        r0 = r0.split(r4);	 Catch:{ Throwable -> 0x00bf }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00bf }
    L_0x0039:
        r2.close();	 Catch:{ IOException -> 0x004f }
    L_0x003c:
        r3.close();	 Catch:{ IOException -> 0x005a }
    L_0x003f:
        return r0;
    L_0x0040:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00bf }
        r5 = "ro.product.cpu.abi";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00bf }
        if (r4 == 0) goto L_0x0010;
    L_0x004b:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00bf }
        goto L_0x002e;
    L_0x004f:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r1);
        if (r2 != 0) goto L_0x003c;
    L_0x0056:
        r1.printStackTrace();
        goto L_0x003c;
    L_0x005a:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r1);
        if (r2 != 0) goto L_0x003f;
    L_0x0061:
        r1.printStackTrace();
        goto L_0x003f;
    L_0x0065:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x0068:
        r4 = com.tencent.bugly.proguard.x.a(r0);	 Catch:{ all -> 0x00ba }
        if (r4 != 0) goto L_0x0071;
    L_0x006e:
        r0.printStackTrace();	 Catch:{ all -> 0x00ba }
    L_0x0071:
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.close();	 Catch:{ IOException -> 0x007d }
    L_0x0076:
        if (r3 == 0) goto L_0x007b;
    L_0x0078:
        r3.close();	 Catch:{ IOException -> 0x0088 }
    L_0x007b:
        r0 = r1;
        goto L_0x003f;
    L_0x007d:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r0);
        if (r2 != 0) goto L_0x0076;
    L_0x0084:
        r0.printStackTrace();
        goto L_0x0076;
    L_0x0088:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r0);
        if (r2 != 0) goto L_0x007b;
    L_0x008f:
        r0.printStackTrace();
        goto L_0x007b;
    L_0x0093:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x0096:
        if (r2 == 0) goto L_0x009b;
    L_0x0098:
        r2.close();	 Catch:{ IOException -> 0x00a1 }
    L_0x009b:
        if (r3 == 0) goto L_0x00a0;
    L_0x009d:
        r3.close();	 Catch:{ IOException -> 0x00ac }
    L_0x00a0:
        throw r0;
    L_0x00a1:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r1);
        if (r2 != 0) goto L_0x009b;
    L_0x00a8:
        r1.printStackTrace();
        goto L_0x009b;
    L_0x00ac:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.x.a(r1);
        if (r2 != 0) goto L_0x00a0;
    L_0x00b3:
        r1.printStackTrace();
        goto L_0x00a0;
    L_0x00b7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0096;
    L_0x00ba:
        r0 = move-exception;
        goto L_0x0096;
    L_0x00bc:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0068;
    L_0x00bf:
        r0 = move-exception;
        goto L_0x0068;
    L_0x00c1:
        r0 = r1;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.p():java.lang.String");
    }

    public static String a(boolean z) {
        String str = null;
        if (z) {
            try {
                str = p();
            } catch (Throwable th) {
                if (!x.a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return str;
    }

    public static long d() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long e() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (x.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long f() {
        Throwable e;
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        FileReader fileReader2;
        BufferedReader bufferedReader2;
        try {
            fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader2, 2048);
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e2) {
                            if (!x.a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            fileReader2.close();
                        } catch (Throwable e22) {
                            if (!x.a(e22)) {
                                e22.printStackTrace();
                            }
                        }
                        return -1;
                    }
                    long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e3) {
                        if (!x.a(e3)) {
                            e3.printStackTrace();
                        }
                    }
                    try {
                        fileReader2.close();
                        return parseLong;
                    } catch (Throwable e32) {
                        if (x.a(e32)) {
                            return parseLong;
                        }
                        e32.printStackTrace();
                        return parseLong;
                    }
                } catch (Throwable th) {
                    e22 = th;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw e22;
                }
            } catch (Throwable th2) {
                e22 = th2;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw e22;
            }
        } catch (Throwable th3) {
            e22 = th3;
            bufferedReader2 = null;
            fileReader2 = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            throw e22;
        }
    }

    public static long g() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader2 = null;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 2048);
                try {
                    bufferedReader2.readLine();
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e) {
                            if (!x.a(e)) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e2) {
                            if (x.a(e2)) {
                                return -1;
                            }
                            e2.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong = 0 + (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e22) {
                            if (!x.a(e22)) {
                                e22.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e222) {
                            if (x.a(e222)) {
                                return -1;
                            }
                            e222.printStackTrace();
                            return -1;
                        }
                    }
                    parseLong += Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e2222) {
                            if (!x.a(e2222)) {
                                e2222.printStackTrace();
                            }
                        }
                        try {
                            fileReader.close();
                            return -1;
                        } catch (Throwable e22222) {
                            if (x.a(e22222)) {
                                return -1;
                            }
                            e22222.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong2 = (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + parseLong;
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e222222) {
                        if (!x.a(e222222)) {
                            e222222.printStackTrace();
                        }
                    }
                    try {
                        fileReader.close();
                        return parseLong2;
                    } catch (Throwable e2222222) {
                        if (x.a(e2222222)) {
                            return parseLong2;
                        }
                        e2222222.printStackTrace();
                        return parseLong2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
            throw th;
        }
    }

    public static long h() {
        if (!o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long i() {
        if (!o()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String j() {
        String str = "fail";
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String k() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (x.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String e(Context context) {
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return NetworkDiagnosisActivity.NETWORKTYPE_WIFI;
            }
            String str2;
            if (activeNetworkInfo.getType() == 0) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    int networkType = telephonyManager.getNetworkType();
                    switch (networkType) {
                        case 1:
                            return "GPRS";
                        case 2:
                            return "EDGE";
                        case 3:
                            return "UMTS";
                        case 4:
                            return "CDMA";
                        case 5:
                            return "EVDO_0";
                        case 6:
                            return "EVDO_A";
                        case 7:
                            return "1xRTT";
                        case 8:
                            return "HSDPA";
                        case 9:
                            return "HSUPA";
                        case 10:
                            return "HSPA";
                        case 11:
                            return "iDen";
                        case 12:
                            return "EVDO_B";
                        case 13:
                            return "LTE";
                        case 14:
                            return "eHRPD";
                        case 15:
                            return "HSPA+";
                        default:
                            str2 = "MOBILE(" + networkType + ")";
                            break;
                    }
                    return str2;
                }
            }
            str2 = str;
            return str2;
        } catch (Throwable e) {
            if (x.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        String a = z.a(context, "ro.miui.ui.version.name");
        if (!z.a(a) && !a.equals("fail")) {
            return "XiaoMi/MIUI/" + a;
        }
        a = z.a(context, "ro.build.version.emui");
        if (!z.a(a) && !a.equals("fail")) {
            return "HuaWei/EMOTION/" + a;
        }
        a = z.a(context, "ro.lenovo.series");
        if (z.a(a) || a.equals("fail")) {
            a = z.a(context, "ro.build.nubia.rom.name");
            if (!z.a(a) && !a.equals("fail")) {
                return "Zte/NUBIA/" + a + "_" + z.a(context, "ro.build.nubia.rom.code");
            }
            a = z.a(context, "ro.meizu.product.model");
            if (!z.a(a) && !a.equals("fail")) {
                return "Meizu/FLYME/" + z.a(context, "ro.build.display.id");
            }
            a = z.a(context, "ro.build.version.opporom");
            if (!z.a(a) && !a.equals("fail")) {
                return "Oppo/COLOROS/" + a;
            }
            a = z.a(context, "ro.vivo.os.build.display.id");
            if (!z.a(a) && !a.equals("fail")) {
                return "vivo/FUNTOUCH/" + a;
            }
            a = z.a(context, "ro.aa.romver");
            if (!z.a(a) && !a.equals("fail")) {
                return "htc/" + a + MqttTopic.TOPIC_LEVEL_SEPARATOR + z.a(context, "ro.build.description");
            }
            a = z.a(context, "ro.lewa.version");
            if (!z.a(a) && !a.equals("fail")) {
                return "tcl/" + a + MqttTopic.TOPIC_LEVEL_SEPARATOR + z.a(context, "ro.build.display.id");
            }
            a = z.a(context, "ro.gn.gnromvernumber");
            if (!z.a(a) && !a.equals("fail")) {
                return "amigo/" + a + MqttTopic.TOPIC_LEVEL_SEPARATOR + z.a(context, "ro.build.display.id");
            }
            a = z.a(context, "ro.build.tyd.kbstyle_version");
            if (z.a(a) || a.equals("fail")) {
                return z.a(context, "ro.build.fingerprint") + MqttTopic.TOPIC_LEVEL_SEPARATOR + z.a(context, "ro.build.rom.id");
            }
            return "dido/" + a;
        }
        return "Lenovo/VIBE/" + z.a(context, "ro.build.version.incremental");
    }

    public static String g(Context context) {
        return z.a(context, "ro.board.platform");
    }

    public static boolean h(Context context) {
        boolean z;
        boolean exists;
        try {
            exists = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            if (!x.b(th)) {
                th.printStackTrace();
            }
            exists = false;
        }
        Boolean bool = null;
        ArrayList a = z.a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                Boolean valueOf;
                String str = (String) it.next();
                x.c(str, new Object[0]);
                if (str.contains("not found")) {
                    valueOf = Boolean.valueOf(false);
                } else {
                    valueOf = bool;
                }
                bool = valueOf;
            }
            if (bool == null) {
                bool = Boolean.valueOf(true);
            }
        }
        bool = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        if (Build.TAGS == null || !Build.TAGS.contains("test-keys")) {
            z = false;
        } else {
            z = true;
        }
        if (z || r1 || bool.booleanValue()) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String l() {
        /*
        r0 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r3.<init>();	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = "/sys/block/mmcblk0/device/type";
        r1.<init>(r2);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        if (r1 == 0) goto L_0x00c0;
    L_0x0013:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r4 = "/sys/block/mmcblk0/device/type";
        r2.<init>(r4);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x008d, all -> 0x009a }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
        if (r2 == 0) goto L_0x0028;
    L_0x0025:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
    L_0x0028:
        r1.close();	 Catch:{ Throwable -> 0x00b6, all -> 0x00a8 }
        r2 = r1;
    L_0x002c:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r4 = "/sys/block/mmcblk0/device/name";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        if (r1 == 0) goto L_0x0057;
    L_0x003e:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r5 = "/sys/block/mmcblk0/device/name";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00b8, all -> 0x00ab }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
        if (r2 == 0) goto L_0x0053;
    L_0x0050:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
    L_0x0053:
        r1.close();	 Catch:{ Throwable -> 0x00b6, all -> 0x00ad }
        r2 = r1;
    L_0x0057:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r4 = "/sys/block/mmcblk0/device/cid";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        if (r1 == 0) goto L_0x00be;
    L_0x0069:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r5 = "/sys/block/mmcblk0/device/cid";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00bb, all -> 0x00ab }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
        if (r2 == 0) goto L_0x007e;
    L_0x007b:
        r3.append(r2);	 Catch:{ Throwable -> 0x00b6, all -> 0x00b0 }
    L_0x007e:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00b6, all -> 0x00b3 }
        if (r1 == 0) goto L_0x0087;
    L_0x0084:
        r1.close();	 Catch:{ IOException -> 0x0088 }
    L_0x0087:
        return r0;
    L_0x0088:
        r1 = move-exception;
        com.tencent.bugly.proguard.x.a(r1);
        goto L_0x0087;
    L_0x008d:
        r1 = move-exception;
        r1 = r0;
    L_0x008f:
        if (r1 == 0) goto L_0x0087;
    L_0x0091:
        r1.close();	 Catch:{ IOException -> 0x0095 }
        goto L_0x0087;
    L_0x0095:
        r1 = move-exception;
        com.tencent.bugly.proguard.x.a(r1);
        goto L_0x0087;
    L_0x009a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x009d:
        if (r2 == 0) goto L_0x00a2;
    L_0x009f:
        r2.close();	 Catch:{ IOException -> 0x00a3 }
    L_0x00a2:
        throw r0;
    L_0x00a3:
        r1 = move-exception;
        com.tencent.bugly.proguard.x.a(r1);
        goto L_0x00a2;
    L_0x00a8:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00ab:
        r0 = move-exception;
        goto L_0x009d;
    L_0x00ad:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b3:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009d;
    L_0x00b6:
        r2 = move-exception;
        goto L_0x008f;
    L_0x00b8:
        r1 = move-exception;
        r1 = r2;
        goto L_0x008f;
    L_0x00bb:
        r1 = move-exception;
        r1 = r2;
        goto L_0x008f;
    L_0x00be:
        r1 = r2;
        goto L_0x007e;
    L_0x00c0:
        r2 = r0;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.l():java.lang.String");
    }

    public static String i(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = z.a(context, "ro.genymotion.version");
        if (a != null) {
            stringBuilder.append("ro.genymotion.version");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = z.a(context, "androVM.vbox_dpi");
        if (a != null) {
            stringBuilder.append("androVM.vbox_dpi");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = z.a(context, "qemu.sf.fake_camera");
        if (a != null) {
            stringBuilder.append("qemu.sf.fake_camera");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    public static String j(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        if (a == null) {
            a = z.a(context, "ro.secure");
        }
        if (a != null) {
            stringBuilder.append("ro.secure");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        if (b == null) {
            b = z.a(context, "ro.debuggable");
        }
        if (b != null) {
            stringBuilder.append("ro.debuggable");
            stringBuilder.append("|");
            stringBuilder.append(b);
            stringBuilder.append("\n");
        }
        try {
            String readLine;
            bufferedReader = new BufferedReader(new FileReader("/proc/self/status"));
            do {
                try {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (!readLine.startsWith("TracerPid:"));
            if (readLine != null) {
                readLine = readLine.substring(10).trim();
                stringBuilder.append("tracer_pid");
                stringBuilder.append("|");
                stringBuilder.append(readLine);
            }
            readLine = stringBuilder.toString();
            try {
                bufferedReader.close();
                return readLine;
            } catch (Throwable e) {
                x.a(e);
                return readLine;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m() {
        /*
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r3 = "/sys/class/power_supply/ac/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        if (r0 == 0) goto L_0x0036;
    L_0x0013:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r4 = "/sys/class/power_supply/ac/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00ae, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        if (r1 == 0) goto L_0x0032;
    L_0x0025:
        r3 = "ac_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
    L_0x0032:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00c7 }
        r1 = r0;
    L_0x0036:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r3 = "/sys/class/power_supply/usb/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        if (r0 == 0) goto L_0x006b;
    L_0x0048:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r4 = "/sys/class/power_supply/usb/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00d8, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        if (r1 == 0) goto L_0x0067;
    L_0x005a:
        r3 = "usb_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
    L_0x0067:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00cc }
        r1 = r0;
    L_0x006b:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r3 = "/sys/class/power_supply/battery/capacity";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        if (r0 == 0) goto L_0x00de;
    L_0x007d:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r4 = "/sys/class/power_supply/battery/capacity";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00db, all -> 0x00bb }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        if (r1 == 0) goto L_0x009c;
    L_0x008f:
        r3 = "battery_capacity";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
        r2.append(r1);	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
    L_0x009c:
        r0.close();	 Catch:{ Throwable -> 0x00d6, all -> 0x00d1 }
    L_0x009f:
        if (r0 == 0) goto L_0x00a4;
    L_0x00a1:
        r0.close();	 Catch:{ IOException -> 0x00a9 }
    L_0x00a4:
        r0 = r2.toString();
        return r0;
    L_0x00a9:
        r0 = move-exception;
        com.tencent.bugly.proguard.x.a(r0);
        goto L_0x00a4;
    L_0x00ae:
        r0 = move-exception;
        r0 = r1;
    L_0x00b0:
        if (r0 == 0) goto L_0x00a4;
    L_0x00b2:
        r0.close();	 Catch:{ IOException -> 0x00b6 }
        goto L_0x00a4;
    L_0x00b6:
        r0 = move-exception;
        com.tencent.bugly.proguard.x.a(r0);
        goto L_0x00a4;
    L_0x00bb:
        r0 = move-exception;
    L_0x00bc:
        if (r1 == 0) goto L_0x00c1;
    L_0x00be:
        r1.close();	 Catch:{ IOException -> 0x00c2 }
    L_0x00c1:
        throw r0;
    L_0x00c2:
        r1 = move-exception;
        com.tencent.bugly.proguard.x.a(r1);
        goto L_0x00c1;
    L_0x00c7:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00cc:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00d1:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00bc;
    L_0x00d6:
        r1 = move-exception;
        goto L_0x00b0;
    L_0x00d8:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00b0;
    L_0x00db:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00b0;
    L_0x00de:
        r0 = r1;
        goto L_0x009f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.m():java.lang.String");
    }

    public static String k(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = z.a(context, "gsm.sim.state");
        if (a != null) {
            stringBuilder.append("gsm.sim.state");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        stringBuilder.append("\n");
        a = z.a(context, "gsm.sim.state2");
        if (a != null) {
            stringBuilder.append("gsm.sim.state2");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    public static long n() {
        BufferedReader bufferedReader;
        Throwable e;
        Throwable th;
        float f = 0.0f;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0]);
                }
                try {
                    bufferedReader.close();
                } catch (Throwable e2) {
                    x.a(e2);
                }
            } catch (Throwable th2) {
                e2 = th2;
                try {
                    x.a(e2);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e22) {
                            x.a(e22);
                        }
                    }
                    return (long) f;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e222) {
                            x.a(e222);
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return (long) f;
    }
}
