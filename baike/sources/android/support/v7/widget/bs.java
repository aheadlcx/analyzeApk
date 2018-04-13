package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

class bs implements b {
    final /* synthetic */ LayoutManager a;

    bs(LayoutManager layoutManager) {
        this.a = layoutManager;
    }

    public int getChildCount() {
        return this.a.getChildCount();
    }

    public View getParent() {
        return this.a.q;
    }

    public View getChildAt(int i) {
        return this.a.getChildAt(i);
    }

    public int getParentStart() {
        return this.a.getPaddingLeft();
    }

    public int getParentEnd() {
        return this.a.getWidth() - this.a.getPaddingRight();
    }

    public int getChildStart(View view) {
        return this.a.getDecoratedLeft(view) - ((LayoutParams) view.getLayoutParams()).leftMargin;
    }

    public int getChildEnd(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.rightMargin + this.a.getDecoratedRight(view);
    }
}
