package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class r implements OnClickListener {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    r(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onClick(View view) {
        this.a.b.setVisibility(4);
        PaymentChangeActivity.launch(this.a, this.a.w, this.a.x, 101, 0);
    }
}
