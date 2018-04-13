package com.baidu.mobad.feeds;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

class c implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ View b;
    final /* synthetic */ int c;
    final /* synthetic */ XAdNativeResponse d;

    c(XAdNativeResponse xAdNativeResponse, Context context, View view, int i) {
        this.d = xAdNativeResponse;
        this.a = context;
        this.b = view;
        this.c = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.d.a(this.a);
        this.d.b.a(this.b, this.d.a, this.c, this.d.d);
    }
}
