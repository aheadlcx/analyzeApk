package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class cq implements OnClickListener {
    final /* synthetic */ cp a;

    cq(cp cpVar) {
        this.a = cpVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
