package com.tencent.open.utils;

import android.graphics.Bitmap;
import android.os.Message;
import com.tencent.open.a.f;
import java.io.File;

class n implements Runnable {
    final /* synthetic */ b a;

    n(b bVar) {
        this.a = bVar;
    }

    public void run() {
        f.a("AsynLoadImg", "saveFileRunnable:");
        String str = "share_qq_" + i.f(this.a.a) + ".jpg";
        String str2 = b.c + str;
        File file = new File(str2);
        Message obtainMessage = this.a.e.obtainMessage();
        if (file.exists()) {
            obtainMessage.arg1 = 0;
            obtainMessage.obj = str2;
            f.a("AsynLoadImg", "file exists: time:" + (System.currentTimeMillis() - this.a.d));
        } else {
            boolean a;
            Bitmap a2 = b.a(this.a.a);
            if (a2 != null) {
                a = this.a.a(a2, str);
            } else {
                f.a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                a = false;
            }
            if (a) {
                obtainMessage.arg1 = 0;
                obtainMessage.obj = str2;
            } else {
                obtainMessage.arg1 = 1;
            }
            f.a("AsynLoadImg", "file not exists: download time:" + (System.currentTimeMillis() - this.a.d));
        }
        this.a.e.sendMessage(obtainMessage);
    }
}
