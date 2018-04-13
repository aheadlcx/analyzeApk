package com.scwang.smartrefresh.layout.c;

import android.view.MotionEvent;
import android.view.View;
import com.scwang.smartrefresh.layout.a.i;
import com.scwang.smartrefresh.layout.f.c;

public class d implements i {
    protected MotionEvent a;
    protected i b;
    protected boolean c;

    void a(i iVar) {
        this.b = iVar;
    }

    void a(MotionEvent motionEvent) {
        this.a = motionEvent;
    }

    public boolean a(View view) {
        if (this.b != null) {
            return this.b.a(view);
        }
        return c.a(view, this.a);
    }

    public boolean b(View view) {
        if (this.b != null) {
            return this.b.b(view);
        }
        if (this.c) {
            return !c.c(view, this.a);
        } else {
            return c.b(view, this.a);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }
}
