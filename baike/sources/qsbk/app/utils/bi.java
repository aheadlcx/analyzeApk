package qsbk.app.utils;

import android.content.Context;

final class bi implements Runnable {
    final /* synthetic */ Context a;

    bi(Context context) {
        this.a = context;
    }

    public void run() {
        UILDiskCacheCleaner.b(StorageUtils.getIndividualCacheDirectory(this.a));
    }
}
