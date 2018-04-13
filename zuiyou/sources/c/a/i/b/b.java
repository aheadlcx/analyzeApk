package c.a.i.b;

import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import c.a.a.d;
import c.a.d.a.a;

public class b extends e {
    private final ImageView a;
    @ColorRes
    private int b = 0;
    private boolean c = true;

    public b(ImageView imageView, @ColorRes int i) {
        this.a = imageView;
        this.b = i;
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.SkinColorFilterHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(d.SkinColorFilterHelper_colorFilterEnable)) {
                this.c = obtainStyledAttributes.getBoolean(d.SkinColorFilterHelper_colorFilterEnable, true);
            }
            if (this.c && obtainStyledAttributes.hasValue(d.SkinColorFilterHelper_colorFilter)) {
                int resourceId = obtainStyledAttributes.getResourceId(d.SkinColorFilterHelper_colorFilter, 0);
                if (resourceId != 0) {
                    this.b = resourceId;
                }
            }
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    public void a() {
        if (this.c) {
            this.b = e.b(this.b);
            if (this.b != 0) {
                a a = a.a();
                if (a != null) {
                    int a2 = a.a(this.b);
                    if (a2 == 0) {
                        this.a.clearColorFilter();
                    } else {
                        this.a.setColorFilter(a2);
                    }
                }
            }
        }
    }
}
