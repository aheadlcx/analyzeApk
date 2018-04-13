package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class i implements OnClickListener {
    final /* synthetic */ ItemSignCardPaymentActivity a;

    i(ItemSignCardPaymentActivity itemSignCardPaymentActivity) {
        this.a = itemSignCardPaymentActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
