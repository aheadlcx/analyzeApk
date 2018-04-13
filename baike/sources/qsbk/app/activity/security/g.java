package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class g implements OnClickListener {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            this.a.a.n = ThirdPartyConstants.THIRDPARTY_TYLE_SINA;
            this.a.a.m = ThirdParty.getInstance(ThirdPartyConstants.SINA_CONSUMER_KEY, ThirdPartyConstants.SINA_REDIRECT_URL, ThirdPartyConstants.THIRDPARTY_TYLE_SINA);
            this.a.a.o = new SsoHandler(this.a.a);
            this.a.a.o.authorize(new a(this.a.a));
            this.a.a.x = true;
        }
        dialogInterface.dismiss();
    }
}
