package com.liulishuo.filedownloader.services;

import android.content.Intent;
import android.os.IBinder;

interface e {
    IBinder onBind(Intent intent);

    void onDestroy();

    void onStartCommand(Intent intent, int i, int i2);
}
