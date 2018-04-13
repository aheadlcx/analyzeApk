package qsbk.app.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class et implements OnClickListener {
    final /* synthetic */ es a;

    et(es esVar) {
        this.a = esVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                this.a.a.d();
                return;
            case 1:
                this.a.a.addLive();
                return;
            default:
                return;
        }
    }
}
