package qsbk.app.im;

import java.util.List;

class dx implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ dw b;

    dx(dw dwVar, List list) {
        this.b = dwVar;
        this.a = list;
    }

    public void run() {
        this.b.a.aP = this.a.size() == 20;
        if (!this.b.a.aP) {
            this.b.a.d.stopPullToRefresh();
        }
        this.b.a.g.appendItem(this.a);
        this.b.a.g.notifyDataSetChanged();
        if (this.b.a.g.getCount() > 0) {
            this.b.a.d.post(new dy(this));
        }
        this.b.a.L();
    }
}
