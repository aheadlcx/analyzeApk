package com.ixintui.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ixintui.plugin.IPushService;
import com.ixintui.smartlink.a.a;

public class DeamonService extends Service {
    private IPushService a;

    public void onCreate() {
        super.onCreate();
        this.a = (IPushService) a.a(this, "com.ixintui.push.DeamonServiceImpl");
        if (this.a != null) {
            this.a.init(this);
            this.a.onCreate();
        }
    }

    public IBinder onBind(Intent intent) {
        if (this.a != null) {
            return this.a.onBind(intent);
        }
        stopSelf();
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.a != null) {
            this.a.onDestroy();
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
