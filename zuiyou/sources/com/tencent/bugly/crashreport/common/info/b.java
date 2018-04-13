package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.ap;
import com.zhihu.matisse.internal.utils.Platform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class b {
    private static String a = null;
    private static String b = null;

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static String b() {
        try {
            return VERSION.RELEASE;
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    public static int c() {
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!an.a(th)) {
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
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                    if (deviceId != null) {
                        try {
                            deviceId = deviceId.toLowerCase();
                        } catch (Throwable th) {
                            an.a("Failed to get IMEI.", new Object[0]);
                            return deviceId;
                        }
                    }
                }
                deviceId = null;
            } catch (Throwable th2) {
                deviceId = null;
                an.a("Failed to get IMEI.", new Object[0]);
                return deviceId;
            }
            return deviceId;
        }
        an.d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
        return null;
    }

    public static String b(Context context) {
        String subscriberId;
        if (context == null) {
            return null;
        }
        if (AppInfo.a(context, "android.permission.READ_PHONE_STATE")) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                    if (subscriberId != null) {
                        try {
                            subscriberId = subscriberId.toLowerCase();
                        } catch (Throwable th) {
                            an.a("Failed to get IMSI.", new Object[0]);
                            return subscriberId;
                        }
                    }
                }
                subscriberId = null;
            } catch (Throwable th2) {
                subscriberId = null;
                an.a("Failed to get IMSI.", new Object[0]);
                return subscriberId;
            }
            return subscriberId;
        }
        an.d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
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
                if (!an.a(th)) {
                    return str;
                }
                an.a("Failed to get Android ID.", new Object[0]);
                return str;
            }
        } catch (Throwable th4) {
            th = th4;
            if (!an.a(th)) {
                return str;
            }
            an.a("Failed to get Android ID.", new Object[0]);
            return str;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d(android.content.Context r8) {
        /*
        r6 = 1;
        r5 = 0;
        r1 = "fail";
        if (r8 != 0) goto L_0x0008;
    L_0x0007:
        return r1;
    L_0x0008:
        r0 = "wifi";
        r0 = r8.getSystemService(r0);	 Catch:{ Throwable -> 0x006f }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Throwable -> 0x006f }
        if (r0 == 0) goto L_0x0079;
    L_0x0013:
        r0 = r0.getConnectionInfo();	 Catch:{ Throwable -> 0x006f }
        if (r0 == 0) goto L_0x0079;
    L_0x0019:
        r0 = r0.getMacAddress();	 Catch:{ Throwable -> 0x006f }
        if (r0 == 0) goto L_0x0028;
    L_0x001f:
        r1 = "02:00:00:00:00:00";
        r1 = r0.equals(r1);	 Catch:{ Throwable -> 0x007b }
        if (r1 == 0) goto L_0x005b;
    L_0x0028:
        r1 = "wifi.interface";
        r1 = com.tencent.bugly.proguard.ap.a(r8, r1);	 Catch:{ Throwable -> 0x007b }
        r2 = "MAC interface: %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x007b }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Throwable -> 0x007b }
        com.tencent.bugly.proguard.an.c(r2, r3);	 Catch:{ Throwable -> 0x007b }
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x007b }
        if (r1 != 0) goto L_0x0048;
    L_0x0041:
        r1 = "wlan0";
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x007b }
    L_0x0048:
        if (r1 != 0) goto L_0x0051;
    L_0x004a:
        r1 = "eth0";
        r1 = java.net.NetworkInterface.getByName(r1);	 Catch:{ Throwable -> 0x007b }
    L_0x0051:
        if (r1 == 0) goto L_0x005b;
    L_0x0053:
        r1 = r1.getHardwareAddress();	 Catch:{ Throwable -> 0x007b }
        r0 = com.tencent.bugly.proguard.ap.e(r1);	 Catch:{ Throwable -> 0x007b }
    L_0x005b:
        if (r0 != 0) goto L_0x0060;
    L_0x005d:
        r0 = "null";
    L_0x0060:
        r1 = "MAC address: %s";
        r2 = new java.lang.Object[r6];
        r2[r5] = r0;
        com.tencent.bugly.proguard.an.c(r1, r2);
        r1 = r0.toLowerCase();
        goto L_0x0007;
    L_0x006f:
        r0 = move-exception;
    L_0x0070:
        r2 = com.tencent.bugly.proguard.an.a(r0);
        if (r2 != 0) goto L_0x0079;
    L_0x0076:
        r0.printStackTrace();
    L_0x0079:
        r0 = r1;
        goto L_0x005b;
    L_0x007b:
        r1 = move-exception;
        r7 = r1;
        r1 = r0;
        r0 = r7;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.d(android.content.Context):java.lang.String");
    }

    public static String e(Context context) {
        String str = "fail";
        if (context == null) {
            return str;
        }
        String simSerialNumber;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                simSerialNumber = telephonyManager.getSimSerialNumber();
                if (simSerialNumber == null) {
                    try {
                        simSerialNumber = "null";
                    } catch (Throwable th) {
                        an.a("Failed to get SIM serial number.", new Object[0]);
                        return simSerialNumber;
                    }
                }
            }
            simSerialNumber = str;
        } catch (Throwable th2) {
            simSerialNumber = str;
            an.a("Failed to get SIM serial number.", new Object[0]);
            return simSerialNumber;
        }
        return simSerialNumber;
    }

    public static String d() {
        try {
            return Build.SERIAL;
        } catch (Throwable th) {
            an.a("Failed to get hardware serial number.", new Object[0]);
            return "fail";
        }
    }

    public static boolean e() {
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                return true;
            }
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String o() {
        /*
        r6 = 2;
        r1 = 0;
        r0 = "/system/build.prop";
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x006e, all -> 0x009c }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x006e, all -> 0x009c }
        r2 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00c5, all -> 0x00c0 }
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2.<init>(r3, r0);	 Catch:{ Throwable -> 0x00c5, all -> 0x00c0 }
    L_0x0011:
        r0 = r2.readLine();	 Catch:{ Throwable -> 0x00c8 }
        if (r0 == 0) goto L_0x00ca;
    L_0x0017:
        r4 = "=";
        r5 = 2;
        r0 = r0.split(r4, r5);	 Catch:{ Throwable -> 0x00c8 }
        r4 = r0.length;	 Catch:{ Throwable -> 0x00c8 }
        if (r4 != r6) goto L_0x0011;
    L_0x0022:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00c8 }
        r5 = "ro.product.cpu.abilist";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00c8 }
        if (r4 == 0) goto L_0x0048;
    L_0x002e:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c8 }
    L_0x0031:
        if (r0 == 0) goto L_0x003d;
    L_0x0033:
        r4 = ",";
        r0 = r0.split(r4);	 Catch:{ Throwable -> 0x00c8 }
        r4 = 0;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c8 }
    L_0x003d:
        if (r2 == 0) goto L_0x0042;
    L_0x003f:
        r2.close();	 Catch:{ IOException -> 0x0058 }
    L_0x0042:
        if (r3 == 0) goto L_0x0047;
    L_0x0044:
        r3.close();	 Catch:{ IOException -> 0x0063 }
    L_0x0047:
        return r0;
    L_0x0048:
        r4 = 0;
        r4 = r0[r4];	 Catch:{ Throwable -> 0x00c8 }
        r5 = "ro.product.cpu.abi";
        r4 = r4.equals(r5);	 Catch:{ Throwable -> 0x00c8 }
        if (r4 == 0) goto L_0x0011;
    L_0x0054:
        r4 = 1;
        r0 = r0[r4];	 Catch:{ Throwable -> 0x00c8 }
        goto L_0x0031;
    L_0x0058:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r1);
        if (r2 != 0) goto L_0x0042;
    L_0x005f:
        r1.printStackTrace();
        goto L_0x0042;
    L_0x0063:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r1);
        if (r2 != 0) goto L_0x0047;
    L_0x006a:
        r1.printStackTrace();
        goto L_0x0047;
    L_0x006e:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x0071:
        r4 = com.tencent.bugly.proguard.an.a(r0);	 Catch:{ all -> 0x00c3 }
        if (r4 != 0) goto L_0x007a;
    L_0x0077:
        r0.printStackTrace();	 Catch:{ all -> 0x00c3 }
    L_0x007a:
        if (r2 == 0) goto L_0x007f;
    L_0x007c:
        r2.close();	 Catch:{ IOException -> 0x0086 }
    L_0x007f:
        if (r3 == 0) goto L_0x0084;
    L_0x0081:
        r3.close();	 Catch:{ IOException -> 0x0091 }
    L_0x0084:
        r0 = r1;
        goto L_0x0047;
    L_0x0086:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r0);
        if (r2 != 0) goto L_0x007f;
    L_0x008d:
        r0.printStackTrace();
        goto L_0x007f;
    L_0x0091:
        r0 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r0);
        if (r2 != 0) goto L_0x0084;
    L_0x0098:
        r0.printStackTrace();
        goto L_0x0084;
    L_0x009c:
        r0 = move-exception;
        r2 = r1;
        r3 = r1;
    L_0x009f:
        if (r2 == 0) goto L_0x00a4;
    L_0x00a1:
        r2.close();	 Catch:{ IOException -> 0x00aa }
    L_0x00a4:
        if (r3 == 0) goto L_0x00a9;
    L_0x00a6:
        r3.close();	 Catch:{ IOException -> 0x00b5 }
    L_0x00a9:
        throw r0;
    L_0x00aa:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r1);
        if (r2 != 0) goto L_0x00a4;
    L_0x00b1:
        r1.printStackTrace();
        goto L_0x00a4;
    L_0x00b5:
        r1 = move-exception;
        r2 = com.tencent.bugly.proguard.an.a(r1);
        if (r2 != 0) goto L_0x00a9;
    L_0x00bc:
        r1.printStackTrace();
        goto L_0x00a9;
    L_0x00c0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x009f;
    L_0x00c3:
        r0 = move-exception;
        goto L_0x009f;
    L_0x00c5:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0071;
    L_0x00c8:
        r0 = move-exception;
        goto L_0x0071;
    L_0x00ca:
        r0 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.o():java.lang.String");
    }

    public static String a(boolean z) {
        String str = null;
        if (z) {
            try {
                str = o();
            } catch (Throwable th) {
                if (!an.a(th)) {
                    th.printStackTrace();
                }
                return "fail";
            }
        }
        if (str == null) {
            str = System.getProperty("os.arch");
        }
        return "" + str;
    }

    public static long f() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (an.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long g() {
        long j = -1;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (an.a(th)) {
                return j;
            }
            th.printStackTrace();
            return j;
        }
    }

    public static long h() {
        FileReader fileReader;
        Throwable th;
        FileReader fileReader2;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                throw th;
            }
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine == null) {
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e) {
                            if (!an.a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (fileReader == null) {
                        return -1;
                    }
                    try {
                        fileReader.close();
                        return -1;
                    } catch (Throwable e2) {
                        if (an.a(e2)) {
                            return -1;
                        }
                        e2.printStackTrace();
                        return -1;
                    }
                }
                long parseLong = Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1024;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable e22) {
                        if (!an.a(e22)) {
                            e22.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return parseLong;
                }
                try {
                    fileReader.close();
                    return parseLong;
                } catch (Throwable e222) {
                    if (an.a(e222)) {
                        return parseLong;
                    }
                    e222.printStackTrace();
                    return parseLong;
                }
            } catch (Throwable th3) {
                th = th3;
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

    public static long i() {
        Throwable th;
        BufferedReader bufferedReader;
        FileReader fileReader = null;
        FileReader fileReader2;
        BufferedReader bufferedReader2;
        try {
            fileReader2 = new FileReader("/proc/meminfo");
            try {
                bufferedReader2 = new BufferedReader(fileReader2, 2048);
                try {
                    bufferedReader2.readLine();
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e) {
                                if (!an.a(e)) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (fileReader2 == null) {
                            return -1;
                        }
                        try {
                            fileReader2.close();
                            return -1;
                        } catch (Throwable e2) {
                            if (an.a(e2)) {
                                return -1;
                            }
                            e2.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong = 0 + (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1024);
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e22) {
                                if (!an.a(e22)) {
                                    e22.printStackTrace();
                                }
                            }
                        }
                        if (fileReader2 == null) {
                            return -1;
                        }
                        try {
                            fileReader2.close();
                            return -1;
                        } catch (Throwable e222) {
                            if (an.a(e222)) {
                                return -1;
                            }
                            e222.printStackTrace();
                            return -1;
                        }
                    }
                    parseLong += Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1024;
                    readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (Throwable e2222) {
                                if (!an.a(e2222)) {
                                    e2222.printStackTrace();
                                }
                            }
                        }
                        if (fileReader2 == null) {
                            return -1;
                        }
                        try {
                            fileReader2.close();
                            return -1;
                        } catch (Throwable e22222) {
                            if (an.a(e22222)) {
                                return -1;
                            }
                            e22222.printStackTrace();
                            return -1;
                        }
                    }
                    long parseLong2 = (Long.parseLong(readLine.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) * 1024) + parseLong;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Throwable e222222) {
                            if (!an.a(e222222)) {
                                e222222.printStackTrace();
                            }
                        }
                    }
                    if (fileReader2 == null) {
                        return parseLong2;
                    }
                    try {
                        fileReader2.close();
                        return parseLong2;
                    } catch (Throwable e2222222) {
                        if (an.a(e2222222)) {
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
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedReader2 = null;
            fileReader2 = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileReader2 != null) {
                fileReader2.close();
            }
            throw th;
        }
    }

    public static long j() {
        if (!e()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static long k() {
        if (!e()) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    public static String l() {
        String str = "fail";
        try {
            str = Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (!an.a(th)) {
                th.printStackTrace();
            }
        }
        return str;
    }

    public static String m() {
        String str = "fail";
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (an.a(th)) {
                return str;
            }
            th.printStackTrace();
            return str;
        }
    }

    public static String f(Context context) {
        String str = "unknown";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            String str2;
            if (activeNetworkInfo.getType() == 1) {
                str2 = "WIFI";
            } else {
                if (activeNetworkInfo.getType() == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                    if (telephonyManager != null) {
                        int networkType = telephonyManager.getNetworkType();
                        switch (networkType) {
                            case 1:
                                str2 = "GPRS";
                                break;
                            case 2:
                                str2 = "EDGE";
                                break;
                            case 3:
                                str2 = "UMTS";
                                break;
                            case 4:
                                str2 = "CDMA";
                                break;
                            case 5:
                                str2 = "EVDO_0";
                                break;
                            case 6:
                                str2 = "EVDO_A";
                                break;
                            case 7:
                                str2 = "1xRTT";
                                break;
                            case 8:
                                str2 = "HSDPA";
                                break;
                            case 9:
                                str2 = "HSUPA";
                                break;
                            case 10:
                                str2 = "HSPA";
                                break;
                            case 11:
                                str2 = "iDen";
                                break;
                            case 12:
                                str2 = "EVDO_B";
                                break;
                            case 13:
                                str2 = "LTE";
                                break;
                            case 14:
                                str2 = "eHRPD";
                                break;
                            case 15:
                                str2 = "HSPA+";
                                break;
                            default:
                                str2 = "MOBILE(" + networkType + ")";
                                break;
                        }
                    }
                }
                str2 = str;
            }
            return str2;
        } catch (Throwable e) {
            if (an.a(e)) {
                return str;
            }
            e.printStackTrace();
            return str;
        }
    }

    public static String g(Context context) {
        String a = ap.a(context, Platform.RUNTIME_MIUI);
        if (!ap.a(a) && !a.equals("fail")) {
            return "XiaoMi/MIUI/" + a;
        }
        a = ap.a(context, "ro.build.version.emui");
        if (!ap.a(a) && !a.equals("fail")) {
            return "HuaWei/EMOTION/" + a;
        }
        a = ap.a(context, "ro.lenovo.series");
        if (ap.a(a) || a.equals("fail")) {
            a = ap.a(context, "ro.build.nubia.rom.name");
            if (!ap.a(a) && !a.equals("fail")) {
                return "Zte/NUBIA/" + a + "_" + ap.a(context, "ro.build.nubia.rom.code");
            }
            a = ap.a(context, "ro.meizu.product.model");
            if (!ap.a(a) && !a.equals("fail")) {
                return "Meizu/FLYME/" + ap.a(context, Platform.RUNTIME_DISPLAY);
            }
            a = ap.a(context, Platform.RUNTIME_OPPO);
            if (!ap.a(a) && !a.equals("fail")) {
                return "Oppo/COLOROS/" + a;
            }
            a = ap.a(context, "ro.vivo.os.build.display.id");
            if (!ap.a(a) && !a.equals("fail")) {
                return "vivo/FUNTOUCH/" + a;
            }
            a = ap.a(context, "ro.aa.romver");
            if (!ap.a(a) && !a.equals("fail")) {
                return "htc/" + a + "/" + ap.a(context, "ro.build.description");
            }
            a = ap.a(context, "ro.lewa.version");
            if (!ap.a(a) && !a.equals("fail")) {
                return "tcl/" + a + "/" + ap.a(context, Platform.RUNTIME_DISPLAY);
            }
            a = ap.a(context, "ro.gn.gnromvernumber");
            if (!ap.a(a) && !a.equals("fail")) {
                return "amigo/" + a + "/" + ap.a(context, Platform.RUNTIME_DISPLAY);
            }
            a = ap.a(context, "ro.build.tyd.kbstyle_version");
            if (ap.a(a) || a.equals("fail")) {
                return ap.a(context, "ro.build.fingerprint") + "/" + ap.a(context, "ro.build.rom.id");
            }
            return "dido/" + a;
        }
        return "Lenovo/VIBE/" + ap.a(context, "ro.build.version.incremental");
    }

    public static String h(Context context) {
        return ap.a(context, "ro.board.platform");
    }

    public static boolean i(Context context) {
        boolean z;
        boolean exists;
        try {
            exists = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            if (!an.b(th)) {
                th.printStackTrace();
            }
            exists = false;
        }
        Boolean bool = null;
        ArrayList a = ap.a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a != null && a.size() > 0) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                Boolean valueOf;
                String str = (String) it.next();
                an.c(str, new Object[0]);
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
    public static java.lang.String n() {
        /*
        r0 = 0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r3.<init>();	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r2 = "/sys/block/mmcblk0/device/type";
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        if (r1 == 0) goto L_0x00c8;
    L_0x0014:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r2 = new java.io.FileReader;	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r4 = "/sys/block/mmcblk0/device/type";
        r2.<init>(r4);	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r1.<init>(r2);	 Catch:{ Throwable -> 0x0095, all -> 0x00a2 }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00be, all -> 0x00b0 }
        if (r2 == 0) goto L_0x002a;
    L_0x0027:
        r3.append(r2);	 Catch:{ Throwable -> 0x00be, all -> 0x00b0 }
    L_0x002a:
        r1.close();	 Catch:{ Throwable -> 0x00be, all -> 0x00b0 }
        r2 = r1;
    L_0x002e:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r4 = "/sys/block/mmcblk0/device/name";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        if (r1 == 0) goto L_0x005c;
    L_0x0042:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r5 = "/sys/block/mmcblk0/device/name";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00c0, all -> 0x00b3 }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00be, all -> 0x00b5 }
        if (r2 == 0) goto L_0x0058;
    L_0x0055:
        r3.append(r2);	 Catch:{ Throwable -> 0x00be, all -> 0x00b5 }
    L_0x0058:
        r1.close();	 Catch:{ Throwable -> 0x00be, all -> 0x00b5 }
        r2 = r1;
    L_0x005c:
        r1 = ",";
        r3.append(r1);	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r1 = new java.io.File;	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r4 = "/sys/block/mmcblk0/device/cid";
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r1 = r1.exists();	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        if (r1 == 0) goto L_0x00c6;
    L_0x0070:
        r1 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r4 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r5 = "/sys/block/mmcblk0/device/cid";
        r4.<init>(r5);	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r1.<init>(r4);	 Catch:{ Throwable -> 0x00c3, all -> 0x00b3 }
        r2 = r1.readLine();	 Catch:{ Throwable -> 0x00be, all -> 0x00b8 }
        if (r2 == 0) goto L_0x0086;
    L_0x0083:
        r3.append(r2);	 Catch:{ Throwable -> 0x00be, all -> 0x00b8 }
    L_0x0086:
        r0 = r3.toString();	 Catch:{ Throwable -> 0x00be, all -> 0x00bb }
        if (r1 == 0) goto L_0x008f;
    L_0x008c:
        r1.close();	 Catch:{ IOException -> 0x0090 }
    L_0x008f:
        return r0;
    L_0x0090:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x008f;
    L_0x0095:
        r1 = move-exception;
        r1 = r0;
    L_0x0097:
        if (r1 == 0) goto L_0x008f;
    L_0x0099:
        r1.close();	 Catch:{ IOException -> 0x009d }
        goto L_0x008f;
    L_0x009d:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x008f;
    L_0x00a2:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x00a5:
        if (r2 == 0) goto L_0x00aa;
    L_0x00a7:
        r2.close();	 Catch:{ IOException -> 0x00ab }
    L_0x00aa:
        throw r0;
    L_0x00ab:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x00aa;
    L_0x00b0:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a5;
    L_0x00b3:
        r0 = move-exception;
        goto L_0x00a5;
    L_0x00b5:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a5;
    L_0x00b8:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a5;
    L_0x00bb:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00a5;
    L_0x00be:
        r2 = move-exception;
        goto L_0x0097;
    L_0x00c0:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0097;
    L_0x00c3:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0097;
    L_0x00c6:
        r1 = r2;
        goto L_0x0086;
    L_0x00c8:
        r2 = r0;
        goto L_0x002e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.n():java.lang.String");
    }

    public static String j(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = ap.a(context, "ro.genymotion.version");
        if (a != null) {
            stringBuilder.append("ro.genymotion.version");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = ap.a(context, "androVM.vbox_dpi");
        if (a != null) {
            stringBuilder.append("androVM.vbox_dpi");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        a = ap.a(context, "qemu.sf.fake_camera");
        if (a != null) {
            stringBuilder.append("qemu.sf.fake_camera");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    public static String k(Context context) {
        BufferedReader bufferedReader;
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        if (a == null) {
            a = ap.a(context, "ro.secure");
        }
        if (a != null) {
            stringBuilder.append("ro.secure");
            stringBuilder.append("|");
            stringBuilder.append(a);
            stringBuilder.append("\n");
        }
        if (b == null) {
            b = ap.a(context, "ro.debuggable");
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
                readLine = readLine.substring("TracerPid:".length()).trim();
                stringBuilder.append("tracer_pid");
                stringBuilder.append("|");
                stringBuilder.append(readLine);
            }
            readLine = stringBuilder.toString();
            if (bufferedReader == null) {
                return readLine;
            }
            try {
                bufferedReader.close();
                return readLine;
            } catch (Throwable e) {
                an.a(e);
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
    public static java.lang.String l(android.content.Context r6) {
        /*
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r3 = "/sys/class/power_supply/ac/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        if (r0 == 0) goto L_0x003a;
    L_0x0014:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r4 = "/sys/class/power_supply/ac/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00bc, all -> 0x00c9 }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00e4, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0036;
    L_0x0027:
        r3 = "ac_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00d5 }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00d5 }
        r2.append(r1);	 Catch:{ Throwable -> 0x00e4, all -> 0x00d5 }
    L_0x0036:
        r0.close();	 Catch:{ Throwable -> 0x00e4, all -> 0x00d5 }
        r1 = r0;
    L_0x003a:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r3 = "/sys/class/power_supply/usb/online";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        if (r0 == 0) goto L_0x0074;
    L_0x004e:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r4 = "/sys/class/power_supply/usb/online";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00e6, all -> 0x00c9 }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00e4, all -> 0x00da }
        if (r1 == 0) goto L_0x0070;
    L_0x0061:
        r3 = "usb_online";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00da }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00da }
        r2.append(r1);	 Catch:{ Throwable -> 0x00e4, all -> 0x00da }
    L_0x0070:
        r0.close();	 Catch:{ Throwable -> 0x00e4, all -> 0x00da }
        r1 = r0;
    L_0x0074:
        r0 = "\n";
        r2.append(r0);	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r3 = "/sys/class/power_supply/battery/capacity";
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        if (r0 == 0) goto L_0x00ec;
    L_0x0088:
        r0 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r3 = new java.io.FileReader;	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r4 = "/sys/class/power_supply/battery/capacity";
        r3.<init>(r4);	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r0.<init>(r3);	 Catch:{ Throwable -> 0x00e9, all -> 0x00c9 }
        r1 = r0.readLine();	 Catch:{ Throwable -> 0x00e4, all -> 0x00df }
        if (r1 == 0) goto L_0x00aa;
    L_0x009b:
        r3 = "battery_capacity";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00df }
        r3 = "|";
        r2.append(r3);	 Catch:{ Throwable -> 0x00e4, all -> 0x00df }
        r2.append(r1);	 Catch:{ Throwable -> 0x00e4, all -> 0x00df }
    L_0x00aa:
        r0.close();	 Catch:{ Throwable -> 0x00e4, all -> 0x00df }
    L_0x00ad:
        if (r0 == 0) goto L_0x00b2;
    L_0x00af:
        r0.close();	 Catch:{ IOException -> 0x00b7 }
    L_0x00b2:
        r0 = r2.toString();
        return r0;
    L_0x00b7:
        r0 = move-exception;
        com.tencent.bugly.proguard.an.a(r0);
        goto L_0x00b2;
    L_0x00bc:
        r0 = move-exception;
        r0 = r1;
    L_0x00be:
        if (r0 == 0) goto L_0x00b2;
    L_0x00c0:
        r0.close();	 Catch:{ IOException -> 0x00c4 }
        goto L_0x00b2;
    L_0x00c4:
        r0 = move-exception;
        com.tencent.bugly.proguard.an.a(r0);
        goto L_0x00b2;
    L_0x00c9:
        r0 = move-exception;
    L_0x00ca:
        if (r1 == 0) goto L_0x00cf;
    L_0x00cc:
        r1.close();	 Catch:{ IOException -> 0x00d0 }
    L_0x00cf:
        throw r0;
    L_0x00d0:
        r1 = move-exception;
        com.tencent.bugly.proguard.an.a(r1);
        goto L_0x00cf;
    L_0x00d5:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00ca;
    L_0x00da:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00ca;
    L_0x00df:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
        goto L_0x00ca;
    L_0x00e4:
        r1 = move-exception;
        goto L_0x00be;
    L_0x00e6:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00be;
    L_0x00e9:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00be;
    L_0x00ec:
        r0 = r1;
        goto L_0x00ad;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.l(android.content.Context):java.lang.String");
    }

    public static String m(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        String a = ap.a(context, "gsm.sim.state");
        if (a != null) {
            stringBuilder.append("gsm.sim.state");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        stringBuilder.append("\n");
        a = ap.a(context, "gsm.sim.state2");
        if (a != null) {
            stringBuilder.append("gsm.sim.state2");
            stringBuilder.append("|");
            stringBuilder.append(a);
        }
        return stringBuilder.toString();
    }

    public static long n(Context context) {
        BufferedReader bufferedReader;
        Throwable e;
        Throwable th;
        float f = 0.0f;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/uptime"));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    f = ((float) (System.currentTimeMillis() / 1000)) - Float.parseFloat(readLine.split(" ")[0]);
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e2) {
                        an.a(e2);
                    }
                }
            } catch (Throwable th2) {
                e2 = th2;
                try {
                    an.a(e2);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e22) {
                            an.a(e22);
                        }
                    }
                    return (long) f;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e222) {
                            an.a(e222);
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
