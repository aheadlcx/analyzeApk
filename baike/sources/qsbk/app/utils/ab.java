package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;

class ab implements OnClickListener {
    final /* synthetic */ z a;

    ab(z zVar) {
        this.a = zVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        if (this.a.b instanceof ProgressDialogCallBack) {
            ((ProgressDialogCallBack) this.a.b).show();
        }
        GroupActionUtil.appointAdmin(this.a.c, this.a.d, this.a.b);
    }
}
