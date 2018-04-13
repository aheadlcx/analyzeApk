package qsbk.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.sdk.packet.d;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.NoAvailableSpaceActivity;
import qsbk.app.activity.group.SplashGroup;

public class DeviceUtils {
    public static final int MIN_AVAILABLE_SIZE_MB = 10;
    private static String a = null;
    private static String b = null;

    public static String getAndroidId() {
        if (b != null) {
            return b;
        }
        b = "IMEI_" + Md5.MD5("\"DEVICEID\":\"" + ((TelephonyManager) QsbkApp.mContext.getSystemService("phone")).getDeviceId() + "\"-" + "\"ANDROID_ID\":\"" + Secure.getString(QsbkApp.mContext.getContentResolver(), "android_id") + "\"");
        return b;
    }

    public static String getPhoneBuildInfoJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("manufacture", Build.MANUFACTURER);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("brand", Build.BRAND);
            jSONObject.put(d.n, Build.DEVICE);
            jSONObject.put("display", Build.DISPLAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static String getDeviceIdInfo() {
        if (a != null) {
            return a;
        }
        JSONObject jSONObject = new JSONObject();
        TelephonyManager telephonyManager = (TelephonyManager) QsbkApp.mContext.getSystemService("phone");
        try {
            jSONObject.put("DEVICEID", telephonyManager.getDeviceId());
            jSONObject.put("SIMNO", telephonyManager.getSimSerialNumber());
            jSONObject.put("IMSI", telephonyManager.getSubscriberId());
            jSONObject.put("ANDROID_ID", Secure.getString(QsbkApp.mContext.getContentResolver(), "android_id"));
            jSONObject.put("SDK_INT", VERSION.SDK_INT);
            try {
                Class cls = Class.forName("android.os.SystemProperties");
                String str = "SERIAL";
                jSONObject.put(str, (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.serialno"}));
            } catch (Exception e) {
                e.printStackTrace();
            }
            WifiManager wifiManager = (WifiManager) QsbkApp.mContext.getSystemService("wifi");
            if (wifiManager != null) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    jSONObject.put("MAC", connectionInfo.getMacAddress());
                }
            }
            jSONObject.put("RANDOM", SharePreferenceUtils.getSharePreferencesValue("random"));
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (SecurityException e3) {
            e3.printStackTrace();
        }
        a = jSONObject.toString();
        return a;
    }

    public static String getOsArch() {
        return System.getProperty("os.arch");
    }

    public static String getSDPath() {
        String str = "";
        if (isExternalStorageAvailable()) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return str;
    }

    public static String getCollectSDPath() {
        if (TextUtils.isEmpty(getSDPath())) {
            return QsbkApp.getInstance().getFilesDir().getAbsolutePath() + File.separator + "qsbk/qiushibaike/myCollect";
        }
        return getSDPath() + File.separator + "qsbk/qiushibaike/myCollect";
    }

    public static boolean isExternalStorageAvailable() {
        try {
            return "mounted".equalsIgnoreCase(Environment.getExternalStorageState());
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public static long getAvailableExternalStorageSize() {
        if (!isExternalStorageAvailable()) {
            return -1;
        }
        long blockSizeLong;
        long availableBlocksLong;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        if (VERSION.SDK_INT >= 18) {
            blockSizeLong = statFs.getBlockSizeLong();
            availableBlocksLong = statFs.getAvailableBlocksLong();
        } else {
            blockSizeLong = (long) statFs.getBlockSize();
            availableBlocksLong = (long) statFs.getAvailableBlocks();
        }
        return availableBlocksLong * blockSizeLong;
    }

    public static boolean checkExternalStorageAvailability(Context context) {
        long availableExternalStorageSize = getAvailableExternalStorageSize();
        if (availableExternalStorageSize > 0 && (availableExternalStorageSize >> 20) > 10) {
            return true;
        }
        Intent intent = new Intent(context, NoAvailableSpaceActivity.class);
        intent.setAction(NoAvailableSpaceActivity.ACTION_NO_AVAILABLE_SPACE);
        context.startActivity(intent);
        return false;
    }

    public static void addShortcut(Context context) {
        if (!Build.MODEL.startsWith("MI") && !Boolean.valueOf(context.getSharedPreferences("qiushibaike", 0).getBoolean("qiushibaike", false)).booleanValue()) {
            Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context, R.drawable.ic_launcher));
            intent.putExtra("android.intent.extra.shortcut.NAME", context.getString(R.string.app_name));
            intent.putExtra("duplicate", false);
            Parcelable intent2 = new Intent("android.intent.action.MAIN");
            intent2.addCategory("android.intent.category.LAUNCHER");
            intent2.setClass(context, SplashGroup.class);
            intent.putExtra("android.intent.extra.shortcut.INTENT", intent2);
            context.sendBroadcast(intent);
            Editor edit = context.getSharedPreferences("qiushibaike", 0).edit();
            edit.putBoolean("qiushibaike", true);
            edit.apply();
        }
    }

    public static void getSingInfo() {
        try {
            a(QsbkApp.mContext.getPackageManager().getPackageInfo("qsbk.app", 64).signatures[0].toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a(byte[] bArr) {
        try {
            String bigInteger = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getSerialNumber().toString();
            Log.e("mamx", "signNumber:" + bigInteger);
            if (!"1315484290".equals(bigInteger)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "此糗事百科为盗版，请下载正版", Integer.valueOf(1)).show();
            }
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    public static void addDeviceInfoToParam(Map<String, Object> map) {
        map.put("mobile_brand", Build.MODEL);
        map.put("device_info", String.format("%s|%s|%s|%s", new Object[]{Build.MODEL, Build.BRAND, Build.DEVICE, Build.DISPLAY}));
    }

    public static boolean hasPermission(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static int getScreenWidth(Context context) {
        return a(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return a(context).heightPixels;
    }

    private static DisplayMetrics a(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            if (defaultDisplay != null) {
                defaultDisplay.getMetrics(displayMetrics);
            } else {
                displayMetrics.setToDefaults();
            }
        } catch (Exception e) {
        }
        return displayMetrics;
    }
}
