package qsbk.app.share;

import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class s extends Handler {
    final /* synthetic */ ShareUtils a;

    s(ShareUtils shareUtils) {
        this.a = shareUtils;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已请求至  " + ((String) message.obj), Integer.valueOf(1)).show();
                return;
            case 499:
            case 999:
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(1)).show();
                return;
            case 500:
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, (String) message.obj, Integer.valueOf(1)).show();
                return;
            default:
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "分享失败，请稍后再试下", Integer.valueOf(1)).show();
                return;
        }
    }
}
