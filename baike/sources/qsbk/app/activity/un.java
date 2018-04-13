package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class un implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    un(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        ToastAndDialog.makeNeutralToast(QsbkApp.mContext, "笑脸数字表示糗事获得的笑脸总数", Integer.valueOf(0)).show();
    }
}
