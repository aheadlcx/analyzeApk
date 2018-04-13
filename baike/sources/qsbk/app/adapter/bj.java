package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bj implements OnClickListener {
    final /* synthetic */ bh a;

    bj(bh bhVar) {
        this.a = bhVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.b.a(1);
        dialogInterface.dismiss();
    }
}
