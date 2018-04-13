package qsbk.app.activity;

import android.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;

class ada implements OnClickListener {
    final /* synthetic */ SocialBindActivity a;

    ada(SocialBindActivity socialBindActivity) {
        this.a = socialBindActivity;
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(QsbkApp.currentUser.qq)) {
            this.a.l();
            return;
        }
        new Builder(this.a).setTitle("操作").setItems(new String[]{"解绑", "取消"}, new adb(this)).show();
    }
}
