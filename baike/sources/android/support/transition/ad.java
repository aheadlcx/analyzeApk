package android.support.transition;

import android.support.v4.view.ViewCompat;
import android.view.ViewTreeObserver.OnPreDrawListener;

class ad implements OnPreDrawListener {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public boolean onPreDraw() {
        this.a.e = this.a.a.getMatrix();
        ViewCompat.postInvalidateOnAnimation(this.a);
        if (!(this.a.b == null || this.a.c == null)) {
            this.a.b.endViewTransition(this.a.c);
            ViewCompat.postInvalidateOnAnimation(this.a.b);
            this.a.b = null;
            this.a.c = null;
        }
        return true;
    }
}
