package qsbk.app.live.share;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import qsbk.app.core.utils.ToastUtil;

class c extends BroadcastReceiver {
    final /* synthetic */ LiveShareActivity a;

    c(LiveShareActivity liveShareActivity) {
        this.a = liveShareActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("share_result");
        if (stringExtra != null) {
            if (stringExtra.equals("success")) {
                this.a.p();
            } else if (stringExtra.equals("cancel")) {
                ToastUtil.Short("取消分享");
            }
        }
    }
}
