package cn.xiaochuankeji.tieba.ui.tag.a;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;

public class d extends Callback {
    private final a a;

    public d(Context context, a aVar) {
        this.a = aVar;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return Callback.makeMovementFlags(15, 0);
        }
        return Callback.makeMovementFlags(3, 48);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        this.a.a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        this.a.a(viewHolder.getAdapterPosition());
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if (i == 1) {
            viewHolder.itemView.setTranslationX(f);
        } else {
            super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        }
    }

    public void onSelectedChanged(ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof b)) {
            a(viewHolder.itemView, 1.2f);
            ((b) viewHolder).a();
        }
        super.onSelectedChanged(viewHolder, i);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        a(viewHolder.itemView, 1.0f);
        if (viewHolder instanceof b) {
            ((b) viewHolder).b();
        }
    }

    public void a(View view, float f) {
        view.setScaleX(f);
        view.setScaleY(f);
    }
}
