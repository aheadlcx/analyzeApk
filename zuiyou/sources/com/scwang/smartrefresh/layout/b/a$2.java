package com.scwang.smartrefresh.layout.b;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.scwang.smartrefresh.layout.a.h;

class a$2 extends AnimatorListenerAdapter {
    final /* synthetic */ h a;
    final /* synthetic */ a b;

    a$2(a aVar, h hVar) {
        this.b = aVar;
        this.a = hVar;
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        a.b(this.b).setVisibility(4);
        a.c(this.b).animate().scaleX(1.0f);
        a.c(this.b).animate().scaleY(1.0f);
        this.a.getLayout().postDelayed(new Runnable(this) {
            final /* synthetic */ a$2 a;

            {
                this.a = r1;
            }

            public void run() {
                a.c(this.a.b).a();
            }
        }, 200);
    }
}
