package qsbk.app.im;

import java.util.List;

class gt implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ IMMessageListFragment b;

    gt(IMMessageListFragment iMMessageListFragment, String str) {
        this.b = iMMessageListFragment;
        this.a = str;
    }

    public void run() {
        b a = this.b.a(this.b.j);
        List list = a.b;
        this.b.C = a.c;
        this.b.D = a.d;
        this.b.f.post(new gu(this, list));
    }
}
