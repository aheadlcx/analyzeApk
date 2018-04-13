package android.support.v7.widget.helper;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView.ViewHolder;

class c extends b {
    final /* synthetic */ int a;
    final /* synthetic */ ViewHolder b;
    final /* synthetic */ ItemTouchHelper c;

    c(ItemTouchHelper itemTouchHelper, ViewHolder viewHolder, int i, int i2, float f, float f2, float f3, float f4, int i3, ViewHolder viewHolder2) {
        this.c = itemTouchHelper;
        this.a = i3;
        this.b = viewHolder2;
        super(viewHolder, i, i2, f, f2, f3, f4);
    }

    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        if (!this.m) {
            if (this.a <= 0) {
                this.c.l.clearView(this.c.p, this.b);
            } else {
                this.c.a.add(this.b.itemView);
                this.mIsPendingCleanup = true;
                if (this.a > 0) {
                    this.c.a((b) this, this.a);
                }
            }
            if (this.c.s == this.b.itemView) {
                this.c.a(this.b.itemView);
            }
        }
    }
}
