package com.budejie.www.download;

import android.widget.Toast;

class a$1 implements Runnable {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void run() {
        Toast.makeText(a.a(this.a), "已在下载队列", 0).show();
    }
}
