package com.bumptech.glide.g.a;

import android.view.View;
import android.view.animation.Animation;

public class f<R> implements c<R> {
    private final a a;

    interface a {
        Animation a();
    }

    f(a aVar) {
        this.a = aVar;
    }

    public boolean a(R r, c$a c_a) {
        View a = c_a.a();
        if (a != null) {
            a.clearAnimation();
            a.startAnimation(this.a.a());
        }
        return false;
    }
}
