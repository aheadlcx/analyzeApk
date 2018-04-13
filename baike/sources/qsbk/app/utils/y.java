package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;

class y implements OnClickListener {
    final /* synthetic */ w a;

    y(w wVar) {
        this.a = wVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        if (this.a.b instanceof ProgressDialogCallBack) {
            ((ProgressDialogCallBack) this.a.b).show();
        }
        GroupActionUtil.deleteMember(this.a.c, this.a.d, this.a.b);
    }
}
