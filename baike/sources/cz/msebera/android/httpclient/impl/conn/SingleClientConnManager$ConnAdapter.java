package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.routing.HttpRoute;

protected class SingleClientConnManager$ConnAdapter extends AbstractPooledConnAdapter {
    final /* synthetic */ SingleClientConnManager b;

    protected SingleClientConnManager$ConnAdapter(SingleClientConnManager singleClientConnManager, SingleClientConnManager$PoolEntry singleClientConnManager$PoolEntry, HttpRoute httpRoute) {
        this.b = singleClientConnManager;
        super(singleClientConnManager, singleClientConnManager$PoolEntry);
        markReusable();
        singleClientConnManager$PoolEntry.c = httpRoute;
    }
}
