package android.support.transition;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.Property;
import android.view.View;

final class cb extends Property<View, Rect> {
    cb(Class cls, String str) {
        super(cls, str);
    }

    public Rect get(View view) {
        return ViewCompat.getClipBounds(view);
    }

    public void set(View view, Rect rect) {
        ViewCompat.setClipBounds(view, rect);
    }
}
