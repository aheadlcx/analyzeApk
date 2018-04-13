package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class uo implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    uo(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.cd <= 0) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, this.a.bC ? "你还没有热评哦！" : "ta还没有热评哦！", Integer.valueOf(0)).show();
        } else if (this.a.bF == null) {
            HotCommentPagerActivity.launch(this.a, this.a.b);
        } else {
            HotCommentPagerActivity.launch(this.a, this.a.bF);
        }
    }
}
