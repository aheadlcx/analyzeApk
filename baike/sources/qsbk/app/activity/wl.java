package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class wl implements OnClickListener {
    final /* synthetic */ wj a;

    wl(wj wjVar) {
        this.a = wjVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.a(1);
        dialogInterface.dismiss();
    }
}
