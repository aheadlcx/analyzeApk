package com.ixintui.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ixintui.plugin.IPushReceiver;
import com.ixintui.smartlink.a.a;

public class Receiver extends BroadcastReceiver {
    public static final int RECEIVER_VERSION = 2;
    private IPushReceiver a;

    public void onReceive(Context context, Intent intent) {
        if (this.a == null) {
            this.a = a.c(context);
        }
        if (this.a != null) {
            this.a.onReceive(context, intent);
            if (a() != null) {
                setResultData(a());
            }
        }
    }

    private String a() {
        try {
            return (String) a.a(this.a, "getResult", null, null);
        } catch (Exception e) {
            return null;
        }
    }
}
