package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;

public final class ConnectivityManagerCompat {
    private static final ConnectivityManagerCompatImpl IMPL;

    interface ConnectivityManagerCompatImpl {
        boolean isActiveNetworkMetered(ConnectivityManager connectivityManager);
    }

    static class BaseConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        BaseConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return true;
            }
            switch (activeNetworkInfo.getType()) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    return true;
            }
        }
    }

    static class GingerbreadConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        GingerbreadConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatGingerbread.isActiveNetworkMetered(connectivityManager);
        }
    }

    static class HoneycombMR2ConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        HoneycombMR2ConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivityManager);
        }
    }

    static class JellyBeanConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        JellyBeanConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivityManager);
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            IMPL = new JellyBeanConnectivityManagerCompatImpl();
        } else if (VERSION.SDK_INT >= 13) {
            IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
        } else if (VERSION.SDK_INT >= 8) {
            IMPL = new GingerbreadConnectivityManagerCompatImpl();
        } else {
            IMPL = new BaseConnectivityManagerCompatImpl();
        }
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        return IMPL.isActiveNetworkMetered(connectivityManager);
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivityManager, Intent intent) {
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo != null) {
            return connectivityManager.getNetworkInfo(networkInfo.getType());
        }
        return null;
    }

    private ConnectivityManagerCompat() {
    }
}
