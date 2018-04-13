package qsbk.app.live.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class dh implements OnDismissListener {
    final /* synthetic */ dg a;

    dh(dg dgVar) {
        this.a = dgVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.a.a.setTransparentNavigationBarIfNeed();
    }
}
