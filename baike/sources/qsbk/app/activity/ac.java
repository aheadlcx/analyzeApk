package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class ac implements OnClickListener {
    final /* synthetic */ ActionBarLoginActivity a;

    ac(ActionBarLoginActivity actionBarLoginActivity) {
        this.a = actionBarLoginActivity;
    }

    public void onClick(View view) {
        ActionBarLoginActivity.a(this.a, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
        ActionBarLoginActivity.a(this.a, ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA));
        ActionBarLoginActivity.a(this.a, new SsoHandler(this.a));
        ActionBarLoginActivity.l(this.a).authorize(new ActionBarLoginActivity$a(this.a));
    }
}
