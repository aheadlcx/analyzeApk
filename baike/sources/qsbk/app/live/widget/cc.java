package qsbk.app.live.widget;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class cc implements OnEmptyClickListener {
    final /* synthetic */ bz a;

    cc(bz bzVar) {
        this.a = bzVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.g.showProgressBar();
        this.a.a.l();
    }
}
