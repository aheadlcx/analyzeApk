package qsbk.app.open.auth;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.ActionBarLoginActivity;

class b implements OnClickListener {
    final /* synthetic */ SSOAuthActivity a;

    b(SSOAuthActivity sSOAuthActivity) {
        this.a = sSOAuthActivity;
    }

    public void onClick(View view) {
        this.a.j();
        this.a.a(new Intent(this.a, ActionBarLoginActivity.class), new c(this));
    }
}
