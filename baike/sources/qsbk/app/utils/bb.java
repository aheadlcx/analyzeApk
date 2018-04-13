package qsbk.app.utils;

import java.io.File;
import qsbk.app.utils.UpdateHelper.DownloadListener;

class bb implements DownloadListener {
    final /* synthetic */ File a;
    final /* synthetic */ File b;
    final /* synthetic */ SplashAdManager c;

    bb(SplashAdManager splashAdManager, File file, File file2) {
        this.c = splashAdManager;
        this.a = file;
        this.b = file2;
    }

    public void onStart(String str) {
    }

    public void onProgress(String str, long j, long j2) {
    }

    public void onFinished(String str) {
        if (this.a.exists()) {
            this.a.delete();
        }
        this.b.renameTo(this.a);
    }

    public void onFailure(String str, Throwable th) {
        this.b.delete();
    }
}
