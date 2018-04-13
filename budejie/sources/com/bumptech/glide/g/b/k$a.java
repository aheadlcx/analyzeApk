package com.bumptech.glide.g.b;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class k$a {
    private final View a;
    private final List<h> b = new ArrayList();
    private a c;
    private Point d;

    private static class a implements OnPreDrawListener {
        private final WeakReference<k$a> a;

        public a(k$a k_a) {
            this.a = new WeakReference(k_a);
        }

        public boolean onPreDraw() {
            if (Log.isLoggable("ViewTarget", 2)) {
                Log.v("ViewTarget", "OnGlobalLayoutListener called listener=" + this);
            }
            k$a k_a = (k$a) this.a.get();
            if (k_a != null) {
                k_a.a();
            }
            return true;
        }
    }

    public k$a(View view) {
        this.a = view;
    }

    private void a(int i, int i2) {
        for (h a : this.b) {
            a.a(i, i2);
        }
        this.b.clear();
    }

    private void a() {
        if (!this.b.isEmpty()) {
            int c = c();
            int b = b();
            if (a(c) && a(b)) {
                a(c, b);
                ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
                if (viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this.c);
                }
                this.c = null;
            }
        }
    }

    public void a(h hVar) {
        int c = c();
        int b = b();
        if (a(c) && a(b)) {
            hVar.a(c, b);
            return;
        }
        if (!this.b.contains(hVar)) {
            this.b.add(hVar);
        }
        if (this.c == null) {
            ViewTreeObserver viewTreeObserver = this.a.getViewTreeObserver();
            this.c = new a(this);
            viewTreeObserver.addOnPreDrawListener(this.c);
        }
    }

    private int b() {
        LayoutParams layoutParams = this.a.getLayoutParams();
        if (a(this.a.getHeight())) {
            return this.a.getHeight();
        }
        if (layoutParams != null) {
            return a(layoutParams.height, true);
        }
        return 0;
    }

    private int c() {
        LayoutParams layoutParams = this.a.getLayoutParams();
        if (a(this.a.getWidth())) {
            return this.a.getWidth();
        }
        if (layoutParams != null) {
            return a(layoutParams.width, false);
        }
        return 0;
    }

    private int a(int i, boolean z) {
        if (i != -2) {
            return i;
        }
        Point d = d();
        return z ? d.y : d.x;
    }

    @TargetApi(13)
    private Point d() {
        if (this.d != null) {
            return this.d;
        }
        Display defaultDisplay = ((WindowManager) this.a.getContext().getSystemService("window")).getDefaultDisplay();
        if (VERSION.SDK_INT >= 13) {
            this.d = new Point();
            defaultDisplay.getSize(this.d);
        } else {
            this.d = new Point(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
        return this.d;
    }

    private boolean a(int i) {
        return i > 0 || i == -2;
    }
}
