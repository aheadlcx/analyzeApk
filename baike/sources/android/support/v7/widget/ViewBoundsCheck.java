package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class ViewBoundsCheck {
    final b a;
    a b = new a();

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    static class a {
        int a = 0;
        int b;
        int c;
        int d;
        int e;

        a() {
        }

        void a(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        void a(int i) {
            this.a |= i;
        }

        void a() {
            this.a = 0;
        }

        int a(int i, int i2) {
            if (i > i2) {
                return 1;
            }
            if (i == i2) {
                return 2;
            }
            return 4;
        }

        boolean b() {
            if ((this.a & 7) != 0 && (this.a & (a(this.d, this.b) << 0)) == 0) {
                return false;
            }
            if ((this.a & 112) != 0 && (this.a & (a(this.d, this.c) << 4)) == 0) {
                return false;
            }
            if ((this.a & 1792) != 0 && (this.a & (a(this.e, this.b) << 8)) == 0) {
                return false;
            }
            if ((this.a & 28672) == 0 || (this.a & (a(this.e, this.c) << 12)) != 0) {
                return true;
            }
            return false;
        }
    }

    interface b {
        View getChildAt(int i);

        int getChildCount();

        int getChildEnd(View view);

        int getChildStart(View view);

        View getParent();

        int getParentEnd();

        int getParentStart();
    }

    ViewBoundsCheck(b bVar) {
        this.a = bVar;
    }

    View a(int i, int i2, int i3, int i4) {
        int parentStart = this.a.getParentStart();
        int parentEnd = this.a.getParentEnd();
        int i5 = i2 > i ? 1 : -1;
        View view = null;
        while (i != i2) {
            View childAt = this.a.getChildAt(i);
            this.b.a(parentStart, parentEnd, this.a.getChildStart(childAt), this.a.getChildEnd(childAt));
            if (i3 != 0) {
                this.b.a();
                this.b.a(i3);
                if (this.b.b()) {
                    return childAt;
                }
            }
            if (i4 != 0) {
                this.b.a();
                this.b.a(i4);
                if (this.b.b()) {
                    i += i5;
                    view = childAt;
                }
            }
            childAt = view;
            i += i5;
            view = childAt;
        }
        return view;
    }

    boolean a(View view, int i) {
        this.b.a(this.a.getParentStart(), this.a.getParentEnd(), this.a.getChildStart(view), this.a.getChildEnd(view));
        if (i == 0) {
            return false;
        }
        this.b.a();
        this.b.a(i);
        return this.b.b();
    }
}
