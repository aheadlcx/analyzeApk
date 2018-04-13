package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.client.entity.InputStreamFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

final class d implements InputStreamFactory {
    d() {
    }

    public InputStream create(InputStream inputStream) throws IOException {
        return new GZIPInputStream(inputStream);
    }
}
