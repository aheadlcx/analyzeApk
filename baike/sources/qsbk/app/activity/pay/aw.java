package qsbk.app.activity.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.PayPwdResetActivity;

class aw implements OnClickListener {
    final /* synthetic */ au a;

    aw(au auVar) {
        this.a = auVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPwdResetActivity.launch(this.a.e);
    }
}
