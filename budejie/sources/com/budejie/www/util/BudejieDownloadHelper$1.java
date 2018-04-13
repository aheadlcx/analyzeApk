package com.budejie.www.util;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class BudejieDownloadHelper$1 implements OnClickListener {
    final /* synthetic */ Dialog a;

    BudejieDownloadHelper$1(Dialog dialog) {
        this.a = dialog;
    }

    public void onClick(View view) {
        if (this.a != null) {
            this.a.dismiss();
        }
    }
}
