package qsbk.app.im;

import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.image.issue.Logger;

class hk implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ IMMessageListFragment b;

    hk(IMMessageListFragment iMMessageListFragment, int i) {
        this.b = iMMessageListFragment;
        this.a = i;
    }

    public void run() {
        try {
            if (this.a == 5) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "用户名帐号验证失败，请重新登录", Integer.valueOf(0)).show();
                this.b.s.destroy(false);
                return;
            }
            Logger.getInstance().debug(IMMessageListFragment.d, "onConnectStatusChange", String.format("status change to[%s:%s]", new Object[]{Integer.valueOf(this.a), IChatMsgListener.connectString[this.a]}));
            this.b.b(this.a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
