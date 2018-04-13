package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class ak implements OnClickListener {
    final /* synthetic */ LaiseePaymentActivity a;

    ak(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onClick(View view) {
        this.a.d.setVisibility(4);
        if (this.a.a == 3) {
            PaymentChangeActivity.launch(this.a, this.a.z, this.a.A, 101, 1);
        } else if (this.a.a == 0 || this.a.a == 1 || this.a.a == 2) {
            PaymentChangeActivity.launch(this.a, this.a.z, this.a.A, 101, 0);
        }
    }
}
