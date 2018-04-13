package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class aas implements OnClickListener {
    final /* synthetic */ ReBindPhoneActivity a;

    aas(ReBindPhoneActivity reBindPhoneActivity) {
        this.a = reBindPhoneActivity;
    }

    public void onClick(View view) {
        this.a.getCode();
        this.a.b.startCountDown(this.a.j, this.a.k);
    }
}
