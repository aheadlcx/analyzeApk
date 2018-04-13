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
import cn.v6.sixrooms.constants.CommonStrs;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.umeng.update.UpdateConfig;
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
    private String c = "";
    private String d = "";
    private String e;
    private String f;
    private String g;
    private String h;
    private int i = -1;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;

    @TargetApi(4)
    public boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public String getSnFrom(Context context) {
        return this.c + this.d;
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int length = str.length();
        do {
            length--;
            if (length < 0) {
                return true;
            }
        } while (str.charAt(length) == '0');
        return false;
    }

    public String getIMEI(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String deviceId;
        if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.READ_PHONE_STATE")) {
            this.d = "1";
            deviceId = getDeviceId(context);
            if (a(deviceId)) {
                deviceId = System.getString(context.getContentResolver(), "bd_setting_i");
                if (a(deviceId)) {
                    this.c = "2";
                    return "";
                }
                this.c = "1";
                return deviceId;
            }
            this.c = "0";
            return deviceId;
        }
        this.c = "1";
        deviceId = System.getString(context.getContentResolver(), "bd_setting_i");
        if (a(deviceId)) {
            this.c = "2";
            deviceId = "";
        }
        try {
            String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            for (String contains : strArr) {
                if (contains.contains("android.permission.READ_PHONE_STATE")) {
                    this.d = "2";
                    return deviceId;
                }
                this.d = "0";
            }
            return deviceId;
        } catch (Exception e) {
            e.printStackTrace();
            return deviceId;
        }
    }

    public String getSn(Context context) {
        try {
            if (TextUtils.isEmpty(this.e)) {
                String imei = getIMEI(context);
                if (TextUtils.isEmpty(imei)) {
                    imei = getMacAddress(context);
                }
                this.e = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(imei);
            }
            return this.e;
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
                Location lastKnownLocation = ((LocationManager) context.getSystemService(CommonStrs.TYPE_LOCATION)).getLastKnownLocation("gps");
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
            if (this.f != null || context == null) {
                return this.f;
            }
            IXAdCommonUtils commonUtils = XAdSDKFoundationFacade.getInstance().getCommonUtils();
            this.f = context.getSharedPreferences("__x_adsdk_agent_header__", 0).getString("guid", "");
            if (this.f == null || this.f.length() <= 0) {
                this.f = commonUtils.md5(getMacAddress(context) + "&" + getIMEI(context) + "&" + "&");
                if (this.f == null || this.f.length() <= 0) {
                    return "";
                }
                context.getSharedPreferences("__x_adsdk_agent_header__", 0).edit().putString("guid", this.f).commit();
            }
            return this.f;
        } catch (Exception e) {
            return "";
        }
    }

    public String getAndroidId(Context context) {
        try {
            if (TextUtils.isEmpty(this.g)) {
                this.g = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(Secure.getString(context.getContentResolver(), "android_id"));
            }
            return this.g;
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
            str = availableExternalMemorySize + "," + getAllExternalMemorySize();
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
            str = availableInternalMemorySize + "," + getAllInternalMemorySize();
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
        if (TextUtils.isEmpty(this.h)) {
            IXAdLogger adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
            try {
                String str = "android.permission.ACCESS_WIFI_STATE";
                WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
                if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                    this.h = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(wifiManager.getConnectionInfo().getMacAddress());
                } else {
                    adLogger.e(new Object[]{"", "Could not get mac address. no android.permission.ACCESS_WIFI_STATE"});
                }
            } catch (Exception e) {
                adLogger.e(new Object[]{"", "Could not get mac address." + e.toString()});
            }
        }
        return this.h;
    }

    @TargetApi(3)
    public String getIp(Context context) {
        CharSequence charSequence = "";
        if (((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            return "";
        }
        try {
            charSequence = Formatter.formatIpAddress(((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo().getIpAddress());
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
        if (this.i < 0) {
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
                            return this.i + "";
                        }
                        return this.i + "";
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
                    this.i = Integer.parseInt(bufferedReader.readLine().trim()) / 1000;
                    try {
                        fileReader.close();
                        bufferedReader.close();
                    } catch (IOException e5) {
                        e = e5;
                        adLogger = XAdSDKFoundationFacade.getInstance().getAdLogger();
                        adLogger.d(e);
                        return this.i + "";
                    }
                } catch (Exception e6) {
                    e = e6;
                    fileReader2 = fileReader;
                    XAdSDKFoundationFacade.getInstance().getAdLogger().d(e);
                    fileReader2.close();
                    bufferedReader.close();
                    return this.i + "";
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
                return this.i + "";
            } catch (Throwable th4) {
                e = th4;
                bufferedReader = null;
                fileReader = null;
                fileReader.close();
                bufferedReader.close();
                throw e;
            }
        }
        return this.i + "";
    }

    public String getNetworkOperatorName(Context context) {
        if (TextUtils.isEmpty(this.j)) {
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
                    this.j = commonUtils.getTextEncoder(stringBuilder.toString());
                }
            } catch (Exception e) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e(new Object[]{"Get operator failed", ""});
            }
        }
        return this.j;
    }

    public String getNetworkOperator(Context context) {
        try {
            if (TextUtils.isEmpty(this.k)) {
                this.k = XAdSDKFoundationFacade.getInstance().getCommonUtils().b(((TelephonyManager) context.getSystemService("phone")).getNetworkOperator());
            }
            return this.k;
        } catch (Exception e) {
            return this.k;
        }
    }

    public String getPhoneOSBuildVersionSdk() {
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(VERSION.SDK);
    }

    @SuppressLint({"DefaultLocale"})
    @TargetApi(3)
    public String getNetworkType(Context context) {
        Exception e;
        String str = "none";
        String str2;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                return str;
            }
            if (activeNetworkInfo.getType() == 1) {
                return IXAdSystemUtils.NT_WIFI;
            }
            str2 = "unknown";
            try {
                if (activeNetworkInfo.getSubtypeName() != null) {
                    return activeNetworkInfo.getSubtypeName().toLowerCase();
                }
                return str2;
            } catch (Exception e2) {
                e = e2;
                XAdSDKFoundationFacade.getInstance().getAdLogger().i(new Object[]{e});
                return str2;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            str2 = str;
            e = exception;
            XAdSDKFoundationFacade.getInstance().getAdLogger().i(new Object[]{e});
            return str2;
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
            return IXAdSystemUtils.NT_WIFI + str;
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
            String str = UpdateConfig.g;
            if (context.checkCallingOrSelfPermission(UpdateConfig.g) != 0) {
                XAdSDKFoundationFacade.getInstance().getAdLogger().e(new Object[]{"Utils", "no permission android.permission.ACCESS_NETWORK_STATE"});
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
        if (TextUtils.isEmpty(this.l) && context != null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("__x_adsdk_agent_header__", 0);
            Object string = sharedPreferences.getString(XAdSDKFoundationFacade.getInstance().getBase64().decodeStr("pyd-pifb"), "");
            if (TextUtils.isEmpty(string)) {
                try {
                    String str = (String) XAdSDKFoundationFacade.getInstance().getCommonUtils().a((TelephonyManager) context.getApplicationContext().getSystemService("phone"), XAdSDKFoundationFacade.getInstance().getBase64().decodeStr("uvNYwANvpyP-iyfb"), new Object[0]);
                    if (!TextUtils.isEmpty(str)) {
                        new Thread(new p(this, sharedPreferences, str)).start();
                        this.l = str;
                    }
                } catch (Throwable e) {
                    l.a().d(e);
                }
            } else {
                this.l = string;
            }
        }
        return XAdSDKFoundationFacade.getInstance().getCommonUtils().b(this.l);
    }

    public String getEncodedSN(Context context) {
        try {
            if (TextUtils.isEmpty(this.m)) {
                this.m = XAdSDKFoundationFacade.getInstance().getBase64().encode(getSn(context));
            }
            return this.m;
        } catch (Exception e) {
            return this.m;
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
                    String[] split = cellLocation.toString().replace("[", "").replace("]", "").split(",");
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
            Object a = ((d) commonUtils).a(IXAdSystemUtils.NT_WIFI);
            if (a != null) {
                return (List) a;
            }
        } catch (Throwable e) {
            l.a().e(e);
        }
        List<String[]> arrayList = new ArrayList();
        try {
            if (commonUtils.hasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
                if (wifiManager.isWifiEnabled()) {
                    List scanResults = wifiManager.getScanResults();
                    Collections.sort(scanResults, new q(this));
                    while (i < scanResults.size() && i < 5) {
                        String toLowerCase = ((ScanResult) scanResults.get(i)).BSSID.replace(":", "").toLowerCase(Locale.getDefault());
                        arrayList.add(new String[]{toLowerCase, Math.abs(r1.level) + ""});
                        i++;
                    }
                }
            }
        } catch (Throwable e2) {
            l.a().e(e2);
        }
        ((d) commonUtils).a(IXAdSystemUtils.NT_WIFI, (Object) arrayList);
        return arrayList;
    }

    public boolean canSupportSdcardStroage(Context context) {
        try {
            if (XAdSDKFoundationFacade.getInstance().getCommonUtils().hasPermission(context, UpdateConfig.f) || !isUseOldStoragePath()) {
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
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo();
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
                WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
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
            if (this.n == null) {
                int myPid = Process.myPid();
                ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null) {
                    List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                            if (runningAppProcessInfo.pid == myPid) {
                                this.n = runningAppProcessInfo.processName;
                            }
                        }
                    }
                }
            }
            return this.n;
        } catch (Exception e) {
            return this.n;
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
