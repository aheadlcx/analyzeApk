package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.HttpResponse;

public interface ConnectionBackoffStrategy {
    boolean shouldBackoff(HttpResponse httpResponse);

    boolean shouldBackoff(Throwable th);
}
