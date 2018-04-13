package cn.v6.sixrooms.utils;

import android.bluetooth.BluetoothAdapter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.ishumei.smantifraud.SmAntiFraud;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Locale;

public class AppInfoUtils {
    public static final String APP_CUSTOM_NAME_BASIC = "xcsdk_";
    private static final String a = AppInfoUtils.class.getSimpleName();
    private static String b;

    public static String getAppVersFion() {
        return "3.2.0";
    }

    public static int getAppCode() {
        return 20002;
    }

    public static String getAppName() {
        return new StringBuilder(APP_CUSTOM_NAME_BASIC).append(V6Coop.mStatisticsName).append("_").toString();
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceVersion() {
        return VERSION.RELEASE;
    }

    public static String getUUID() {
        String deviceId = ((TelephonyManager) V6Coop.getInstance().getContext().getSystemService("phone")).getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        StringBuilder stringBuilder = new StringBuilder();
        WifiInfo connectionInfo = ((WifiManager) V6Coop.getInstance().getContext().getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo();
        if (connectionInfo != null) {
            stringBuilder.append(connectionInfo.getMacAddress());
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            stringBuilder.append(defaultAdapter.getAddress());
        }
        return MD5Utils.encode(stringBuilder.toString());
    }

    public static String getNumber() {
        return V6Coop.mReleaseNum;
    }

    public static String getIPhone() {
        return "3";
    }

    public static String getIPad() {
        return "2";
    }

    public static String getAppInfo() {
        if (TextUtils.isEmpty(b)) {
            b = getAppName() + getAppVersFion() + "|" + getUUID() + "|" + getDeviceModel() + "|" + getNumber() + "|" + getIPhone() + "|" + getDeviceId() + "|" + getMacI() + "|" + getIMEII();
        }
        return b;
    }

    public static String getAppHDInfo() {
        return getAppName() + getAppVersFion() + "|" + getUUID() + "|" + getDeviceModel() + "|" + getNumber() + "|" + getIPad();
    }

    public static String getDeviceId() {
        return SmAntiFraud.getDeviceId();
    }

    public static String getMacI() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        stringBuilder.append(Integer.toHexString(b & 255) + ":");
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getIMEII() {
        return ((TelephonyManager) V6Coop.getInstance().getContext().getSystemService("phone")).getDeviceId();
    }

    public static String getCpuAbil() {
        return Build.CPU_ABI;
    }

    public static String getIMSII() {
        return ((TelephonyManager) V6Coop.getInstance().getContext().getSystemService("phone")).getSubscriberId();
    }

    public static synchronized int getAppCodeI() {
        int i;
        synchronized (AppInfoUtils.class) {
            try {
                i = V6Coop.getInstance().getContext().getPackageManager().getPackageInfo(V6Coop.getInstance().getContext().getPackageName(), 0).versionCode;
            } catch (Exception e) {
                e.printStackTrace();
                i = 1;
            }
        }
        return i;
    }
}
