package antistatic.spinnerwheel.adapters;

import android.content.Context;

public class NumericWheelAdapter extends AbstractWheelTextAdapter {
    public static final int DEFAULT_MAX_VALUE = 9;
    private int f;
    private int g;
    private String h;

    public NumericWheelAdapter(Context context) {
        this(context, 0, 9);
    }

    public NumericWheelAdapter(Context context, int i, int i2) {
        this(context, i, i2, null);
    }

    public NumericWheelAdapter(Context context, int i, int i2, String str) {
        super(context);
        this.f = i;
        this.g = i2;
        this.h = str;
    }

    public void setMinValue(int i) {
        this.f = i;
        a();
    }

    public void setMaxValue(int i) {
        this.g = i;
        a();
    }

    public CharSequence getItemText(int i) {
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        int i2 = this.f + i;
        if (this.h == null) {
            return Integer.toString(i2);
        }
        return String.format(this.h, new Object[]{Integer.valueOf(i2)});
    }

    public int getItemsCount() {
        return (this.g - this.f) + 1;
    }
}
