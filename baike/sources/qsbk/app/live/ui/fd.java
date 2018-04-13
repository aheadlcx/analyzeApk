package qsbk.app.live.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class fd implements OnDismissListener {
    final /* synthetic */ LivePushActivity a;

    fd(LivePushActivity livePushActivity) {
        this.a = livePushActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.setTransparentNavigationBarIfNeed();
    }
}
