package qsbk.app.open.auth;

import android.view.View;
import android.view.View.OnClickListener;

class d implements OnClickListener {
    final /* synthetic */ SSOAuthActivity a;

    d(SSOAuthActivity sSOAuthActivity) {
        this.a = sSOAuthActivity;
    }

    public void onClick(View view) {
        if (this.a.g == 3) {
            this.a.i = true;
            this.a.g();
        } else if (this.a.g == 1) {
            this.a.runGetAuthCodeProcess();
            this.a.a(2);
        }
    }
}
