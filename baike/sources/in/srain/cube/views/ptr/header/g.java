package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class g extends Animation {
    final /* synthetic */ c a;
    final /* synthetic */ MaterialProgressDrawable b;

    g(MaterialProgressDrawable materialProgressDrawable, c cVar) {
        this.b = materialProgressDrawable;
        this.a = cVar;
    }

    public void applyTransformation(float f, Transformation transformation) {
        float toRadians = (float) Math.toRadians(((double) this.a.getStrokeWidth()) / (6.283185307179586d * this.a.getCenterRadius()));
        float startingEndTrim = this.a.getStartingEndTrim();
        float startingStartTrim = this.a.getStartingStartTrim();
        float startingRotation = this.a.getStartingRotation();
        this.a.setEndTrim(((0.8f - toRadians) * MaterialProgressDrawable.c.getInterpolation(f)) + startingEndTrim);
        this.a.setStartTrim((MaterialProgressDrawable.b.getInterpolation(f) * 0.8f) + startingStartTrim);
        this.a.setRotation((0.25f * f) + startingRotation);
        this.b.a((144.0f * f) + (720.0f * (this.b.m / 5.0f)));
    }
}
