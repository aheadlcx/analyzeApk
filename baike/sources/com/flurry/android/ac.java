package com.flurry.android;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.TextView;

final class ac implements OnFocusChangeListener {
    private /* synthetic */ TextView a;
    private /* synthetic */ ab b;

    ac(ab abVar, TextView textView) {
        this.b = abVar;
        this.a = textView;
    }

    public final void onFocusChange(View view, boolean z) {
        this.a.setText(z ? this.b.b : this.b.a);
    }
}
