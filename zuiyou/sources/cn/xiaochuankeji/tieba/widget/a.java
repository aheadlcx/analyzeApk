package cn.xiaochuankeji.tieba.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import cn.xiaochuan.base.BaseApplication;

public class a extends ItemDecoration {
    int a = ((int) TypedValue.applyDimension(1, 16.0f, BaseApplication.getAppContext().getResources().getDisplayMetrics()));

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            int viewLayoutPosition = ((RecyclerView.LayoutParams) layoutParams).getViewLayoutPosition();
            Adapter adapter = recyclerView.getAdapter();
            if ((adapter == null ? 0 : adapter.getItemCount()) - 1 == viewLayoutPosition) {
                rect.set(0, 0, 0, this.a);
                return;
            }
        }
        super.getItemOffsets(rect, view, recyclerView, state);
    }
}
