package android.support.transition;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

final class j extends Property<View, PointF> {
    j(Class cls, String str) {
        super(cls, str);
    }

    public void set(View view, PointF pointF) {
        bz.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
    }

    public PointF get(View view) {
        return null;
    }
}
