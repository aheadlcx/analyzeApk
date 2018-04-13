package com.a.a;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;

public class e {
    public String a = null;
    private TelephonyManager b;
    private WifiManager c;
    private String d;
    private Context e;
    private int f = 0;

    public e(Context context) {
        this.e = context;
        this.b = (TelephonyManager) context.getSystemService("phone");
        this.c = (WifiManager) context.getSystemService(IXAdSystemUtils.NT_WIFI);
    }

    public String a() {
        return this.b.getLine1Number();
    }

    public String b() {
        this.d = this.b.getSubscriberId();
        if (this.d == null) {
            this.d = "";
        }
        return this.d;
    }
}
