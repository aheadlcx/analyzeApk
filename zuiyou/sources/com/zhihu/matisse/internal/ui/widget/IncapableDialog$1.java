package com.zhihu.matisse.internal.ui.widget;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class IncapableDialog$1 implements OnClickListener {
    final /* synthetic */ IncapableDialog this$0;

    IncapableDialog$1(IncapableDialog incapableDialog) {
        this.this$0 = incapableDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
