package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class do implements OnClickListener {
    final /* synthetic */ BindPhoneActivity a;

    do(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onClick(View view) {
        this.a.a.startCountDown(60000, 1000);
        this.a.a.setEnabled(false);
        this.a.r();
    }
}
