package cn.v6.sixrooms.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class RotationYAnimation extends Animation {
    int a;
    int b;
    Camera c = new Camera();

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.a = i / 2;
        this.b = i2 / 2;
        setInterpolator(new LinearInterpolator());
    }

    protected void applyTransformation(float f, Transformation transformation) {
        Matrix matrix = transformation.getMatrix();
        this.c.save();
        this.c.rotateY(360.0f * f);
        this.c.getMatrix(matrix);
        matrix.preTranslate((float) (-this.a), (float) (-this.b));
        matrix.postTranslate((float) this.a, (float) this.b);
        this.c.restore();
    }
}
