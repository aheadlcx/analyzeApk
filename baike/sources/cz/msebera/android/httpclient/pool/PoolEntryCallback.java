package cz.msebera.android.httpclient.pool;

public interface PoolEntryCallback<T, C> {
    void process(PoolEntry<T, C> poolEntry);
}
