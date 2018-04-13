package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.thirdparty.ThirdPartyConstants;

class ab implements OnClickListener {
    final /* synthetic */ ActionBarLoginActivity a;

    ab(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onClick(View view) {
        ActionBarLoginActivity.a(this.a, true);
        ActionBarLoginActivity.c(this.a, true);
        ActionBarLoginActivity.a(this.a, ThirdPartyConstants.THIRDPARTY_TYLE_WX);
        ActionBarLoginActivity.k(this.a).startAuth(new ActionBarLoginActivity$c(this.a));
    }
}
