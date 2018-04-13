package qsbk.app.guide.dialog;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.activity.WeiboLoginActivity;
import qsbk.app.utils.DebugUtil;

class d implements OnClickListener {
    final /* synthetic */ LoginGuideDialog a;

    d(LoginGuideDialog loginGuideDialog) {
        this.a = loginGuideDialog;
    }

    public void onClick(View view) {
        DebugUtil.debug(LoginGuideDialog.c, "weiboLogin ");
        LoginGuideDialog.b("login_guide_dialog", "login_guide_dialog_weibo");
        LoginGuideDialog.d.startActivity(new Intent(LoginGuideDialog.d, WeiboLoginActivity.class));
        this.a.dismiss();
    }
}
