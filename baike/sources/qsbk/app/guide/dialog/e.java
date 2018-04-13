package qsbk.app.guide.dialog;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.ActionBarLoginActivity;
import qsbk.app.utils.DebugUtil;

class e implements OnClickListener {
    final /* synthetic */ LoginGuideDialog a;

    e(LoginGuideDialog loginGuideDialog) {
        this.a = loginGuideDialog;
    }

    public void onClick(View view) {
        DebugUtil.debug(LoginGuideDialog.c, "qiubaiLogin ");
        this.a.dismiss();
        LoginGuideDialog.b("login_guide_dialog", "login_guide_dialog_qb");
        if (QsbkApp.currentUser == null) {
            LoginGuideDialog.d.startActivity(new Intent(LoginGuideDialog.d, ActionBarLoginActivity.class));
        }
    }
}
