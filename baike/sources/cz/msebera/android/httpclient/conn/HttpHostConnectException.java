package cz.msebera.android.httpclient.conn;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.util.Arrays;

@Immutable
public class HttpHostConnectException extends ConnectException {
    private final HttpHost a;

    @Deprecated
    public HttpHostConnectException(HttpHost httpHost, ConnectException connectException) {
        this(connectException, httpHost, (InetAddress[]) null);
    }

    public HttpHostConnectException(IOException iOException, HttpHost httpHost, InetAddress... inetAddressArr) {
        StringBuilder append = new StringBuilder().append("Connect to ").append(httpHost != null ? httpHost.toHostString() : "remote host");
        String str = (inetAddressArr == null || inetAddressArr.length <= 0) ? "" : MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Arrays.asList(inetAddressArr);
        append = append.append(str);
        if (iOException == null || iOException.getMessage() == null) {
            str = " refused";
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
