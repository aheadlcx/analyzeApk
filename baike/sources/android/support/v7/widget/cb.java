package android.support.v7.widget;

class cb implements Runnable {
    final /* synthetic */ SearchView a;

    cb(SearchView searchView) {
        this.a = searchView;
    }

    public void run() {
        if (this.a.g != null && (this.a.g instanceof cq)) {
            this.a.g.changeCursor(null);
        }
    }
}
