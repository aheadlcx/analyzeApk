package cz.msebera.android.httpclient.pool;

class c implements PoolEntryCallback<T, C> {
    final /* synthetic */ long a;
    final /* synthetic */ AbstractConnPool b;

    c(AbstractConnPool abstractConnPool, long j) {
        this.b = abstractConnPool;
        this.a = j;
    }

    public void process(PoolEntry<T, C> poolEntry) {
        if (poolEntry.getUpdated() <= this.a) {
            poolEntry.close();
        }
    }
}
