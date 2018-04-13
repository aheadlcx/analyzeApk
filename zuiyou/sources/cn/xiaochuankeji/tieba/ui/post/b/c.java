package cn.xiaochuankeji.tieba.ui.post.b;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class c extends ItemDecoration {
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        if (recyclerView.getLayoutManager().getPosition(view) == 0) {
            rect.set(e.a(13.0f), 0, 0, 0);
        }
    }
}
