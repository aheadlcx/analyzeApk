package cz.msebera.android.httpclient.client.cache;

import java.io.IOException;

public class HttpCacheEntrySerializationException extends IOException {
    public HttpCacheEntrySerializationException(String str) {
    }

    public HttpCacheEntrySerializationException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
