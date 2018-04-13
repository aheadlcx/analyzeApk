package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class xe implements OnClickListener {
    final /* synthetic */ xa a;

    xe(xa xaVar) {
        this.a = xaVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
