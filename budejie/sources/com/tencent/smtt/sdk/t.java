package com.tencent.smtt.sdk;

class t implements Runnable {
    final /* synthetic */ s a;

    t(s sVar) {
        this.a = sVar;
    }

    public void run() {
        if (!TbsShareManager.forceLoadX5FromTBSDemo(s.a(this.a).getContext()) && TbsDownloader.needDownload(s.a(this.a).getContext(), false)) {
            TbsDownloader.startDownload(s.a(this.a).getContext());
        }
    }
}
