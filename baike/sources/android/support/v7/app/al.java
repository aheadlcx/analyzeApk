package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorUpdateListener;
import android.view.View;

class al implements ViewPropertyAnimatorUpdateListener {
    final /* synthetic */ WindowDecorActionBar a;

    al(WindowDecorActionBar windowDecorActionBar) {
        this.a = windowDecorActionBar;
    }

    public void onAnimationUpdate(View view) {
        ((View) this.a.c.getParent()).invalidate();
    }
}
