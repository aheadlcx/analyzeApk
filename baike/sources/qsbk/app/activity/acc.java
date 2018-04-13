package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.activity.security.EmailBindActivity;

class acc implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    acc(SecurityBindActivity securityBindActivity) {
        this.a = securityBindActivity;
    }

    public void onClick(View view) {
        if (this.a.c(QsbkApp.currentUser.email)) {
            this.a.b(EmailBindActivity.ACTION_BIND);
        } else {
            new Builder(this.a).setTitle("操作").setItems(this.a.l() ? new String[]{"去验证", "换一个", "取消"} : new String[]{"换一个", "取消"}, new acd(this)).show();
        }
    }
}
