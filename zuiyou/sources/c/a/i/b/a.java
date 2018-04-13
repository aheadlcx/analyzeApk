package c.a.i.b;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import c.a.a.d;

public class a extends e {
    private final View a;
    private int b = 0;

    public a(View view) {
        this.a = view;
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.SkinBackgroundHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(d.SkinBackgroundHelper_android_background)) {
                this.b = obtainStyledAttributes.getResourceId(d.SkinBackgroundHelper_android_background, 0);
            }
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public void a(int i) {
        this.b = i;
        a();
    }

    public void a() {
        this.b = e.b(this.b);
        if (this.b != 0) {
            Drawable b = c.a.d.a.a.a().b(this.b);
            if (b != null) {
                int paddingLeft = this.a.getPaddingLeft();
                int paddingTop = this.a.getPaddingTop();
                int paddingRight = this.a.getPaddingRight();
                int paddingBottom = this.a.getPaddingBottom();
                ViewCompat.setBackground(this.a, b);
                this.a.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
            }
        }
    }
}
