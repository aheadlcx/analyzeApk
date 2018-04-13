package in.srain.cube.views.ptr.header;

import android.view.animation.Animation;
import android.view.animation.Transformation;

class a extends Animation {
    final /* synthetic */ MaterialHeader a;

    a(MaterialHeader materialHeader) {
        this.a = materialHeader;
    }

    public void applyTransformation(float f, Transformation transformation) {
        this.a.b = 1.0f - f;
        this.a.a.setAlpha((int) (255.0f * this.a.b));
        this.a.invalidate();
    }
}
