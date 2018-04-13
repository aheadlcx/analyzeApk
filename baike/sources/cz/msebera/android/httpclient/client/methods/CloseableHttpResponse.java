package cz.msebera.android.httpclient.client.methods;

import cz.msebera.android.httpclient.HttpResponse;
import java.io.Closeable;

public interface CloseableHttpResponse extends HttpResponse, Closeable {
}
