package cn.xiaochuankeji.tieba.ui.ugcvideodetail;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class i extends ItemDecoration {
    private int a = 0;

    public i(int i) {
        this.a = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int itemCount;
        if (recyclerView.getAdapter() != null) {
            itemCount = recyclerView.getAdapter().getItemCount();
        } else {
            itemCount = 0;
        }
        if (itemCount == 0) {
            rect.set(0, 0, 0, 0);
        } else if (recyclerView.getLayoutManager().getPosition(view) == 0) {
            rect.set(this.a, 0, this.a, 0);
        } else {
            rect.set(0, 0, this.a, 0);
        }
    }
}
