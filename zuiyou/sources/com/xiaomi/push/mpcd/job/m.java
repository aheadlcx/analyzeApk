package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.misc.f;
import com.xiaomi.channel.commonutils.network.d;
import com.xiaomi.metoknlp.devicediscover.a;
import com.xiaomi.push.service.am;
import com.xiaomi.xmpush.thrift.g;
import org.json.JSONObject;

public class m extends f {
    private final Object a = new Object();
    private String b;
    private SharedPreferences e;
    private a f = new n(this);

    public m(Context context, int i) {
        super(context, i);
        a(context);
        this.e = context.getSharedPreferences("mipush_extra", 0);
    }

    private void a(Context context) {
        com.xiaomi.metoknlp.a.a(context).f();
        com.xiaomi.metoknlp.a.a().a(this.f, 1);
    }

    private static String b(String str) {
        try {
            String jSONArray = new JSONObject(str).getJSONArray("devices").toString();
            return jSONArray.substring(1, jSONArray.length() - 1);
        } catch (Throwable th) {
            return "";
        }
    }

    public int a() {
        return 14;
    }

    public String b() {
        if (d.e(this.d)) {
            com.xiaomi.metoknlp.a.a().c();
            synchronized (this.a) {
                try {
                    this.a.wait(10000);
                } catch (Throwable e) {
                    b.a(e);
                }
            }
            Editor edit = this.e.edit();
            edit.putLong("last_mac_upload_timestamp", System.currentTimeMillis());
            edit.commit();
        }
        String str = this.b;
        this.b = "";
        return str;
    }

    public com.xiaomi.xmpush.thrift.d d() {
        return com.xiaomi.xmpush.thrift.d.WifiDevicesMac;
    }

    protected boolean e() {
        if (f()) {
            return f.a(this.d, String.valueOf(a()), (long) this.c);
        }
        int max = Math.max(3600, am.a(this.d).a(g.WifiDevicesMacWifiUnchangedCollectionFrequency.a(), 7200));
        return ((((float) Math.abs(System.currentTimeMillis() - this.e.getLong("last_mac_upload_timestamp", 0))) > (((float) (max * 1000)) * 0.9f) ? 1 : (((float) Math.abs(System.currentTimeMillis() - this.e.getLong("last_mac_upload_timestamp", 0))) == (((float) (max * 1000)) * 0.9f) ? 0 : -1)) >= 0) && f.a(this.d, String.valueOf(a()), (long) max);
    }

    public boolean f() {
        try {
            CharSequence string = this.e.getString("last_wifi_ssid", "");
            WifiManager wifiManager = (WifiManager) this.d.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    Editor edit = this.e.edit();
                    edit.putString("last_wifi_ssid", connectionInfo.getSSID());
                    edit.commit();
                    if (TextUtils.isEmpty(string)) {
                        return false;
                    }
                    return !TextUtils.equals(connectionInfo.getSSID(), string);
                }
            }
        } catch (Throwable th) {
        }
        return true;
    }
}
