package qsbk.app.core.web.ui;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import qsbk.app.core.model.Share;

public class WebActivity$GetWebShareInfo {
    final /* synthetic */ WebActivity a;

    public WebActivity$GetWebShareInfo(WebActivity webActivity) {
        this.a = webActivity;
    }

    @JavascriptInterface
    public void onWebShareInfo(String str, String str2, String str3, String str4) {
        if (!(TextUtils.isEmpty(str) || "undefined".equals(str) || TextUtils.isEmpty(str2) || "undefined".equals(str2) || TextUtils.isEmpty(str3) || "undefined".equals(str3) || TextUtils.isEmpty(str4) || "undefined".equals(str4))) {
            WebActivity.a(this.a, new Share());
            WebActivity.c(this.a).caption = str;
            WebActivity.c(this.a).wb_info = str2;
            WebActivity.c(this.a).imageUrl = str3;
            WebActivity.c(this.a).url = str4;
        }
        this.a.mHandler.post(new j(this));
    }
}
