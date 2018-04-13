package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.View;

class bt implements b {
    final /* synthetic */ LayoutManager a;

    bt(LayoutManager layoutManager) {
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
        return this.a.getPaddingTop();
    }

    public int getParentEnd() {
        return this.a.getHeight() - this.a.getPaddingBottom();
    }

    public int getChildStart(View view) {
        return this.a.getDecoratedTop(view) - ((LayoutParams) view.getLayoutParams()).topMargin;
    }

    public int getChildEnd(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.bottomMargin + this.a.getDecoratedBottom(view);
    }
}
