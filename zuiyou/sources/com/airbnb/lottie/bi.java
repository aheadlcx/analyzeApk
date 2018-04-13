package com.airbnb.lottie;

import android.graphics.Path;
import android.graphics.PointF;
import java.io.Closeable;

final class bi {
    private static PointF a;

    static PointF a() {
        if (a == null) {
            a = new PointF();
        }
        return a;
    }

    static Path a(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4) {
        Path path = new Path();
        path.moveTo(pointF.x, pointF.y);
        if (pointF3 == null || pointF3.length() == 0.0f || pointF4 == null || pointF4.length() == 0.0f) {
            path.lineTo(pointF2.x, pointF2.y);
        } else {
            path.cubicTo(pointF.x + pointF3.x, pointF.y + pointF3.y, pointF2.x + pointF4.x, pointF2.y + pointF4.y, pointF2.x, pointF2.y);
        }
        return path;
    }

    static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
            }
        }
    }
}
