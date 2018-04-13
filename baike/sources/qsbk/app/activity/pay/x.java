package qsbk.app.activity.pay;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.activity.PayPwdResetActivity;

class x implements OnClickListener {
    final /* synthetic */ v a;

    x(v vVar) {
        this.a = vVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPwdResetActivity.launch(this.a.b);
    }
}
