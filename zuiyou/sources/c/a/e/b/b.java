package c.a.e.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import c.a.d.a.a;
import c.a.e.a.c;
import c.a.e.a.d;
import c.a.i.b.e;
import c.a.i.u;

public class b extends BottomNavigationView implements u {
    private static final int[] a = new int[]{16842912};
    private static final int[] b = new int[]{-16842910};
    private int c;
    private int d;
    private int e;

    public b(@NonNull Context context) {
        this(context, null);
    }

    public b(@NonNull Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = 0;
        this.e = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.BottomNavigationView, i, c.Widget_Design_BottomNavigationView);
        if (obtainStyledAttributes.hasValue(d.BottomNavigationView_itemIconTint)) {
            this.d = obtainStyledAttributes.getResourceId(d.BottomNavigationView_itemIconTint, 0);
        } else {
            this.e = c();
        }
        if (obtainStyledAttributes.hasValue(d.BottomNavigationView_itemTextColor)) {
            this.c = obtainStyledAttributes.getResourceId(d.BottomNavigationView_itemTextColor, 0);
        } else {
            this.e = c();
        }
        obtainStyledAttributes.recycle();
        b();
        a();
    }

    private void a() {
        this.c = e.b(this.c);
        if (this.c != 0) {
            setItemTextColor(a.a().c(this.c));
            return;
        }
        this.e = e.b(this.e);
        if (this.e != 0) {
            setItemTextColor(a(16842808));
        }
    }

    private void b() {
        this.d = e.b(this.d);
        if (this.d != 0) {
            setItemIconTintList(a.a().c(this.d));
            return;
        }
        this.e = e.b(this.e);
        if (this.e != 0) {
            setItemIconTintList(a(16842808));
        }
    }

    private ColorStateList a(int i) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i, typedValue, true)) {
            return null;
        }
        ColorStateList c = a.a().c(typedValue.resourceId);
        int a = a.a().a(this.e);
        int defaultColor = c.getDefaultColor();
        return new ColorStateList(new int[][]{b, a, EMPTY_STATE_SET}, new int[]{c.getColorForState(b, defaultColor), a, defaultColor});
    }

    private int c() {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(c.a.e.a.a.colorPrimary, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }

    public void d() {
        b();
        a();
    }
}
