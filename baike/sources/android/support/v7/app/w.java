package android.support.v7.app;

import android.support.v4.view.ViewCompat;

class w implements Runnable {
    final /* synthetic */ AppCompatDelegateImplV9 a;

    w(AppCompatDelegateImplV9 appCompatDelegateImplV9) {
        this.a = appCompatDelegateImplV9;
    }

    public void run() {
        this.a.o.showAtLocation(this.a.n, 55, 0, 0);
        this.a.g();
        if (this.a.f()) {
            this.a.n.setAlpha(0.0f);
            this.a.q = ViewCompat.animate(this.a.n).alpha(1.0f);
            this.a.q.setListener(new x(this));
            return;
        }
        this.a.n.setAlpha(1.0f);
        this.a.n.setVisibility(0);
    }
}
