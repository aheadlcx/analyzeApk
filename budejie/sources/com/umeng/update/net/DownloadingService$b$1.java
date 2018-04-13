package com.umeng.update.net;

import android.widget.Toast;
import com.umeng.update.net.DownloadingService.b;
import u.upd.k;

class DownloadingService$b$1 implements Runnable {
    final /* synthetic */ b a;

    DownloadingService$b$1(b bVar) {
        this.a = bVar;
    }

    public void run() {
        Toast.makeText(b.b(this.a), k.i(b.a(this.a)), 0).show();
    }
}
