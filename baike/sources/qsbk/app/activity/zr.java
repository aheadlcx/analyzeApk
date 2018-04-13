package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class zr implements OnClickListener {
    final /* synthetic */ PayPwdResetActivity a;

    zr(PayPwdResetActivity payPwdResetActivity) {
        this.a = payPwdResetActivity;
    }

    public void onClick(View view) {
        this.a.c.startCountDown(60000, 1000);
        this.a.c.setEnabled(false);
        this.a.k();
    }
}
