package qsbk.app.nearby.api;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Rotate3dAnimation2 extends Animation {
    public static final String ROTATE_X = "x";
    public static final String ROTATE_Y = "y";
    private final float a;
    private final float b;
    private final float c;
    private final float d;
    private final float e;
    private final boolean f;
    private final String g;
    private Camera h;

    public Rotate3dAnimation2(float f, float f2, float f3, float f4, float f5, boolean z, String str) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        this.e = f5;
        this.f = z;
        this.g = str;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.h = new Camera();
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.a;
        f2 += (this.b - f2) * f;
        float f3 = this.c;
        float f4 = this.d;
        Camera camera = this.h;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.f) {
            camera.translate(0.0f, 0.0f, this.e * f);
        } else {
            camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
        }
        if ("x".equalsIgnoreCase(this.g)) {
            camera.rotateX(f2);
        } else {
            camera.rotateY(f2);
        }
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f3, -f4);
        matrix.postTranslate(f3, f4);
        matrix.preScale(a(f), 1.0f, f3, f4);
    }

    private float a(float f) {
        if (f > 0.75f) {
            return (-0.099999994f * f) + 0.5f;
        }
        return 1.0f - (0.5f * f);
    }
}
