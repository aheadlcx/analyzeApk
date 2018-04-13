package qsbk.app.live.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class ha implements OnDismissListener {
    final /* synthetic */ LiveEndDialog a;

    ha(LiveEndDialog liveEndDialog) {
        this.a = liveEndDialog;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.c.performClick();
    }
}
