package com.loc;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class dy implements OnClickListener {
    final /* synthetic */ d a;

    dy(d dVar) {
        this.a = dVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.e();
        dialogInterface.cancel();
    }
}
