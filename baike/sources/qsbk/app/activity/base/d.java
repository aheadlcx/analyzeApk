package qsbk.app.activity.base;

import android.text.TextUtils;
import qsbk.app.utils.ListViewHelper;

class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ c b;

    d(c cVar, String str) {
        this.b = cVar;
        this.a = str;
    }

    public void run() {
        if (TextUtils.isEmpty(this.a)) {
            this.b.a.l.refresh();
        } else {
            this.b.a.a(this.a);
            if (this.b.a.x()) {
                ListViewHelper.onRestoreListViewSelection(this.b.a.B, this.b.a.v, this.b.a.j, this.b.a.m);
            }
            this.b.a.y = false;
        }
        this.b.a.r = true;
    }
}
