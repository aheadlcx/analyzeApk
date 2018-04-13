package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class acz implements OnClickListener {
    final /* synthetic */ acy a;

    acz(acy acy) {
        this.a = acy;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == 0) {
            Object obj = null;
            if (!QsbkApp.currentUser.hasPwd()) {
                obj = "请先绑定手机或邮箱，再解绑";
            } else if (!(QsbkApp.currentUser.hasPhone() || QsbkApp.currentUser.isBindTwoOfQQWXWB())) {
                obj = "请先绑定手机或者其他社交账号";
            }
            if (TextUtils.isEmpty(obj)) {
                this.a.a.m();
            } else {
                ToastAndDialog.makeNegativeToast(this.a.a, obj).show();
                return;
            }
        }
        dialogInterface.dismiss();
    }
}
