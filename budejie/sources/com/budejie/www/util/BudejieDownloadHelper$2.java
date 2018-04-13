package com.budejie.www.util;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class BudejieDownloadHelper$2 implements OnClickListener {
    final /* synthetic */ BudejieDownloadHelper$a a;
    final /* synthetic */ Dialog b;

    BudejieDownloadHelper$2(BudejieDownloadHelper$a budejieDownloadHelper$a, Dialog dialog) {
        this.a = budejieDownloadHelper$a;
        this.b = dialog;
    }

    public void onClick(View view) {
        this.a.a(view, this.b);
    }
}
