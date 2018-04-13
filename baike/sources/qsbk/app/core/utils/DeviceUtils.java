package qsbk.app.core.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.qiushibaike.statsdk.common.DeviceId;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceUtils {
    private static String a = null;
    private static String b;
    private static String c;

    public static String getAndroidId() {
        if (a != null) {
            return a;
        }
        a = "IMEI_" + Md5Utils.encryptMD5("\"DEVICEID\":\"" + ((TelephonyManager) AppUtils.getInstance().getAppContext().getSystemService("phone")).getDeviceId() + "\"-" + "\"ANDROID_ID\":\"" + Secure.getString(AppUtils.getInstance().getAppContext().getContentResolver(), "android_id") + "\"");
        return a;
    }

    public static String getAppVersion() {
        String str = "1.0";
        try {
            return AppUtils.getInstance().getAppContext().getPackageManager().getPackageInfo(AppUtils.getInstance().getAppContext().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getSystemVersion() {
        return VERSION.RELEASE;
    }

    public static int getSystemSDKInt() {
        return VERSION.SDK_INT;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceId() {
        return DeviceId.getDeviceID(AppUtils.getInstance().getAppContext());
    }

    public static int getAPPVersionCode() {
        try {
            return AppUtils.getInstance().getAppContext().getPackageManager().getPackageInfo(AppUtils.getInstance().getAppContext().getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isExternalStorageAvailable() {
        try {
            return "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
        } catch (Exception e) {
            return false;
        }
    }

    @TargetApi(18)
    public static long getAvailableExternalMemorySize() {
        if (!isExternalStorageAvailable()) {
            return -1;
        }
        try {
            long blockSize;
            long availableBlocks;
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (VERSION.SDK_INT < 18) {
                blockSize = (long) statFs.getBlockSize();
                availableBlocks = (long) statFs.getAvailableBlocks();
            } else {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            }
            return availableBlocks * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getAvailableInternalMemorySize() {
        try {
            long blockSize;
            long availableBlocks;
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (VERSION.SDK_INT < 18) {
                blockSize = (long) statFs.getBlockSize();
                availableBlocks = (long) statFs.getAvailableBlocks();
            } else {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            }
            return availableBlocks * blockSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getSDPath() {
        String str = "";
        if (isExternalStorageAvailable()) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return str;
    }

    public static boolean isMeizuMobile() {
        return "meizu".equalsIgnoreCase(Build.BRAND);
    }

    public static String getChannel() {
        return getChannel(AppUtils.getInstance().getAppContext());
    }

    public static String getChannel(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String str = "";
        File file = new File(context.getFilesDir() + "/channel");
        try {
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[256];
                if (fileInputStream.read(bArr) > 0) {
                    fileInputStream.close();
                    b = new String(bArr).trim();
                    return b;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Object trim = PreferenceUtils.instance().getString(PrefrenceKeys.CHANNEL_OF_FIRST_INSTALL, "").trim();
            if (!TextUtils.isEmpty(trim)) {
                b = trim;
                return b;
            }
        }
        str = a(context);
        try {
            if (TextUtils.isEmpty(str)) {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    str = applicationInfo.metaData.get("UMENG_CHANNEL").toString();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str)) {
            str = a(context, str.trim());
            b = str;
            try {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();
            } catch (Exception e22) {
                e22.printStackTrace();
                PreferenceUtils.instance().putString(PrefrenceKeys.CHANNEL_OF_FIRST_INSTALL, str);
            }
        }
        if (TextUtils.isEmpty(str)) {
            return a(context, "remix");
        }
        return str;
    }

    private static String a(Context context, String str) {
        if (PackageUtils.isPackageDoll(context)) {
            return "doll_" + str;
        }
        if (PackageUtils.isPackageLSJXC(context)) {
            return "mjlsjxc_" + str;
        }
        if (PackageUtils.isPackageYGBH(context)) {
            return "mjygbh_" + str;
        }
        if (PackageUtils.isPackageFSS(context)) {
            return "mjfss_" + str;
        }
        return str;
    }

    private static String a(Context context) {
        String name;
        IOException e;
        String[] split;
        Throwable th;
        String str = "";
        ZipFile zipFile;
        try {
            zipFile = new ZipFile(context.getApplicationInfo().sourceDir);
            try {
                Enumeration entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    name = ((ZipEntry) entries.nextElement()).getName();
                    if (name.startsWith("META-INF/qbchannel_")) {
                        break;
                    }
                }
                name = str;
                if (zipFile != null) {
                    try {
                        zipFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (zipFile == null) {
                        name = str;
                    } else {
                        try {
                            zipFile.close();
                            name = str;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            name = str;
                        }
                    }
                    split = name.split("_");
                    if (split != null) {
                    }
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e4 = e5;
            zipFile = null;
            e4.printStackTrace();
            if (zipFile == null) {
                zipFile.close();
                name = str;
            } else {
                name = str;
            }
            split = name.split("_");
            if (split != null) {
            }
            return "";
        } catch (Throwable th3) {
            th = th3;
            zipFile = null;
            if (zipFile != null) {
                zipFile.close();
            }
            throw th;
        }
        split = name.split("_");
        if (split != null || split.length < 2) {
            return "";
        }
        return name.substring(split[0].length() + 1);
    }

    public static String getBuildTime() {
        NameNotFoundException e;
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String str;
        String str2 = "";
        try {
            Context appContext = AppUtils.getInstance().getAppContext();
            ApplicationInfo applicationInfo = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                str = str2;
            } else {
                str = applicationInfo.metaData.getString("BUILD_TIME");
            }
            try {
                c = str;
            } catch (NameNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                if (TextUtils.isEmpty(str)) {
                    return "200011221122_";
                }
                return str;
            }
        } catch (NameNotFoundException e3) {
            NameNotFoundException nameNotFoundException = e3;
            str = str2;
            e = nameNotFoundException;
            e.printStackTrace();
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            return "200011221122_";
        }
        if (TextUtils.isEmpty(str)) {
            return "200011221122_";
        }
        return str;
    }

    public static long getTotalMemorySize(Context context) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 2048);
            String readLine = bufferedReader.readLine();
            readLine = readLine.substring(readLine.indexOf("MemTotal:"));
            bufferedReader.close();
            return ((long) Integer.parseInt(readLine.replaceAll("\\D+", ""))) * 1024;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getCpuNumCores() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new i()).length;
        } catch (Exception e) {
            return 1;
        }
    }

    public static String getDeviceIdInfo() {
        Context appContext = AppUtils.getInstance().getAppContext();
        JSONObject jSONObject = new JSONObject();
        TelephonyManager telephonyManager = (TelephonyManager) appContext.getSystemService("phone");
        try {
            jSONObject.put("DEVICEID", telephonyManager.getDeviceId());
            jSONObject.put("SIMNO", telephonyManager.getSimSerialNumber());
            jSONObject.put("IMSI", telephonyManager.getSubscriberId());
            jSONObject.put("ANDROID_ID", Secure.getString(appContext.getContentResolver(), "android_id"));
            jSONObject.put("SDK_INT", VERSION.SDK_INT);
            try {
                Class cls = Class.forName("android.os.SystemProperties");
                String str = "SERIAL";
                jSONObject.put(str, (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.serialno"}));
            } catch (Exception e) {
                e.printStackTrace();
            }
            WifiManager wifiManager = (WifiManager) appContext.getSystemService("wifi");
            if (wifiManager != null) {
                jSONObject.put("MAC", wifiManager.getConnectionInfo().getMacAddress());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }
}
