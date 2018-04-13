package qsbk.app.core.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class NetworkUtils {
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 1;
    public static final int NETWORK_TYPE_4G = 3;
    public static final int NETWORK_TYPE_UNKNOWN = -1;
    public static final int NETWORK_TYPE_WIFI = 0;
    private static NetworkUtils c;
    private ConnectivityManager a = ((ConnectivityManager) this.d.getSystemService("connectivity"));
    private TelephonyManager b = ((TelephonyManager) this.d.getSystemService("phone"));
    private Context d = AppUtils.getInstance().getAppContext();

    private NetworkUtils() {
    }

    public static NetworkUtils getInstance() {
        if (c == null) {
            synchronized (NetworkUtils.class) {
                c = new NetworkUtils();
            }
        }
        return c;
    }

    public int getNetworkType() {
        if (isWifiAvailable()) {
            return 0;
        }
        if (isMobileNetAvailable()) {
            if (isConnection3G()) {
                return 1;
            }
            if (isConnection2G()) {
                return 2;
            }
            if (isConnection4G()) {
                return 3;
            }
        }
        return -1;
    }

    public String getNetworkDetailType() {
        if (isWifiAvailable()) {
            return "WiFi";
        }
        if (isMobileNetAvailable()) {
            return this.b.getNetworkType() + "";
        }
        return "unknow";
    }

    public boolean isConnection4G() {
        switch (this.b.getNetworkType()) {
            case 13:
                return true;
            default:
                return false;
        }
    }

    public boolean isConnection3G() {
        switch (this.b.getNetworkType()) {
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    public boolean isConnection2G() {
        switch (this.b.getNetworkType()) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return true;
            default:
                return false;
        }
    }

    public boolean isNetworkAvailable() {
        return a(this.a.getActiveNetworkInfo());
    }

    public boolean isMobileNetAvailable() {
        return a(this.a.getNetworkInfo(0));
    }

    public String getLocalMacAddressFromWifiInfo() {
        String macAddress = ((WifiManager) this.d.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (TextUtils.isEmpty(macAddress)) {
            return "00:00:00:00:00:00";
        }
        return macAddress;
    }

    public boolean isWifiAvailable() {
        return a(this.a.getNetworkInfo(1));
    }

    private boolean a(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }
}
