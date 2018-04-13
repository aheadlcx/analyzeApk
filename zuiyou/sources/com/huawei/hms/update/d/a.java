package com.huawei.hms.update.d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class a extends BroadcastReceiver {
    private Handler a;

    public a(Handler handler) {
        this.a = handler;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                intent.getStringExtra("TestIntent");
                String action = intent.getAction();
                Bundle extras;
                Message message;
                if ("com.huawei.appmarket.service.downloadservice.Receiver".equals(action)) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        message = new Message();
                        message.what = 101;
                        message.obj = extras;
                        this.a.sendMessage(message);
                    }
                } else if ("com.huawei.appmarket.service.downloadservice.progress.Receiver".equals(action)) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        message = new Message();
                        message.what = 102;
                        message.obj = extras;
                        this.a.sendMessage(message);
                    }
                } else if ("com.huawei.appmarket.service.installerservice.Receiver".equals(action)) {
                    extras = intent.getExtras();
                    if (extras != null) {
                        message = new Message();
                        message.what = 103;
                        message.obj = extras;
                        this.a.sendMessage(message);
                    }
                }
            } catch (Exception e) {
                com.huawei.hms.support.log.a.d("SilentInstallReceive", "intent has some error" + e.getMessage());
            }
        }
    }
}
