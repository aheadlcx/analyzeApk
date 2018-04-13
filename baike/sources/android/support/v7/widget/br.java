package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;

class br implements a {
    final /* synthetic */ RecyclerView a;

    br(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public ViewHolder findViewHolder(int i) {
        ViewHolder a = this.a.a(i, true);
        if (a == null || this.a.f.c(a.itemView)) {
            return null;
        }
        return a;
    }

    public void offsetPositionsForRemovingInvisible(int i, int i2) {
        this.a.a(i, i2, true);
        this.a.C = true;
        State state = this.a.B;
        state.b += i2;
    }

    public void offsetPositionsForRemovingLaidOutOrNewView(int i, int i2) {
        this.a.a(i, i2, false);
        this.a.C = true;
    }

    public void markViewHoldersUpdated(int i, int i2, Object obj) {
        this.a.a(i, i2, obj);
        this.a.D = true;
    }

    public void onDispatchFirstPass(b bVar) {
        a(bVar);
    }

    void a(b bVar) {
        switch (bVar.a) {
            case 1:
                this.a.m.onItemsAdded(this.a, bVar.b, bVar.d);
                return;
            case 2:
                this.a.m.onItemsRemoved(this.a, bVar.b, bVar.d);
                return;
            case 4:
                this.a.m.onItemsUpdated(this.a, bVar.b, bVar.d, bVar.c);
                return;
            case 8:
                this.a.m.onItemsMoved(this.a, bVar.b, bVar.d, 1);
                return;
            default:
                return;
        }
    }

    public void onDispatchSecondPass(b bVar) {
        a(bVar);
    }

    public void offsetPositionsForAdd(int i, int i2) {
        this.a.e(i, i2);
        this.a.C = true;
    }

    public void offsetPositionsForMove(int i, int i2) {
        this.a.d(i, i2);
        this.a.C = true;
    }
}
