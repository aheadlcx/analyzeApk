package qsbk.app.fragments;

import android.view.View;
import qsbk.app.widget.NoUnderlineClickableSpan;

class dw extends NoUnderlineClickableSpan {
    final /* synthetic */ LaiseeSendFragment a;

    dw(LaiseeSendFragment laiseeSendFragment) {
        this.a = laiseeSendFragment;
    }

    public void onClick(View view) {
        this.a.r = 2;
        this.a.g();
    }
}
