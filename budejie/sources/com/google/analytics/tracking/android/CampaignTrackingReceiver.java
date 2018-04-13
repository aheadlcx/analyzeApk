package com.google.analytics.tracking.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class CampaignTrackingReceiver extends BroadcastReceiver {
    static final String CAMPAIGN_KEY = "referrer";
    static final String INSTALL_ACTION = "com.android.vending.INSTALL_REFERRER";

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(CAMPAIGN_KEY);
        if (INSTALL_ACTION.equals(intent.getAction()) && stringExtra != null) {
            Intent intent2 = new Intent(context, CampaignTrackingService.class);
            intent2.putExtra(CAMPAIGN_KEY, stringExtra);
            context.startService(intent2);
        }
    }
}
