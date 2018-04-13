package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bf implements OnClickListener {
    final /* synthetic */ LaiseePaymentActivity a;

    bf(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onClick(View view) {
        if (this.a.a == 3) {
            this.a.b(this.a.y);
        } else if (this.a.a == 0 || this.a.a == 1 || this.a.a == 2) {
            this.a.a(this.a.y);
        }
    }
}
