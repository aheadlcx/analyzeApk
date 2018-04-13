package qsbk.app.service;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import qsbk.app.activity.MainActivity;

class j extends Handler {
    final /* synthetic */ VersionCheckService a;

    j(VersionCheckService versionCheckService) {
        this.a = versionCheckService;
    }

    public void handleMessage(Message message) {
        LocalBroadcastManager.getInstance(this.a.getApplicationContext()).sendBroadcast(new Intent(MainActivity.ACTION_UPDATE));
    }
}
