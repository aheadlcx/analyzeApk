package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.client.entity.DeflateInputStream;
import cz.msebera.android.httpclient.client.entity.InputStreamFactory;
import java.io.IOException;
import java.io.InputStream;

final class e implements InputStreamFactory {
    e() {
    }

    public InputStream create(InputStream inputStream) throws IOException {
        return new DeflateInputStream(inputStream);
    }
}
