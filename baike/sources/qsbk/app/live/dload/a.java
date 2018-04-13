package qsbk.app.live.dload;

import android.webkit.DownloadListener;
import qsbk.app.utils.RemixUtil;

class a implements DownloadListener {
    final /* synthetic */ LiveDownloadWebviewActivity a;

    a(LiveDownloadWebviewActivity liveDownloadWebviewActivity) {
        this.a = liveDownloadWebviewActivity;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        if (str != null && str.contains(".apk") && str.contains("remix")) {
            RemixUtil.downloadOrIntallOrOpenRemix(this.a, str);
        }
    }
}
