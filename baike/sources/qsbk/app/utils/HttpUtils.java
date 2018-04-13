package qsbk.app.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.alipay.sdk.sys.a;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.NameValuePair;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.live.ui.NetworkDiagnosisActivity;

public class HttpUtils {
    public static final int HTTP_OK_CODE = 202;
    public static final int MOBILE_CONNECTED = 2;
    public static final String NET = "net";
    public static final int NO_NETWORK_CONNECTED = 0;
    public static final int OTHER_CONNECTED = 2;
    public static final String PROXY_IP = "10.0.0.172";
    public static final int TYPE_NET = 2;
    public static final int TYPE_UNKNOWN = 3;
    public static final int TYPE_WAP = 1;
    public static final String WAP = "wap";
    public static final int WIFI_CONNECTED = 1;
    public static final String http = "http://";
    public static final String https = "https://";
    public static int mNetworkState = 0;

    public static boolean netIsAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) QsbkApp.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    public static String buildParamListInHttpRequest(List<NameValuePair> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append(((NameValuePair) list.get(i)).getName());
            stringBuffer.append("=");
            stringBuffer.append(((NameValuePair) list.get(i)).getValue());
            if (i < list.size() - 1) {
                stringBuffer.append(a.b);
            }
        }
        return stringBuffer.toString();
    }

    public static boolean isHttp(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http://");
    }

    public static boolean isHttps(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("https://");
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null ? activeNetworkInfo.getTypeName().toLowerCase().endsWith("wifi") : false;
    }

    public static int getNetType(Context context) {
        if (context == null) {
            return 3;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getExtraInfo() == null) {
            return 3;
        }
        String extraInfo = activeNetworkInfo.getExtraInfo();
        if (extraInfo.endsWith(WAP)) {
            return 1;
        }
        return extraInfo.endsWith("net") ? 2 : 3;
    }

    public static int safePositiveInteger(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt < 0) {
                return 0;
            }
            return parseInt;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long safePositiveLong(String str) {
        try {
            long parseLong = Long.parseLong(str);
            if (parseLong < 0) {
                return 0;
            }
            return parseLong;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String filterXmlTags(String str, List<String> list) {
        if (list != null) {
            for (String str2 : list) {
                String str3 = "<" + str2 + ">";
                str = str.replaceAll(str3, "").replaceAll("</" + str2 + ">", "");
            }
        }
        return str;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable th) {
            th.printStackTrace();
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type == 0) {
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (subtypeName != null) {
                    return subtypeName.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
                }
                return "GPRS";
            } else if (type == 1) {
                return NetworkDiagnosisActivity.NETWORKTYPE_WIFI;
            }
        }
        return NetworkDiagnosisActivity.NETWORKTYPE_INVALID;
    }

    public static String getNetwork(Context context) {
        NetworkInfo activeNetworkInfo;
        try {
            activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Throwable th) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo == null) {
            return "unconnect";
        }
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            String str = "";
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                    return "2G";
                case 9:
                case 10:
                case 13:
                case 15:
                    return "4G";
                default:
                    return "3G";
            }
        } else if (type == 1) {
            return "wifi";
        } else {
            return "unknow";
        }
    }

    public static String getTrafficStats() {
        int i = 0;
        try {
            int mobileRxBytes;
            PackageManager packageManager = QsbkApp.mContext.getPackageManager();
            if (TrafficStats.getMobileRxBytes() != -1) {
                mobileRxBytes = (int) ((TrafficStats.getMobileRxBytes() / 1024) / 1024);
            } else {
                mobileRxBytes = 0;
            }
            for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
                int i2;
                int i3 = applicationInfo.uid;
                if (!applicationInfo.packageName.equals("qsbk.app") || TrafficStats.getUidRxBytes(i3) == -1) {
                    i2 = i;
                } else {
                    i2 = (int) ((TrafficStats.getUidRxBytes(i3) / 1024) / 1024);
                }
                i = i2;
            }
            return "qb:" + i + " all:" + mobileRxBytes;
        } catch (Exception e) {
            return "";
        }
    }

    public static NetworkInfo getAvailableNetWorkInfo(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            return (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) ? null : activeNetworkInfo;
        } catch (Exception e) {
            return null;
        }
    }

    public static int getWifiSignalStrength(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo.getBSSID() == null) {
            return 0;
        }
        int calculateSignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 5);
        connectionInfo.getLinkSpeed();
        String str = "Mbps";
        connectionInfo.getSSID();
        return calculateSignalLevel;
    }

    public static void setNetworkState(int i) {
        mNetworkState = i;
    }
}
