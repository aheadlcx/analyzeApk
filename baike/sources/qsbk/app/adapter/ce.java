package qsbk.app.adapter;

import android.view.View;
import android.view.View.OnClickListener;

class ce implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ ManageMyContentsAdapter b;

    ce(ManageMyContentsAdapter manageMyContentsAdapter, int i) {
        this.b = manageMyContentsAdapter;
        this.a = i;
    }

    public void onClick(View view) {
        this.b.l.performItemClick(this.b.l, this.a, (long) this.a);
    }
}
