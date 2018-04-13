package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;

class dm implements OnClickListener {
    final /* synthetic */ BindPhoneActivity a;

    dm(BindPhoneActivity bindPhoneActivity) {
        this.a = bindPhoneActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
