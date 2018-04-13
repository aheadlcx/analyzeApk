package com.zxt.download2;

import com.zxt.download2.DownloadListActivity.b;

class DownloadListActivity$b$1 implements Runnable {
    final /* synthetic */ b a;

    DownloadListActivity$b$1(b bVar) {
        this.a = bVar;
    }

    public void run() {
        b.a(this.a).a(DownloadState.FINISHED);
        b.a(this.a).a(b.a(this.a).e());
        b.a(this.a).c(100);
        this.a.a.c.notifyDataSetChanged();
        this.a.a.d.add(b.a(this.a));
        this.a.a.c.remove(b.a(this.a));
    }
}
