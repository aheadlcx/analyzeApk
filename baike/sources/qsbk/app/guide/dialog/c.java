package qsbk.app.guide.dialog;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.wxapi.WXAuthHelper;

class c implements OnClickListener {
    final /* synthetic */ LoginGuideDialog a;

    c(LoginGuideDialog loginGuideDialog) {
        this.a = loginGuideDialog;
    }

    public void onClick(View view) {
        DebugUtil.debug(LoginGuideDialog.c, "weixinLogin ");
        LoginGuideDialog.b("login_guide_dialog", "login_guide_dialog_wx");
        LoginGuideDialog.f = ThirdPartyConstants.THIRDPARTY_TYLE_WX;
        LoginGuideDialog.l = WXAuthHelper.getInstance(view.getContext());
        LoginGuideDialog.l.startAuth(new b(this.a));
    }
}
