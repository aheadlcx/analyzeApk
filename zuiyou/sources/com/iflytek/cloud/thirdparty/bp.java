package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import com.umeng.analytics.b.g;
import com.zhihu.matisse.internal.utils.Platform;
import java.lang.reflect.Field;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bp {
    public static String[][] a;
    public static String[][] b;
    private static String[][] c;
    private static ce d = new ce();
    private static boolean e = false;
    private static String f = null;
    private static String g = null;

    static {
        r0 = new String[2][];
        r0[0] = new String[]{"=", ":"};
        r0[1] = new String[]{",", "_"};
        a = r0;
        r0 = new String[11][];
        r0[0] = new String[]{"os.manufact", Build.MANUFACTURER};
        r0[1] = new String[]{"os.model", Build.MODEL};
        r0[2] = new String[]{"os.product", Build.PRODUCT};
        r0[3] = new String[]{"os.display", Build.DISPLAY};
        r0[4] = new String[]{"os.host", Build.HOST};
        r0[5] = new String[]{"os.id", Build.ID};
        r0[6] = new String[]{"os.device", Build.DEVICE};
        r0[7] = new String[]{"os.board", Build.BOARD};
        r0[8] = new String[]{"os.brand", Build.BRAND};
        r0[9] = new String[]{"os.user", Build.USER};
        r0[10] = new String[]{"os.type", Build.TYPE};
        b = r0;
        r0 = new String[6][];
        r0[0] = new String[]{"os.cpu", "CPU_ABI"};
        r0[1] = new String[]{"os.cpu2", "CPU_ABI2"};
        r0[2] = new String[]{"os.serial", "SERIAL"};
        r0[3] = new String[]{"os.hardware", "HARDWARE"};
        r0[4] = new String[]{"os.bootloader", "BOOTLOADER"};
        r0[5] = new String[]{"os.radio", "RADIO"};
        c = r0;
    }

    public static synchronized ce a(Context context) {
        ce ceVar;
        synchronized (bp.class) {
            if (e) {
                ceVar = d;
            } else {
                h(context);
                ceVar = d;
            }
        }
        return ceVar;
    }

    public static String a(String str) {
        cb.d("check and replace src: " + str);
        if (str != null) {
            for (int i = 0; i < a.length; i++) {
                str = str.replace(a[i][0], a[i][1]);
            }
        } else {
            str = null;
        }
        cb.d("check and replace result: " + str);
        return str;
    }

    public static void a(ce ceVar, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ceVar.a("app.ver.name", packageInfo.versionName);
            ceVar.a("app.ver.code", "" + packageInfo.versionCode);
            ceVar.a("app.pkg", applicationInfo.packageName);
            ceVar.a("app.path", applicationInfo.dataDir);
            ceVar.a("app.name", applicationInfo.loadLabel(context.getPackageManager()).toString());
        } catch (Exception e) {
        }
    }

    public static ce b(Context context) {
        ce a = a(context);
        ce ceVar = new ce();
        ceVar.a(a, "app.name");
        ceVar.a(a, "app.path");
        ceVar.a(a, "app.pkg");
        ceVar.a(a, "app.ver.name");
        ceVar.a(a, "app.ver.code");
        ceVar.a(a, "os.system");
        ceVar.a(a, "os.resolution");
        ceVar.a(a, "os.density");
        ceVar.a(a, "net.mac");
        ceVar.a(a, "os.imei");
        ceVar.a(a, "os.imsi");
        ceVar.a(a, "os.version");
        ceVar.a(a, "os.release");
        ceVar.a(a, "os.incremental");
        ceVar.a(a, "os.android_id");
        ceVar.a(a, g.H);
        ceVar.a(a, b[0][0]);
        ceVar.a(a, b[1][0]);
        ceVar.a(a, b[2][0]);
        ceVar.a(a, b[3][0]);
        return ceVar;
    }

    public static String c(Context context) {
        String str = "";
        try {
            Object networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            return !TextUtils.isEmpty(networkOperatorName) ? networkOperatorName : str;
        } catch (Throwable th) {
            return str;
        }
    }

    private static String c(String str) {
        try {
            Field field = Build.class.getField(str);
            return field != null ? field.get(new Build()).toString() : "unknown";
        } catch (Exception e) {
            return "unknown";
        }
    }

    public static String d(Context context) {
        String str = "";
        if (VERSION.SDK_INT >= 23) {
            try {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    byte[] hardwareAddress = ((NetworkInterface) networkInterfaces.nextElement()).getHardwareAddress();
                    if (!(hardwareAddress == null || hardwareAddress.length == 0)) {
                        StringBuilder stringBuilder = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                        }
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }
                        String stringBuilder2 = stringBuilder.toString();
                        if (stringBuilder2 != null && stringBuilder2.length() > 0) {
                            return stringBuilder2;
                        }
                    }
                }
                return str;
            } catch (SocketException e) {
                cb.e(e + "");
                return str;
            }
        }
        try {
            return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Throwable th) {
            cb.a("Failed to get mac Info");
            return str;
        }
    }

    public static String e(final Context context) {
        if (TextUtils.isEmpty(f)) {
            try {
                new Handler(context.getMainLooper(), new Callback() {
                    public boolean handleMessage(Message message) {
                        switch (message.what) {
                            case 1:
                                bp.f = new WebView(context).getSettings().getUserAgentString();
                                cb.d("user agent: " + bp.f);
                                break;
                        }
                        return false;
                    }
                }).sendEmptyMessage(1);
            } catch (Throwable th) {
                cb.a(th);
            }
        }
        cb.d("get user agent: " + f);
        return f;
    }

    public static String f(Context context) {
        if (TextUtils.isEmpty(g)) {
            try {
                int i = context.getResources().getConfiguration().screenLayout;
                if ((i & 15) >= 3) {
                    g = "tablet";
                } else if ((i & 15) >= 1) {
                    g = "handset";
                } else {
                    g = Platform.UNKNOW;
                }
            } catch (Throwable th) {
                cb.a(th);
            }
        }
        cb.d("get device type: " + g);
        return g;
    }

    public static Map<String, Object> g(Context context) {
        Map<String, Object> hashMap = new HashMap();
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null && wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    hashMap.put("info", connectionInfo);
                }
                List scanResults = wifiManager.getScanResults();
                if (scanResults != null && scanResults.size() > 0) {
                    hashMap.put("scan", scanResults);
                }
                cb.b("UserLogger", "getWifiList :" + scanResults.size());
            }
        } catch (SecurityException e) {
            cb.a("Perm denied to get wfs");
        } catch (Exception e2) {
            cb.e("failed to get wfs");
        }
        return hashMap;
    }

    private static void h(Context context) {
        try {
            int i;
            d.a();
            d.a("os.system", "Android");
            a(d, context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            d.a("os.resolution", displayMetrics.widthPixels + "*" + displayMetrics.heightPixels);
            d.a("os.density", "" + displayMetrics.density);
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            try {
                d.a("os.imei", telephonyManager.getDeviceId());
            } catch (Exception e) {
                cb.a("Failed to get prop Info");
                e = false;
            } catch (Throwable th) {
                cb.a("Failed to get did Info");
            }
            try {
                d.a("os.imsi", telephonyManager.getSubscriberId());
            } catch (Exception e2) {
                cb.a("Failed to get prop Info");
                e = false;
            } catch (Throwable th2) {
                cb.a("Failed to get sbid Info");
            }
            String string = Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                d.a("os.android_id", string);
            }
            d.a("os.version", VERSION.SDK);
            d.a("os.release", VERSION.RELEASE);
            d.a("os.incremental", VERSION.INCREMENTAL);
            for (i = 0; i < b.length; i++) {
                d.a(b[i][0], b[i][1]);
            }
            for (i = 0; i < c.length; i++) {
                d.a(c[i][0], c(c[i][1]));
            }
            d.a("net.mac", d(context));
            d.a(g.H, c(context));
            d.d();
            e = true;
        } catch (Exception e22) {
            cb.a("Failed to get prop Info");
            e = false;
        } catch (Throwable th3) {
            cb.a("Failed to get property Info");
            e = false;
        }
    }
}
