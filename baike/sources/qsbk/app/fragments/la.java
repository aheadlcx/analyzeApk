package qsbk.app.fragments;

class la implements Runnable {
    final /* synthetic */ kz a;

    la(kz kzVar) {
        this.a = kzVar;
    }

    public void run() {
        this.a.a.T();
        this.a.a.U();
        this.a.a.i.notifyDataSetChanged();
        if (this.a.a.m != null) {
            this.a.a.m.post(this.a.a.R);
        }
    }
}
