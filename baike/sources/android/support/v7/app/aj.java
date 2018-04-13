package android.support.v7.app;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class aj extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ WindowDecorActionBar a;

    aj(WindowDecorActionBar windowDecorActionBar) {
        this.a = windowDecorActionBar;
    }

    public void onAnimationEnd(View view) {
        if (this.a.k && this.a.f != null) {
            this.a.f.setTranslationY(0.0f);
            this.a.c.setTranslationY(0.0f);
        }
        this.a.c.setVisibility(8);
        this.a.c.setTransitioning(false);
        this.a.n = null;
        this.a.b();
        if (this.a.b != null) {
            ViewCompat.requestApplyInsets(this.a.b);
        }
    }
}
