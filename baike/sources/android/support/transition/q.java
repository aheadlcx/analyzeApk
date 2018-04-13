package android.support.transition;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

final class q extends Property<ImageView, Matrix> {
    q(Class cls, String str) {
        super(cls, str);
    }

    public void set(ImageView imageView, Matrix matrix) {
        ah.a(imageView, matrix);
    }

    public Matrix get(ImageView imageView) {
        return null;
    }
}
