package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bi implements OnClickListener {
    final /* synthetic */ PayPWDUniversalActivity a;

    bi(PayPWDUniversalActivity payPWDUniversalActivity) {
        this.a = payPWDUniversalActivity;
    }

    public void onClick(View view) {
        this.a.i.setVisibility(8);
    }
}
