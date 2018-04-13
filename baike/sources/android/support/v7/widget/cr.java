package android.support.v7.widget;

import android.util.Property;

final class cr extends Property<SwitchCompat, Float> {
    cr(Class cls, String str) {
        super(cls, str);
    }

    public Float get(SwitchCompat switchCompat) {
        return Float.valueOf(switchCompat.z);
    }

    public void set(SwitchCompat switchCompat, Float f) {
        switchCompat.setThumbPosition(f.floatValue());
    }
}
