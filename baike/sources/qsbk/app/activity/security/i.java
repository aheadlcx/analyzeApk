package qsbk.app.activity.security;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;

class i implements OnClickListener {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            this.a.a.n = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
            this.a.a.q = new b();
            this.a.a.p = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, this.a.a.getApplicationContext());
            this.a.a.p.login(this.a.a, "get_user_info,get_simple_userinfo", this.a.a.q);
            this.a.a.w = true;
        }
        dialogInterface.dismiss();
    }
}
