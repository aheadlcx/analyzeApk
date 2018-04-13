package android.support.transition;

import android.graphics.Path;

final class bf extends PathMotion {
    bf() {
    }

    public Path getPath(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        return path;
    }
}
