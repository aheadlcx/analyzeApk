package qsbk.app.video;

import java.io.File;
import qsbk.app.QsbkApp;
import qsbk.app.utils.StorageUtils;

final class o implements Runnable {
    o() {
    }

    public void run() {
        File cacheDirectory = StorageUtils.getCacheDirectory(QsbkApp.mContext, false);
        File externalCacheDir = StorageUtils.getExternalCacheDir(QsbkApp.mContext);
        try {
            Utils.b(new File(cacheDirectory + "/qbvideo"));
        } catch (Exception e) {
        }
        try {
            Utils.b(new File(externalCacheDir + "/qbvideo"));
        } catch (Exception e2) {
        }
    }
}
