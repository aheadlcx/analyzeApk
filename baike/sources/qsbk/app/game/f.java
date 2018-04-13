package qsbk.app.game;

import android.content.Intent;
import qsbk.app.QsbkApp;
import qsbk.app.push.PushMessageProcessor;

class f implements Runnable {
    final /* synthetic */ DownloadUnit a;
    final /* synthetic */ ContinueDownloader b;

    f(ContinueDownloader continueDownloader, DownloadUnit downloadUnit) {
        this.b = continueDownloader;
        this.a = downloadUnit;
    }

    public void run() {
        PushMessageProcessor.notification(new Intent(QsbkApp.mContext, GameIndexActivity.class), QsbkApp.mContext, "糗百游戏中心", this.a.getDownloadUnitName() + "下载完成", 101, null);
    }
}
