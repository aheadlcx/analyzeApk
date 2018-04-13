package com.sina.weibo.sdk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Pair;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public class NetStateManager {
    public static NetState a = NetState.Mobile;
    private static Context b;

    public enum NetState {
        Mobile,
        WIFI,
        NOWAY
    }

    public class NetStateReceive extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            NetStateManager.b = context;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (!wifiManager.isWifiEnabled() || -1 == connectionInfo.getNetworkId()) {
                    NetStateManager.a = NetState.Mobile;
                }
            }
        }
    }

    public static Pair<String, Integer> a() {
        Cursor query;
        Pair<String, Integer> pair = null;
        Uri parse = Uri.parse("content://telephony/carriers/preferapn");
        if (b != null) {
            query = b.getContentResolver().query(parse, null, null, null, null);
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
}
