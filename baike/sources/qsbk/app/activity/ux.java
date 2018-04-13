package qsbk.app.activity;

import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ux implements Runnable {
    final /* synthetic */ MyInfoActivity a;

    ux(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void run() {
        this.a.bJ = true;
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加粉失败，请重试", Integer.valueOf(0)).show();
    }
}
