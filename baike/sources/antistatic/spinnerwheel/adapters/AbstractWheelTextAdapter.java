package antistatic.spinnerwheel.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter {
    public static final int DEFAULT_TEXT_COLOR = -15724528;
    public static final int DEFAULT_TEXT_SIZE = 24;
    public static final int LABEL_COLOR = -9437072;
    public static final int TEXT_VIEW_ITEM_RESOURCE = -1;
    protected Context a;
    protected LayoutInflater b;
    protected int c;
    protected int d;
    protected int e;
    private Typeface f;
    private int g;
    private int h;

    protected abstract CharSequence getItemText(int i);

    protected AbstractWheelTextAdapter(Context context) {
        this(context, -1);
    }

    protected AbstractWheelTextAdapter(Context context, int i) {
        this(context, i, 0);
    }

    protected AbstractWheelTextAdapter(Context context, int i, int i2) {
        this.g = DEFAULT_TEXT_COLOR;
        this.h = 24;
        this.a = context;
        this.c = i;
        this.d = i2;
        this.b = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getTextColor() {
        return this.g;
    }

    public void setTextColor(int i) {
        this.g = i;
    }

    public void setTextTypeface(Typeface typeface) {
        this.f = typeface;
    }

    public int getTextSize() {
        return this.h;
    }

    public void setTextSize(int i) {
        this.h = i;
    }

    public int getItemResource() {
        return this.c;
    }

    public void setItemResource(int i) {
        this.c = i;
    }

    public int getItemTextResource() {
        return this.d;
    }

    public void setItemTextResource(int i) {
        this.d = i;
    }

    public int getEmptyItemResource() {
        return this.e;
    }

    public void setEmptyItemResource(int i) {
        this.e = i;
    }

    public View getItem(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        View a;
        if (view == null) {
            a = a(this.c, viewGroup);
        } else {
            a = view;
        }
        if (a == null) {
            return null;
        }
        TextView a2 = a(a, this.d);
        if (a2 != null) {
            CharSequence itemText = getItemText(i);
            if (itemText == null) {
                itemText = "";
            }
            a2.setText(itemText);
            a(a2);
        }
        return a;
    }

    public View getEmptyItem(View view, ViewGroup viewGroup) {
        View a;
        if (view == null) {
            a = a(this.e, viewGroup);
        } else {
            a = view;
        }
        if (a instanceof TextView) {
            a((TextView) a);
        }
        return a;
    }

    protected void a(TextView textView) {
        if (this.c == -1) {
            textView.setTextColor(this.g);
            textView.setGravity(17);
            textView.setTextSize((float) this.h);
            textView.setLines(1);
        }
        if (this.f != null) {
            textView.setTypeface(this.f);
        } else {
            textView.setTypeface(Typeface.SANS_SERIF, 1);
        }
    }

    private TextView a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (Throwable e) {
                Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView");
                throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView", e);
            }
        }
        if (i != 0) {
            return (TextView) view.findViewById(i);
        }
        return null;
    }

    private View a(int i, ViewGroup viewGroup) {
        switch (i) {
            case -1:
                return new TextView(this.a);
            case 0:
                return null;
            default:
                return this.b.inflate(i, viewGroup, false);
        }
    }
}
