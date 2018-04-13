package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bb implements OnClickListener {
    final /* synthetic */ LaiseePaymentActivity a;

    bb(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onClick(View view) {
        this.a.p.setVisibility(8);
    }
}
