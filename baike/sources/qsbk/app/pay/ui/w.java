package qsbk.app.pay.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class w implements OnClickListener {
    final /* synthetic */ WithdrawActivity a;

    w(WithdrawActivity withdrawActivity) {
        this.a = withdrawActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.d();
        dialogInterface.dismiss();
        this.a.finish();
    }
}
