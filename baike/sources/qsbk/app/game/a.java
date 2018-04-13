package qsbk.app.game;

import qsbk.app.utils.LogUtil;

class a implements Runnable {
    final /* synthetic */ ContinueDownloader a;

    a(ContinueDownloader continueDownloader) {
        this.a = continueDownloader;
    }

    public void run() {
        this.a.saveDownloadUnitToMap();
        LogUtil.d("save download unit to cache");
    }
}
