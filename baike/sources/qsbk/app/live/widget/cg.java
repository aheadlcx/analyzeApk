package qsbk.app.live.widget;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class cg implements OnEmptyClickListener {
    final /* synthetic */ ce a;

    cg(ce ceVar) {
        this.a = ceVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.f.showProgressBar();
        this.a.a.j();
    }
}
