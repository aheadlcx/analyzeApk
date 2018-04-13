package qsbk.app.activity;

import android.os.Build.VERSION;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class age implements OnGlobalLayoutListener {
    final /* synthetic */ agd a;

    age(agd agd) {
        this.a = agd;
    }

    public void onGlobalLayout() {
        if (VERSION.SDK_INT >= 16) {
            this.a.a.c.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            this.a.a.c.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
        this.a.a.c.post(new agf(this));
    }
}
