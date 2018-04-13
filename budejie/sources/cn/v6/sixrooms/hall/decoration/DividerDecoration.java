package cn.v6.sixrooms.hall.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class DividerDecoration extends ItemDecoration {
    private ColorDrawable a;
    private int b;
    private int c;
    private int d;
    private boolean e = true;
    private boolean f = false;

    public DividerDecoration(int i, int i2) {
        this.a = new ColorDrawable(i);
        this.b = i2;
    }

    public DividerDecoration(int i, int i2, int i3, int i4) {
        this.a = new ColorDrawable(i);
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public void setDrawLastItem(boolean z) {
        this.e = z;
    }

    public void setDrawHeaderFooter(boolean z) {
        this.f = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int orientation;
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else {
            orientation = 0;
        }
        if ((childAdapterPosition >= 0 && childAdapterPosition < recyclerView.getAdapter().getItemCount() + 0) || this.f) {
            if (orientation == 1) {
                rect.bottom = this.b;
            } else {
                rect.right = this.b;
            }
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
        int orientation;
        int paddingLeft;
        int i;
        int i2 = 0;
        int itemCount = recyclerView.getAdapter().getItemCount() + 0;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else {
            orientation = 0;
        }
        if (orientation == 1) {
            paddingLeft = this.c + recyclerView.getPaddingLeft();
            i = paddingLeft;
            paddingLeft = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.d;
        } else {
            paddingLeft = this.c + recyclerView.getPaddingTop();
            i = paddingLeft;
            paddingLeft = (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - this.d;
        }
        int childCount = recyclerView.getChildCount();
        while (i2 < childCount) {
            View childAt = recyclerView.getChildAt(i2);
            int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt);
            if ((childAdapterPosition >= 0 && childAdapterPosition < itemCount - 1) || ((childAdapterPosition == itemCount - 1 && this.e) || ((childAdapterPosition < 0 || childAdapterPosition >= itemCount) && this.f))) {
                LayoutParams layoutParams;
                if (orientation == 1) {
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    childAdapterPosition = layoutParams.bottomMargin + childAt.getBottom();
                    this.a.setBounds(i, childAdapterPosition, paddingLeft, this.b + childAdapterPosition);
                    this.a.draw(canvas);
                } else {
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    childAdapterPosition = layoutParams.rightMargin + childAt.getRight();
                    this.a.setBounds(childAdapterPosition, i, this.b + childAdapterPosition, paddingLeft);
                    this.a.draw(canvas);
                }
            }
            i2++;
        }
    }
}
