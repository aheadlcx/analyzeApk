package com.spriteapp.booklibrary.widget.xrecyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class XRecyclerView$DividerItemDecoration extends ItemDecoration {
    private Drawable mDivider;
    private int mOrientation;
    final /* synthetic */ XRecyclerView this$0;

    public XRecyclerView$DividerItemDecoration(XRecyclerView xRecyclerView, Drawable drawable) {
        this.this$0 = xRecyclerView;
        this.mDivider = drawable;
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, State state) {
        if (this.mOrientation == 0) {
            drawHorizontalDividers(canvas, recyclerView);
        } else if (this.mOrientation == 1) {
            drawVerticalDividers(canvas, recyclerView);
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) > XRecyclerView.access$100(this.this$0).getHeadersCount() + 1) {
            this.mOrientation = ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation();
            if (this.mOrientation == 0) {
                rect.left = this.mDivider.getIntrinsicWidth();
            } else if (this.mOrientation == 1) {
                rect.top = this.mDivider.getIntrinsicHeight();
            }
        }
    }

    private void drawHorizontalDividers(Canvas canvas, RecyclerView recyclerView) {
        int paddingTop = recyclerView.getPaddingTop();
        int height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int right = layoutParams.rightMargin + childAt.getRight();
            this.mDivider.setBounds(right, paddingTop, this.mDivider.getIntrinsicWidth() + right, height);
            this.mDivider.draw(canvas);
        }
    }

    private void drawVerticalDividers(Canvas canvas, RecyclerView recyclerView) {
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View childAt = recyclerView.getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int bottom = layoutParams.bottomMargin + childAt.getBottom();
            this.mDivider.setBounds(paddingLeft, bottom, width, this.mDivider.getIntrinsicHeight() + bottom);
            this.mDivider.draw(canvas);
        }
    }
}
