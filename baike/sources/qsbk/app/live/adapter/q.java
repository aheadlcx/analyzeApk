package qsbk.app.live.adapter;

import android.util.Property;

final class q extends Property<MutableForegroundColorSpan, Integer> {
    q(Class cls, String str) {
        super(cls, str);
    }

    public void set(MutableForegroundColorSpan mutableForegroundColorSpan, Integer num) {
        mutableForegroundColorSpan.setForegroundColor(num.intValue());
    }

    public Integer get(MutableForegroundColorSpan mutableForegroundColorSpan) {
        return Integer.valueOf(mutableForegroundColorSpan.getForegroundColor());
    }
}
