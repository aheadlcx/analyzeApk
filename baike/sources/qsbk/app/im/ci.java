package qsbk.app.im;

class ci implements Runnable {
    final /* synthetic */ cg a;

    ci(cg cgVar) {
        this.a = cgVar;
    }

    public void run() {
        this.a.b.a.d.setListSelection(this.a.b.a.g.getCount());
        if (this.a.b.a.as) {
            this.a.b.a.d.setCanRefresh(true);
        }
    }
}
