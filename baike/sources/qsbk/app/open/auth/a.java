package qsbk.app.open.auth;

import android.view.View;
import android.view.View.OnClickListener;

class a implements OnClickListener {
    final /* synthetic */ SSOAuthActivity a;

    a(SSOAuthActivity sSOAuthActivity) {
        this.a = sSOAuthActivity;
    }

    public void onClick(View view) {
        this.a.setResult(0);
        this.a.finish();
    }
}
