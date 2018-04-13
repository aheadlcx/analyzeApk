package qsbk.app.cafe.Jsnativeinteraction.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import qsbk.app.cafe.UrlHandler;
import qsbk.app.im.OfficialMsgDetailActivity;

public class QsbkWebViewClient extends qsbk.app.core.web.ui.QsbkWebViewClient {
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String toLowerCase = str.toLowerCase();
        if (toLowerCase.startsWith("weixin://") || toLowerCase.startsWith("alipays://")) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            try {
                Context context = webView.getContext();
                if (context instanceof OfficialMsgDetailActivity) {
                    ((OfficialMsgDetailActivity) context).startActivityIfNeeded(intent, -1);
                    return true;
                }
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                return true;
            }
        }
        boolean z = new UrlHandler(webView.getContext()).shouldOverrideUrlLoading(webView, str) || super.shouldOverrideUrlLoading(webView, str);
        return z;
    }
}
