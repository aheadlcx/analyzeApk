package qsbk.app.live.ui.fragment;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class f implements OnEmptyClickListener {
    final /* synthetic */ d a;

    f(d dVar) {
        this.a = dVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.d.hide();
        this.a.a.a();
    }
}
