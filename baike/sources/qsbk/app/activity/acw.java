package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class acw implements OnClickListener {
    final /* synthetic */ SocialBindActivity a;

    acw(SocialBindActivity socialBindActivity) {
        this.a = socialBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.wx) || TextUtils.getTrimmedLength(QsbkApp.currentUser.wx) <= 0) {
            this.a.k();
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"解绑", "取消"}, new acx(this)).show();
    }
}
