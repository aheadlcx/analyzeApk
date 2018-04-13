package cn.v6.sixrooms.ui.phone;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class bs implements OnFocusChangeListener {
    final /* synthetic */ RegisterActivity a;

    bs(RegisterActivity registerActivity) {
        this.a = registerActivity;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            this.a.b(this.a.b());
            return;
        }
        this.a.b((CharSequence) "");
        this.a.f.perRegister(false, this.a.b(), null, null, null);
    }
}
