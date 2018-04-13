package qsbk.app.ad.feedsad.qbad;

import android.content.Intent;
import android.net.Uri;
import com.facebook.cache.disk.DefaultDiskStorage.FileType;
import java.io.File;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader.ApkDownloadListener;

class f implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ApkDownloadListener b;

    f(ApkDownloadListener apkDownloadListener, String str) {
        this.b = apkDownloadListener;
        this.a = str;
    }

    public void run() {
        File file = new File(this.b.mDownloadFile.getAbsolutePath().replace(FileType.TEMP, ""));
        this.b.mDownloadFile.renameTo(file);
        this.b.cleanNotification(this.a);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        this.b.mContext.startActivity(intent);
    }
}
