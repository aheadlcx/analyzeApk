package qsbk.app.guide.dialog;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.thirdparty.ThirdParty;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;

class b implements OnClickListener {
    final /* synthetic */ LoginGuideDialog a;

    b(LoginGuideDialog loginGuideDialog) {
        this.a = loginGuideDialog;
    }

    public void onClick(View view) {
        DebugUtil.debug(LoginGuideDialog.c, "qqLogin ");
        LoginGuideDialog.b("login_guide_dialog", "login_guide_dialog_qq");
        LoginGuideDialog.f = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
        LoginGuideDialog.n = new a(this.a);
        LoginGuideDialog.m = ThirdParty.getTencentInstance(ThirdPartyConstants.QQ_CONSUMER_KEY, LoginGuideDialog.d.getApplicationContext());
        LoginGuideDialog.m.login((Activity) LoginGuideDialog.d, "all", LoginGuideDialog.n);
    }
}
