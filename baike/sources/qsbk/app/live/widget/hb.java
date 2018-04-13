package qsbk.app.live.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class hb implements OnCancelListener {
    final /* synthetic */ LiveEndDialog a;

    hb(LiveEndDialog liveEndDialog) {
        this.a = liveEndDialog;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.a.c.performClick();
    }
}
