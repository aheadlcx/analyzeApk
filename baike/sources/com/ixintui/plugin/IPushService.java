package com.ixintui.plugin;

import android.app.Service;
import android.content.Intent;

public interface IPushService {
    void init(Service service);

    void onCreate();

    void onDestroy();

    int onStartCommand(Intent intent, int i, int i2);
}
