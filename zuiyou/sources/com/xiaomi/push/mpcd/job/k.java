package com.xiaomi.push.mpcd.job;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.channel.commonutils.misc.c;
import com.xiaomi.xmpush.thrift.d;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class k extends f {
    private Comparator<ScanResult> a = new l(this);

    public k(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 8;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            WifiManager wifiManager = (WifiManager) this.d.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    stringBuilder.append(connectionInfo.getSSID()).append(",").append(connectionInfo.getBSSID()).append("|");
                }
            }
            List scanResults = wifiManager.getScanResults();
            if (!c.a(scanResults)) {
                Collections.sort(scanResults, this.a);
                for (int i = 0; i < Math.min(5, scanResults.size()); i++) {
                    ScanResult scanResult = (ScanResult) scanResults.get(i);
                    if (i > 0) {
                        stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    if (scanResult != null) {
                        stringBuilder.append(scanResult.SSID).append(",").append(scanResult.BSSID).append(",").append(scanResult.level);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return stringBuilder.toString();
    }

    public d d() {
        return d.WIFI;
    }
}
