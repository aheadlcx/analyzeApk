package qsbk.app.im;

import java.util.List;

class ce implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ cd b;

    ce(cd cdVar, List list) {
        this.b = cdVar;
        this.a = list;
    }

    public void run() {
        if (this.a.size() > 0) {
            this.b.a.a.g.insertMsg(this.a);
            this.b.a.a.g.notifyDataSetChanged();
            this.b.a.a.a(this.a);
        }
        if (this.a.size() < 20) {
            this.b.a.a.as = false;
        }
        this.b.a.a.d.setListSelection(this.a.size());
    }
}
