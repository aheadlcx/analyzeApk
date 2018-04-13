package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class y extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ AppCompatDelegateImplV9 a;

    y(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.a = appCompatDelegateImplV9;
    }

    public void onAnimationStart(View view) {
        this.a.n.setVisibility(0);
        this.a.n.sendAccessibilityEvent(32);
        if (this.a.n.getParent() instanceof View) {
            ViewCompat.requestApplyInsets((View) this.a.n.getParent());
        }
    }

    public void onAnimationEnd(View view) {
        this.a.n.setAlpha(1.0f);
        this.a.q.setListener(null);
        this.a.q = null;
    }
}
