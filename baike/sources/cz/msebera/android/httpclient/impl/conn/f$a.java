package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.pool.ConnFactory;
import java.io.IOException;

class f$a implements ConnFactory<HttpRoute, OperatedClientConnection> {
    private final ClientConnectionOperator a;

    f$a(ClientConnectionOperator clientConnectionOperator) {
        this.a = clientConnectionOperator;
    }

    public OperatedClientConnection create(HttpRoute httpRoute) throws IOException {
        return this.a.createConnection();
    }
}
