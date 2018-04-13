package qsbk.app.thirdparty.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import cz.msebera.android.httpclient.HttpHost;

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

    public static HttpHost getAPN() {
        Cursor query;
        HttpHost httpHost = null;
        Uri parse = Uri.parse("content://telephony/carriers/preferapn");
        if (a != null) {
            query = a.getContentResolver().query(parse, null, null, null, null);
        } else {
            query = null;
        }
        if (query != null && query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("proxy"));
            if (string != null && string.trim().length() > 0) {
                httpHost = new HttpHost(string, 80);
            }
            query.close();
        }
        return httpHost;
    }
}
