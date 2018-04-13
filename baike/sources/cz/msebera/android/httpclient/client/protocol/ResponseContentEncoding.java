package cz.msebera.android.httpclient.client.protocol;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.entity.DecompressingEntity;
import cz.msebera.android.httpclient.client.entity.InputStreamFactory;
import cz.msebera.android.httpclient.config.Lookup;
import cz.msebera.android.httpclient.config.RegistryBuilder;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.io.IOException;
import java.util.Locale;

@Immutable
public class ResponseContentEncoding implements HttpResponseInterceptor {
    public static final String UNCOMPRESSED = "http.client.response.uncompressed";
    private static final InputStreamFactory a = new d();
    private static final InputStreamFactory b = new e();
    private final Lookup<InputStreamFactory> c;

    public ResponseContentEncoding(Lookup<InputStreamFactory> lookup) {
        if (lookup == null) {
            lookup = RegistryBuilder.create().register("gzip", a).register("x-gzip", a).register("deflate", b).build();
        }
        this.c = lookup;
    }

    public ResponseContentEncoding() {
        this(null);
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpEntity entity = httpResponse.getEntity();
        if (HttpClientContext.adapt(httpContext).getRequestConfig().isDecompressionEnabled() && entity != null && entity.getContentLength() != 0) {
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                for (HeaderElement headerElement : contentEncoding.getElements()) {
                    String toLowerCase = headerElement.getName().toLowerCase(Locale.ROOT);
                    InputStreamFactory inputStreamFactory = (InputStreamFactory) this.c.lookup(toLowerCase);
                    if (inputStreamFactory != null) {
                        httpResponse.setEntity(new DecompressingEntity(httpResponse.getEntity(), inputStreamFactory));
                        httpResponse.removeHeaders("Content-Length");
                        httpResponse.removeHeaders("Content-Encoding");
                        httpResponse.removeHeaders(HttpHeaders.CONTENT_MD5);
                    } else if (!HTTP.IDENTITY_CODING.equals(toLowerCase)) {
                        throw new HttpException("Unsupported Content-Coding: " + headerElement.getName());
                    }
                }
            }
        }
    }
}
