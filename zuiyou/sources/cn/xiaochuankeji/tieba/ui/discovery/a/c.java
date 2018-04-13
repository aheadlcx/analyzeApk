package cn.xiaochuankeji.tieba.ui.discovery.a;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class c extends ItemDecoration {
    private int a = e.a(4.0f);

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        rect.left = this.a;
        rect.right = this.a;
    }
}
