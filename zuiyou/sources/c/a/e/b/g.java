package c.a.e.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import c.a.d.a.a;
import c.a.e.a.c;
import c.a.e.a.d;
import c.a.i.b.e;
import c.a.i.u;

public class g extends TabLayout implements u {
    private int a;
    private int b;
    private int c;

    public g(Context context) {
        this(context, null);
    }

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public g(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = 0;
        this.c = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.TabLayout, i, 0);
        this.a = obtainStyledAttributes.getResourceId(d.TabLayout_tabIndicatorColor, 0);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(obtainStyledAttributes.getResourceId(d.TabLayout_tabTextAppearance, c.TextAppearance_Design_Tab), d.SkinTextAppearance);
        try {
            this.b = obtainStyledAttributes2.getResourceId(d.SkinTextAppearance_android_textColor, 0);
            if (obtainStyledAttributes.hasValue(d.TabLayout_tabTextColor)) {
                this.b = obtainStyledAttributes.getResourceId(d.TabLayout_tabTextColor, 0);
            }
            if (obtainStyledAttributes.hasValue(d.TabLayout_tabSelectedTextColor)) {
                this.c = obtainStyledAttributes.getResourceId(d.TabLayout_tabSelectedTextColor, 0);
            }
            obtainStyledAttributes.recycle();
            d();
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    public void d() {
        this.a = e.b(this.a);
        if (this.a != 0) {
            setSelectedTabIndicatorColor(a.a().a(this.a));
        }
        this.b = e.b(this.b);
        if (this.b != 0) {
            setTabTextColors(a.a().c(this.b));
        }
        this.c = e.b(this.c);
        if (this.c != 0) {
            int a = a.a().a(this.c);
            if (getTabTextColors() != null) {
                setTabTextColors(getTabTextColors().getDefaultColor(), a);
            }
        }
    }
}
