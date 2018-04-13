package com.umeng.commonsdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.proguard.b;
import com.umeng.commonsdk.proguard.g;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.common.MLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.microedition.khronos.opengles.GL10;

public class UMUtils {
    public static final int DEFAULT_TIMEZONE = 8;
    public static final String MOBILE_NETWORK = "2G/3G";
    public static final String UNKNOW = "";
    public static final String WIFI = "Wi-Fi";
    private static final Pattern a = Pattern.compile("UTDID\">([^<]+)");

    public static void setLastAppkey(Context context, String str) {
        if (context != null && str != null) {
            try {
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString("last_appkey", str).commit();
                }
            } catch (Throwable e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "set last app key e is " + e);
                }
                b.a(context, e);
            } catch (Throwable e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "set last app key e is " + e2);
                }
                b.a(context, e2);
            }
        }
    }

    public static String getLastAppkey(Context context) {
        String str = null;
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                if (sharedPreferences != null) {
                    str = sharedPreferences.getString("last_appkey", null);
                }
            } catch (Throwable e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get last app key e is " + e);
                }
                b.a(context, e);
            } catch (Throwable e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get last app key e is " + e2);
                }
                b.a(context, e2);
            }
        }
        return str;
    }

    public static synchronized void setAppkey(Context context, String str) {
        synchronized (UMUtils.class) {
            if (!(context == null || str == null)) {
                try {
                    SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putString("appkey", str).commit();
                    }
                } catch (Throwable e) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e("UMUtils", "set app key e is " + e);
                    }
                    b.a(context, e);
                } catch (Throwable e2) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e("UMUtils", "set app key e is " + e2);
                    }
                    b.a(context, e2);
                }
            }
        }
    }

    public static synchronized String getAppkey(Context context) {
        String str = null;
        synchronized (UMUtils.class) {
            if (context != null) {
                try {
                    SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                    if (sharedPreferences != null) {
                        str = sharedPreferences.getString("appkey", null);
                    }
                } catch (Throwable e) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e("UMUtils", "get app key e is " + e);
                    }
                    b.a(context, e);
                } catch (Throwable e2) {
                    if (AnalyticsConstants.UM_DEBUG) {
                        Log.e("UMUtils", "get app key e is " + e2);
                    }
                    b.a(context, e2);
                }
            }
        }
        return str;
    }

    public static void setChannel(Context context, String str) {
        if (context != null && str != null) {
            try {
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString("channel", str).commit();
                }
            } catch (Throwable e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "set channel e is " + e);
                }
                b.a(context, e);
            } catch (Throwable e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "set channel e is " + e2);
                }
                b.a(context, e2);
            }
        }
    }

    public static String getChannel(Context context) {
        String str = null;
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(a.o, 0);
                if (sharedPreferences != null) {
                    str = sharedPreferences.getString("channel", null);
                }
            } catch (Throwable e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get channel e is " + e);
                }
                b.a(context, e);
            } catch (Throwable e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get channel e is " + e2);
                }
                b.a(context, e2);
            }
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getUTDID(android.content.Context r6) {
        /*
        r1 = 0;
        if (r6 != 0) goto L_0x0005;
    L_0x0003:
        r0 = r1;
    L_0x0004:
        return r0;
    L_0x0005:
        r0 = "com.ut.device.UTDevice";
        r0 = java.lang.Class.forName(r0);	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r2 = "getUtdid";
        r3 = 1;
        r3 = new java.lang.Class[r3];	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r4 = 0;
        r5 = android.content.Context.class;
        r3[r4] = r5;	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r0 = r0.getMethod(r2, r3);	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r2 = 0;
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r4 = 0;
        r5 = r6.getApplicationContext();	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r3[r4] = r5;	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r0 = r0.invoke(r2, r3);	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x002b, Throwable -> 0x0050 }
        goto L_0x0004;
    L_0x002b:
        r0 = move-exception;
        r0 = a(r6);	 Catch:{ Exception -> 0x0031, Throwable -> 0x0050 }
        goto L_0x0004;
    L_0x0031:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x004e;
    L_0x0036:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get utiid e is ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.e(r2, r0);
    L_0x004e:
        r0 = r1;
        goto L_0x0004;
    L_0x0050:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x006d;
    L_0x0055:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get utiid e is ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        android.util.Log.e(r2, r0);
    L_0x006d:
        r0 = r1;
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getUTDID(android.content.Context):java.lang.String");
    }

    private static String a(Context context) {
        InputStream fileInputStream;
        if (context == null) {
            return null;
        }
        File b = b(context);
        if (b == null || !b.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(b);
            String a = a(b(fileInputStream));
            a(fileInputStream);
            return a;
        } catch (Exception e) {
            return null;
        } catch (Throwable th) {
            a(fileInputStream);
        }
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = a.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String b(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (-1 == read) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, read);
        }
    }

    private static File b(Context context) {
        if (context == null || !checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
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
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not read gpu infor, e is " + e);
            }
            return new String[0];
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not read gpu infor, e is " + th);
            }
            return new String[0];
        }
    }

    public static String getCPU() {
        FileNotFoundException fileNotFoundException;
        String str;
        Object obj;
        String str2 = null;
        try {
            Reader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str2 = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e) {
                    try {
                        if (AnalyticsConstants.UM_DEBUG) {
                            Log.e("UMUtils", "Could not read from file /proc/cpuinfo, e is " + e);
                        }
                    } catch (FileNotFoundException e2) {
                        fileNotFoundException = e2;
                        str = str2;
                        obj = fileNotFoundException;
                        if (AnalyticsConstants.UM_DEBUG) {
                            Log.e("UMUtils", "Could not read from file /proc/cpuinfo, e is " + obj);
                        }
                        if (str == null) {
                            return "";
                        }
                        try {
                            return str.substring(str.indexOf(58) + 1).trim();
                        } catch (Exception e3) {
                            if (AnalyticsConstants.UM_DEBUG) {
                                Log.e("UMUtils", "get cpu e is " + e3);
                            }
                            return "";
                        } catch (Throwable th) {
                            if (AnalyticsConstants.UM_DEBUG) {
                                Log.e("UMUtils", "get cpu e is " + th);
                            }
                            return "";
                        }
                    }
                }
            }
            str = str2;
        } catch (FileNotFoundException e22) {
            fileNotFoundException = e22;
            str = str2;
            FileNotFoundException fileNotFoundException2 = fileNotFoundException;
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not read from file /proc/cpuinfo, e is " + obj);
            }
            if (str == null) {
                return "";
            }
            return str.substring(str.indexOf(58) + 1).trim();
        }
        if (str == null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }

    public static String getImsi(Context context) {
        try {
            String subscriberId;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                subscriberId = telephonyManager.getSubscriberId();
            } else {
                subscriberId = null;
            }
            return subscriberId;
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get imei e is " + e);
            }
            b.a(context, e);
            return null;
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get imei e is " + e2);
            }
            b.a(context, e2);
            return null;
        }
    }

    public static String getRegisteredOperator(Context context) {
        if (context == null) {
            return null;
        }
        try {
            String networkOperator;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                networkOperator = telephonyManager.getNetworkOperator();
            } else {
                networkOperator = null;
            }
            return networkOperator;
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get registered operator e is " + e);
            }
            b.a(context, e);
            return null;
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get registered operator e is " + e2);
            }
            b.a(context, e2);
            return null;
        }
    }

    public static String getNetworkOperatorName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!checkPermission(context, "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            if (telephonyManager == null) {
                return "";
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network operator e is " + e);
            }
            b.a(context, e);
            return "";
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network operator e is " + e2);
            }
            b.a(context, e2);
            return "";
        }
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
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get display resolution e is " + e);
            }
            b.a(context, e);
            return "";
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get display resolution e is " + e2);
            }
            b.a(context, e2);
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
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network access mode e is " + e);
            }
            b.a(context, e);
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get network access mode e is " + e2);
            }
            b.a(context, e2);
        }
    }

    public static boolean isSdCardWrittenable() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Locale getLocale(android.content.Context r5) {
        /*
        r1 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r1;
    L_0x0004:
        r0 = new android.content.res.Configuration;	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
        r0.<init>();	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
        r0.setToDefaults();	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
        r2 = r5.getContentResolver();	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
        android.provider.Settings.System.getConfiguration(r2, r0);	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
        if (r0 == 0) goto L_0x003c;
    L_0x0015:
        r0 = r0.locale;	 Catch:{ Exception -> 0x001f, Throwable -> 0x005f }
    L_0x0017:
        if (r0 != 0) goto L_0x001d;
    L_0x0019:
        r0 = java.util.Locale.getDefault();	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
    L_0x001d:
        r1 = r0;
        goto L_0x0003;
    L_0x001f:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        if (r2 == 0) goto L_0x003c;
    L_0x0024:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        r3.<init>();	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        r4 = "fail to read user config locale, e is ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
        android.util.Log.e(r2, r0);	 Catch:{ Exception -> 0x003e, Throwable -> 0x005f }
    L_0x003c:
        r0 = r1;
        goto L_0x0017;
    L_0x003e:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x005b;
    L_0x0043:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get locale e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x005b:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
    L_0x005f:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x007c;
    L_0x0064:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get locale e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x007c:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getLocale(android.content.Context):java.util.Locale");
    }

    public static String getMac(Context context) {
        if (context == null) {
            return null;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
            }
            return "";
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get mac e is " + e);
            }
            b.a(context, e);
            return null;
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get mac e is " + e2);
            }
            b.a(context, e2);
            return null;
        }
    }

    public static String getOperator(Context context) {
        if (context == null) {
            return "Unknown";
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get get operator e is " + e);
            }
            b.a(context, e);
            return "Unknown";
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get get operator e is " + e2);
            }
            b.a(context, e2);
            return "Unknown";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSName(android.content.Context r5) {
        /*
        r1 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r1;
    L_0x0004:
        r2 = getBuildProp();	 Catch:{ Exception -> 0x0034, Throwable -> 0x0055 }
        r0 = "ro.miui.ui.version.name";
        r0 = r2.getProperty(r0);	 Catch:{ Exception -> 0x002e, Throwable -> 0x0055 }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002e, Throwable -> 0x0055 }
        if (r3 == 0) goto L_0x002b;
    L_0x0014:
        r3 = a();	 Catch:{ Exception -> 0x002e, Throwable -> 0x0055 }
        if (r3 == 0) goto L_0x001e;
    L_0x001a:
        r0 = "Flyme";
    L_0x001c:
        r1 = r0;
        goto L_0x0003;
    L_0x001e:
        r2 = a(r2);	 Catch:{ Exception -> 0x002e, Throwable -> 0x0055 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Exception -> 0x002e, Throwable -> 0x0055 }
        if (r2 != 0) goto L_0x001c;
    L_0x0028:
        r0 = "YunOS";
        goto L_0x001c;
    L_0x002b:
        r0 = "MIUI";
        goto L_0x001c;
    L_0x002e:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r5, r0);	 Catch:{ Exception -> 0x0034, Throwable -> 0x0055 }
        r0 = r1;
        goto L_0x001c;
    L_0x0034:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0051;
    L_0x0039:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get sub os name e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x0051:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
    L_0x0055:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0072;
    L_0x005a:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get sub os name e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x0072:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSName(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getSubOSVersion(android.content.Context r5) {
        /*
        r1 = 0;
        if (r5 != 0) goto L_0x0004;
    L_0x0003:
        return r1;
    L_0x0004:
        r2 = getBuildProp();	 Catch:{ Exception -> 0x002b, Throwable -> 0x004c }
        r0 = "ro.miui.ui.version.name";
        r0 = r2.getProperty(r0);	 Catch:{ Exception -> 0x0025, Throwable -> 0x004c }
        r3 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x0025, Throwable -> 0x004c }
        if (r3 == 0) goto L_0x001e;
    L_0x0014:
        r3 = a();	 Catch:{ Exception -> 0x0025, Throwable -> 0x004c }
        if (r3 == 0) goto L_0x0020;
    L_0x001a:
        r0 = b(r2);	 Catch:{ Exception -> 0x006d, Throwable -> 0x004c }
    L_0x001e:
        r1 = r0;
        goto L_0x0003;
    L_0x0020:
        r0 = a(r2);	 Catch:{ Exception -> 0x006f, Throwable -> 0x004c }
        goto L_0x001e;
    L_0x0025:
        r0 = move-exception;
        com.umeng.commonsdk.proguard.b.a(r5, r0);	 Catch:{ Exception -> 0x002b, Throwable -> 0x004c }
        r0 = r1;
        goto L_0x001e;
    L_0x002b:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0048;
    L_0x0030:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get sub os version e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x0048:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
    L_0x004c:
        r0 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0069;
    L_0x0051:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get sub os version e is ";
        r3 = r3.append(r4);
        r3 = r3.append(r0);
        r3 = r3.toString();
        android.util.Log.e(r2, r3);
    L_0x0069:
        com.umeng.commonsdk.proguard.b.a(r5, r0);
        goto L_0x0003;
    L_0x006d:
        r1 = move-exception;
        goto L_0x001e;
    L_0x006f:
        r1 = move-exception;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getSubOSVersion(android.content.Context):java.lang.String");
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
        } catch (Exception e) {
        }
        return null;
    }

    public static Properties getBuildProp() {
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
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                    }
                }
                return properties;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                fileInputStream2 = fileInputStream;
                th = th3;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
        } catch (IOException e5) {
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return properties;
        } catch (Throwable th4) {
            th = th4;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
        return properties;
    }

    private static boolean a() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getDeviceType(Context context) {
        try {
            String str = "Phone";
            if (context == null) {
                return str;
            }
            if (((context.getResources().getConfiguration().screenLayout & 15) >= 3 ? 1 : null) != null) {
                return "Tablet";
            }
            return "Phone";
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get device type e is " + e);
            }
            b.a(context, e);
            return null;
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get device type e is " + e2);
            }
            b.a(context, e2);
            return null;
        }
    }

    public static String getAppVersionCode(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersinoCode(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (Exception e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version code e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e is " + e);
            }
            return "";
        } catch (Throwable th) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e is " + th);
            }
            return "";
        }
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null || str == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Throwable e) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e is " + e);
            }
            b.a(context, e);
            return "";
        } catch (Throwable e2) {
            if (AnalyticsConstants.UM_DEBUG) {
                Log.e("UMUtils", "get app version name e is " + e2);
            }
            b.a(context, e2);
            return "";
        }
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
            } catch (Throwable e) {
                b.a(context, e);
                return false;
            }
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String getAppMD5Signature(Context context) {
        Object obj;
        String str = null;
        if (context != null) {
            try {
                PackageInfo packageInfo;
                CertificateFactory instance;
                X509Certificate x509Certificate;
                try {
                    packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
                } catch (NameNotFoundException e) {
                    obj = str;
                }
                InputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                try {
                    instance = CertificateFactory.getInstance("X509");
                } catch (CertificateException e2) {
                    obj = str;
                }
                try {
                    x509Certificate = (X509Certificate) instance.generateCertificate(byteArrayInputStream);
                } catch (CertificateException e3) {
                    obj = str;
                }
                try {
                    str = a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
                } catch (NoSuchAlgorithmException e4) {
                } catch (CertificateEncodingException e5) {
                }
            } catch (Throwable e6) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get app MD5 signature e is " + e6);
                }
                b.a(context, e6);
            } catch (Throwable e62) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get app MD5 signature e is " + e62);
                }
                b.a(context, e62);
            }
        }
        return str;
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
            stringBuilder.append(toHexString.toUpperCase());
            if (i < bArr.length - 1) {
                stringBuilder.append(':');
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isDebug(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if ((context.getApplicationInfo().flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            b.a(context, e);
            return false;
        }
    }

    public static String getAppName(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
            } catch (Throwable e) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get app name e is " + e);
                }
                b.a(context, e);
            } catch (Throwable e2) {
                if (AnalyticsConstants.UM_DEBUG) {
                    Log.e("UMUtils", "get app name e is " + e2);
                }
                b.a(context, e2);
            }
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String MD5(java.lang.String r8) {
        /*
        r1 = 0;
        r0 = 0;
        if (r8 != 0) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r2 = r8.getBytes();	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3 = "MD5";
        r3 = java.security.MessageDigest.getInstance(r3);	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3.reset();	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3.update(r2);	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r2 = r3.digest();	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3.<init>();	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
    L_0x001e:
        r4 = r2.length;	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        if (r1 >= r4) goto L_0x0039;
    L_0x0021:
        r4 = "%02X";
        r5 = 1;
        r5 = new java.lang.Object[r5];	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r6 = 0;
        r7 = r2[r1];	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r7 = java.lang.Byte.valueOf(r7);	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r5[r6] = r7;	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r4 = java.lang.String.format(r4, r5);	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r3.append(r4);	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        r1 = r1 + 1;
        goto L_0x001e;
    L_0x0039:
        r0 = r3.toString();	 Catch:{ Exception -> 0x003e, Throwable -> 0x0066 }
        goto L_0x0004;
    L_0x003e:
        r1 = move-exception;
        r1 = "[^[a-z][A-Z][0-9][.][_]]";
        r2 = "";
        r0 = r8.replaceAll(r1, r2);	 Catch:{ Exception -> 0x0048, Throwable -> 0x0066 }
        goto L_0x0004;
    L_0x0048:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0004;
    L_0x004d:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "MD5 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0004;
    L_0x0066:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0004;
    L_0x006b:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "MD5 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.MD5(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileMD5(java.io.File r6) {
        /*
        r0 = 0;
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r2 = r6.isFile();	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        if (r2 != 0) goto L_0x000e;
    L_0x000b:
        r0 = "";
    L_0x000d:
        return r0;
    L_0x000e:
        r2 = "MD5";
        r2 = java.security.MessageDigest.getInstance(r2);	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        r3 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        r3.<init>(r6);	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
    L_0x0019:
        r4 = 0;
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r4 = r3.read(r1, r4, r5);	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        r5 = -1;
        if (r4 == r5) goto L_0x002a;
    L_0x0023:
        r5 = 0;
        r2.update(r1, r5, r4);	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        goto L_0x0019;
    L_0x0028:
        r1 = move-exception;
        goto L_0x000d;
    L_0x002a:
        r3.close();	 Catch:{ Exception -> 0x0028, Throwable -> 0x0062 }
        r1 = new java.math.BigInteger;	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r3 = 1;
        r2 = r2.digest();	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r1.<init>(r3, r2);	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r2 = "%1$032x";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r4 = 0;
        r3[r4] = r1;	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        r0 = java.lang.String.format(r2, r3);	 Catch:{ Exception -> 0x0044, Throwable -> 0x0062 }
        goto L_0x000d;
    L_0x0044:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x000d;
    L_0x0049:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get file MD5 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
    L_0x0062:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x000d;
    L_0x0067:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "get file MD5 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x000d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.getFileMD5(java.io.File):java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String encryptBySHA1(java.lang.String r5) {
        /*
        r0 = 0;
        r1 = r5.getBytes();	 Catch:{ Exception -> 0x0017, Throwable -> 0x0035 }
        r2 = "SHA1";
        r2 = java.security.MessageDigest.getInstance(r2);	 Catch:{ Exception -> 0x0053, Throwable -> 0x0035 }
        r2.update(r1);	 Catch:{ Exception -> 0x0053, Throwable -> 0x0035 }
        r1 = r2.digest();	 Catch:{ Exception -> 0x0053, Throwable -> 0x0035 }
        r0 = b(r1);	 Catch:{ Exception -> 0x0053, Throwable -> 0x0035 }
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0016;
    L_0x001c:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "encrypt by SHA1 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0016;
    L_0x0035:
        r1 = move-exception;
        r2 = com.umeng.commonsdk.statistics.AnalyticsConstants.UM_DEBUG;
        if (r2 == 0) goto L_0x0016;
    L_0x003a:
        r2 = "UMUtils";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "encrypt by SHA1 e is ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        android.util.Log.e(r2, r1);
        goto L_0x0016;
    L_0x0053:
        r1 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.utils.UMUtils.encryptBySHA1(java.lang.String):java.lang.String");
    }

    private static String b(byte[] bArr) {
        String str = "";
        int i = 0;
        while (i < bArr.length) {
            String toHexString = Integer.toHexString(bArr[i] & 255);
            if (toHexString.length() == 1) {
                str = str + "0";
            }
            i++;
            str = str + toHexString;
        }
        return str;
    }

    public static String getUMId(Context context) {
        String str = null;
        if (context != null) {
            try {
                str = UMEnvelopeBuild.imprintProperty(context.getApplicationContext(), g.f, null);
            } catch (Throwable e) {
                b.a(context, e);
            }
        }
        return str;
    }

    public static String getDeviceToken(Context context) {
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            String str;
            Class cls = Class.forName("com.umeng.message.MessageSharedPrefs");
            if (cls != null) {
                Method method = cls.getMethod("getInstance", new Class[]{Context.class});
                if (method != null) {
                    Object invoke = method.invoke(cls, new Object[]{applicationContext});
                    if (invoke != null) {
                        Method method2 = cls.getMethod("getDeviceToken", new Class[0]);
                        if (method2 != null) {
                            invoke = method2.invoke(invoke, new Object[0]);
                            if (invoke != null && (invoke instanceof String)) {
                                str = (String) invoke;
                                return str;
                            }
                        }
                    }
                }
            }
            str = null;
            return str;
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getAppkeyByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("UMENG_APPKEY");
                if (string != null) {
                    return string.trim();
                }
                MLog.e(AnalyticsConstants.LOG_TAG, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable th) {
            MLog.e(AnalyticsConstants.LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", th);
        }
        return null;
    }

    public static String getChannelByXML(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                Object obj = applicationInfo.metaData.get("UMENG_CHANNEL");
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (obj2 != null) {
                        return obj2.trim();
                    }
                    if (AnalyticsConstants.UM_DEBUG) {
                        MLog.i(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                    }
                }
            }
        } catch (Throwable th) {
            MLog.e(AnalyticsConstants.LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.", th);
        }
        return null;
    }
}
