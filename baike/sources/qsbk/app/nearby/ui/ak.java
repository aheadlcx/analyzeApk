package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ak implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    ak(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(View view) {
        if (this.a.C()) {
            this.a.q();
            return;
        }
        ToastAndDialog.makeNegativeToast(this.a, String.format("请%s天之后再来编辑头像吧。", new Object[]{Integer.valueOf(QsbkApp.currentUser.usrIconEday)})).show();
    }
}
