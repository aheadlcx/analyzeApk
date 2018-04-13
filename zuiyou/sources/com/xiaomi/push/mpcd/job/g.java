package com.xiaomi.push.mpcd.job;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.channel.commonutils.android.d;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;

public class g extends f {
    private boolean a;
    private boolean b;
    private boolean e;
    private boolean f;

    public g(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        super(context, i);
        this.a = z;
        this.b = z2;
        this.e = z3;
        this.f = z4;
    }

    private String a(Context context) {
        if (!this.f) {
            return "off";
        }
        String str = "";
        try {
            Iterator it = d.h(context).iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (!TextUtils.isEmpty(str)) {
                    str = str + VoiceWakeuperAidl.PARAMS_SEPARATE;
                }
                str = str + com.xiaomi.channel.commonutils.string.d.a(str2) + "," + com.xiaomi.channel.commonutils.string.d.b(str2);
            }
            return str;
        } catch (Throwable th) {
            return "";
        }
    }

    private String f() {
        if (!this.a) {
            return "off";
        }
        try {
            String g = g();
            return TextUtils.isEmpty(g) ? "" : com.xiaomi.channel.commonutils.string.d.a(g) + "," + com.xiaomi.channel.commonutils.string.d.b(g);
        } catch (Throwable th) {
            return "";
        }
    }

    @TargetApi(9)
    private String g() {
        Object obj = "";
        if (VERSION.SDK_INT < 23) {
            obj = ((WifiManager) this.d.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        }
        if (!TextUtils.isEmpty(obj)) {
            return obj;
        }
        if (VERSION.SDK_INT < 9) {
            return "";
        }
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if ("wlan0".equalsIgnoreCase(networkInterface.getName())) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString().toLowerCase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String h() {
        if (!this.b) {
            return "off";
        }
        try {
            String subscriberId = ((TelephonyManager) this.d.getSystemService("phone")).getSubscriberId();
            return TextUtils.isEmpty(subscriberId) ? "" : com.xiaomi.channel.commonutils.string.d.a(subscriberId) + "," + com.xiaomi.channel.commonutils.string.d.b(subscriberId);
        } catch (Throwable th) {
            return "";
        }
    }

    private String i() {
        if (!this.e) {
            return "off";
        }
        try {
            String simSerialNumber = ((TelephonyManager) this.d.getSystemService("phone")).getSimSerialNumber();
            return TextUtils.isEmpty(simSerialNumber) ? "" : com.xiaomi.channel.commonutils.string.d.a(simSerialNumber) + "," + com.xiaomi.channel.commonutils.string.d.b(simSerialNumber);
        } catch (Throwable th) {
            return "";
        }
    }

    public int a() {
        return 13;
    }

    public String b() {
        return f() + "|" + h() + "|" + i() + "|" + a(this.d);
    }

    public com.xiaomi.xmpush.thrift.d d() {
        return com.xiaomi.xmpush.thrift.d.DeviceBaseInfo;
    }
}
