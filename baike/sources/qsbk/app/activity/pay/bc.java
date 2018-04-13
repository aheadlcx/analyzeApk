package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bc implements OnClickListener {
    final /* synthetic */ LaiseePaymentActivity a;

    bc(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onClick(View view) {
        this.a.p.setFocusable(true);
        this.a.p.setFocusableInTouchMode(true);
        this.a.p.setVisibility(0);
    }
}
