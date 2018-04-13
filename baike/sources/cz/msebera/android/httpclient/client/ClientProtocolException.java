package cz.msebera.android.httpclient.client;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.IOException;

@Immutable
public class ClientProtocolException extends IOException {
    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }

    public ClientProtocolException(String str, Throwable th) {
        super(str);
        initCause(th);
    }
}
