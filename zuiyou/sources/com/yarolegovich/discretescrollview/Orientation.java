package com.yarolegovich.discretescrollview;

import android.graphics.Point;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;

public enum Orientation {
    HORIZONTAL {
        a createHelper() {
            return new b();
        }
    },
    VERTICAL {
        a createHelper() {
            return new c();
        }
    };

    interface a {
        float a(Point point, int i, int i2);

        int a(int i);

        int a(int i, int i2);

        void a(int i, LayoutManager layoutManager);

        void a(Point point, int i, Point point2);

        void a(Direction direction, int i, Point point);

        boolean a();

        boolean a(Point point, int i, int i2, int i3, int i4);

        boolean a(a aVar);

        int b(int i);

        int b(int i, int i2);

        boolean b();

        int c(int i, int i2);
    }

    protected static class b implements a {
        protected b() {
        }

        public int a(int i, int i2) {
            return i;
        }

        public int b(int i, int i2) {
            return i;
        }

        public void a(Point point, int i, Point point2) {
            point2.set(point.x - i, point.y);
        }

        public void a(Direction direction, int i, Point point) {
            point.set(point.x + direction.applyTo(i), point.y);
        }

        public boolean a(Point point, int i, int i2, int i3, int i4) {
            return point.x - i < i3 + i4 && point.x + i > (-i4);
        }

        public boolean a(a aVar) {
            boolean z;
            View d = aVar.d();
            View e = aVar.e();
            int width = aVar.getWidth() + aVar.f();
            if (aVar.getDecoratedLeft(d) <= (-aVar.f()) || aVar.getPosition(d) <= 0) {
                z = false;
            } else {
                z = true;
            }
            boolean z2;
            if (aVar.getDecoratedRight(e) >= width || aVar.getPosition(e) >= aVar.getItemCount() - 1) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z || r3) {
                return true;
            }
            return false;
        }

        public void a(int i, LayoutManager layoutManager) {
            layoutManager.offsetChildrenHorizontal(i);
        }

        public float a(Point point, int i, int i2) {
            return (float) (i - point.x);
        }

        public int c(int i, int i2) {
            return i;
        }

        public boolean b() {
            return true;
        }

        public boolean a() {
            return false;
        }

        public int a(int i) {
            return i;
        }

        public int b(int i) {
            return 0;
        }
    }

    protected static class c implements a {
        protected c() {
        }

        public int a(int i, int i2) {
            return i2;
        }

        public int b(int i, int i2) {
            return i2;
        }

        public void a(Point point, int i, Point point2) {
            point2.set(point.x, point.y - i);
        }

        public void a(Direction direction, int i, Point point) {
            point.set(point.x, point.y + direction.applyTo(i));
        }

        public void a(int i, LayoutManager layoutManager) {
            layoutManager.offsetChildrenVertical(i);
        }

        public float a(Point point, int i, int i2) {
            return (float) (i2 - point.y);
        }

        public boolean a(Point point, int i, int i2, int i3, int i4) {
            return point.y - i2 < i3 + i4 && point.y + i2 > (-i4);
        }

        public boolean a(a aVar) {
            boolean z;
            View d = aVar.d();
            View e = aVar.e();
            int height = aVar.getHeight() + aVar.f();
            if (aVar.getDecoratedTop(d) <= (-aVar.f()) || aVar.getPosition(d) <= 0) {
                z = false;
            } else {
                z = true;
            }
            boolean z2;
            if (aVar.getDecoratedBottom(e) >= height || aVar.getPosition(e) >= aVar.getItemCount() - 1) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z || r3) {
                return true;
            }
            return false;
        }

        public int c(int i, int i2) {
            return i2;
        }

        public boolean b() {
            return false;
        }

        public boolean a() {
            return true;
        }

        public int a(int i) {
            return 0;
        }

        public int b(int i) {
            return i;
        }
    }

    abstract a createHelper();
}
