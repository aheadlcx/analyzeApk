package qsbk.app.core.widget.refresh;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class b extends Animation {
    final /* synthetic */ a a;
    final /* synthetic */ MaterialProgressDrawable b;

    b(MaterialProgressDrawable materialProgressDrawable, a aVar) {
        this.b = materialProgressDrawable;
        this.a = aVar;
    }

    public void applyTransformation(float f, Transformation transformation) {
        if (this.b.a) {
            this.b.b(f, this.a);
            return;
        }
        float a = this.b.a(this.a);
        float startingEndTrim = this.a.getStartingEndTrim();
        float startingStartTrim = this.a.getStartingStartTrim();
        float startingRotation = this.a.getStartingRotation();
        this.b.a(f, this.a);
        if (f <= 0.5f) {
            float f2 = 0.8f - a;
            this.a.setStartTrim(startingStartTrim + (MaterialProgressDrawable.c.getInterpolation(f / 0.5f) * f2));
        }
        if (f > 0.5f) {
            this.a.setEndTrim(((0.8f - a) * MaterialProgressDrawable.c.getInterpolation((f - 0.5f) / 0.5f)) + startingEndTrim);
        }
        this.a.setRotation((0.25f * f) + startingRotation);
        this.b.a((216.0f * f) + (1080.0f * (this.b.k / 5.0f)));
    }
}
