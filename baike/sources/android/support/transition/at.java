package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.util.Property;

class at {
    private static final aw a;

    static {
        if (VERSION.SDK_INT >= 21) {
            a = new av();
        } else {
            a = new au();
        }
    }

    static PropertyValuesHolder a(Property<?, PointF> property, Path path) {
        return a.ofPointF(property, path);
    }
}
