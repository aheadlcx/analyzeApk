package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class dn implements OnClickListener {
    final /* synthetic */ BindPhoneActivity a;

    dn(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
