package cn.xiaochuankeji.tieba.ui.videomaker.music;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class e extends ItemDecoration {
    private int a = cn.xiaochuankeji.tieba.ui.utils.e.a(15.0f);

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int itemCount;
        if (recyclerView.getAdapter() != null) {
            itemCount = recyclerView.getAdapter().getItemCount();
        } else {
            itemCount = 0;
        }
        if (itemCount == 0) {
            rect.set(0, 0, 0, 0);
        } else if (recyclerView.getLayoutManager().getPosition(view) == itemCount - 1) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(0, 0, this.a, 0);
        }
    }
}
