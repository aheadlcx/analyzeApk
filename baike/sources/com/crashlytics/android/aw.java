package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class aw implements OnClickListener {
    private /* synthetic */ au a;

    aw(au auVar) {
        this.a = auVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.a.a.a(false);
        dialogInterface.dismiss();
    }
}
