package qsbk.app.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.wxapi.WXAuthHelper.WXAuthException;

class a extends BroadcastReceiver {
    final /* synthetic */ WXAuthHelper a;

    a(WXAuthHelper wXAuthHelper) {
        this.a = wXAuthHelper;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("state");
        String stringExtra2 = intent.getStringExtra("code");
        if (stringExtra == null || stringExtra2 == null) {
            this.a.a(new WXAuthException());
        } else {
            this.a.a(stringExtra, stringExtra2);
        }
    }
}
