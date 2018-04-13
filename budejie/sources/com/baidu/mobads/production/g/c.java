package com.baidu.mobads.production.g;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import java.util.Observable;
import java.util.Observer;

class c implements Observer {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void update(Observable observable, Object obj) {
        IOAdDownloader iOAdDownloader = (IOAdDownloader) observable;
        if (iOAdDownloader.getState() == DownloadStatus.COMPLETED) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.a.f(this.a.A + this.a.B);
            } else {
                new Handler(this.a.y.getMainLooper()).post(new d(this));
            }
        } else if (iOAdDownloader.getState() == DownloadStatus.ERROR) {
            Log.e("XAbstractAdProdTemplate", "download error");
            this.a.a("开屏因为3d背景图片下载失败跳过");
        }
    }
}
