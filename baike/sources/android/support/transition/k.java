package android.support.transition;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

final class k extends Property<View, PointF> {
    k(Class cls, String str) {
        super(cls, str);
    }

    public void set(View view, PointF pointF) {
        int round = Math.round(pointF.x);
        int round2 = Math.round(pointF.y);
        bz.a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
    }

    public PointF get(View view) {
        return null;
    }
}
