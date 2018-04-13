package android.support.transition;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

final class i extends Property<View, PointF> {
    i(Class cls, String str) {
        super(cls, str);
    }

    public void set(View view, PointF pointF) {
        bz.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
    }

    public PointF get(View view) {
        return null;
    }
}
