package android.support.v7.widget.helper;

import android.support.v4.view.ViewCompat;

class a implements Runnable {
    final /* synthetic */ ItemTouchHelper a;

    a(ItemTouchHelper itemTouchHelper) {
        this.a = itemTouchHelper;
    }

    public void run() {
        if (this.a.b != null && this.a.b()) {
            if (this.a.b != null) {
                this.a.a(this.a.b);
            }
            this.a.p.removeCallbacks(this.a.q);
            ViewCompat.postOnAnimation(this.a.p, this);
        }
    }
}
