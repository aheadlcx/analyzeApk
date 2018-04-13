package qsbk.app.push;

import android.webkit.WebView;
import qsbk.app.QsbkApp;
import qsbk.app.cafe.Jsnativeinteraction.ui.QsbkWebViewClient;
import qsbk.app.utils.ToastAndDialog;

class j extends QsbkWebViewClient {
    final /* synthetic */ PushMessageShow a;

    j(PushMessageShow pushMessageShow) {
        this.a = pushMessageShow;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "Oh no! " + str, Integer.valueOf(0)).show();
    }
}
