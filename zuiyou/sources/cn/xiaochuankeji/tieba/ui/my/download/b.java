package cn.xiaochuankeji.tieba.ui.my.download;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class b extends ItemDecoration {
    private int a;
    private int b;

    public b(int i) {
        this.a = i;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int itemViewType = recyclerView.getAdapter().getItemViewType(childAdapterPosition);
        for (int i = childAdapterPosition; i >= 0; i--) {
            if (recyclerView.getAdapter().getItemViewType(i) == 1) {
                this.b = i;
                break;
            }
        }
        if (itemViewType == 2) {
            rect.right = this.a;
            rect.bottom = this.a;
            if ((childAdapterPosition - this.b) % 4 == 0) {
                rect.right = 0;
            }
        }
    }
}
