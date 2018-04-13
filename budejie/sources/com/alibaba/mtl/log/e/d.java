package com.alibaba.mtl.log.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.alibaba.mtl.appmonitor.SdkMeta;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.model.LogField;
import com.umeng.analytics.pro.bv;
import com.ut.device.UTDevice;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.httpclient.HttpState;

public class d {
    private static Map<String, String> t = null;

    public static synchronized Map<String, String> a(Context context) {
        Map<String, String> map = null;
        synchronized (d.class) {
            Object imei;
            if (t != null) {
                t.put(LogField.CHANNEL.toString(), b.l());
                t.put(LogField.APPKEY.toString(), b.getAppkey());
                imei = m.getImei(context);
                Object imsi = m.getImsi(context);
                if (TextUtils.isEmpty(imei) || TextUtils.isEmpty(imsi)) {
                    imei = "";
                    imsi = "";
                }
                t.put(LogField.IMEI.toString(), imei);
                t.put(LogField.IMSI.toString(), imsi);
                a(t, context);
                map = t;
            } else {
                t = new HashMap();
                if (context != null) {
                    if (t != null) {
                        try {
                            Object imei2 = m.getImei(context);
                            imei = m.getImsi(context);
                            if (TextUtils.isEmpty(imei2) || TextUtils.isEmpty(imei)) {
                                imei2 = "";
                                imei = "";
                            }
                            t.put(LogField.IMEI.toString(), imei2);
                            t.put(LogField.IMSI.toString(), imei);
                            t.put(LogField.BRAND.toString(), Build.BRAND);
                            t.put(LogField.DEVICE_MODEL.toString(), Build.MODEL);
                            t.put(LogField.RESOLUTION.toString(), c(context));
                            t.put(LogField.CHANNEL.toString(), b.l());
                            t.put(LogField.APPKEY.toString(), b.getAppkey());
                            t.put(LogField.APPVERSION.toString(), d(context));
                            t.put(LogField.LANGUAGE.toString(), b(context));
                            t.put(LogField.OS.toString(), p());
                            t.put(LogField.OSVERSION.toString(), o());
                            t.put(LogField.SDKVERSION.toString(), SdkMeta.SDK_VERSION);
                            t.put(LogField.SDKTYPE.toString(), "mini");
                            try {
                                t.put(LogField.UTDID.toString(), UTDevice.getUtdid(context));
                            } catch (Throwable th) {
                                Log.e("DeviceUtil", "utdid4all jar doesn't exist, please copy the libs folder.");
                                th.printStackTrace();
                            }
                            a(t, context);
                        } catch (Exception e) {
                        }
                    }
                    map = t;
                }
            }
        }
        return map;
    }

    private static String o() {
        String str = VERSION.RELEASE;
        if (k()) {
            System.getProperty("ro.yunos.version");
            str = s();
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return str;
    }

    private static String p() {
        String str = "a";
        if (!k() || l()) {
            return str;
        }
        return "y";
    }

    private static void a(Map<String, String> map, Context context) {
        try {
            String[] networkState = l.getNetworkState(context);
            map.put(LogField.ACCESS.toString(), networkState[0]);
            if (networkState[0].equals(bv.c)) {
                map.put(LogField.ACCESS_SUBTYPE.toString(), networkState[1]);
            } else {
                map.put(LogField.ACCESS_SUBTYPE.toString(), "Unknown");
            }
        } catch (Exception e) {
            map.put(LogField.ACCESS.toString(), "Unknown");
            map.put(LogField.ACCESS_SUBTYPE.toString(), "Unknown");
        }
        try {
            Object networkOperatorName;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String str = "";
            if (telephonyManager == null || telephonyManager.getSimState() != 5) {
                String str2 = str;
            } else {
                networkOperatorName = telephonyManager.getNetworkOperatorName();
            }
            if (TextUtils.isEmpty(networkOperatorName)) {
                networkOperatorName = "Unknown";
            }
            map.put(LogField.CARRIER.toString(), networkOperatorName);
        } catch (Exception e2) {
        }
    }

    private static String b(Context context) {
        String str = "Unknown";
        try {
            str = Locale.getDefault().getLanguage();
        } catch (Throwable th) {
        }
        return str;
    }

    private static String c(Context context) {
        String str = "Unknown";
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            if (i > i2) {
                i ^= i2;
                i2 ^= i;
                i ^= i2;
            }
            str = i2 + "*" + i;
        } catch (Exception e) {
        }
        return str;
    }

    public static String d(Context context) {
        String appVersion = b.a().getAppVersion();
        if (TextUtils.isEmpty(appVersion)) {
            appVersion = "Unknown";
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                if (packageInfo != null) {
                    t.put(LogField.APPVERSION.toString(), packageInfo.versionName);
                    appVersion = packageInfo.versionName;
                }
            } catch (Throwable th) {
            }
        }
        return appVersion;
    }

    public static boolean k() {
        if ((System.getProperty("java.vm.name") == null || !System.getProperty("java.vm.name").toLowerCase().contains("lemur")) && System.getProperty("ro.yunos.version") == null && TextUtils.isEmpty(q.get("ro.yunos.build.version"))) {
            return l();
        }
        return true;
    }

    private static boolean l() {
        if (TextUtils.isEmpty(c("ro.yunos.product.chip")) && TextUtils.isEmpty(c("ro.yunos.hardware"))) {
            return false;
        }
        return true;
    }

    public static String c(String str) {
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls.newInstance(), new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String q() {
        String str = q.get("ro.aliyun.clouduuid", HttpState.PREEMPTIVE_DEFAULT);
        if (HttpState.PREEMPTIVE_DEFAULT.equals(str)) {
            str = q.get("ro.sys.aliyun.clouduuid", HttpState.PREEMPTIVE_DEFAULT);
        }
        if (TextUtils.isEmpty(str)) {
            return r();
        }
        return str;
    }

    private static String r() {
        try {
            return (String) Class.forName("com.yunos.baseservice.clouduuid.CloudUUID").getMethod("getCloudUUID", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private static String s() {
        try {
            Field declaredField = Build.class.getDeclaredField("YUNOS_BUILD_VERSION");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                return (String) declaredField.get(new String());
            }
        } catch (Exception e) {
        }
        return null;
    }
}
