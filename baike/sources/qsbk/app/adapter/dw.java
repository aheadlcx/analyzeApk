package qsbk.app.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class dw implements OnClickListener {
    final /* synthetic */ du a;

    dw(du duVar) {
        this.a = duVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
