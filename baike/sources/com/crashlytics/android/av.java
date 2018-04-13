package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class av implements OnClickListener {
    private /* synthetic */ au a;

    av(au auVar) {
        this.a = auVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.a(true);
        dialogInterface.dismiss();
    }
}
