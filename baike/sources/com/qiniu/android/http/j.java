package com.qiniu.android.http;

import java.io.IOException;

class j implements Runnable {
    final /* synthetic */ CountingSink a;

    j(CountingSink countingSink) {
        this.a = countingSink;
    }

    public void run() {
        try {
            this.a.a.b.onProgress(this.a.b, (int) this.a.a.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
