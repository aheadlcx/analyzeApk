package com.igexin.sdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import qsbk.app.utils.LogUtil;

public class PushService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        LogUtil.d("push service on create");
        super.onCreate();
    }
}
