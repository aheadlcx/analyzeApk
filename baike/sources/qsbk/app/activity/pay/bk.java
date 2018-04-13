package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bk implements OnClickListener {
    final /* synthetic */ PayPWDUniversalActivity a;

    bk(PayPWDUniversalActivity payPWDUniversalActivity) {
        this.a = payPWDUniversalActivity;
    }

    public void onClick(View view) {
        this.a.i.setFocusable(true);
        this.a.i.setFocusableInTouchMode(true);
        this.a.i.setVisibility(0);
    }
}
