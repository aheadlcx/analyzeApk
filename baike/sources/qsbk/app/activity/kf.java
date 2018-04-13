package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class kf implements OnClickListener {
    final /* synthetic */ EditInfoEntranceActivity a;

    kf(EditInfoEntranceActivity editInfoEntranceActivity) {
        this.a = editInfoEntranceActivity;
    }

    public void onClick(View view) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "系统暂不提供变性服务，请谅解").show();
    }
}
