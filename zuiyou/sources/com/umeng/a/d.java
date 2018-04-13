package com.umeng.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent$EScenarioType;
import com.umeng.analytics.a;
import com.zhihu.matisse.internal.utils.Platform;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

public class d {
    protected static final String a = d.class.getName();

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        if (VERSION.SDK_INT >= 23) {
            try {
                boolean z;
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[]{String.class}).invoke(context, new Object[]{str})).intValue() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                return z;
            } catch (Throwable th) {
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] a(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Throwable th) {
            return new String[0];
        }
    }

    private static String b() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
                if (!"wlan0".equals(networkInterface.getName())) {
                    if ("eth0".equals(networkInterface.getName())) {
                    }
                }
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null || hardwareAddress.length == 0) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                int length = hardwareAddress.length;
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                }
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                return stringBuilder.toString().toLowerCase(Locale.getDefault());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    private static String c() {
        try {
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            int i = 0;
            while (i < strArr.length) {
                try {
                    String a = a(strArr[i]);
                    if (a != null) {
                        return a;
                    }
                    i++;
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    private static String a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            Reader fileReader = new FileReader(str);
            if (fileReader != null) {
                try {
                    bufferedReader = new BufferedReader(fileReader, 1024);
                    try {
                        str2 = bufferedReader.readLine();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable th2) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th3) {
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable th5) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th6) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    bufferedReader = str2;
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th8) {
        }
        return str2;
    }

    public static String a() {
        Throwable th;
        String str;
        Throwable th2;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (Throwable th3) {
                    try {
                        g.d(a, "Could not read from file /proc/cpuinfo", th3);
                    } catch (Throwable th32) {
                        th = th32;
                        str = str2;
                        th2 = th;
                        g.d(a, "Could not open file /proc/cpuinfo", th2);
                        str2 = str;
                        if (str2 != null) {
                            return str2.substring(str2.indexOf(58) + 1).trim();
                        }
                        return "";
                    }
                }
            }
        } catch (Throwable th322) {
            th = th322;
            str = str2;
            th2 = th;
            g.d(a, "Could not open file /proc/cpuinfo", th2);
            str2 = str;
            if (str2 != null) {
                return "";
            }
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String c(Context context) {
        if (MobclickAgent$EScenarioType.E_UM_ANALYTICS_OEM.toValue() == AnalyticsConfig.getVerticalType(context) || MobclickAgent$EScenarioType.E_UM_GAME_OEM.toValue() == AnalyticsConfig.getVerticalType(context)) {
            return A(context);
        }
        return z(context);
    }

    public static String d(Context context) {
        return e.b(c(context));
    }

    public static String e(Context context) {
        if (f(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", new Object[]{Integer.valueOf(i2)});
        }
        return new StringBuffer().append(String.valueOf(i)).append(valueOf).toString();
    }

    public static String f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    public static String g(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (a(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String[] h(Context context) {
        String[] strArr = new String[]{"", ""};
        try {
            if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    strArr[0] = "";
                    return strArr;
                }
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                    if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                        strArr[0] = "2G/3G";
                        strArr[1] = networkInfo2.getSubtypeName();
                        return strArr;
                    }
                    return strArr;
                }
                strArr[0] = "Wi-Fi";
                return strArr;
            }
            strArr[0] = "";
            return strArr;
        } catch (Throwable th) {
        }
    }

    public static boolean i(Context context) {
        return "Wi-Fi".equals(h(context)[0]);
    }

    public static boolean j(Context context) {
        try {
            if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnectedOrConnecting();
                    }
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static int k(Context context) {
        try {
            Calendar instance = Calendar.getInstance(x(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable th) {
            g.b(a, "error in getTimeZone", th);
        }
        return 8;
    }

    public static String[] l(Context context) {
        String[] strArr = new String[2];
        try {
            Locale x = x(context);
            if (x != null) {
                strArr[0] = x.getCountry();
                strArr[1] = x.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = "Unknown";
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = "Unknown";
            }
        } catch (Throwable th) {
            g.d(a, "error in getLocaleInfo", th);
        }
        return strArr;
    }

    private static Locale x(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Throwable th) {
            g.a(a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String m(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                g.a(a, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable th) {
            g.d(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", th);
        }
        return null;
    }

    public static String n(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23) {
            return y(context);
        }
        if (VERSION.SDK_INT == 23) {
            str = b();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (a.d) {
                return c();
            }
            return y(context);
        }
        str = b();
        if (TextUtils.isEmpty(str)) {
            return y(context);
        }
        return str;
    }

    private static String y(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    public static int[] o(Context context) {
        try {
            int a;
            int a2;
            int i;
            Object displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                a = a(displayMetrics, "noncompatWidthPixels");
                a2 = a(displayMetrics, "noncompatHeightPixels");
            } else {
                a2 = -1;
                a = -1;
            }
            if (a == -1 || a2 == -1) {
                i = displayMetrics.widthPixels;
                a = displayMetrics.heightPixels;
            } else {
                i = a;
                a = a2;
            }
            int[] iArr = new int[2];
            if (i > a) {
                iArr[0] = a;
                iArr[1] = i;
                return iArr;
            }
            iArr[0] = i;
            iArr[1] = a;
            return iArr;
        } catch (Throwable th) {
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Throwable th) {
            return -1;
        }
    }

    public static String p(Context context) {
        String str = "Unknown";
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2;
                    }
                }
            }
            return str;
        } catch (Throwable th) {
            return str;
        }
    }

    public static String q(Context context) {
        return context.getPackageName();
    }

    public static String r(Context context) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(q(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable th) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String toHexString = Integer.toHexString(bArr[i]);
            int length = toHexString.length();
            if (length == 1) {
                toHexString = "0" + toHexString;
            }
            if (length > 2) {
                toHexString = toHexString.substring(length - 2, length);
            }
            stringBuilder.append(toHexString.toUpperCase(Locale.getDefault()));
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    public static String s(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String t(Context context) {
        String str = null;
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
        }
        return str;
    }

    private static String z(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23) {
            str = B(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = y(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(str)) {
                return d();
            }
            return str;
        } else if (VERSION.SDK_INT == 23) {
            str = B(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b();
            if (TextUtils.isEmpty(str)) {
                if (a.d) {
                    str = c();
                } else {
                    str = y(context);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(str)) {
                return d();
            }
            return str;
        } else {
            str = B(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = d();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b();
            if (TextUtils.isEmpty(str)) {
                return y(context);
            }
            return str;
        }
    }

    private static String A(Context context) {
        String str = "";
        if (VERSION.SDK_INT < 23) {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = y(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = d();
            if (TextUtils.isEmpty(str)) {
                return B(context);
            }
            return str;
        } else if (VERSION.SDK_INT == 23) {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b();
            if (TextUtils.isEmpty(str)) {
                if (a.d) {
                    str = c();
                } else {
                    str = y(context);
                }
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = d();
            if (TextUtils.isEmpty(str)) {
                return B(context);
            }
            return str;
        } else {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = d();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = B(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b();
            if (TextUtils.isEmpty(str)) {
                return y(context);
            }
            return str;
        }
    }

    private static String B(Context context) {
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return str;
        }
        try {
            String deviceId;
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                deviceId = telephonyManager.getDeviceId();
            } else {
                deviceId = str;
            }
            return deviceId;
        } catch (Throwable th) {
            return str;
        }
    }

    private static String d() {
        String str = "";
        if (VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return str;
    }

    public static String u(Context context) {
        Properties e = e();
        try {
            String property = e.getProperty(Platform.RUNTIME_MIUI);
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (f()) {
                return Platform.FLYME;
            }
            if (TextUtils.isEmpty(a(e))) {
                return property;
            }
            return "YunOS";
        } catch (Throwable th) {
            return null;
        }
    }

    public static String v(Context context) {
        Properties e = e();
        try {
            String property = e.getProperty(Platform.RUNTIME_MIUI);
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (f()) {
                try {
                    return b(e);
                } catch (Throwable th) {
                    return property;
                }
            }
            try {
                return a(e);
            } catch (Throwable th2) {
                return property;
            }
        } catch (Throwable th3) {
            return null;
        }
    }

    private static String a(Properties properties) {
        Object property = properties.getProperty("ro.yunos.version");
        return !TextUtils.isEmpty(property) ? property : null;
    }

    private static String b(Properties properties) {
        try {
            String toLowerCase = properties.getProperty(Platform.RUNTIME_DISPLAY).toLowerCase(Locale.getDefault());
            if (toLowerCase.contains("flyme os")) {
                return toLowerCase.split(" ")[2];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    private static Properties e() {
        FileInputStream fileInputStream;
        Throwable th;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileInputStream2 = fileInputStream;
                th = th4;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable th5) {
                    }
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return properties;
    }

    private static boolean f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String w(Context context) {
        if (context == null) {
            return null;
        }
        try {
            String str;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || !a(context, "android.permission.READ_PHONE_STATE")) {
                str = null;
            } else {
                str = telephonyManager.getDeviceId();
            }
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                str = Secure.getString(context.getContentResolver(), "android_id");
                if (!TextUtils.isEmpty(str) || VERSION.SDK_INT < 9) {
                    return str;
                }
                return Build.SERIAL;
            } catch (Throwable th) {
                return str;
            }
        } catch (Throwable th2) {
            return null;
        }
    }
}
