package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bl implements OnClickListener {
    final /* synthetic */ PaymentChangeActivity a;

    bl(PaymentChangeActivity paymentChangeActivity) {
        this.a = paymentChangeActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
