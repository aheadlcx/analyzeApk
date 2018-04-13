package com.loc;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;

public final class de {
    static String a = null;
    private static int b = 0;
    private static String[] c = null;
    private static Hashtable<String, Long> d = new Hashtable();
    private static SparseArray<String> e = null;
    private static SimpleDateFormat f = null;
    private static String[] g = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    public static double a(double d) {
        return ((double) ((long) (d * 1000000.0d))) / 1000000.0d;
    }

    public static float a(float f) {
        return (float) (((double) ((long) (((double) f) * 100.0d))) / 100.0d);
    }

    public static float a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        return a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
    }

    public static float a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int a(int i) {
        return (i * 2) - 113;
    }

    public static int a(NetworkInfo networkInfo) {
        return (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) ? networkInfo.getType() : -1;
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static Object a(Context context, String str) {
        Object obj = null;
        if (context != null) {
            try {
                obj = context.getApplicationContext().getSystemService(str);
            } catch (Throwable th) {
                cw.a(th, "Utils", "getServ");
            }
        }
        return obj;
    }

    public static void a(Context context, int i) {
        if (context != null) {
            try {
                af afVar = new af(context, af.a(co.class), i());
                Object clVar = new cl();
                clVar.a(i);
                afVar.a(clVar, "_id=1");
            } catch (Throwable th) {
                cw.a(th, "Utils", "getDBConfigVersion");
            }
        }
    }

    public static boolean a(long j, long j2) {
        String str = "yyyyMMddHH";
        boolean z = false;
        if (f == null) {
            try {
                f = new SimpleDateFormat(str, Locale.CHINA);
            } catch (Throwable th) {
                cw.a(th, "Utils", "isSameDay part1");
            }
        } else {
            f.applyPattern(str);
        }
        try {
            if (f != null) {
                z = f.format(Long.valueOf(j)).equals(f.format(Long.valueOf(j2)));
            }
        } catch (Throwable th2) {
            cw.a(th2, "Utils", "isSameHour");
        }
        return z;
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            return c() < 17 ? c(context, "android.provider.Settings$System") : c(context, "android.provider.Settings$Global");
        } catch (Throwable th) {
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.database.sqlite.SQLiteDatabase r6, java.lang.String r7) {
        /*
        r2 = 0;
        r1 = 1;
        r0 = 0;
        r3 = android.text.TextUtils.isEmpty(r7);
        if (r3 == 0) goto L_0x000a;
    L_0x0009:
        return r0;
    L_0x000a:
        r3 = "2.0.201501131131";
        r4 = ".";
        r5 = "";
        r3 = r3.replace(r4, r5);
        if (r6 == 0) goto L_0x0009;
    L_0x0016:
        r4 = r6.isOpen();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        if (r4 == 0) goto L_0x0009;
    L_0x001c:
        r4 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r4.<init>();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = "SELECT count(*) as c FROM sqlite_master WHERE type = 'table' AND name = '";
        r4.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = r7.trim();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = r4.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r3 = r5.append(r3);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = "' ";
        r3.append(r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r3 = r4.toString();	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        r5 = 0;
        r2 = r6.rawQuery(r3, r5);	 Catch:{ Throwable -> 0x005e, all -> 0x0067 }
        if (r2 == 0) goto L_0x0050;
    L_0x0042:
        r3 = r2.moveToFirst();	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r3 == 0) goto L_0x0050;
    L_0x0048:
        r3 = 0;
        r3 = r2.getInt(r3);	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r3 <= 0) goto L_0x0050;
    L_0x004f:
        r0 = r1;
    L_0x0050:
        r3 = 0;
        r5 = r4.length();	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        r4.delete(r3, r5);	 Catch:{ Throwable -> 0x006e, all -> 0x0067 }
        if (r2 == 0) goto L_0x0009;
    L_0x005a:
        r2.close();
        goto L_0x0009;
    L_0x005e:
        r0 = move-exception;
        r0 = r2;
    L_0x0060:
        if (r0 == 0) goto L_0x0071;
    L_0x0062:
        r0.close();
        r0 = r1;
        goto L_0x0009;
    L_0x0067:
        r0 = move-exception;
        if (r2 == 0) goto L_0x006d;
    L_0x006a:
        r2.close();
    L_0x006d:
        throw r0;
    L_0x006e:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0060;
    L_0x0071:
        r0 = r1;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.de.a(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    public static boolean a(Location location, int i) {
        Bundle extras = location.getExtras();
        return (extras != null ? extras.getInt("satellites") : 0) <= 0 ? true : i == 0 && location.getAltitude() == 0.0d && location.getBearing() == 0.0f && location.getSpeed() == 0.0f;
    }

    public static boolean a(AMapLocation aMapLocation) {
        return (aMapLocation != null && aMapLocation.getErrorCode() == 0) ? b(aMapLocation) : false;
    }

    public static boolean a(AMapLocationServer aMapLocationServer) {
        return (aMapLocationServer == null || aMapLocationServer.d().equals(Constants.VIA_SHARE_TYPE_PUBLISHVIDEO) || aMapLocationServer.d().equals("5") || aMapLocationServer.d().equals(Constants.VIA_SHARE_TYPE_INFO)) ? false : b((AMapLocation) aMapLocationServer);
    }

    public static boolean a(String str) {
        return (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) ? ",202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,901,".contains(new StringBuilder(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP).append(str).append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP).toString()) : false;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        String[] split = str.split(MqttTopic.MULTI_LEVEL_WILDCARD);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < split.length) {
            if (split[i].contains(",nb") || split[i].contains(",access")) {
                arrayList.add(split[i]);
            }
            i++;
        }
        String[] split2 = str2.toString().split(MqttTopic.MULTI_LEVEL_WILDCARD);
        i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < split2.length) {
            if (split2[i2].contains(",nb") || split2[i2].contains(",access")) {
                i3++;
                if (arrayList.contains(split2[i2])) {
                    i++;
                }
            }
            i2++;
        }
        return ((double) (i * 2)) >= ((double) (arrayList.size() + i3)) * 0.618d;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return t.a(jSONObject, str);
    }

    public static byte[] a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8);
        return bArr;
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((int) ((j >> (i * 8)) & 255));
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            bArr2 = t.b(bArr);
        } catch (Throwable th) {
            cw.a(th, "Utils", "gz");
        }
        return bArr2;
    }

    public static String[] a(TelephonyManager telephonyManager) {
        int parseInt;
        Object obj = null;
        if (telephonyManager != null) {
            obj = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[]{"0", "0"};
        int i = TextUtils.isEmpty(obj) ? 0 : !TextUtils.isDigitsOnly(obj) ? 0 : obj.length() <= 4 ? 0 : 1;
        if (i != 0) {
            strArr[0] = obj.substring(0, 3);
            char[] toCharArray = obj.substring(3).toCharArray();
            i = 0;
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = obj.substring(3, i + 3);
        }
        try {
            parseInt = Integer.parseInt(strArr[0]);
        } catch (Throwable th) {
            cw.a(th, "Utils", "getMccMnc");
            parseInt = 0;
        }
        if (parseInt == 0) {
            strArr[0] = "0";
        }
        if (strArr[0].equals("0") || strArr[1].equals("0")) {
            return (strArr[0].equals("0") && strArr[1].equals("0") && c != null) ? c : strArr;
        } else {
            c = strArr;
            return strArr;
        }
    }

    public static double b(double d) {
        return ((double) ((long) (d * 100.0d))) / 100.0d;
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static String b(int i) {
        String str = "其他错误";
        switch (i) {
            case 0:
                return "success";
            case 1:
                return "重要参数为空";
            case 2:
                return "WIFI信息不足";
            case 3:
                return "请求参数获取出现异常";
            case 4:
                return "网络连接异常";
            case 5:
                return "解析数据异常";
            case 6:
                return "定位结果错误";
            case 7:
                return "KEY错误";
            case 8:
                return "其他错误";
            case 9:
                return "初始化异常";
            case 10:
                return "定位服务启动失败";
            case 11:
                return "错误的基站信息，请检查是否插入SIM卡";
            case 12:
                return "缺少定位权限";
            case 13:
                return "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块";
            case 14:
                return "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试";
            case 15:
                return "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟";
            default:
                return str;
        }
    }

    public static String b(Context context) {
        CharSequence charSequence = null;
        if (!TextUtils.isEmpty(cw.f)) {
            return cw.f;
        }
        if (context == null) {
            return null;
        }
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(k.c(context), 64);
        } catch (Throwable th) {
            cw.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(cw.g)) {
                cw.g = null;
            }
        } catch (Throwable th2) {
            cw.a(th2, "Utils", "getAppName");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (packageInfo != null) {
            if (packageInfo.applicationInfo != null) {
                charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager());
            }
            if (charSequence != null) {
                stringBuilder.append(charSequence.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                stringBuilder.append(packageInfo.versionName);
            }
        }
        Object c = k.c(context);
        if (!TextUtils.isEmpty(c)) {
            stringBuilder.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP).append(c);
        }
        if (!TextUtils.isEmpty(cw.g)) {
            stringBuilder.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP).append(cw.g);
        }
        String stringBuilder2 = stringBuilder.toString();
        cw.f = stringBuilder2;
        return stringBuilder2;
    }

    public static String b(TelephonyManager telephonyManager) {
        int i = 0;
        if (e == null) {
            SparseArray sparseArray = new SparseArray();
            e = sparseArray;
            sparseArray.append(0, "UNKWN");
            e.append(1, "GPRS");
            e.append(2, "EDGE");
            e.append(3, "UMTS");
            e.append(4, "CDMA");
            e.append(5, "EVDO_0");
            e.append(6, "EVDO_A");
            e.append(7, "1xRTT");
            e.append(8, "HSDPA");
            e.append(9, "HSUPA");
            e.append(10, "HSPA");
            e.append(11, "IDEN");
            e.append(12, "EVDO_B");
            e.append(13, "LTE");
            e.append(14, "EHRPD");
            e.append(15, "HSPAP");
        }
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return (String) e.get(i, "UNKWN");
    }

    public static String b(byte[] bArr) {
        return t.f(bArr);
    }

    public static boolean b(long j, long j2) {
        String str = "yyyyMMdd";
        boolean z = false;
        if (f == null) {
            try {
                f = new SimpleDateFormat(str, Locale.CHINA);
            } catch (Throwable th) {
                cw.a(th, "Utils", "isSameDay part1");
            }
        } else {
            f.applyPattern(str);
        }
        try {
            if (f != null) {
                z = f.format(Long.valueOf(j)).equals(f.format(Long.valueOf(j2)));
            }
        } catch (Throwable th2) {
            cw.a(th2, "Utils", "isSameDay");
        }
        return z;
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    public static boolean b(String str) {
        return (TextUtils.isEmpty(str) || str.equals("00:00:00:00:00:00") || str.contains(" :")) ? false : true;
    }

    public static byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static double c(double d) {
        return ((double) ((long) (d * 1000000.0d))) / 1000000.0d;
    }

    public static int c() {
        if (b > 0) {
            return b;
        }
        String str = "android.os.Build$VERSION";
        try {
            return ((Integer) cz.a(str, "SDK_INT")).intValue();
        } catch (Throwable th) {
            return 0;
        }
    }

    public static NetworkInfo c(Context context) {
        NetworkInfo networkInfo = null;
        try {
            networkInfo = n.n(context);
        } catch (Throwable th) {
            cw.a(th, "Utils", "getNetWorkInfo");
        }
        return networkInfo;
    }

    public static String c(String str) {
        return g(str);
    }

    public static boolean c(long j, long j2) {
        boolean z = true;
        if (!b(j, j2)) {
            return false;
        }
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTimeInMillis(j);
        int i = instance.get(11);
        instance.setTimeInMillis(j2);
        int i2 = instance.get(11);
        if (i <= 12 ? i2 <= 12 : i2 > 12) {
            z = false;
        }
        return z;
    }

    private static boolean c(Context context, String str) throws Throwable {
        ContentResolver contentResolver = context.getContentResolver();
        String str2 = ((String) cz.a(str, "AIRPLANE_MODE_ON")).toString();
        return ((Integer) cz.a(str, "getInt", new Object[]{contentResolver, str2}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[(bArr.length + 1)];
        bArr2[0] = (byte) 0;
        for (int i = 1; i <= bArr.length; i++) {
            bArr2[i] = bArr[bArr.length - i];
        }
        return bArr2;
    }

    public static String d() {
        return Build.MODEL;
    }

    public static String d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Throwable th) {
            cw.a(th, "Utils", "base642Str");
            return null;
        }
    }

    public static boolean d(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(k.c(context))) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
            return false;
        } catch (Throwable th) {
            cw.a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    public static byte[] d(byte[] bArr) {
        Object obj = new byte[16];
        System.arraycopy(bArr, 10, obj, 0, Math.min(26, bArr.length) - 10);
        return obj;
    }

    public static int e(Context context) {
        try {
            List b = new af(context, af.a(co.class), i()).b("_id=1", cl.class);
            if (b != null && b.size() > 0) {
                return ((cl) b.get(0)).a();
            }
        } catch (Throwable th) {
            cw.a(th, "Utils", "getDBConfigVersion");
        }
        return -1;
    }

    public static String e() {
        return VERSION.RELEASE;
    }

    public static byte[] e(String str) {
        return a(Integer.parseInt(str), null);
    }

    public static int f() {
        return new Random().nextInt(65536) - 32768;
    }

    public static boolean f(Context context) {
        if (VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            for (String checkCallingOrSelfPermission : g) {
                if (context.checkCallingOrSelfPermission(checkCallingOrSelfPermission) != 0) {
                    return false;
                }
            }
            return true;
        }
        Application application = (Application) context;
        for (Object obj : g) {
            int b;
            try {
                b = cz.b(application.getBaseContext(), "checkSelfPermission", obj);
            } catch (Throwable th) {
                b = 0;
            }
            if (b != 0) {
                return false;
            }
        }
        return true;
    }

    public static byte[] f(String str) {
        return b(Integer.parseInt(str), null);
    }

    private static String g(String str) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Throwable th) {
            cw.a(th, "Utils", "str2Base64");
        }
        return Base64.encodeToString(bArr, 0);
    }

    public static void g() {
        d.clear();
    }

    public static String h() {
        try {
            return o.a("S128DF1572465B890OE3F7A13167KLEI".getBytes("UTF-8")).substring(20);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String i() {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator);
        stringBuilder.append("amap").append(File.separator);
        stringBuilder.append("openamaplocationsdk").append(File.separator);
        return stringBuilder.toString();
    }
}
