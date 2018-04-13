package android.support.v7.app;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class ak extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar a;

    ak(WindowDecorActionBar windowDecorActionBar) {
        this.a = windowDecorActionBar;
    }

    public void onAnimationEnd(View view) {
        this.a.n = null;
        this.a.c.requestLayout();
    }
}
