package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class e extends Animation {
    final /* synthetic */ c a;
    final /* synthetic */ MaterialProgressDrawable b;

    e(MaterialProgressDrawable materialProgressDrawable, c cVar) {
        this.b = materialProgressDrawable;
        this.a = cVar;
    }

    public void applyTransformation(float f, Transformation transformation) {
        float floor = (float) (Math.floor((double) (this.a.getStartingRotation() / 0.8f)) + 1.0d);
        this.a.setStartTrim(this.a.getStartingStartTrim() + ((this.a.getStartingEndTrim() - this.a.getStartingStartTrim()) * f));
        this.a.setRotation(((floor - this.a.getStartingRotation()) * f) + this.a.getStartingRotation());
        this.a.setArrowScale(1.0f - f);
    }
}
