package qsbk.app.adapter;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

class at extends Animation {
    TextView a = this.b.a.k;
    final /* synthetic */ as b;

    at(as asVar) {
        this.b = asVar;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.a.setHeight((int) (((float) this.b.c) + (((float) (this.b.d - this.b.c)) * f)));
    }
}
