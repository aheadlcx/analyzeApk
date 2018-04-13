package qsbk.app.cafe.Jsnativeinteraction.ui;

import android.content.Context;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import qsbk.app.ad.feedsad.qbad.QbAdApkDownloader;

public class SimpleWebViewDownloadListener implements DownloadListener {
    protected final WebView a;

    public SimpleWebViewDownloadListener(WebView webView) {
        this.a = webView;
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        Context context = this.a.getContext();
        if (context != null) {
            QbAdApkDownloader.instance().downloadFile(context, str, URLUtil.guessFileName(str, str3, str4));
        }
    }
}
