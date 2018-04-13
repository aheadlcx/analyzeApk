package qsbk.app.activity.pay;

import android.view.View;
import android.view.View.OnClickListener;

class bg implements OnClickListener {
    final /* synthetic */ PayPWDUniversalActivity a;

    bg(PayPWDUniversalActivity payPWDUniversalActivity) {
        this.a = payPWDUniversalActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
