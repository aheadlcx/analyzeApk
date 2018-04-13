package qsbk.app.core.widget;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.widget.EmptyPlaceholderView.OnEmptyClickListener;

class d implements OnClickListener {
    final /* synthetic */ OnEmptyClickListener a;
    final /* synthetic */ EmptyPlaceholderView b;

    d(EmptyPlaceholderView emptyPlaceholderView, OnEmptyClickListener onEmptyClickListener) {
        this.b = emptyPlaceholderView;
        this.a = onEmptyClickListener;
    }

    public void onClick(View view) {
        this.a.onEmptyClick(view);
    }
}
