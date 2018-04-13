package qsbk.app.video;

import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class bi implements Runnable {
    final /* synthetic */ bg a;

    bi(bg bgVar) {
        this.a = bgVar;
    }

    public void run() {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "保存失败").show();
    }
}
