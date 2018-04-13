package antistatic.spinnerwheel.adapters;

import android.content.Context;

public class ArrayWheelAdapter<T> extends AbstractWheelTextAdapter {
    private T[] f;

    public ArrayWheelAdapter(Context context, T[] tArr) {
        super(context);
        this.f = tArr;
    }

    public CharSequence getItemText(int i) {
        if (i < 0 || i >= this.f.length) {
            return null;
        }
        Object obj = this.f[i];
        if (obj instanceof CharSequence) {
            return (CharSequence) obj;
        }
        return obj.toString();
    }

    public int getItemsCount() {
        return this.f.length;
    }
}
