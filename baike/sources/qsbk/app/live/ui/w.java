package qsbk.app.live.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class w implements OnDismissListener {
    final /* synthetic */ LiveBaseActivity a;

    w(LiveBaseActivity liveBaseActivity) {
        this.a = liveBaseActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.setTransparentNavigationBarIfNeed();
    }
}
