package cn.htjyb.b.a;

public interface b {

    public interface a {
        void b();
    }

    public interface b {
        void a(boolean z, boolean z2, String str);
    }

    void cancelQuery();

    boolean hasMore();

    int itemCount();

    void queryMore();

    void refresh();

    void registerOnClearListener(a aVar);

    void registerOnListUpdateListener(cn.htjyb.b.a.a.a aVar);

    void registerOnQueryFinishListener(b bVar);

    void unregisterOnClearListener(a aVar);

    void unregisterOnListUpdateListener(cn.htjyb.b.a.a.a aVar);

    void unregisterOnQueryFinishedListener(b bVar);
}
