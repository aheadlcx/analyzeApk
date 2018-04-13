package qsbk.app.core.widget.toast;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

class CustomToast$a {
    final Handler a = new Handler();
    int b;
    int c;
    int d;
    float e;
    float f;
    int g;
    View h;
    View i;
    WindowManager j;
    final Runnable k = new a(this);
    final Runnable l = new b(this);
    private final LayoutParams m = new LayoutParams();

    CustomToast$a() {
        LayoutParams layoutParams = this.m;
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        layoutParams.type = 2005;
        layoutParams.setTitle("Toast");
        layoutParams.flags = 152;
    }

    public void show() {
        this.a.post(this.k);
        this.a.postDelayed(this.l, (long) this.g);
    }

    public void hide() {
        this.a.post(this.l);
    }

    public void handleShow() {
        if (this.h != this.i) {
            handleHide();
            this.h = this.i;
            Context applicationContext = this.h.getContext().getApplicationContext();
            String packageName = this.h.getContext().getPackageName();
            if (applicationContext == null) {
                applicationContext = this.h.getContext();
            }
            this.j = (WindowManager) applicationContext.getSystemService("window");
            int i = this.b;
            this.m.gravity = i;
            if ((i & 7) == 7) {
                this.m.horizontalWeight = 1.0f;
            }
            if ((i & 112) == 112) {
                this.m.verticalWeight = 1.0f;
            }
            this.m.x = this.c;
            this.m.y = this.d;
            this.m.verticalMargin = this.f;
            this.m.horizontalMargin = this.e;
            this.m.packageName = packageName;
            if (this.h.getParent() != null) {
                this.j.removeView(this.h);
            }
            this.j.addView(this.h, this.m);
        }
    }

    public void handleHide() {
        if (this.h != null) {
            if (this.h.getParent() != null) {
                this.j.removeView(this.h);
            }
            this.h = null;
        }
    }
}
