package qsbk.app.utils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.QsbkApp;

class bm implements OnClickListener {
    final /* synthetic */ UserLogoutHelper a;

    bm(UserLogoutHelper userLogoutHelper) {
        this.a = userLogoutHelper;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        LogUtil.d("登录成功后退出登录，删除当前用户信息");
        this.a.doLogout();
        ToastAndDialog.makePositiveToast(QsbkApp.mContext, "您已退出登录", Integer.valueOf(0)).show();
        if (this.a.b != null) {
            this.a.b.onClick(dialogInterface, i);
        }
    }
}
