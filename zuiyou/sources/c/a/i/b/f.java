package c.a.i.b;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import c.a.a.d;
import c.a.d.a.a;

public class f extends e {
    private static final String a = f.class.getSimpleName();
    private final ImageView b;
    private int c = 0;
    private int d = 0;

    public f(ImageView imageView) {
        this.b = imageView;
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray typedArray = null;
        try {
            typedArray = this.b.getContext().obtainStyledAttributes(attributeSet, d.SkinCompatImageView, i, 0);
            this.c = typedArray.getResourceId(d.SkinCompatImageView_android_src, 0);
            this.d = typedArray.getResourceId(d.SkinCompatImageView_srcCompat, 0);
            a();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    public void a(int i) {
        this.c = i;
        a();
    }

    public void a() {
        this.d = e.b(this.d);
        Drawable a;
        if (this.d != 0) {
            a = a.a().a(this.b.getContext(), this.d);
            if (a != null) {
                this.b.setImageDrawable(a);
                return;
            }
            return;
        }
        this.c = e.b(this.c);
        if (this.c != 0) {
            a = a.a().b(this.c);
            if (a != null) {
                this.b.setImageDrawable(a);
            }
        }
    }
}
