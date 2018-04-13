package cn.xiaochuankeji.tieba.ui.mediabrowse.component;

import android.view.MotionEvent;

public class e {
    private float a;
    private float b;
    private float c;
    private boolean d = false;
    private a e;

    public interface a {
        void a();

        void a(float f, boolean z);

        void b();
    }

    public e(a aVar) {
        this.e = aVar;
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getActionMasked()) {
            case 0:
                return b(motionEvent);
            case 1:
                return a();
            case 2:
                return c(motionEvent);
            case 3:
                return b();
            default:
                return false;
        }
    }

    private boolean b(MotionEvent motionEvent) {
        this.a = motionEvent.getX();
        this.b = motionEvent.getY();
        this.c = this.a;
        return false;
    }

    private boolean c(MotionEvent motionEvent) {
        boolean z = false;
        float x = motionEvent.getX();
        float f = x - this.a;
        float y = motionEvent.getY() - this.b;
        float abs = Math.abs(f);
        y = Math.abs(y);
        if (!this.d && abs > 5.0f && abs > y) {
            this.e.a();
            this.d = true;
        }
        if (!this.d) {
            return false;
        }
        if (x > this.c) {
            z = true;
        }
        this.e.a(f, z);
        this.c = x;
        return true;
    }

    private boolean a() {
        if (this.d) {
            c();
            this.e.b();
            return true;
        }
        c();
        return false;
    }

    private boolean b() {
        c();
        return false;
    }

    private void c() {
        this.d = false;
    }
}
