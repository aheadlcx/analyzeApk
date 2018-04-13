package qsbk.app.im.datastore;

class ba implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ LatestUsedCollectionStore a;

    ba(LatestUsedCollectionStore latestUsedCollectionStore) {
        this.a = latestUsedCollectionStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
