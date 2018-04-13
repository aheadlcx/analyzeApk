package com.crashlytics.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class ax implements OnClickListener {
    private /* synthetic */ au a;

    ax(au auVar) {
        this.a = auVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Crashlytics crashlytics = this.a.b;
        Crashlytics.a(true);
        this.a.a.a(true);
        dialogInterface.dismiss();
    }
}
