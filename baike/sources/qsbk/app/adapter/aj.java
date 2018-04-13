package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnLongClickListener;

class aj implements OnLongClickListener {
    final /* synthetic */ View a;
    final /* synthetic */ int b;
    final /* synthetic */ CircleCommentAdapter c;

    aj(CircleCommentAdapter circleCommentAdapter, View view, int i) {
        this.c = circleCommentAdapter;
        this.a = view;
        this.b = i;
    }

    public boolean onLongClick(View view) {
        this.c.l.getOnItemLongClickListener().onItemLongClick(this.c.l, this.a, this.b + this.c.l.getHeaderViewsCount(), (long) (this.b + this.c.l.getHeaderViewsCount()));
        return true;
    }
}
