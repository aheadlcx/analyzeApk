package android.support.v7.widget.helper;

import android.support.v7.widget.RecyclerView.ItemAnimator;

class d implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ int b;
    final /* synthetic */ ItemTouchHelper c;

    d(ItemTouchHelper itemTouchHelper, b bVar, int i) {
        this.c = itemTouchHelper;
        this.a = bVar;
        this.b = i;
    }

    public void run() {
        if (this.c.p != null && this.c.p.isAttachedToWindow() && !this.a.m && this.a.h.getAdapterPosition() != -1) {
            ItemAnimator itemAnimator = this.c.p.getItemAnimator();
            if ((itemAnimator == null || !itemAnimator.isRunning(null)) && !this.c.a()) {
                this.c.l.onSwiped(this.a.h, this.b);
            } else {
                this.c.p.post(this);
            }
        }
    }
}
