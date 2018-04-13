package c.a.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import c.a.a.d;
import c.a.i.b.a;
import c.a.i.b.e;

public class y extends Toolbar implements u {
    private int a;
    private int b;
    private int c;
    private a d;

    public y(Context context) {
        this(context, null);
    }

    public y(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.toolbarStyle);
    }

    @SuppressLint({"RestrictedApi"})
    public y(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = new a(this);
        this.d.a(attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.Toolbar, i, 0);
        this.c = obtainStyledAttributes.getResourceId(d.Toolbar_navigationIcon, 0);
        int resourceId = obtainStyledAttributes.getResourceId(d.Toolbar_titleTextAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(d.Toolbar_subtitleTextAppearance, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId, d.SkinTextAppearance);
            this.a = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColor, 0);
            obtainStyledAttributes.recycle();
        }
        if (resourceId2 != 0) {
            obtainStyledAttributes = context.obtainStyledAttributes(resourceId2, d.SkinTextAppearance);
            this.b = obtainStyledAttributes.getResourceId(d.SkinTextAppearance_android_textColor, 0);
            obtainStyledAttributes.recycle();
        }
        obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.Toolbar, i, 0);
        if (obtainStyledAttributes.hasValue(d.Toolbar_titleTextColor)) {
            this.a = obtainStyledAttributes.getResourceId(d.Toolbar_titleTextColor, 0);
        }
        if (obtainStyledAttributes.hasValue(d.Toolbar_subtitleTextColor)) {
            this.b = obtainStyledAttributes.getResourceId(d.Toolbar_subtitleTextColor, 0);
        }
        obtainStyledAttributes.recycle();
        a();
        b();
        c();
    }

    private void a() {
        this.a = e.b(this.a);
        if (this.a != 0) {
            setTitleTextColor(c.a.d.a.a.a().a(this.a));
        }
    }

    private void b() {
        this.b = e.b(this.b);
        if (this.b != 0) {
            setSubtitleTextColor(c.a.d.a.a.a().a(this.b));
        }
    }

    private void c() {
        this.c = e.b(this.c);
        if (this.c != 0) {
            setNavigationIcon(c.a.d.a.a.a().b(this.c));
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public void setNavigationIcon(@DrawableRes int i) {
        super.setNavigationIcon(i);
        this.c = i;
        c();
    }

    public void d() {
        if (this.d != null) {
            this.d.a();
        }
        a();
        b();
        c();
    }
}
