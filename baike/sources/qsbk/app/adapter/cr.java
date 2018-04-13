package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class cr implements OnClickListener {
    final /* synthetic */ cp a;

    cr(cp cpVar) {
        this.a = cpVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.a(1);
        dialogInterface.dismiss();
    }
}
