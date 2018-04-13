package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class z extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ b a;

    z(b bVar) {
        this.a = bVar;
    }

    public void onAnimationEnd(View view) {
        this.a.a.n.setVisibility(8);
        if (this.a.a.o != null) {
            this.a.a.o.dismiss();
        } else if (this.a.a.n.getParent() instanceof View) {
            ViewCompat.requestApplyInsets((View) this.a.a.n.getParent());
        }
        this.a.a.n.removeAllViews();
        this.a.a.q.setListener(null);
        this.a.a.q = null;
    }
}
