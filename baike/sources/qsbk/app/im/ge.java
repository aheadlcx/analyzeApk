package qsbk.app.im;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

class ge extends WebChromeClient {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    ge(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i < 100) {
            this.a.g.setText("载入中...");
        }
        this.a.e.setProgress(i);
        if (i == 100) {
            this.a.g.setText(this.a.d.getTitle());
        }
        this.a.e.setVisibility(i == 100 ? 8 : 0);
        super.onProgressChanged(webView, i);
    }
}
