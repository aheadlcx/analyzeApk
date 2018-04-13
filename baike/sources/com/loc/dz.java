package com.loc;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class dz implements OnClickListener {
    final /* synthetic */ d a;

    dz(d dVar) {
        this.a = dVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
