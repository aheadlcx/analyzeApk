package com.sina.weibo.sdk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Pair;
import com.sina.weibo.sdk.utils.LogUtil;

public class NetStateManager {
    public static NetState CUR_NETSTATE = NetState.Mobile;
    private static Context a;

    public enum NetState {
        Mobile,
        WIFI,
        NOWAY
    }

    public class NetStateReceive extends BroadcastReceiver {
        final /* synthetic */ NetStateManager a;

        public NetStateReceive(NetStateManager netStateManager) {
            this.a = netStateManager;
        }

        public void onReceive(Context context, Intent intent) {
            NetStateManager.a = context;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (!wifiManager.isWifiEnabled() || -1 == connectionInfo.getNetworkId()) {
                    NetStateManager.CUR_NETSTATE = NetState.Mobile;
                }
            }
        }
    }

    public static Pair<String, Integer> getAPN() {
        Cursor query;
        Pair<String, Integer> pair = null;
        Uri parse = Uri.parse("content://telephony/carriers/preferapn");
        if (a != null) {
            query = a.getContentResolver().query(parse, null, null, null, null);
        } else {
            query = null;
        }
        if (query != null && query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("proxy"));
            if (string != null && string.trim().length() > 0) {
                pair = new Pair(string, Integer.valueOf(80));
            }
            query.close();
        }
        return pair;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context == null) {
            LogUtil.e("Weibosdk", "unexpected null context in isNetworkConnected");
            return false;
        } else if (context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) != 0) {
            return false;
        } else {
            NetworkInfo activeNetworkInfo;
            try {
                activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            } catch (NullPointerException e) {
                activeNetworkInfo = null;
            }
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return false;
            }
            return true;
        }
    }
}
