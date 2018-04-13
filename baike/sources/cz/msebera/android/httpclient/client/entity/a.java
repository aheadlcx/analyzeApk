package cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;

class a implements InputStreamFactory {
    a() {
    }

    public InputStream create(InputStream inputStream) throws IOException {
        return new DeflateInputStream(inputStream);
    }
}
