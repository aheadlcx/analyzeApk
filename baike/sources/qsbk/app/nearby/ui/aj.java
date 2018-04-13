package qsbk.app.nearby.ui;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class aj implements OnClickListener {
    final /* synthetic */ InfoCompleteActivity a;

    aj(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public void onClick(View view) {
        if (this.a.D()) {
            this.a.w();
            return;
        }
        ToastAndDialog.makeNegativeToast(this.a, String.format("请%s天之后再来编辑大罩吧。", new Object[]{Integer.valueOf(QsbkApp.currentUser.bigCoverEday)})).show();
    }
}
