package cn.tatagou.sdk.view.dialog;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public abstract class b extends a {
    private static int i = 24;
    private static int j = 14;
    private int a = -15724528;
    private int b = 24;
    protected Context c;
    protected LayoutInflater d;
    protected int e;
    protected int f;
    protected int g;
    private int h = 0;
    private ArrayList<View> k = new ArrayList();

    protected abstract CharSequence a(int i);

    protected b(Context context, int i, int i2, int i3, int i4, int i5) {
        this.c = context;
        this.e = i;
        this.f = i2;
        this.h = i3;
        i = i4;
        j = i5;
        this.d = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public ArrayList<View> getTestViews() {
        return this.k;
    }

    public int getTextColor() {
        return this.a;
    }

    public void setTextColor(int i) {
        this.a = i;
    }

    public int getTextSize() {
        return this.b;
    }

    public void setTextSize(int i) {
        this.b = i;
    }

    public int getItemResource() {
        return this.e;
    }

    public void setItemResource(int i) {
        this.e = i;
    }

    public int getItemTextResource() {
        return this.f;
    }

    public void setItemTextResource(int i) {
        this.f = i;
    }

    public int getEmptyItemResource() {
        return this.g;
    }

    public void setEmptyItemResource(int i) {
        this.g = i;
    }

    public View getItem(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getItemsCount()) {
            return null;
        }
        if (view == null) {
            view = a(this.e, viewGroup);
        }
        TextView a = a(view, this.f);
        if (!this.k.contains(a)) {
            this.k.add(a);
        }
        if (a == null) {
            return view;
        }
        CharSequence a2 = a(i);
        if (a2 == null) {
            a2 = "";
        }
        a.setText(a2);
        if (i == this.h) {
            a.setTextSize((float) i);
        } else {
            a.setTextSize((float) j);
        }
        if (this.e != -1) {
            return view;
        }
        a(a);
        return view;
    }

    public View getEmptyItem(View view, ViewGroup viewGroup) {
        View a;
        if (view == null) {
            a = a(this.g, viewGroup);
        } else {
            a = view;
        }
        if (this.g == -1 && (a instanceof TextView)) {
            a((TextView) a);
        }
        return a;
    }

    protected void a(TextView textView) {
        textView.setTextColor(this.a);
        textView.setGravity(17);
        textView.setTextSize((float) this.b);
        textView.setLines(1);
        textView.setTypeface(Typeface.SANS_SERIF, 1);
    }

    private TextView a(View view, int i) {
        if (i == 0) {
            try {
                if (view instanceof TextView) {
                    return (TextView) view;
                }
            } catch (Throwable e) {
                Log.e("AbstractWheelAdapter", "You must supply a resource ID for a TextView", e);
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
                return new TextView(this.c);
            case 0:
                return null;
            default:
                return this.d.inflate(i, viewGroup, false);
        }
    }
}
