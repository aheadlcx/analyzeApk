package qsbk.app.core.web.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class e extends BroadcastReceiver {
    final /* synthetic */ QsbkWebView a;

    e(QsbkWebView qsbkWebView) {
        this.a = qsbkWebView;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.getSettings().getUserAgentString();
    }
}
