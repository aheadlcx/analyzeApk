package cz.msebera.android.httpclient.conn;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.util.Arrays;

@Immutable
public class ConnectTimeoutException extends InterruptedIOException {
    private final HttpHost a;

    public ConnectTimeoutException() {
        this.a = null;
    }

    public ConnectTimeoutException(String str) {
        super(str);
        this.a = null;
    }

    public ConnectTimeoutException(IOException iOException, HttpHost httpHost, InetAddress... inetAddressArr) {
        StringBuilder append = new StringBuilder().append("Connect to ").append(httpHost != null ? httpHost.toHostString() : "remote host");
        String str = (inetAddressArr == null || inetAddressArr.length <= 0) ? "" : MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.asList(inetAddressArr);
        append = append.append(str);
        if (iOException == null || iOException.getMessage() == null) {
            str = " timed out";
        } else {
            str = " failed: " + iOException.getMessage();
        }
        super(append.append(str).toString());
        this.a = httpHost;
        initCause(iOException);
    }

    public HttpHost getHost() {
        return this.a;
    }
}
