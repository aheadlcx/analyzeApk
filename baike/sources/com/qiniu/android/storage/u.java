package com.qiniu.android.storage;

import android.os.Looper;
import com.qiniu.android.utils.AndroidNetwork;

class u implements NetReadyHandler {
    final /* synthetic */ UploadOptions a;

    u(UploadOptions uploadOptions) {
        this.a = uploadOptions;
    }

    public void waitReady() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            int i = 0;
            while (i < 6) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!AndroidNetwork.isNetWorkReady()) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
