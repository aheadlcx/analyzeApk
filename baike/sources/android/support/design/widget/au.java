package android.support.design.widget;

import android.view.View;
import android.view.View.OnClickListener;

class au implements OnClickListener {
    final /* synthetic */ OnClickListener a;
    final /* synthetic */ Snackbar b;

    au(Snackbar snackbar, OnClickListener onClickListener) {
        this.b = snackbar;
        this.a = onClickListener;
    }

    public void onClick(View view) {
        this.a.onClick(view);
        this.b.a(1);
    }
}
