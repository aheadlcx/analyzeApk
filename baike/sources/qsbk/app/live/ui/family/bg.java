package qsbk.app.live.ui.family;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class bg implements OnEmptyClickListener {
    final /* synthetic */ be a;

    bg(be beVar) {
        this.a = beVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.f.hide();
        this.a.a.forceRefresh();
    }
}
