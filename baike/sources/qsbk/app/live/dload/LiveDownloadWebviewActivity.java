package qsbk.app.live.dload;

import qsbk.app.activity.base.BaseWebviewActivity;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.widget.CommonWebView;

public class LiveDownloadWebviewActivity extends BaseWebviewActivity {
    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "发起直播";
    }

    protected String g() {
        return UrlConstants.LIVE_DOWNLOAD_WEB;
    }

    protected void a(CommonWebView commonWebView) {
        commonWebView.setDownloadListener(new a(this));
    }
}
