package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class aa implements OnClickListener {
    final /* synthetic */ ActionBarLoginActivity a;

    aa(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onClick(View view) {
        ActionBarLoginActivity.a(this.a, ThirdPartyConstants.THIRDPARTY_TYLE_QQ);
        ActionBarLoginActivity.a(this.a, new ActionBarLoginActivity$b(this.a));
        ActionBarLoginActivity.a(this.a, ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.getApplicationContext()));
        ActionBarLoginActivity.j(this.a).login(this.a, "all", ActionBarLoginActivity.i(this.a));
    }
}
