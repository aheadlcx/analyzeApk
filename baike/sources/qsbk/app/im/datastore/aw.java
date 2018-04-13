package qsbk.app.im.datastore;

class aw implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ GroupNoticeStore a;

    aw(GroupNoticeStore groupNoticeStore) {
        this.a = groupNoticeStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
