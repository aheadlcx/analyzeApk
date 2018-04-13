package qsbk.app.pay.ui;

import android.view.View;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class an implements OnEmptyClickListener {
    final /* synthetic */ al a;

    an(al alVar) {
        this.a = alVar;
    }

    public void onEmptyClick(View view) {
        this.a.a.e.hide();
        this.a.a.b.setVisibility(0);
        this.a.a.a();
    }
}
