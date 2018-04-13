package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class bq implements OnFocusChangeListener {
    final /* synthetic */ RegisterActivity a;

    bq(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.a(this.a.d());
        } else {
            this.a.a((CharSequence) "");
        }
    }
}
