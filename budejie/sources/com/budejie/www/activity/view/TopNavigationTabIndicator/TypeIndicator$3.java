package com.budejie.www.activity.view.TopNavigationTabIndicator;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.budejie.www.activity.video.a;

class TypeIndicator$3 implements OnGlobalLayoutListener {
    final /* synthetic */ View a;
    final /* synthetic */ TypeIndicator b;

    TypeIndicator$3(TypeIndicator typeIndicator, View view) {
        this.b = typeIndicator;
        this.a = view;
    }

    public void onGlobalLayout() {
        TypeIndicator.a(this.b, new Runnable(this) {
            final /* synthetic */ TypeIndicator$3 a;

            {
                this.a = r1;
            }

            public void run() {
                int left = this.a.a.getLeft() - ((a.a((Activity) TypeIndicator.c(this.a.b)) - this.a.a.getWidth()) / 2);
                Log.i("tangjian", "scrollPos   " + left);
                TypeIndicator.d(this.a.b).smoothScrollTo(left, 0);
                TypeIndicator.a(this.a.b, null);
            }
        });
        this.b.post(TypeIndicator.e(this.b));
        TypeIndicator.d(this.b).getViewTreeObserver().removeGlobalOnLayoutListener(this);
    }
}
