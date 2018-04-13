package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class agw implements OnClickListener {
    final /* synthetic */ agu a;

    agw(agu agu) {
        this.a = agu;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPwdResetActivity.launch(this.a.a);
    }
}
