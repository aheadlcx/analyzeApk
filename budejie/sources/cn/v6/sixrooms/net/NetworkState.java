package cn.v6.sixrooms.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import cn.v6.sixrooms.utils.LogUtils;

public class NetworkState {
    private static final String a = NetworkState.class.getSimpleName();

    public static boolean checkNet(Context context) {
        if (context == null) {
            return false;
        }
        LogUtils.i(a, "context:" + context);
        boolean isWIFIConnected = isWIFIConnected(context);
        boolean isMobileConnected = isMobileConnected(context);
        if (isWIFIConnected || isMobileConnected) {
            return true;
        }
        return false;
    }

    public static boolean isWIFIConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(0);
        return networkInfo != null && networkInfo.isConnected();
    }
}
