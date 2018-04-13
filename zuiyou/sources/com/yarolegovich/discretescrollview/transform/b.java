package com.yarolegovich.discretescrollview.transform;

import android.support.annotation.FloatRange;
import android.view.View;
import com.yarolegovich.discretescrollview.transform.Pivot.X;
import com.yarolegovich.discretescrollview.transform.Pivot.Y;

public class b implements a {
    private Pivot a = X.CENTER.create();
    private Pivot b = Y.CENTER.create();
    private float c = 0.8f;
    private float d = 0.2f;

    public static class a {
        private b a = new b();
        private float b = 1.0f;

        public a a(@FloatRange(from = 0.01d) float f) {
            this.a.c = f;
            return this;
        }

        public b a() {
            this.a.d = this.b - this.a.c;
            return this.a;
        }
    }

    public void a(View view, float f) {
        this.a.a(view);
        this.b.a(view);
        float abs = 1.0f - Math.abs(f);
        abs = (abs * this.d) + this.c;
        view.setScaleX(abs);
        view.setScaleY(abs);
    }
}
