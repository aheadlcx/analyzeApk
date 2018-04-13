package qsbk.app.fragments;

import android.view.View;
import qsbk.app.widget.NoUnderlineClickableSpan;

class dx extends NoUnderlineClickableSpan {
    final /* synthetic */ LaiseeSendFragment a;

    dx(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void onClick(View view) {
        this.a.r = 1;
        this.a.g();
    }
}
