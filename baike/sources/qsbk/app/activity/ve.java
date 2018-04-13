package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import qsbk.app.utils.UIHelper;

class ve implements OnDismissListener {
    final /* synthetic */ MyInfoActivity a;

    ve(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        UIHelper.hideKeyboard(this.a);
    }
}
