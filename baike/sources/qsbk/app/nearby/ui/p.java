package qsbk.app.nearby.ui;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class p implements OnTouchListener {
    final /* synthetic */ InfoCompleteActivity a;

    p(InfoCompleteActivity infoCompleteActivity) {
        this.a = infoCompleteActivity;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && !this.a.B()) {
            this.a.hideSoftKeyboard();
            view.setFocusable(false);
            view.setFocusableInTouchMode(false);
            ToastAndDialog.makeNegativeToast(this.a, String.format("请%s天之后再来编辑名字吧。", new Object[]{Integer.valueOf(QsbkApp.currentUser.usrNameEday)})).show();
        }
        return false;
    }
}
