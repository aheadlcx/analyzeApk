package com.bdj.picture.edit.network;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class b$1 implements OnCancelListener {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        b.a(this.a);
    }
}
