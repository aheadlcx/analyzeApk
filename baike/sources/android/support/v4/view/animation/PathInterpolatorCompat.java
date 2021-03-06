package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public final class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        if (VERSION.SDK_INT >= 21) {
            return new PathInterpolator(path);
        }
        return new b(path);
    }

    public static Interpolator create(float f, float f2) {
        if (VERSION.SDK_INT >= 21) {
            return new PathInterpolator(f, f2);
        }
        return new b(f, f2);
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        if (VERSION.SDK_INT >= 21) {
            return new PathInterpolator(f, f2, f3, f4);
        }
        return new b(f, f2, f3, f4);
    }
}
