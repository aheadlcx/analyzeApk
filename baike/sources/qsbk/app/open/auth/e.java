package qsbk.app.open.auth;

import android.content.Intent;
import qsbk.app.utils.ResultActivityListener;

class e implements ResultActivityListener {
    final /* synthetic */ SSOAuthActivity a;

    e(SSOAuthActivity sSOAuthActivity) {
        this.a = sSOAuthActivity;
    }

    public void onResult(int i, int i2, Intent intent) {
        if (i2 == 0) {
            this.a.setResult(0);
            this.a.finish();
            return;
        }
        this.a.checkLoginUser();
    }
}
