package qsbk.app.im.datastore;

class bd implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ SyncMsgStore a;

    bd(SyncMsgStore syncMsgStore) {
        this.a = syncMsgStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
