package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class bw implements OnClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ ManageMyContentsAdapter c;

    bw(ManageMyContentsAdapter manageMyContentsAdapter, View view, int i) {
        this.c = manageMyContentsAdapter;
        this.a = view;
        this.b = i;
    }

    public void onClick(View view) {
        if (this.c.f != null) {
            this.c.l.getOnItemLongClickListener().onItemLongClick(this.c.l, this.a, this.b + this.c.l.getHeaderViewsCount(), (long) (this.b + this.c.l.getHeaderViewsCount()));
        }
    }
}
