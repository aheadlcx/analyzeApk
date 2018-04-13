package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class x extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ w a;

    x(w wVar) {
        this.a = wVar;
    }

    public void onAnimationStart(View view) {
        this.a.a.n.setVisibility(0);
    }

    public void onAnimationEnd(View view) {
        this.a.a.n.setAlpha(1.0f);
        this.a.a.q.setListener(null);
        this.a.a.q = null;
    }
}
