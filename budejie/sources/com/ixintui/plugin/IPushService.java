package com.ixintui.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public interface IPushService {
    void init(Service service);

    IBinder onBind(Intent intent);

    void onCreate();

    void onDestroy();

    int onStartCommand(Intent intent, int i, int i2);
}
