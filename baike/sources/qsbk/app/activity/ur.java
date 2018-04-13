package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;

class ur implements OnClickListener {
    final /* synthetic */ MyInfoActivity a;

    ur(MyInfoActivity myInfoActivity) {
        this.a = myInfoActivity;
    }

    public void onClick(View view) {
        if (this.a.cc <= 0) {
            ToastAndDialog.makeNeutralToast(QsbkApp.mContext, this.a.bC ? "你还没有糗事上精选哦！" : "ta还没有糗事上精选哦！", Integer.valueOf(0)).show();
        } else if (this.a.bF != null) {
            MyHighlightQiushiActivity.launch(this.a, this.a.bF);
        } else {
            MyHighlightQiushiActivity.launch(this.a, this.a.b);
        }
    }
}
