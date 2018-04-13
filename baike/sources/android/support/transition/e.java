package android.support.transition;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;

final class e extends Property<Drawable, PointF> {
    private Rect a = new Rect();

    e(Class cls, String str) {
        super(cls, str);
    }

    public void set(Drawable drawable, PointF pointF) {
        drawable.copyBounds(this.a);
        this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
        drawable.setBounds(this.a);
    }

    public PointF get(Drawable drawable) {
        drawable.copyBounds(this.a);
        return new PointF((float) this.a.left, (float) this.a.top);
    }
}
