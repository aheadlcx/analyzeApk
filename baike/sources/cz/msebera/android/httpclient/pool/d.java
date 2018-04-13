package cz.msebera.android.httpclient.pool;

class d implements PoolEntryCallback<T, C> {
    final /* synthetic */ long a;
    final /* synthetic */ AbstractConnPool b;

    d(AbstractConnPool abstractConnPool, long j) {
        this.b = abstractConnPool;
        this.a = j;
    }

    public void process(PoolEntry<T, C> poolEntry) {
        if (poolEntry.isExpired(this.a)) {
            poolEntry.close();
        }
    }
}
