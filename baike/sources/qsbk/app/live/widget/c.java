package qsbk.app.live.widget;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class c implements OnEmptyClickListener {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.loadData();
    }
}
