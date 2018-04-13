package qsbk.app.activity;

import android.view.ViewTreeObserver.OnPreDrawListener;

class fa implements OnPreDrawListener {
    final /* synthetic */ h a;

    fa(h hVar) {
        this.a = hVar;
    }

    public boolean onPreDraw() {
        this.a.a.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.a.postDelayed(new fb(this), 500);
        return true;
    }
}
