package qsbk.app.im;

import android.webkit.WebView;
import qsbk.app.R;
import qsbk.app.cafe.Jsnativeinteraction.ui.QsbkWebViewClient;

class gb extends QsbkWebViewClient {
    final /* synthetic */ IMChatingUrlContentDisplayActivity a;

    gb(IMChatingUrlContentDisplayActivity iMChatingUrlContentDisplayActivity) {
        this.a = iMChatingUrlContentDisplayActivity;
    }

    public void onPageFinished(WebView webView, String str) {
        if (webView.canGoBack()) {
            this.a.h.setEnabled(true);
            this.a.h.setImageResource(R.drawable.url_content_back_active);
            this.a.h.setOnClickListener(new gc(this));
        } else {
            this.a.h.setImageResource(R.drawable.url_content_back);
            this.a.h.setEnabled(false);
            this.a.h.setOnClickListener(null);
        }
        if (webView.canGoForward()) {
            this.a.i.setImageResource(R.drawable.url_content_forward_active);
            this.a.i.setEnabled(true);
            this.a.i.setOnClickListener(new gd(this));
        } else {
            this.a.i.setImageResource(R.drawable.url_content_forward);
            this.a.i.setEnabled(false);
            this.a.i.setOnClickListener(null);
        }
        super.onPageFinished(webView, str);
    }
}
