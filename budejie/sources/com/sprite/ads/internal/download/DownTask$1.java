package com.sprite.ads.internal.download;

import android.widget.Toast;

class DownTask$1 implements Runnable {
    final /* synthetic */ DownTask a;

    DownTask$1(DownTask downTask) {
        this.a = downTask;
    }

    public void run() {
        Toast.makeText(DownTask.a(this.a), "已在下载队列", 0).show();
    }
}
