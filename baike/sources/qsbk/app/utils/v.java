package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.utils.GroupActionUtil.ProgressDialogCallBack;

class v implements OnClickListener {
    final /* synthetic */ s a;

    v(s sVar) {
        this.a = sVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        if (this.a.c instanceof ProgressDialogCallBack) {
            ((ProgressDialogCallBack) this.a.c).show();
        }
        GroupActionUtil.muteMember(this.a.d, this.a.e, GroupActionUtil.a[this.a.b], this.a.c);
    }
}
