package cn.xiaochuankeji.tieba.widget;

import android.annotation.TargetApi;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import c.a.a.d;
import c.a.d.a.a;
import c.a.i.b.e;

public class b extends e {
    private final LinearLayoutCompat a;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;

    public b(LinearLayoutCompat linearLayoutCompat) {
        this.a = linearLayoutCompat;
    }

    public void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.a.getContext().obtainStyledAttributes(attributeSet, d.SkinDividerHelper, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                this.b = obtainStyledAttributes.getResourceId(0, 0);
            }
            if (obtainStyledAttributes.hasValue(1)) {
                this.c = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            }
            if (obtainStyledAttributes.hasValue(2)) {
                this.d = obtainStyledAttributes.getDimensionPixelSize(2, 0);
            }
            if (obtainStyledAttributes.hasValue(3)) {
                this.e = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            }
            if (obtainStyledAttributes.hasValue(4)) {
                this.f = obtainStyledAttributes.getDimensionPixelSize(4, 0);
            }
            obtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
    }

    @TargetApi(16)
    public void a() {
        this.b = b(this.b);
        if (this.b != 0) {
            this.a.a(a.a().b(this.b), this.c, this.d, this.e, this.f);
        }
    }
}
