package android.support.transition;

import android.util.Property;
import android.view.View;

final class ca extends Property<View, Float> {
    ca(Class cls, String str) {
        super(cls, str);
    }

    public Float get(View view) {
        return Float.valueOf(bz.c(view));
    }

    public void set(View view, Float f) {
        bz.a(view, f.floatValue());
    }
}
