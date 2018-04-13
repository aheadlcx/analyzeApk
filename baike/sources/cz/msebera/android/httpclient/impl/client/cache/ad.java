package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.HttpResponse;
import java.io.IOException;

class ad extends ab {
    final /* synthetic */ ac a;

    ad(ac acVar, HttpResponse httpResponse) {
        this.a = acVar;
        super(httpResponse);
    }

    public void close() throws IOException {
        this.a.d.close();
    }
}
