package qsbk.app.im.datastore;

class a implements DatabaseHelper$LifeCycleListener {
    final /* synthetic */ BaseChatMsgStore a;

    a(BaseChatMsgStore baseChatMsgStore) {
        this.a = baseChatMsgStore;
    }

    public void onRelease() {
        this.a.a();
    }
}
