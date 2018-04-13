package qsbk.app.activity;

class ph implements Runnable {
    final /* synthetic */ pg a;

    ph(pg pgVar) {
        this.a = pgVar;
    }

    public void run() {
        this.a.a.a.clear();
        this.a.a.a.addAll(this.a.a.t.getImageFolders());
        this.a.a.c.notifyDataSetChanged();
        this.a.a.a(0);
    }
}
