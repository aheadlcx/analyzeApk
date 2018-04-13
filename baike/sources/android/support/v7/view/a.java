package android.support.v7.view;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;

class a extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ ViewPropertyAnimatorCompatSet a;
    private boolean b = false;
    private int c = 0;

    a(ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet) {
        this.a = viewPropertyAnimatorCompatSet;
    }

    public void onAnimationStart(View view) {
        if (!this.b) {
            this.b = true;
            if (this.a.b != null) {
                this.a.b.onAnimationStart(null);
            }
        }
    }

    void a() {
        this.c = 0;
        this.b = false;
        this.a.a();
    }

    public void onAnimationEnd(View view) {
        int i = this.c + 1;
        this.c = i;
        if (i == this.a.a.size()) {
            if (this.a.b != null) {
                this.a.b.onAnimationEnd(null);
            }
            a();
        }
    }
}
