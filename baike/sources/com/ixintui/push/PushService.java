package com.ixintui.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.ixintui.plugin.IPushService;
import com.ixintui.pushsdk.a.a;

public class PushService extends Service {
    private IPushService a;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.a = (IPushService) a.a((Context) this, "com.ixintui.push.PushServiceImpl");
        if (this.a != null) {
            this.a.init(this);
            this.a.onCreate();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (this.a != null) {
            return this.a.onStartCommand(intent, i, i2);
        }
        stopSelf();
        return 2;
    }
}
