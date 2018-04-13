package android.support.v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.ItemAnimator.ItemHolderInfo;
import android.support.v7.widget.RecyclerView.ViewHolder;

class bp implements b {
    final /* synthetic */ RecyclerView a;

    bp(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public void processDisappeared(ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @Nullable ItemHolderInfo itemHolderInfo2) {
        this.a.d.c(viewHolder);
        this.a.b(viewHolder, itemHolderInfo, itemHolderInfo2);
    }

    public void processAppeared(ViewHolder viewHolder, ItemHolderInfo itemHolderInfo, ItemHolderInfo itemHolderInfo2) {
        this.a.a(viewHolder, itemHolderInfo, itemHolderInfo2);
    }

    public void processPersistent(ViewHolder viewHolder, @NonNull ItemHolderInfo itemHolderInfo, @NonNull ItemHolderInfo itemHolderInfo2) {
        viewHolder.setIsRecyclable(false);
        if (this.a.w) {
            if (this.a.x.animateChange(viewHolder, viewHolder, itemHolderInfo, itemHolderInfo2)) {
                this.a.n();
            }
        } else if (this.a.x.animatePersistence(viewHolder, itemHolderInfo, itemHolderInfo2)) {
            this.a.n();
        }
    }

    public void unused(ViewHolder viewHolder) {
        this.a.m.removeAndRecycleView(viewHolder.itemView, this.a.d);
    }
}
