package qsbk.app.ad.feedsad.qbad;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class c implements OnClickListener {
    final /* synthetic */ QbAdApkDownloader a;

    c(QbAdApkDownloader qbAdApkDownloader) {
        this.a = qbAdApkDownloader;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
