package qsbk.app.im;

class gw implements Runnable {
    final /* synthetic */ gv a;

    gw(gv gvVar) {
        this.a = gvVar;
    }

    public void run() {
        this.a.c.g();
        this.a.c.i.remove(this.a.b);
        this.a.c.q.removeItem(this.a.b, false);
        this.a.c.q.notifyDataSetChanged();
    }
}
