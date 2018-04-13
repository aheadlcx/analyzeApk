package qsbk.app.live.widget;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class u implements OnEmptyClickListener {
    final /* synthetic */ s a;

    u(s sVar) {
        this.a = sVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.g.showProgressBar();
        this.a.a.j();
    }
}
