package qsbk.app.im;

import java.util.List;

class ds implements Runnable {
    final /* synthetic */ dr a;

    ds(dr drVar) {
        this.a = drVar;
    }

    public void run() {
        if (this.a.a.aP) {
            List list = this.a.a.af.get(this.a.a.getToId(), this.a.a.aO);
            if (list.size() > 0) {
                this.a.a.aO = ((ChatMsg) list.get(0)).dbid;
            }
            this.a.a.y.post(new dt(this, list));
            return;
        }
        this.a.a.aM = 0;
        this.a.a.aK = 0;
    }
}
