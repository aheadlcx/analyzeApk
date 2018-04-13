package qsbk.app.activity;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextView;

class ha extends Animation {
    TextView a = this.b.a.n;
    final /* synthetic */ gz b;

    ha(gz gzVar) {
        this.b = gzVar;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        this.a.setHeight((int) (((float) this.b.e) + (((float) (this.b.f - this.b.e)) * f)));
    }
}
