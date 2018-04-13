package qsbk.app.adapter;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

class do extends Animation {
    TextView a = this.b.a.l;
    final /* synthetic */ dn b;

    do(dn dnVar) {
        this.b = dnVar;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.a.setHeight((int) (((float) this.b.c) + (((float) (this.b.d - this.b.c)) * f)));
    }
}
