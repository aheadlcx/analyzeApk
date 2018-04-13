package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class bu implements OnFocusChangeListener {
    final /* synthetic */ RegisterActivity a;

    bu(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            RegisterActivity.c(this.a, this.a.c());
        } else {
            RegisterActivity.c(this.a, "");
        }
    }
}
