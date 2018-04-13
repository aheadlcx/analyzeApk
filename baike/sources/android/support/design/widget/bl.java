package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

class bl {
    private final View a;
    private int b;
    private int c;
    private int d;
    private int e;

    public bl(View view) {
        this.a = view;
    }

    public void onViewLayout() {
        this.b = this.a.getTop();
        this.c = this.a.getLeft();
        a();
    }

    private void a() {
        ViewCompat.offsetTopAndBottom(this.a, this.d - (this.a.getTop() - this.b));
        ViewCompat.offsetLeftAndRight(this.a, this.e - (this.a.getLeft() - this.c));
    }

    public boolean setTopAndBottomOffset(int i) {
        if (this.d == i) {
            return false;
        }
        this.d = i;
        a();
        return true;
    }

    public boolean setLeftAndRightOffset(int i) {
        if (this.e == i) {
            return false;
        }
        this.e = i;
        a();
        return true;
    }

    public int getTopAndBottomOffset() {
        return this.d;
    }

    public int getLeftAndRightOffset() {
        return this.e;
    }

    public int getLayoutTop() {
        return this.b;
    }

    public int getLayoutLeft() {
        return this.c;
    }
}
