package com.umeng.commonsdk.statistics.common;

import android.content.Context;
import android.content.pm.Signature;
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
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

public class DeviceConfig {
    public static final int DEFAULT_TIMEZONE = 8;
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";
    protected static final String a = DeviceConfig.class.getName();

    public static String getImei(Context context) {
        String deviceId;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    deviceId = telephonyManager.getDeviceId();
                    return deviceId;
                }
            } catch (Throwable e) {
                if (!AnalyticsConstants.UM_DEBUG) {
                    return null;
                }
                MLog.w("No IMEI.", e);
                return null;
            }
        }
        deviceId = null;
        return deviceId;
    }

    public static String getImeiNew(Context context) {
        Throwable th;
        String str;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null && checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    if (VERSION.SDK_INT < 26) {
                        return telephonyManager.getDeviceId();
                    }
                    CharSequence charSequence;
                    try {
                        Method method = telephonyManager.getClass().getMethod("getImei", new Class[0]);
                        method.setAccessible(true);
                        charSequence = (String) method.invoke(telephonyManager, new Object[0]);
                    } catch (Exception e) {
                        charSequence = null;
                    }
                    try {
                        if (TextUtils.isEmpty(charSequence)) {
                            return telephonyManager.getDeviceId();
                        }
                        return charSequence;
                    } catch (Throwable e2) {
                        Throwable th2 = e2;
                        CharSequence charSequence2 = charSequence;
                        th = th2;
                        if (AnalyticsConstants.UM_DEBUG) {
                            return str;
                        }
                        MLog.w("No IMEI.", th);
                        return str;
                    }
                }
            } catch (Throwable e22) {
                th = e22;
                str = null;
                if (AnalyticsConstants.UM_DEBUG) {
                    return str;
                }
                MLog.w("No IMEI.", th);
                return str;
            }
        }
        return null;
    }

    public static String getAndroidId(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = Secure.getString(context.getContentResolver(), "android_id");
            } catch (Exception e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w("can't read android id");
                }
            }
        }
        return str;
    }

    public static String getSerial() {
        if (VERSION.SDK_INT < 9) {
            return null;
        }
        if (VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        try {
            Class cls = Class.forName("android.os.Build");
            return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        return UMUtils.getAppVersionCode(context);
    }

    public static String getAppVersionName(Context context) {
        return UMUtils.getAppVersionName(context);
    }

    public static boolean checkPermission(Context context, String str) {
        if (context == null) {
            return false;
        }
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

    public static String[] getGPU(GL10 gl10) {
        try {
            String[] strArr = new String[2];
            String glGetString = gl10.glGetString(7936);
            String glGetString2 = gl10.glGetString(7937);
            strArr[0] = glGetString;
            strArr[1] = glGetString2;
            return strArr;
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(a, "Could not read gpu infor:", th);
            }
            return new String[0];
        }
    }

    private static String a() {
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

    private static String b() {
        try {
            String[] strArr = new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"};
            for (String a : strArr) {
                String a2 = a(a2);
                if (a2 != null) {
                    return a2;
                }
            }
        } catch (Throwable th) {
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

    public static String getCPU() {
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
                        MLog.e(a, "Could not read from file /proc/cpuinfo", th3);
                    } catch (Throwable th32) {
                        th = th32;
                        str = str2;
                        th2 = th;
                        MLog.e(a, "Could not open file /proc/cpuinfo", th2);
                        str2 = str;
                        if (str2 != null) {
                            return "";
                        }
                        return str2.substring(str2.indexOf(58) + 1).trim();
                    }
                }
            }
        } catch (Throwable th322) {
            th = th322;
            str = str2;
            th2 = th;
            MLog.e(a, "Could not open file /proc/cpuinfo", th2);
            str2 = str;
            if (str2 != null) {
                return str2.substring(str2.indexOf(58) + 1).trim();
            }
            return "";
        }
        if (str2 != null) {
            return str2.substring(str2.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String getDeviceId(Context context) {
        if (AnalyticsConstants.getDeviceType() == 2) {
            return getDeviceIdForBox(context);
        }
        return getDeviceIdForGeneral(context);
    }

    public static String getDeviceIdUmengMD5(Context context) {
        return HelperUtils.getUmengMD5(getDeviceId(context));
    }

    public static String getMCCMNC(Context context) {
        if (context == null || getImsi(context) == null) {
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

    public static String getImsi(Context context) {
        if (context == null) {
            return null;
        }
        String subscriberId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            subscriberId = telephonyManager.getSubscriberId();
        } else {
            subscriberId = null;
        }
        return subscriberId;
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        String networkOperator;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            networkOperator = telephonyManager.getNetworkOperator();
        } else {
            networkOperator = null;
        }
        return networkOperator;
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String getDisplayResolution(Context context) {
        if (context == null) {
            return "";
        }
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String[] getNetworkAccessMode(Context context) {
        String[] strArr = new String[]{"", ""};
        if (context == null) {
            return strArr;
        }
        try {
            if (checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
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

    public static boolean isWiFiAvailable(Context context) {
        if (context == null) {
            return false;
        }
        return "Wi-Fi".equals(getNetworkAccessMode(context)[0]);
    }

    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
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

    public static int getTimeZone(Context context) {
        int i = 8;
        if (context != null) {
            try {
                Calendar instance = Calendar.getInstance(a(context));
                if (instance != null) {
                    i = instance.getTimeZone().getRawOffset() / 3600000;
                }
            } catch (Throwable th) {
                MLog.i(a, "error in getTimeZone", th);
            }
        }
        return i;
    }

    public static boolean isChineseAera(Context context) {
        if (context == null) {
            return false;
        }
        Object imprintProperty = UMEnvelopeBuild.imprintProperty(context, g.N, "");
        if (TextUtils.isEmpty(imprintProperty)) {
            if (getImsi(context) == null) {
                imprintProperty = getLocaleInfo(context)[0];
                if (TextUtils.isEmpty(imprintProperty) || !imprintProperty.equalsIgnoreCase("cn")) {
                    return false;
                }
                return true;
            }
            int i = context.getResources().getConfiguration().mcc;
            if (i == 460 || i == 461) {
                return true;
            }
            if (i != 0) {
                return false;
            }
            imprintProperty = getLocaleInfo(context)[0];
            if (TextUtils.isEmpty(imprintProperty) || !imprintProperty.equalsIgnoreCase("cn")) {
                return false;
            }
            return true;
        } else if (imprintProperty.equals("cn")) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] getLocaleInfo(Context context) {
        String[] strArr = new String[]{"Unknown", "Unknown"};
        if (context != null) {
            try {
                Locale a = a(context);
                if (a != null) {
                    strArr[0] = a.getCountry();
                    strArr[1] = a.getLanguage();
                }
                if (TextUtils.isEmpty(strArr[0])) {
                    strArr[0] = "Unknown";
                }
                if (TextUtils.isEmpty(strArr[1])) {
                    strArr[1] = "Unknown";
                }
            } catch (Throwable th) {
                MLog.e(a, "error in getLocaleInfo", th);
            }
        }
        return strArr;
    }

    private static Locale a(Context context) {
        Locale locale = null;
        if (context == null) {
            return Locale.getDefault();
        }
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Throwable th) {
            MLog.e(a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String getMac(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        if (VERSION.SDK_INT < 23) {
            return b(context);
        }
        if (VERSION.SDK_INT == 23) {
            str = a();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (AnalyticsConstants.CHECK_DEVICE) {
                return b();
            }
            return b(context);
        }
        str = a();
        if (TextUtils.isEmpty(str)) {
            return b(context);
        }
        return str;
    }

    private static String b(Context context) {
        if (context == null) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(a, "Could not get mac address." + th.toString());
            }
            return "";
        }
    }

    public static int[] getResolutionArray(Context context) {
        if (context == null) {
            return null;
        }
        try {
            int a;
            int a2;
            int i;
            DisplayMetrics displayMetrics = new DisplayMetrics();
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
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.e(a, "read resolution fail", th);
            }
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

    public static String getPackageName(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public static String getAppSHA1Key(Context context) {
        try {
            return a(MessageDigest.getInstance("SHA1").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getAppHashKey(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures;
            if (0 < signatureArr.length) {
                Signature signature = signatureArr[0];
                MessageDigest instance = MessageDigest.getInstance("SHA");
                instance.update(signature.toByteArray());
                return Base64.encodeToString(instance.digest(), 0).trim();
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String getAppMD5Signature(Context context) {
        if (context == null) {
            return null;
        }
        String a;
        try {
            a = a(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(getPackageName(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable th) {
            a = null;
        }
        return a;
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

    public static String getApplicationLable(Context context) {
        if (context == null) {
            return "";
        }
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String getAppName(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
            } catch (Throwable th) {
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.i(a, th);
                }
            }
        }
        return str;
    }

    public static String getDeviceIdForGeneral(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        if (VERSION.SDK_INT < 23) {
            str = c(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.w(a, "No IMEI.");
            }
            str = b(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, ANDROID_ID: " + str);
            }
            if (TextUtils.isEmpty(str)) {
                return c();
            }
            return str;
        } else if (VERSION.SDK_INT == 23) {
            str = c(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = a();
            if (TextUtils.isEmpty(str)) {
                if (AnalyticsConstants.CHECK_DEVICE) {
                    str = b();
                } else {
                    str = b(context);
                }
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, MAC: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, ANDROID_ID: " + str);
            }
            if (TextUtils.isEmpty(str)) {
                return c();
            }
            return str;
        } else {
            str = c(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = c();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, ANDROID_ID: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = a();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b(context);
            if (!AnalyticsConstants.UM_DEBUG) {
                return str;
            }
            MLog.i(a, "getDeviceId, MAC: " + str);
            return str;
        }
    }

    public static String getDeviceIdForBox(Context context) {
        String str = "";
        if (context == null) {
            return str;
        }
        if (VERSION.SDK_INT < 23) {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, ANDROID_ID: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b(context);
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, MAC: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = c();
            if (TextUtils.isEmpty(str)) {
                return c(context);
            }
            return str;
        } else if (VERSION.SDK_INT == 23) {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, ANDROID_ID: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = a();
            if (TextUtils.isEmpty(str)) {
                if (AnalyticsConstants.CHECK_DEVICE) {
                    str = b();
                } else {
                    str = b(context);
                }
            }
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId, MAC: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = c();
            if (TextUtils.isEmpty(str)) {
                return c(context);
            }
            return str;
        } else {
            str = Secure.getString(context.getContentResolver(), "android_id");
            if (AnalyticsConstants.UM_DEBUG) {
                MLog.i(a, "getDeviceId: ANDROID_ID: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = c();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = c(context);
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = a();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            str = b(context);
            if (!AnalyticsConstants.UM_DEBUG) {
                return str;
            }
            MLog.i(a, "getDeviceId, MAC: " + str);
            return str;
        }
    }

    private static String c(Context context) {
        Throwable th;
        String str = "";
        if (context == null) {
            return str;
        }
        String deviceId;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                    deviceId = telephonyManager.getDeviceId();
                    try {
                        if (AnalyticsConstants.UM_DEBUG) {
                            MLog.i(a, "getDeviceId, IMEI: " + deviceId);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (AnalyticsConstants.UM_DEBUG) {
                            MLog.w(a, "No IMEI.", th);
                        }
                        return deviceId;
                    }
                    return deviceId;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                deviceId = str;
                th = th4;
                if (AnalyticsConstants.UM_DEBUG) {
                    MLog.w(a, "No IMEI.", th);
                }
                return deviceId;
            }
        }
        deviceId = str;
        return deviceId;
    }

    private static String c() {
        String str;
        String str2 = "";
        if (VERSION.SDK_INT < 9) {
            str = str2;
        } else if (VERSION.SDK_INT >= 26) {
            try {
                Class cls = Class.forName("android.os.Build");
                str = (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
            } catch (Throwable th) {
                str = str2;
            }
        } else {
            str = Build.SERIAL;
        }
        if (AnalyticsConstants.UM_DEBUG) {
            MLog.i(a, "getDeviceId, serial no: " + str);
        }
        return str;
    }

    public static String getSubOSName(Context context) {
        Properties d = d();
        try {
            String property = d.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (e()) {
                return "Flyme";
            }
            if (d(d)) {
                return "Emui";
            }
            if (TextUtils.isEmpty(a(d))) {
                return property;
            }
            return "YunOS";
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getSubOSVersion(Context context) {
        Properties d = d();
        try {
            String property = d.getProperty("ro.miui.ui.version.name");
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (e()) {
                try {
                    return b(d);
                } catch (Throwable th) {
                    return property;
                }
            } else if (d(d)) {
                try {
                    return c(d);
                } catch (Throwable th2) {
                    return property;
                }
            } else {
                try {
                    return a(d);
                } catch (Throwable th3) {
                    return property;
                }
            }
        } catch (Throwable th4) {
            return null;
        }
    }

    private static String a(Properties properties) {
        Object property = properties.getProperty("ro.yunos.version");
        return !TextUtils.isEmpty(property) ? property : null;
    }

    private static String b(Properties properties) {
        try {
            String toLowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (toLowerCase.contains("flyme os")) {
                return toLowerCase.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[2];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    private static String c(Properties properties) {
        String str = null;
        try {
            str = properties.getProperty("ro.build.hw_emui_api_level", null);
        } catch (Exception e) {
        }
        return str;
    }

    private static Properties d() {
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

    private static boolean e() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static boolean d(Properties properties) {
        try {
            if (properties.getProperty("ro.build.hw_emui_api_level", null) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        String str = "Phone";
        if (context == null) {
            return str;
        }
        if (((context.getResources().getConfiguration().screenLayout & 15) >= 3 ? 1 : null) != null) {
            return "Tablet";
        }
        return "Phone";
    }

    public static String getDBencryptID(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || !checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                str = null;
            } else {
                str = telephonyManager.getDeviceId();
            }
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                CharSequence string = Secure.getString(context.getContentResolver(), "android_id");
                try {
                    if (!TextUtils.isEmpty(string) || VERSION.SDK_INT < 9) {
                        return string;
                    }
                    if (VERSION.SDK_INT < 26) {
                        return Build.SERIAL;
                    }
                    try {
                        Class cls = Class.forName("android.os.Build");
                        return (String) cls.getMethod("getSerial", new Class[0]).invoke(cls, new Object[0]);
                    } catch (Throwable th) {
                        return string;
                    }
                } catch (Throwable th2) {
                    return string;
                }
            } catch (Throwable th3) {
                return str;
            }
        } catch (Throwable th4) {
            return null;
        }
    }
}
