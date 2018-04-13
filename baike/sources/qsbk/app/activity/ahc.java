package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ahc implements OnClickListener {
    final /* synthetic */ aha a;

    ahc(aha aha) {
        this.a = aha;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        PayPwdResetActivity.launch(this.a.a);
    }
}
