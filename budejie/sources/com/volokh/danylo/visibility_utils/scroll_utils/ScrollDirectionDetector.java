package com.volokh.danylo.visibility_utils.scroll_utils;

import android.view.View;

public class ScrollDirectionDetector {
    private static final String a = ScrollDirectionDetector.class.getSimpleName();
    private final a b;
    private int c;
    private int d;
    private ScrollDirection e = null;

    public interface a {
        void a(ScrollDirection scrollDirection);
    }

    public enum ScrollDirection {
        UP,
        DOWN
    }

    public ScrollDirectionDetector(a aVar) {
        this.b = aVar;
    }

    public void a(a aVar, int i) {
        int i2 = 0;
        View a = aVar.a(0);
        if (a != null) {
            i2 = a.getTop();
        }
        if (i == this.d) {
            if (i2 > this.c) {
                b();
            } else if (i2 < this.c) {
                a();
            }
        } else if (i < this.d) {
            b();
        } else {
            a();
        }
        this.c = i2;
        this.d = i;
    }

    private void a() {
        if (this.e != ScrollDirection.DOWN) {
            this.e = ScrollDirection.DOWN;
            this.b.a(ScrollDirection.DOWN);
        }
    }

    private void b() {
        if (this.e != ScrollDirection.UP) {
            this.e = ScrollDirection.UP;
            this.b.a(ScrollDirection.UP);
        }
    }
}
