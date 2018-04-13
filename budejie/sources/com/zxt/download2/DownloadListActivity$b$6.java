package com.zxt.download2;

import com.zxt.download2.DownloadListActivity.b;

class DownloadListActivity$b$6 implements Runnable {
    final /* synthetic */ b a;

    DownloadListActivity$b$6(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.a.c.notifyDataSetChanged();
    }
}
