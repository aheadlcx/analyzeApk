package qsbk.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class o implements OnClickListener {
    final /* synthetic */ QsbkApp a;

    o(QsbkApp qsbkApp) {
        this.a = qsbkApp;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
