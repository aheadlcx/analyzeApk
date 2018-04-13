package cz.msebera.android.httpclient.impl.conn;

import java.io.IOException;

protected class SingleClientConnManager$PoolEntry extends AbstractPoolEntry {
    final /* synthetic */ SingleClientConnManager f;

    protected SingleClientConnManager$PoolEntry(SingleClientConnManager singleClientConnManager) {
        this.f = singleClientConnManager;
        super(singleClientConnManager.b, null);
    }

    protected void b() throws IOException {
        a();
        if (this.b.isOpen()) {
            this.b.close();
        }
    }

    protected void c() throws IOException {
        a();
        if (this.b.isOpen()) {
            this.b.shutdown();
        }
    }
}
