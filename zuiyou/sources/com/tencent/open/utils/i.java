package com.tencent.open.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.meizu.cloud.pushsdk.platform.pushstrategy.Strategy;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import org.json.JSONException;
import org.json.JSONObject;

public class i {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static int e = -1;
    private static String f;
    private static String g = "0123456789ABCDEF";

    public static class a {
        public String a;
        public long b;
        public long c;

        public a(String str, int i) {
            this.a = str;
            this.b = (long) i;
            if (this.a != null) {
                this.c = (long) this.a.length();
            }
        }
    }

    public static Bundle a(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length == 2) {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
            return bundle;
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length == 2) {
                    try {
                        split2[0] = URLDecoder.decode(split2[0]);
                        split2[1] = URLDecoder.decode(split2[1]);
                    } catch (Exception e) {
                    }
                    try {
                        jSONObject.put(split2[0], split2[1]);
                    } catch (JSONException e2) {
                        f.e("openSDK_LOG.Util", "decodeUrlToJson has exception: " + e2.getMessage());
                    }
                }
            }
        }
        return jSONObject;
    }

    public static Bundle b(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            Bundle a = a(url.getQuery());
            a.putAll(a(url.getRef()));
            return a;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static JSONObject c(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            JSONObject a = a(null, url.getQuery());
            a(a, url.getRef());
            return a;
        } catch (MalformedURLException e) {
            return new JSONObject();
        }
    }

    public static JSONObject d(String str) throws JSONException {
        if (str.equals("false")) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = "{online:" + str.charAt(str.length() - 2) + "}";
        }
        return new JSONObject(str);
    }

    public static String a() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Throwable e) {
            f.a("openSDK_LOG.Util", "getUserIp SocketException ", e);
        }
        return "";
    }

    public static boolean e(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean g(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
            String str = packageInfo.versionName;
            if (g.a(str, "4.3") < 0 || str.startsWith("4.4")) {
                return false;
            }
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr == null) {
                return false;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(signatureArr[0].toByteArray());
                String a = a(instance.digest());
                instance.reset();
                str = "d8391a394d4a179e6fe7bdb8a301258b";
                if (a.equals("d8391a394d4a179e6fe7bdb8a301258b")) {
                    return true;
                }
                return false;
            } catch (NoSuchAlgorithmException e) {
                f.e("openSDK_LOG.Util", "isQQBrowerAvailable has exception: " + e.getMessage());
                return false;
            }
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        boolean g;
        try {
            g = g(context);
            if (g) {
                try {
                    a(context, "com.tencent.mtt", "com.tencent.mtt.MainActivity", str);
                } catch (Exception e) {
                    if (g) {
                        try {
                            a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                        } catch (Exception e2) {
                            try {
                                a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                            } catch (Exception e3) {
                                return false;
                            }
                        }
                    }
                    try {
                        a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                    } catch (Exception e4) {
                        try {
                            a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                        } catch (Exception e5) {
                            try {
                                a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                            } catch (Exception e6) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
            a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
        } catch (Exception e7) {
            g = false;
            if (g) {
                a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
            } else {
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
            }
            return true;
        }
        return true;
    }

    private static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1073741824);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.setData(Uri.parse(str3));
        context.startActivity(intent);
    }

    public static String f(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(i(str));
            byte[] digest = instance.digest();
            if (digest != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i : digest) {
                    stringBuilder.append(a(i >>> 4));
                    stringBuilder.append(a(i));
                }
                str = stringBuilder.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            f.e("openSDK_LOG.Util", "encrypt has exception: " + e.getMessage());
        }
        return str;
    }

    private static char a(int i) {
        int i2 = i & 15;
        if (i2 < 10) {
            return (char) (i2 + 48);
        }
        return (char) ((i2 - 10) + 97);
    }

    public static boolean b() {
        File file = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = Environment.getExternalStorageDirectory();
        }
        if (file != null) {
            return true;
        }
        return false;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            String num = Integer.toString(b & 255, 16);
            if (num.length() == 1) {
                num = "0" + num;
            }
            stringBuilder.append(num);
        }
        return stringBuilder.toString();
    }

    public static final String a(Context context) {
        if (context != null) {
            CharSequence applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo());
            if (applicationLabel != null) {
                return applicationLabel.toString();
            }
        }
        return null;
    }

    public static final boolean g(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            return true;
        }
        return false;
    }

    public static boolean h(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public static final String a(String str, int i, String str2, String str3) {
        Exception exception;
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str4 = "UTF-8";
        if (TextUtils.isEmpty(str2)) {
            str2 = str4;
        }
        try {
            if (str.getBytes(str2).length <= i) {
                return str;
            }
            int i3 = 0;
            while (i2 < str.length()) {
                int length = str.substring(i2, i2 + 1).getBytes(str2).length;
                if (i3 + length > i) {
                    String substring = str.substring(0, i2);
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            substring = substring + str3;
                        }
                        return substring;
                    } catch (Exception e) {
                        str = substring;
                        exception = e;
                        System.out.println("StructMsg sSubString error : " + exception.getMessage());
                        return str;
                    }
                }
                i3 += length;
                i2++;
            }
            return str;
        } catch (Exception e2) {
            exception = e2;
            System.out.println("StructMsg sSubString error : " + exception.getMessage());
            return str;
        }
    }

    public static boolean b(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo isConnectedOrConnecting : allNetworkInfo) {
            if (isConnectedOrConnecting.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6) {
        return a(str, str3, str4, str2, str5, str6, "", "", "", "", "", "");
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Bundle bundle = new Bundle();
        bundle.putString("openid", str);
        bundle.putString("report_type", str2);
        bundle.putString("act_type", str3);
        bundle.putString("via", str4);
        bundle.putString(Strategy.APP_ID, str5);
        bundle.putString(SpeechUtility.TAG_RESOURCE_RESULT, str6);
        bundle.putString("type", str7);
        bundle.putString("login_status", str8);
        bundle.putString("need_user_auth", str9);
        bundle.putString("to_uin", str10);
        bundle.putString("call_source", str11);
        bundle.putString("to_type", str12);
        return bundle;
    }

    public static Bundle a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Bundle bundle = new Bundle();
        bundle.putString("platform", "1");
        bundle.putString(SpeechUtility.TAG_RESOURCE_RESULT, str);
        bundle.putString("code", str2);
        bundle.putString("tmcost", str3);
        bundle.putString("rate", str4);
        bundle.putString(SpeechConstant.ISV_CMD, str5);
        bundle.putString("uin", str6);
        bundle.putString("appid", str7);
        bundle.putString("share_type", str8);
        bundle.putString("detail", str9);
        bundle.putString("os_ver", VERSION.RELEASE);
        bundle.putString("network", com.tencent.open.b.a.a(d.a()));
        bundle.putString("apn", com.tencent.open.b.a.b(d.a()));
        bundle.putString("model_name", Build.MODEL);
        bundle.putString("sdk_ver", Constants.SDK_VERSION);
        bundle.putString("packagename", d.b());
        bundle.putString("app_ver", d(d.a(), d.b()));
        return bundle;
    }

    public static String c(Context context) {
        if (context == null) {
            return "";
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
            Criteria criteria = new Criteria();
            criteria.setCostAllowed(false);
            criteria.setAccuracy(2);
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (bestProvider != null) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation == null) {
                    return "";
                }
                double latitude = lastKnownLocation.getLatitude();
                f = latitude + "*" + lastKnownLocation.getLongitude();
                return f;
            }
        } catch (Throwable e) {
            f.b("openSDK_LOG.Util", "getLocation>>>", e);
        }
        return "";
    }

    public static void b(Context context, String str) {
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
                b = packageInfo.versionName;
                a = b.substring(0, b.lastIndexOf(46));
                d = b.substring(b.lastIndexOf(46) + 1, b.length());
                e = packageInfo.versionCode;
            } catch (NameNotFoundException e) {
                f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e.getMessage());
            } catch (Exception e2) {
                f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e2.getMessage());
            }
        }
    }

    public static String c(Context context, String str) {
        if (context == null) {
            return "";
        }
        b(context, str);
        return b;
    }

    public static String d(Context context, String str) {
        if (context == null) {
            return "";
        }
        b(context, str);
        return a;
    }

    public static String e(Context context, String str) {
        if (context == null) {
            return "";
        }
        c = d(context, str);
        return c;
    }

    public static byte[] i(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static boolean d(Context context) {
        double d = 0.0d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f = ((float) displayMetrics.widthPixels) / displayMetrics.xdpi;
            float f2 = ((float) displayMetrics.heightPixels) / displayMetrics.ydpi;
            d = Math.sqrt(Math.pow((double) f2, 2.0d) + Math.pow((double) f, 2.0d));
        } catch (Throwable th) {
        }
        if (d > 6.5d) {
            return true;
        }
        return false;
    }

    public static boolean f(Context context, String str) {
        boolean z;
        if (!d(context) || g.a(context, Constants.PACKAGE_QQ_PAD) == null) {
            z = true;
        } else {
            z = false;
        }
        if (z && g.a(context, Constants.PACKAGE_TIM) != null) {
            z = false;
        }
        if (!z) {
            return z;
        }
        if (g.c(context, str) < 0) {
            return true;
        }
        return false;
    }

    public static boolean e(Context context) {
        if ((!d(context) || g.a(context, Constants.PACKAGE_QQ_PAD) == null) && g.c(context, "4.1") < 0 && g.a(context, Constants.PACKAGE_TIM) == null) {
            return false;
        }
        return true;
    }

    public static boolean f(Context context) {
        return g.c(context, "5.9.5") >= 0 || g.a(context, Constants.PACKAGE_TIM) != null;
    }
}
