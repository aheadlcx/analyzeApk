package cn.xiaochuankeji.tieba.d.a;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class d {
    private a a;
    private Rect b = new Rect();
    private int c = -1;
    private View d;

    @Nullable
    public abstract View a(int i);

    public d(a aVar) {
        this.a = aVar;
    }

    public int a(View view) {
        view.getLocalVisibleRect(this.b);
        int height = view.getHeight();
        if (b()) {
            return ((height - this.b.top) * 100) / height;
        }
        if (b(height)) {
            return (this.b.bottom * 100) / height;
        }
        return 100;
    }

    public void a(int i, int i2) {
        View view;
        int max = Math.max(i2, i);
        int min = Math.min(i2, i);
        int i3 = 0;
        int i4 = -1;
        View view2 = null;
        while (min < max && min > 0) {
            View a = a(min);
            if (a == null) {
                i4 = min;
                view = null;
                break;
            }
            int i5;
            int a2 = a(a);
            if (a2 > i3) {
                i4 = min;
                i5 = a2;
            } else {
                a = view2;
                i5 = i3;
            }
            min++;
            i3 = i5;
            view2 = a;
        }
        view = view2;
        if (i == 0 && i2 - 1 >= 0) {
            i4 = i2 - 1;
            view = a(i4);
        }
        if (i4 != -1 && i4 != this.c) {
            if (this.c != -1) {
                this.a.b(this.c, this.d);
            }
            this.c = i4;
            this.d = view;
            this.a.a(i4, view);
        }
    }

    public void a(b bVar) {
        a(bVar.b(), bVar.a());
    }

    private boolean b(int i) {
        return this.b.bottom > 0 && this.b.bottom < i;
    }

    private boolean b() {
        return this.b.top > 0;
    }

    public void a() {
        this.c = -1;
        this.d = null;
    }
}
