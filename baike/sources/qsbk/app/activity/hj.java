package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;
import qsbk.app.game.GamePlugin;
import qsbk.app.utils.RemixUtil;

class hj implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ CircleTopicActivity c;

    hj(CircleTopicActivity circleTopicActivity, int i, String str) {
        this.c = circleTopicActivity;
        this.a = i;
        this.b = str;
    }

    public void onClick(View view) {
        if (this.a == 1) {
            QbAdApkDownloader.instance().downloadFile(this.c, this.b, "Remix");
        } else if (this.a == 2) {
            GamePlugin.installApp(QbAdApkDownloader.instance().getDownloadedFileByUrl(this.b).getAbsolutePath());
        } else if (this.a == 3) {
            GamePlugin.openAndroidAppByPackage(RemixUtil.REMIX_PACKAGE_NAME);
        }
    }
}
