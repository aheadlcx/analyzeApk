package com.budejie.www.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.sprite.ads.internal.log.ADLog;

public class BDJDownReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        ADLog.d("onReceive:" + intent.getAction());
        if ("stop.down".equals(intent.getAction())) {
            a(intent);
        }
    }

    private void a(Intent intent) {
        intent.getIntExtra("downId", -1);
        String stringExtra = intent.getStringExtra("downloadUrl");
        ADLog.d("stop download :" + stringExtra);
        b.a();
        a aVar = (a) b.a.get(stringExtra);
        if (aVar != null) {
            aVar.a();
        }
    }
}
