package cz.msebera.android.httpclient.client.entity;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamFactory {
    InputStream create(InputStream inputStream) throws IOException;
}
