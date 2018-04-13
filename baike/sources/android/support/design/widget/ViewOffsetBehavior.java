package android.support.design.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.util.AttributeSet;
import android.view.View;

class ViewOffsetBehavior<V extends View> extends Behavior<V> {
    private bl a;
    private int b = 0;
    private int c = 0;

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        a(coordinatorLayout, v, i);
        if (this.a == null) {
            this.a = new bl(v);
        }
        this.a.onViewLayout();
        if (this.b != 0) {
            this.a.setTopAndBottomOffset(this.b);
            this.b = 0;
        }
        if (this.c != 0) {
            this.a.setLeftAndRightOffset(this.c);
            this.c = 0;
        }
        return true;
    }

    protected void a(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.onLayoutChild(v, i);
    }

    public boolean setTopAndBottomOffset(int i) {
        if (this.a != null) {
            return this.a.setTopAndBottomOffset(i);
        }
        this.b = i;
        return false;
    }

    public boolean setLeftAndRightOffset(int i) {
        if (this.a != null) {
            return this.a.setLeftAndRightOffset(i);
        }
        this.c = i;
        return false;
    }

    public int getTopAndBottomOffset() {
        return this.a != null ? this.a.getTopAndBottomOffset() : 0;
    }

    public int getLeftAndRightOffset() {
        return this.a != null ? this.a.getLeftAndRightOffset() : 0;
    }
}
