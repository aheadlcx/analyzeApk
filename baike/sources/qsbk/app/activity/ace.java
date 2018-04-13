package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class ace implements OnClickListener {
    final /* synthetic */ SecurityBindActivity a;

    ace(SecurityBindActivity securityBindActivity) {
        this.a = securityBindActivity;
    }

    public void onClick(View view) {
        if (this.a.c(QsbkApp.currentUser.phone)) {
            this.a.k();
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"换一个", "取消"}, new acf(this)).show();
    }
}
