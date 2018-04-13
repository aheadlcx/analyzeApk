package com.igexin.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.AppStat;

public class PushReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        AppStat.reportAppStart("igexin_pushreceiver");
    }
}
