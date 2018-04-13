package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class l implements OnClickListener {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    l(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onClick(View view) {
        this.a.m.setFocusable(true);
        this.a.m.setFocusableInTouchMode(true);
        this.a.m.setVisibility(0);
    }
}
