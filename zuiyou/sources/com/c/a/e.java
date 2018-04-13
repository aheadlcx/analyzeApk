package com.c.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class e {
    Activity a;
    d b;
    b c;
    private boolean d = true;
    private boolean e = false;

    e(Activity activity) {
        this.a = activity;
    }

    void a() {
        Window window = this.a.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setBackgroundColor(0);
        this.b = new d(this.a);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
        this.c = new b(this);
    }

    void b() {
        d();
    }

    @TargetApi(11)
    public e a(boolean z) {
        this.e = z;
        this.c.a(z);
        return this;
    }

    public e b(boolean z) {
        this.d = z;
        this.b.setEnableGesture(z);
        d();
        return this;
    }

    private void d() {
        if (this.d || this.e) {
            this.b.a(this.a);
        } else {
            this.b.b(this.a);
        }
    }

    public e a(float f) {
        this.b.setEdgeSizePercent(f);
        return this;
    }

    public e b(float f) {
        this.b.a(this.a, f);
        return this;
    }

    public e a(f fVar) {
        this.b.a(fVar);
        return this;
    }

    public e b(f fVar) {
        this.b.b(fVar);
        return this;
    }

    public void c(boolean z) {
        this.b.setPageTranslucent(z);
    }

    public d c() {
        return this.b;
    }
}
