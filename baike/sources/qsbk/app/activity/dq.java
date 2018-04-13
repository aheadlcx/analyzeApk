package qsbk.app.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;

class dq implements OnClickListener {
    final /* synthetic */ BindPhoneActivity a;

    dq(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(this.a.n)) {
            this.a.j();
        } else {
            this.a.i();
        }
    }
}
