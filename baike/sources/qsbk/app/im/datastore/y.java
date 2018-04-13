package qsbk.app.im.datastore;

class y implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ ContactListItemStore a;

    y(ContactListItemStore contactListItemStore) {
        this.a = contactListItemStore;
    }

    public void onRelease() {
        this.a.release();
    }
}
