package com.alibaba.mtl.log.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.umeng.analytics.pro.bv;
import com.umeng.update.UpdateConfig;

public class l {
    private static a a = new a();
    /* renamed from: a */
    private static b f19a = new b();
    /* renamed from: a */
    private static String[] f20a = new String[]{"Unknown", "Unknown"};

    private static class a implements Runnable {
        private Context a;

        private a() {
        }

        public a a(Context context) {
            this.a = context;
            return this;
        }

        public void run() {
            if (this.a != null) {
                try {
                    if (this.a.getPackageManager().checkPermission(UpdateConfig.g, this.a.getPackageName()) != 0) {
                        l.a[0] = "Unknown";
                        return;
                    }
                    ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                    if (connectivityManager == null) {
                        l.a[0] = "Unknown";
                        return;
                    }
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        if (1 == activeNetworkInfo.getType()) {
                            l.a[0] = bv.d;
                        } else if (activeNetworkInfo.getType() == 0) {
                            l.a[0] = bv.c;
                            l.a[1] = activeNetworkInfo.getSubtypeName();
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private static class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(Context context, Intent intent) {
            r.a().b(l.a.a(context));
        }
    }

    public static String u() {
        Context context = com.alibaba.mtl.log.a.getContext();
        if (context == null) {
            return "Unknown";
        }
        try {
            if (context.getPackageManager().checkPermission(UpdateConfig.g, context.getPackageName()) != 0) {
                return "Unknown";
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "Unknown";
            }
            if (activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() == 1) {
                    return IXAdSystemUtils.NT_WIFI;
                }
                if (activeNetworkInfo.getType() == 0) {
                    return a(activeNetworkInfo.getSubtype());
                }
            }
            return "Unknown";
        } catch (Throwable th) {
        }
    }

    public static boolean isConnected() {
        Context context = com.alibaba.mtl.log.a.getContext();
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        return activeNetworkInfo.isConnected();
                    }
                    return false;
                }
            } catch (Exception e) {
            }
        }
        return true;
    }

    private static String a(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "Unknown";
        }
    }

    public static String[] getNetworkState(Context context) {
        return f20a;
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return "00:00:00:00:00:00";
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI)).getConnectionInfo();
        if (connectionInfo == null) {
            return "00:00:00:00:00:00";
        }
        String macAddress = connectionInfo.getMacAddress();
        if (TextUtils.isEmpty(macAddress)) {
            return "00:00:00:00:00:00";
        }
        return macAddress;
    }

    public static void a(Context context) {
        if (context != null) {
            context.registerReceiver(f19a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public static void b(Context context) {
        if (context != null && f19a != null) {
            context.unregisterReceiver(f19a);
        }
    }
}
