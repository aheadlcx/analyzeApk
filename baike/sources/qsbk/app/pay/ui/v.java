package qsbk.app.pay.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class v implements OnClickListener {
    final /* synthetic */ Dialog a;
    final /* synthetic */ WithdrawActivity b;

    v(WithdrawActivity withdrawActivity, Dialog dialog) {
        this.b = withdrawActivity;
        this.a = dialog;
    }

    public void onClick(View view) {
        this.a.dismiss();
        this.b.c();
    }
}
