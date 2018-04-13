package com.zxt.download2;

import com.zxt.download2.DownloadListActivity.b;

class DownloadListActivity$b$2 implements Runnable {
    final /* synthetic */ b a;

    DownloadListActivity$b$2(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.a.c.notifyDataSetChanged();
    }
}
