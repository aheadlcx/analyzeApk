package com.xiaomi.push.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class XMJobService extends Service {
    public void onCreate() {
        super.onCreate();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
