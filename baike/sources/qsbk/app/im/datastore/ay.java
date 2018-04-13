package qsbk.app.im.datastore;

class ay implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ GroupStore a;

    ay(GroupStore groupStore) {
        this.a = groupStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
