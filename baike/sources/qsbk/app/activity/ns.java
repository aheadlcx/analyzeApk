package qsbk.app.activity;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.model.GroupNotice;

class ns implements OnClickListener {
    final /* synthetic */ GroupNotice a;
    final /* synthetic */ a b;

    ns(a aVar, GroupNotice groupNotice) {
        this.b = aVar;
        this.a = groupNotice;
    }

    public void onClick(View view) {
        if (this.a.gid > 0) {
            this.b.a.b(this.a.gid);
        }
    }
}
