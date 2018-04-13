package c.a.e.b;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.StyleRes;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.util.TypedValue;
import c.a.e.a.c;
import c.a.e.a.d;
import c.a.i.b.a;
import c.a.i.b.e;
import c.a.i.u;
import c.a.i.x;

public class f extends NavigationView implements u {
    private static final int[] a = new int[]{16842912};
    private static final int[] b = new int[]{-16842910};
    private int c;
    private int d;
    private int e;
    private int f;
    private a g;

    public f(Context context) {
        this(context, null);
    }

    public f(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public f(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = new a(this);
        this.g.a(attributeSet, 0);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.NavigationView, i, c.Widget_Design_NavigationView);
        if (obtainStyledAttributes.hasValue(d.NavigationView_itemIconTint)) {
            this.f = obtainStyledAttributes.getResourceId(d.NavigationView_itemIconTint, 0);
        } else {
            this.e = x.a(context);
        }
        if (obtainStyledAttributes.hasValue(d.NavigationView_itemTextAppearance)) {
            int resourceId = obtainStyledAttributes.getResourceId(d.NavigationView_itemTextAppearance, 0);
            if (resourceId != 0) {
                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, d.SkinTextAppearance);
                if (obtainStyledAttributes2.hasValue(d.SkinTextAppearance_android_textColor)) {
                    this.d = obtainStyledAttributes2.getResourceId(d.SkinTextAppearance_android_textColor, 0);
                }
                obtainStyledAttributes2.recycle();
            }
        }
        if (obtainStyledAttributes.hasValue(d.NavigationView_itemTextColor)) {
            this.d = obtainStyledAttributes.getResourceId(d.NavigationView_itemTextColor, 0);
        } else {
            this.e = x.a(context);
        }
        if (this.d == 0) {
            this.d = x.c(context);
        }
        this.c = obtainStyledAttributes.getResourceId(d.NavigationView_itemBackground, 0);
        obtainStyledAttributes.recycle();
        c();
        b();
        a();
    }

    public void setItemBackgroundResource(@DrawableRes int i) {
        super.setItemBackgroundResource(i);
        this.c = i;
        a();
    }

    private void a() {
        this.c = e.b(this.c);
        if (this.c != 0) {
            setItemBackground(c.a.d.a.a.a().b(this.c));
        }
    }

    public void setItemTextAppearance(@StyleRes int i) {
        super.setItemTextAppearance(i);
        if (i != 0) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i, d.SkinTextAppearance);
            if (obtainStyledAttributes.hasValue(d.SkinTextAppearance_android_textColor)) {
                this.d = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColor, 0);
            }
            obtainStyledAttributes.recycle();
            b();
        }
    }

    private void b() {
        this.d = e.b(this.d);
        if (this.d != 0) {
            setItemTextColor(c.a.d.a.a.a().c(this.d));
            return;
        }
        this.e = e.b(this.e);
        if (this.e != 0) {
            setItemTextColor(a(16842806));
        }
    }

    private void c() {
        this.f = e.b(this.f);
        if (this.f != 0) {
            setItemIconTintList(c.a.d.a.a.a().c(this.f));
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
        ColorStateList c = c.a.d.a.a.a().c(typedValue.resourceId);
        int a = c.a.d.a.a.a().a(this.e);
        int defaultColor = c.getDefaultColor();
        return new ColorStateList(new int[][]{b, a, EMPTY_STATE_SET}, new int[]{c.getColorForState(b, defaultColor), a, defaultColor});
    }

    public void d() {
        if (this.g != null) {
            this.g.a();
        }
        c();
        b();
        a();
    }
}
