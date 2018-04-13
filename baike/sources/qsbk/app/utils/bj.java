package qsbk.app.utils;

import android.content.Context;

final class bj implements Runnable {
    final /* synthetic */ Context a;

    bj(Context context) {
        this.a = context;
    }

    public void run() {
        UILDiskCacheCleaner.b(StorageUtils.getCacheDirectory(this.a, true));
    }
}
