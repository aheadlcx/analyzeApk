package com.budejie.www.service;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.service.MediaPlayerServer.a;
import com.budejie.www.util.aa;

class MediaPlayerServer$a$4 extends Handler {
    final /* synthetic */ a a;

    MediaPlayerServer$a$4(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        if (message.what == 1020) {
            aa.a("MediaPlayerServer", "暂停");
            this.a.c();
        }
    }
}
