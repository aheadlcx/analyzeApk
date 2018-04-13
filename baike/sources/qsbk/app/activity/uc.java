package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class uc implements OnClickListener {
    final /* synthetic */ ua a;

    uc(ua uaVar) {
        this.a = uaVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a.clearAllMsgs();
    }
}
