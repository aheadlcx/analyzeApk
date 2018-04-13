package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

class bq implements b {
    final /* synthetic */ RecyclerView a;

    bq(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public int getChildCount() {
        return this.a.getChildCount();
    }

    public void addView(View view, int i) {
        this.a.addView(view, i);
        this.a.f(view);
    }

    public int indexOfChild(View view) {
        return this.a.indexOfChild(view);
    }

    public void removeViewAt(int i) {
        View childAt = this.a.getChildAt(i);
        if (childAt != null) {
            this.a.e(childAt);
            childAt.clearAnimation();
        }
        this.a.removeViewAt(i);
    }

    public View getChildAt(int i) {
        return this.a.getChildAt(i);
    }

    public void removeAllViews() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            this.a.e(childAt);
            childAt.clearAnimation();
        }
        this.a.removeAllViews();
    }

    public ViewHolder getChildViewHolder(View view) {
        return RecyclerView.b(view);
    }

    public void attachViewToParent(View view, int i, LayoutParams layoutParams) {
        ViewHolder b = RecyclerView.b(view);
        if (b != null) {
            if (b.n() || b.c()) {
                b.h();
            } else {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + b + this.a.a());
            }
        }
        this.a.attachViewToParent(view, i, layoutParams);
    }

    public void detachViewFromParent(int i) {
        View childAt = getChildAt(i);
        if (childAt != null) {
            ViewHolder b = RecyclerView.b(childAt);
            if (b != null) {
                if (!b.n() || b.c()) {
                    b.b(256);
                } else {
                    throw new IllegalArgumentException("called detach on an already detached child " + b + this.a.a());
                }
            }
        }
        this.a.detachViewFromParent(i);
    }

    public void onEnteredHiddenState(View view) {
        ViewHolder b = RecyclerView.b(view);
        if (b != null) {
            b.a(this.a);
        }
    }

    public void onLeftHiddenState(View view) {
        ViewHolder b = RecyclerView.b(view);
        if (b != null) {
            b.b(this.a);
        }
    }
}
