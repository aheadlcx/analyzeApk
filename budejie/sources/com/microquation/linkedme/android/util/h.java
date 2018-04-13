package com.microquation.linkedme.android.util;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.microquation.linkedme.android.a.a;
import com.microquation.linkedme.android.f.b;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class h implements e {
    private Context a;
    private boolean b;
    private String c = "";
    private String d = "";
    private String e = "";

    public h(Context context) {
        this.a = context;
        this.b = true;
    }

    private a a(TelephonyManager telephonyManager, int i, int i2) throws Exception {
        if (VERSION.SDK_INT <= 25) {
            return null;
        }
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        if (!(allCellInfo == null || allCellInfo.size() == 0)) {
            for (CellInfo cellInfo : allCellInfo) {
                if (cellInfo.isRegistered()) {
                    a aVar = new a();
                    if (cellInfo instanceof CellInfoGsm) {
                        CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
                        if (cellIdentity != null) {
                            aVar.a(16);
                            CellSignalStrengthGsm cellSignalStrength = ((CellInfoGsm) cellInfo).getCellSignalStrength();
                            aVar.h(cellIdentity.getMcc());
                            aVar.f(cellIdentity.getMnc());
                            aVar.d(cellIdentity.getLac());
                            aVar.e(cellIdentity.getCid());
                            aVar.g(cellSignalStrength.getAsuLevel());
                        }
                    } else if (cellInfo instanceof CellInfoCdma) {
                        CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
                        if (cellIdentity2 != null) {
                            aVar.a(4);
                            CellSignalStrengthCdma cellSignalStrength2 = ((CellInfoCdma) cellInfo).getCellSignalStrength();
                            aVar.h(i);
                            aVar.f(i2);
                            aVar.d(cellIdentity2.getNetworkId());
                            int basestationId = cellIdentity2.getBasestationId();
                            if (basestationId != Integer.MAX_VALUE) {
                                basestationId /= 16;
                            }
                            aVar.e(cellIdentity2.getBasestationId());
                            aVar.g(cellSignalStrength2.getAsuLevel());
                            aVar.c(cellIdentity2.getSystemId());
                        }
                    } else if (cellInfo instanceof CellInfoLte) {
                        CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
                        if (cellIdentity3 != null) {
                            aVar.a(13);
                            CellSignalStrengthLte cellSignalStrength3 = ((CellInfoLte) cellInfo).getCellSignalStrength();
                            aVar.h(cellIdentity3.getMcc());
                            aVar.f(cellIdentity3.getMnc());
                            aVar.d(cellIdentity3.getTac());
                            aVar.e(cellIdentity3.getCi());
                            aVar.g(cellSignalStrength3.getAsuLevel());
                        }
                    } else if ((cellInfo instanceof CellInfoWcdma) && VERSION.SDK_INT >= 18) {
                        CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                        if (cellIdentity4 != null) {
                            aVar.a(17);
                            CellSignalStrengthWcdma cellSignalStrength4 = ((CellInfoWcdma) cellInfo).getCellSignalStrength();
                            aVar.h(cellIdentity4.getMcc());
                            aVar.f(cellIdentity4.getMnc());
                            aVar.d(cellIdentity4.getLac());
                            aVar.e(cellIdentity4.getCid());
                            aVar.g(cellSignalStrength4.getAsuLevel());
                        }
                    }
                    aVar.b(telephonyManager.getPhoneType());
                    return aVar;
                }
            }
        }
        return null;
    }

    private a a(TelephonyManager telephonyManager, a aVar) throws Exception {
        if (telephonyManager.getPhoneType() == 1) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            if (gsmCellLocation == null) {
                return null;
            }
            aVar.d(gsmCellLocation.getLac());
            aVar.e(gsmCellLocation.getCid());
            return aVar;
        } else if (telephonyManager.getPhoneType() != 2) {
            return aVar;
        } else {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();
            if (cdmaCellLocation == null) {
                return null;
            }
            int baseStationId = cdmaCellLocation.getBaseStationId();
            if (baseStationId != -1) {
                baseStationId /= 16;
            }
            aVar.d(cdmaCellLocation.getNetworkId());
            aVar.e(baseStationId);
            return aVar;
        }
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    private String c(String str) {
        return (VERSION.SDK_INT > 16 && str.startsWith("\"") && str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    public String A() {
        try {
            WifiManager wifiManager = (WifiManager) this.a.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            if (!wifiManager.isWifiEnabled() || !n()) {
                return "";
            }
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            String t = t();
            String a = a(connectionInfo.getIpAddress());
            String c = c(connectionInfo.getSSID());
            int networkId = connectionInfo.getNetworkId();
            return c + "," + connectionInfo.getBSSID() + "," + t + "," + a + "," + networkId + "," + connectionInfo.getLinkSpeed();
        } catch (Exception e) {
            return "";
        }
    }

    public String B() {
        try {
            WifiManager wifiManager = (WifiManager) this.a.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            return (wifiManager.isWifiEnabled() && n()) ? c(wifiManager.getConnectionInfo().getSSID()) : "";
        } catch (Exception e) {
            return "";
        }
    }

    public String C() {
        int i = 5;
        int i2 = 0;
        String str = "";
        try {
            WifiManager wifiManager = (WifiManager) this.a.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            if (!wifiManager.isWifiEnabled() || !n()) {
                return "";
            }
            List scanResults = wifiManager.getScanResults();
            if (scanResults.size() <= 5) {
                i = scanResults.size();
            }
            String str2 = str;
            while (i2 < i) {
                str = str2 + c(((ScanResult) scanResults.get(i2)).SSID) + ",";
                i2++;
                str2 = str;
            }
            return str2.length() > 0 ? str2.substring(0, str2.length() - 1) : str2;
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public String D() {
        try {
            if (!g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            String line1Number = ((TelephonyManager) this.a.getSystemService("phone")).getLine1Number();
            return line1Number == null ? "" : line1Number;
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
            return "";
        }
    }

    public String E() {
        b a = b.a(this.a);
        return (!TextUtils.isEmpty(a.C()) || TextUtils.isEmpty(q())) ? (!TextUtils.isEmpty(a.D()) || TextUtils.isEmpty(t())) ? "0" : "1" : "1";
    }

    @TargetApi(9)
    public int a(boolean z) {
        b a = b.a(this.a);
        String b = b();
        if ("lkme_no_value".equals(a.g())) {
            if (z) {
                a.b(b);
            }
            if (VERSION.SDK_INT >= 9) {
                try {
                    PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
                    return packageInfo.lastUpdateTime != packageInfo.firstInstallTime ? 2 : 0;
                } catch (NameNotFoundException e) {
                }
            }
            return 0;
        } else if (TextUtils.equals(a.g(), b)) {
            return 1;
        } else {
            if (!z) {
                return 2;
            }
            a.b(b);
            return 2;
        }
    }

    public String a(Context context) {
        try {
            if (!TextUtils.isEmpty(this.d)) {
                return this.d;
            }
            if (!g.a(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                return this.d;
            }
            new Thread(new Runnable(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        if (Environment.getExternalStorageState().equals("mounted")) {
                            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "tencent" + File.separator + "MicroMsg");
                            if (file.exists()) {
                                File[] listFiles = file.listFiles(new FileFilter(this) {
                                    final /* synthetic */ AnonymousClass3 a;

                                    {
                                        this.a = r1;
                                    }

                                    public boolean accept(File file) {
                                        return file.getName().length() == 32;
                                    }
                                });
                                long j = 0;
                                for (int i = 0; i < listFiles.length; i++) {
                                    String name = listFiles[i].getName();
                                    if (name.length() == 32) {
                                        long lastModified = listFiles[i].lastModified();
                                        if (lastModified > j) {
                                            this.a.d = name;
                                            j = lastModified;
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        if (b.a()) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return this.d;
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(9)
    public JSONArray a() {
        JSONArray jSONArray = new JSONArray();
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            return jSONArray;
        }
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
        if (installedApplications != null) {
            for (ApplicationInfo applicationInfo : installedApplications) {
                if ((applicationInfo.flags & 1) != 1) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        CharSequence loadLabel = applicationInfo.loadLabel(packageManager);
                        Object charSequence = loadLabel == null ? null : loadLabel.toString();
                        if (charSequence != null) {
                            jSONObject.put("name", charSequence);
                        }
                        String str = applicationInfo.packageName;
                        if (str != null) {
                            jSONObject.put(c.a.AppIdentifier.a(), str);
                        }
                        str = applicationInfo.publicSourceDir;
                        if (str != null) {
                            jSONObject.put("public_source_dir", str);
                        }
                        str = applicationInfo.sourceDir;
                        if (str != null) {
                            jSONObject.put("source_dir", str);
                        }
                        PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 4096);
                        if (packageInfo != null) {
                            if (packageInfo.versionCode >= 9) {
                                jSONObject.put("install_date", packageInfo.firstInstallTime);
                                jSONObject.put("last_update_date", packageInfo.lastUpdateTime);
                            }
                            jSONObject.put("version_code", packageInfo.versionCode);
                            if (packageInfo.versionName != null) {
                                jSONObject.put("version_name", packageInfo.versionName);
                            }
                        }
                        jSONObject.put(c.a.OS.a(), j());
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                    } catch (NameNotFoundException e2) {
                    }
                }
            }
        }
        return jSONArray;
    }

    public void a(final String str) {
        if (!TextUtils.isEmpty(str)) {
            final b a = b.a(this.a);
            new Thread(new Runnable(this) {
                final /* synthetic */ h c;

                public void run() {
                    try {
                        a.s(str);
                        b.a().a(str);
                        if (VERSION.SDK_INT < 23 && this.c.a.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                            System.putString(this.c.a.getContentResolver(), "lm_device_id", str);
                        }
                    } catch (Exception e) {
                        if (b.a()) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public String b() {
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
            return packageInfo.versionName != null ? packageInfo.versionName : "lkme_no_value";
        } catch (NameNotFoundException e) {
            return "lkme_no_value";
        }
    }

    public String b(Context context) {
        try {
            if (!TextUtils.isEmpty(this.e)) {
                return this.e;
            }
            if (!g.a(context, "android.permission.READ_EXTERNAL_STORAGE")) {
                return this.e;
            }
            new Thread(new Runnable(this) {
                final /* synthetic */ h a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        if (Environment.getExternalStorageState().equals("mounted")) {
                            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "tencent" + File.separator + "MobileQQ" + File.separator + "WebViewCheck");
                            if (file.exists()) {
                                int i;
                                File[] listFiles = file.listFiles(new FileFilter(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public boolean accept(File file) {
                                        return file.getName().endsWith(".json");
                                    }
                                });
                                for (int i2 = 0; i2 < listFiles.length - 1; i2++) {
                                    for (i = 0; i < (listFiles.length - 1) - i2; i++) {
                                        if (listFiles[i].lastModified() < listFiles[i + 1].lastModified()) {
                                            File file2 = listFiles[i];
                                            listFiles[i] = listFiles[i + 1];
                                            listFiles[i + 1] = file2;
                                        }
                                    }
                                }
                                String str = "";
                                for (File file3 : listFiles) {
                                    String name = file3.getName();
                                    if (name.endsWith(".json")) {
                                        str = str + name.replace("config.json", "") + ",";
                                    }
                                }
                                if (str.length() > 0) {
                                    str = str.substring(0, str.length() - 1);
                                }
                                this.a.e = str;
                            }
                        }
                    } catch (Exception e) {
                        if (b.a()) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            return this.e;
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
        }
    }

    public void b(final String str) {
        if (!TextUtils.isEmpty(str)) {
            new Thread(new Runnable(this) {
                final /* synthetic */ h b;

                public void run() {
                    try {
                        Object c = b.a().c();
                        if (!TextUtils.equals(c, str)) {
                            b.a().a(c, str);
                            if (VERSION.SDK_INT < 23 && this.b.a.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                                System.putString(this.b.a.getContentResolver(), "lm_device_info", str);
                            }
                        }
                    } catch (Exception e) {
                        if (b.a()) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public int c() {
        try {
            PackageInfo packageInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0);
            return packageInfo != null ? packageInfo.versionCode : 0;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }

    public String d() {
        try {
            if (!g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.READ_PHONE_STATE")) {
                return "lkme_no_value";
            }
            TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
            if (telephonyManager != null) {
                String networkOperatorName = telephonyManager.getNetworkOperatorName();
                if (networkOperatorName != null) {
                    return networkOperatorName;
                }
            }
            return "lkme_no_value";
        } catch (Exception e) {
        }
    }

    public boolean e() {
        try {
            if (g.a(this.a, "android.permission.BLUETOOTH")) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    return defaultAdapter.isEnabled();
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    @TargetApi(9)
    public boolean f() {
        try {
            return this.a.getPackageManager().hasSystemFeature("android.hardware.nfc");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean g() {
        try {
            return this.a.getPackageManager().hasSystemFeature("android.hardware.telephony");
        } catch (Exception e) {
            return false;
        }
    }

    public String h() {
        return Build.MANUFACTURER;
    }

    public String i() {
        return Build.MODEL;
    }

    public String j() {
        return "Android";
    }

    public int k() {
        return VERSION.SDK_INT;
    }

    public String l() {
        return VERSION.RELEASE;
    }

    public DisplayMetrics m() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public boolean n() {
        try {
            if (this.a.checkCallingOrSelfPermission(UpdateConfig.g) == 0) {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                if (VERSION.SDK_INT >= 21) {
                    for (Network networkInfo : connectivityManager.getAllNetworks()) {
                        if (connectivityManager.getNetworkInfo(networkInfo).getType() == 1) {
                            return true;
                        }
                    }
                } else {
                    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
                    boolean z = networkInfo2 != null && networkInfo2.isConnected();
                    return z;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public String o() {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.a});
            return (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public boolean p() {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.a});
            return ((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    public String q() {
        try {
            if (!g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.READ_PHONE_STATE")) {
                return "";
            }
            String deviceId = ((TelephonyManager) this.a.getSystemService("phone")).getDeviceId();
            return deviceId == null ? "" : deviceId;
        } catch (Exception e) {
            return "";
        }
    }

    public String r() {
        try {
            return System.getString(this.a.getContentResolver(), "android_id");
        } catch (Exception e) {
            return "";
        }
    }

    public String s() {
        try {
            return Build.SERIAL;
        } catch (Exception e) {
            return "";
        }
    }

    public String t() {
        try {
            if (!TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            String str = "";
            String str2 = "";
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            while (str != null) {
                str = lineNumberReader.readLine();
                if (str != null) {
                    this.c = str.trim();
                    break;
                }
            }
            if (!TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            LineNumberReader lineNumberReader2 = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/eth0/address").getInputStream()));
            while (str2 != null) {
                str2 = lineNumberReader2.readLine();
                if (str2 != null) {
                    this.c = str2.trim();
                    break;
                }
            }
            if (!TextUtils.isEmpty(this.c)) {
                return this.c;
            }
            return "";
        } catch (Exception e) {
        }
    }

    public String u() {
        try {
            return Build.FINGERPRINT;
        } catch (Exception e) {
            return "";
        }
    }

    public String v() {
        try {
            if (g.a(this.a, "android.permission.BLUETOOTH")) {
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter != null) {
                    return defaultAdapter.getName();
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    public String w() {
        try {
            WifiManager wifiManager = (WifiManager) this.a.getApplicationContext().getSystemService(IXAdSystemUtils.NT_WIFI);
            if (wifiManager.isWifiEnabled()) {
                int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
                return ipAddress != 0 ? a(ipAddress) : "";
            } else {
                Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress()) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
                return "";
            }
        } catch (Exception e) {
        }
    }

    public String x() {
        Object A = b.a(this.a).A();
        if (!TextUtils.isEmpty(A) && !"lkme_no_value".equals(A)) {
            return A;
        }
        A = b.a().b();
        if (!TextUtils.isEmpty(A) && !"lkme_no_value".equals(A)) {
            return A;
        }
        try {
            if (VERSION.SDK_INT < 23 && this.a.checkCallingOrSelfPermission("android.permission.WRITE_SETTINGS") == 0) {
                A = System.getString(this.a.getContentResolver(), "lm_device_id");
            }
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
        }
        return (TextUtils.isEmpty(A) || "lkme_no_value".equals(A)) ? "" : A;
    }

    public String y() {
        try {
            ApplicationInfo applicationInfo = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 0).applicationInfo;
            return applicationInfo != null ? (String) this.a.getPackageManager().getApplicationLabel(applicationInfo) : "";
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    public String z() {
        try {
            if (!g.a(com.microquation.linkedme.android.a.a().i(), "android.permission.ACCESS_COARSE_LOCATION")) {
                return "";
            }
            a aVar = new a();
            TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            if (telephonyManager.getSimState() != 5) {
                return "";
            }
            String networkOperator = telephonyManager.getNetworkOperator();
            if (networkOperator == null || networkOperator.length() < 5) {
                return "";
            }
            a a;
            int intValue = Integer.valueOf(networkOperator.substring(0, 3)).intValue();
            int intValue2 = Integer.valueOf(networkOperator.substring(3)).intValue();
            aVar.h(intValue);
            aVar.f(intValue2);
            if (VERSION.SDK_INT > 25) {
                a = a(telephonyManager, intValue, intValue2);
                if (a == null) {
                    a = a(telephonyManager, aVar);
                }
            } else {
                a = a(telephonyManager, aVar);
            }
            if (a == null) {
                return "";
            }
            a.b(telephonyManager.getPhoneType());
            a.a(telephonyManager.getNetworkType());
            b.a("cellInfoBase == " + a.toString());
            return a.toString();
        } catch (Exception e) {
            if (b.a()) {
                e.printStackTrace();
            }
            return "";
        }
    }
}
