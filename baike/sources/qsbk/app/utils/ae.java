package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;

class ae implements OnClickListener {
    final /* synthetic */ ac a;

    ae(ac acVar) {
        this.a = acVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        if (this.a.b instanceof ProgressDialogCallBack) {
            ((ProgressDialogCallBack) this.a.b).show();
        }
        GroupActionUtil.firedAdmin(this.a.c, this.a.d, this.a.b);
    }
}
