package qsbk.app.activity;

import android.os.Handler;
import android.os.Message;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ke extends Handler {
    final /* synthetic */ EditInfoEntranceActivity a;

    ke(EditInfoEntranceActivity editInfoEntranceActivity) {
        this.a = editInfoEntranceActivity;
    }

    public void handleMessage(Message message) {
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, (String) message.obj).show();
    }
}
