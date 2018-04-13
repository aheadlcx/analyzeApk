package com.lt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.budejie.www.util.aa;

public class NetworkStateChangeReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        aa.b("test", "NetworkStateChangeReceiver onReceive()");
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && !TextUtils.isEmpty(activeNetworkInfo.getTypeName()) && "MOBILE".equals(activeNetworkInfo.getTypeName().toUpperCase())) {
            a a = a.a();
            if (a != null) {
                aa.b("test", "NetworkStateChangeReceiver ltManager.getPhoneNumber()");
                a.c();
            }
        }
    }
}
