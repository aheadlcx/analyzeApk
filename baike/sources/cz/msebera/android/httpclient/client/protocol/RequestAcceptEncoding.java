package cz.msebera.android.httpclient.client.protocol;

import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpRequestInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.util.List;

@Immutable
public class RequestAcceptEncoding implements HttpRequestInterceptor {
    private final String a;

    public RequestAcceptEncoding(List<String> list) {
        if (list == null || list.isEmpty()) {
            this.a = "gzip,deflate";
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                stringBuilder.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            stringBuilder.append((String) list.get(i));
        }
        this.a = stringBuilder.toString();
    }

    public RequestAcceptEncoding() {
        this(null);
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        if (!httpRequest.containsHeader(HttpHeaders.ACCEPT_ENCODING)) {
            httpRequest.addHeader(HttpHeaders.ACCEPT_ENCODING, this.a);
        }
    }
}
