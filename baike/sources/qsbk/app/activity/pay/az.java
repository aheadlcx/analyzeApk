package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class az implements OnClickListener {
    final /* synthetic */ LaiseePaymentActivity a;

    az(LaiseePaymentActivity laiseePaymentActivity) {
        this.a = laiseePaymentActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
