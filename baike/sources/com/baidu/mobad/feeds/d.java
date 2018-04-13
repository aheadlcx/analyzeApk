package com.baidu.mobad.feeds;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ XAdNativeResponse a;

    d(XAdNativeResponse xAdNativeResponse) {
        this.a = xAdNativeResponse;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
