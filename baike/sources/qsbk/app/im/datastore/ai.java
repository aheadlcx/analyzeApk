package qsbk.app.im.datastore;

class ai implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ DraftStore a;

    ai(DraftStore draftStore) {
        this.a = draftStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
