package qsbk.app.activity.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.PayPwdResetActivity;

class p implements OnClickListener {
    final /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPwdResetActivity.launch(this.a.a);
    }
}
