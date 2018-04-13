package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class acy implements OnClickListener {
    final /* synthetic */ SocialBindActivity a;

    acy(SocialBindActivity socialBindActivity) {
        this.a = socialBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.wb)) {
            this.a.j();
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"解绑", "取消"}, new acz(this)).show();
    }
}
