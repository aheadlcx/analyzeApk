package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class zi implements OnClickListener {
    final /* synthetic */ PayPasswordModifyActivity a;

    zi(PayPasswordModifyActivity payPasswordModifyActivity) {
        this.a = payPasswordModifyActivity;
    }

    public void onClick(View view) {
        PayPwdResetActivity.launch(this.a);
    }
}
