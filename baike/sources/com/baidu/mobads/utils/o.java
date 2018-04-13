package com.baidu.mobads.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobstat.Config;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;

public class o implements IXAdSystemUtils {
    private static String b;
    public JSONArray a = new JSONArray();
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h = -1;
    private String i;
    private String j;
    private String k;
    private String l;

    @TargetApi(4)
    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public String getIMEI(Context context) {
        if (TextUtils.isEmpty(this.c) && context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("__x_adsdk_agent_header__", 0);
            Object string = sharedPreferences.getString(XAdSDKFoundationFacade.getInstance().getBase64().decodeStr("pyd-pifb"), "");
            if (TextUtils.isEmpty(string)) {
                try {
                    String str = (String) XAdSDKFoundationFacade.getInstance().getCommonUtils().a((TelephonyManager) context.getApplicationContext().getSystemService("phone"), XAdSDKFoundationFacade.getInstance().getBase64().decodeStr("uvNYwANvpyP-iyfb"), new Object[0]);
                    if (!TextUtils.isEmpty(str)) {
                        new Thread(new p(this, sharedPreferences, str)).start();
                        this.c = str;
                    }
                } catch (Throwable e) {
                    l.a().d(e);
                }
            } else {
                this.c = string;
            }
        }
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(this.c);
    }

    public String getSn(Context context) {
        try {
            if (TextUtils.isEmpty(this.d)) {
                String imei = getIMEI(context);
                if (TextUtils.isEmpty(imei)) {
                    imei = getMacAddress(context);
                }
                this.d = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(imei);
            }
            return this.d;
        } catch (Exception e) {
            return "";
        }
    }

    public String getCUID(Context context) {
        try {
            if (TextUtils.isEmpty(b)) {
                String string = System.getString(context.getContentResolver(), "com.baidu.deviceid");
                if (!(string == null || string.equals(""))) {
                    String string2 = System.getString(context.getContentResolver(), "bd_setting_i");
                    if (TextUtils.isEmpty(string2)) {
                        string2 = "0";
                    }
                    b = string + "|" + new StringBuffer(string2).reverse().toString();
                }
            }
            return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(b);
        } catch (Exception e) {
            return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(b);
        }
    }

    public double[] getGPS(Context context) {
        Object a;
        try {
            a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a("SYSGPS");
            if (a != null) {
                return (double[]) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.ACCESS_FINE_LOCATION")) {
            try {
                Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("gps");
                if (lastKnownLocation != null) {
                    a = new double[3];
                    try {
                        a[0] = (double) lastKnownLocation.getTime();
                        a[1] = lastKnownLocation.getLongitude();
                        a[2] = lastKnownLocation.getLatitude();
                    } catch (Exception e2) {
                    }
                    XAdSDKFoundationFacade.getInstance().getCommonUtils().a("SYSGPS", a);
                    return a;
                }
            } catch (Exception e3) {
                a = null;
            }
        }
        a = null;
        XAdSDKFoundationFacade.getInstance().getCommonUtils().a("SYSGPS", a);
        return a;
    }

    public String getGUID(Context context) {
        try {
            if (this.e != null || context == null) {
                return this.e;
            }
            IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            this.e = context.getSharedPreferences("__x_adsdk_agent_header__", 0).getString("guid", "");
            if (this.e == null || this.e.length() <= 0) {
                this.e = commonUtils.md5(getMacAddress(context) + a.b + getIMEI(context) + a.b + a.b);
                if (this.e == null || this.e.length() <= 0) {
                    return "";
                }
                context.getSharedPreferences("__x_adsdk_agent_header__", 0).edit().putString("guid", this.e).commit();
            }
            return this.e;
        } catch (Exception e) {
            return "";
        }
    }

    public String getAndroidId(Context context) {
        try {
            if (TextUtils.isEmpty(this.f)) {
                this.f = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(Secure.getString(context.getContentResolver(), "android_id"));
            }
            return this.f;
        } catch (Exception e) {
            return "";
        }
    }

    public String getAppSDC() {
        try {
            Object a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a("sysSdc");
            if (a != null) {
                return (String) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return "0,0";
        }
        String str = "";
        try {
            long availableExternalMemorySize = getAvailableExternalMemorySize();
            str = availableExternalMemorySize + Constants.ACCEPT_TIME_SEPARATOR_SP + getAllExternalMemorySize();
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a("sysSdc", (Object) str);
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    public String getMem() {
        try {
            Object a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a("sysMem");
            if (a != null) {
                return (String) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        String str = "";
        try {
            long availableInternalMemorySize = getAvailableInternalMemorySize();
            str = availableInternalMemorySize + Constants.ACCEPT_TIME_SEPARATOR_SP + getAllInternalMemorySize();
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a("sysMem", (Object) str);
            return str;
        } catch (Exception e2) {
            return str;
        }
    }

    public long getAllExternalMemorySize() {
        long j = -1;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                j = a(Environment.getExternalStorageDirectory());
            }
        } catch (Exception e) {
        }
        return j;
    }

    public long getAllInternalMemorySize() {
        try {
            return a(Environment.getDataDirectory());
        } catch (Exception e) {
            return -1;
        }
    }

    public long getAvailableExternalMemorySize() {
        long j = -1;
        try {
            if (Environment.getExternalStorageState().equals("mounted")) {
                j = b(Environment.getExternalStorageDirectory());
            }
        } catch (Exception e) {
        }
        return j;
    }

    public long getAvailableInternalMemorySize() {
        try {
            return b(Environment.getDataDirectory());
        } catch (Exception e) {
            return -1;
        }
    }

    @TargetApi(18)
    private long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return (long) (((statFs.getBlockSize() * statFs.getBlockCount()) / 1024) / 1024);
        } catch (Exception e) {
            return -1;
        }
    }

    @TargetApi(18)
    private long b(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return (long) (((statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1024) / 1024);
        } catch (Exception e) {
            return -1;
        }
    }

    public String getMacAddress(Context context) {
        if (TextUtils.isEmpty(this.g)) {
            IXAdLogger adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
            try {
                String str = "android.permission.ACCESS_WIFI_STATE";
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                    this.g = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(wifiManager.getConnectionInfo().getMacAddress());
                } else {
                    adLogger.e("", "Could not get mac address. no android.permission.ACCESS_WIFI_STATE");
                }
            } catch (Exception e) {
                adLogger.e("", "Could not get mac address." + e.toString());
            }
        }
        return this.g;
    }

    @TargetApi(3)
    public String getIp(Context context) {
        CharSequence charSequence = "";
        if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            return "";
        }
        try {
            charSequence = Formatter.formatIpAddress(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getIpAddress());
            if (!TextUtils.isEmpty(charSequence)) {
                return "0.0.0.0".equals(charSequence) ? "" : charSequence;
            } else {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress() && (inetAddress instanceof Inet4Address)) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
                return charSequence;
            }
        } catch (SocketException e) {
        } catch (Exception e2) {
        }
    }

    public String getMaxCpuFreq() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        Throwable e;
        IXAdLogger adLogger;
        FileReader fileReader2 = null;
        if (this.h < 0) {
            String str = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
            try {
                fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                try {
                    bufferedReader = new BufferedReader(fileReader);
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = null;
                    fileReader2 = fileReader;
                    try {
                        XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
                        try {
                            fileReader2.close();
                            bufferedReader.close();
                        } catch (IOException e3) {
                            e = e3;
                            adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                            adLogger.d(e);
                            return this.h + "";
                        }
                        return this.h + "";
                    } catch (Throwable th) {
                        e = th;
                        fileReader = fileReader2;
                        try {
                            fileReader.close();
                            bufferedReader.close();
                        } catch (Throwable e4) {
                            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e4);
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    bufferedReader = null;
                    fileReader.close();
                    bufferedReader.close();
                    throw e;
                }
                try {
                    this.h = Integer.parseInt(bufferedReader.readLine().trim()) / 1000;
                    try {
                        fileReader.close();
                        bufferedReader.close();
                    } catch (IOException e5) {
                        e = e5;
                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                        adLogger.d(e);
                        return this.h + "";
                    }
                } catch (Exception e6) {
                    e = e6;
                    fileReader2 = fileReader;
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
                    fileReader2.close();
                    bufferedReader.close();
                    return this.h + "";
                } catch (Throwable th3) {
                    e = th3;
                    fileReader.close();
                    bufferedReader.close();
                    throw e;
                }
            } catch (Exception e7) {
                e = e7;
                bufferedReader = null;
                XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
                fileReader2.close();
                bufferedReader.close();
                return this.h + "";
            } catch (Throwable th4) {
                e = th4;
                bufferedReader = null;
                fileReader = null;
                fileReader.close();
                bufferedReader.close();
                throw e;
            }
        }
        return this.h + "";
    }

    public String getNetworkOperatorName(Context context) {
        if (TextUtils.isEmpty(this.i)) {
            try {
                IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                Object simOperatorName = telephonyManager.getSimOperatorName();
                StringBuilder stringBuilder = new StringBuilder();
                if (TextUtils.isEmpty(simOperatorName)) {
                    simOperatorName = telephonyManager.getNetworkOperatorName();
                    if (TextUtils.isEmpty(simOperatorName)) {
                        return "";
                    }
                    stringBuilder.append(simOperatorName);
                } else {
                    stringBuilder.append(simOperatorName);
                }
                stringBuilder.append("_");
                Object simOperator = telephonyManager.getSimOperator();
                if (!TextUtils.isEmpty(simOperator)) {
                    stringBuilder.append(simOperator);
                }
                if (stringBuilder.length() > 1) {
                    this.i = commonUtils.getTextEncoder(stringBuilder.toString());
                }
            } catch (Exception e) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e("Get operator failed", "");
            }
        }
        return this.i;
    }

    public String getNetworkOperator(Context context) {
        try {
            if (TextUtils.isEmpty(this.j)) {
                this.j = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(((TelephonyManager) context.getSystemService("phone")).getNetworkOperator());
            }
            return this.j;
        } catch (Exception e) {
            return this.j;
        }
    }

    public String getPhoneOSBuildVersionSdk() {
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(VERSION.SDK);
    }

    @SuppressLint({"DefaultLocale"})
    @TargetApi(3)
    public String getNetworkType(Context context) {
        String str;
        Exception e;
        String str2 = IXAdSystemUtils.NT_NONE;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return str2;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            str = "unknown";
            try {
                if (activeNetworkInfo.getSubtypeName() != null) {
                    return activeNetworkInfo.getSubtypeName().toLowerCase();
                }
                return str;
            } catch (Exception e2) {
                e = e2;
                XAdSDKFoundationFacade.getInstance().getAdLogger().i(e);
                return str;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str = str2;
            e = exception;
            XAdSDKFoundationFacade.getInstance().getAdLogger().i(e);
            return str;
        }
    }

    public String getNetType(Context context) {
        String str = "";
        try {
            str = "_" + ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isAvailable()) {
                return networkInfo.getExtraInfo() + str;
            }
            if (networkInfo2 == null || !networkInfo2.isAvailable()) {
                return str;
            }
            return "wifi" + str;
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = str;
            l.a().e(th);
            return str2;
        }
    }

    public int getNetworkCatagory(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return 100;
                }
                if (activeNetworkInfo.getType() == 0) {
                    String subtypeName = activeNetworkInfo.getSubtypeName();
                    switch (activeNetworkInfo.getSubtype()) {
                        case 0:
                            return 1;
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                            return 3;
                        default:
                            if (subtypeName == null || (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000"))) {
                                return 1;
                            }
                            return 3;
                    }
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public Boolean isWifiConnected(Context context) {
        return a(context, 1);
    }

    public Boolean is3GConnected(Context context) {
        return a(context, 0);
    }

    private Boolean a(Context context, int i) {
        try {
            String str = "android.permission.ACCESS_NETWORK_STATE";
            if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e("Utils", "no permission android.permission.ACCESS_NETWORK_STATE");
                return Boolean.valueOf(false);
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.getType() == i && activeNetworkInfo.isConnected();
            return Boolean.valueOf(z);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    public String getDeviceId(Context context) {
        return getIMEI(context);
    }

    public String getEncodedSN(Context context) {
        try {
            if (TextUtils.isEmpty(this.k)) {
                this.k = XAdSDKFoundationFacade.getInstance().getBase64().encode(getSn(context));
            }
            return this.k;
        } catch (Exception e) {
            return this.k;
        }
    }

    public String getPhoneOSBrand() {
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(Build.BRAND);
    }

    public List<String[]> getCell(Context context) {
        try {
            Object a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a("cell");
            if (a != null) {
                return (List) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        List<String[]> arrayList = new ArrayList();
        try {
            CellLocation cellLocation = ((TelephonyManager) context.getSystemService("phone")).getCellLocation();
            if (cellLocation != null) {
                Object obj = new String[3];
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    obj[0] = gsmCellLocation.getCid() + "";
                    obj[1] = gsmCellLocation.getLac() + "";
                    obj[2] = "0";
                } else {
                    String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    obj[0] = split[0];
                    obj[1] = split[3];
                    obj[2] = split[4];
                }
                arrayList.add(obj);
            }
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a("cell", (Object) arrayList);
        } catch (Exception e2) {
        }
        return arrayList;
    }

    public List<String[]> getWIFI(Context context) {
        int i = 0;
        IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
        try {
            Object a = ((d) commonUtils).a("wifi");
            if (a != null) {
                return (List) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        List<String[]> arrayList = new ArrayList();
        try {
            if (commonUtils.hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager.isWifiEnabled()) {
                    List scanResults = wifiManager.getScanResults();
                    Collections.sort(scanResults, new q(this));
                    while (i < scanResults.size() && i < 5) {
                        String toLowerCase = ((ScanResult) scanResults.get(i)).BSSID.replace(Config.TRACE_TODAY_VISIT_SPLIT, "").toLowerCase(Locale.getDefault());
                        arrayList.add(new String[]{toLowerCase, Math.abs(r1.level) + ""});
                        i++;
                    }
                }
            }
        } catch (Throwable e2) {
            l.a().e(e2);
        }
        ((d) commonUtils).a("wifi", (Object) arrayList);
        return arrayList;
    }

    public boolean canSupportSdcardStroage(Context context) {
        try {
            if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") || !isUseOldStoragePath()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUseOldStoragePath() {
        return VERSION.SDK_INT < 23;
    }

    public String getWifiConnected(Context context) {
        String str = "";
        try {
            if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
                String ssid = connectionInfo.getSSID();
                if (ssid == null) {
                    ssid = "";
                } else if (ssid.length() > 2 && ssid.startsWith("\"") && ssid.endsWith("\"")) {
                    ssid = ssid.substring(1, ssid.length() - 1);
                }
                return connectionInfo.getBSSID() + "|" + XAdSDKFoundationFacade.getInstance().getBase64().encode(ssid);
            }
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
        }
        return str;
    }

    public JSONArray getWifiScans(Context context) {
        try {
            Object a = XAdSDKFoundationFacade.getInstance().getCommonUtils().a("wifiScans");
            if (a != null) {
                return (JSONArray) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        JSONArray jSONArray = new JSONArray();
        try {
            if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager.isWifiEnabled()) {
                    List scanResults = wifiManager.getScanResults();
                    Collections.sort(scanResults, new r(this));
                    int i = 0;
                    while (i < scanResults.size() && i < 50) {
                        ScanResult scanResult = (ScanResult) scanResults.get(i);
                        jSONArray.put(scanResult.BSSID + "|" + XAdSDKFoundationFacade.getInstance().getBase64().encode(scanResult.SSID));
                        i++;
                    }
                }
            }
        } catch (Throwable e2) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d(e2);
        }
        XAdSDKFoundationFacade.getInstance().getCommonUtils().a("wifiScans", (Object) jSONArray);
        return jSONArray;
    }

    public JSONArray getBackgroundBrowsers(Context context) {
        IXAdLogger adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
        String[] supportedBrowsers = XAdSDKFoundationFacade.getInstance().getAdConstants().getSupportedBrowsers();
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (!(packageManager.getLaunchIntentForPackage(runningAppProcessInfo.processName) == null || packageManager.getApplicationInfo(runningAppProcessInfo.processName, 128) == null)) {
                    for (Object equals : supportedBrowsers) {
                        if (runningAppProcessInfo.processName.equals(equals)) {
                            this.a.put(runningAppProcessInfo.processName);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            adLogger.d(e);
        }
        adLogger.d("bgBrowsers:" + this.a);
        return this.a;
    }

    public HttpURLConnection getHttpConnection(Context context, String str, int i, int i2) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i2);
                return httpURLConnection;
            } catch (Exception e) {
                return httpURLConnection;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean isCurrentNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        } catch (Throwable e) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().d("isCurrentNetworkAvailable", e);
            return false;
        }
    }

    public String getCurrentProcessName(Context context) {
        try {
            if (this.l == null) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.pid == myPid) {
                                this.l = runningAppProcessInfo.processName;
                            }
                        }
                    }
                }
            }
            return this.l;
        } catch (Exception e) {
            return this.l;
        }
    }

    public int getCurrentProcessId(Context context) {
        try {
            return Process.myPid();
        } catch (Exception e) {
            return 0;
        }
    }
}
