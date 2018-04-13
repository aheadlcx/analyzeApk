package qsbk.app.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class bm extends BroadcastReceiver {
    final /* synthetic */ CommonWebView a;

    bm(CommonWebView commonWebView) {
        this.a = commonWebView;
    }

    public void onReceive(Context context, Intent intent) {
        this.a.getSettings().getUserAgentString();
    }
}
