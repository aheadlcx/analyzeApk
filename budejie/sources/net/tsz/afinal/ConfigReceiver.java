package net.tsz.afinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConfigReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.LOCALE_CHANGED")) {
            b.b();
        } else if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            b.c();
        }
    }
}
