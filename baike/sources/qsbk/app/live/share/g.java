package qsbk.app.live.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class g extends BroadcastReceiver {
    final /* synthetic */ LiveShareActivity a;

    g(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("share_result");
        if (TextUtils.equals(this.a.l, intent.getStringExtra("share_id")) && "success".equals(stringExtra)) {
            this.a.p();
        }
    }
}
