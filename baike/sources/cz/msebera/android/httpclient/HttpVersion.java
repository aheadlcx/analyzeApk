package cz.msebera.android.httpclient;

import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.Serializable;

@Immutable
public final class HttpVersion extends ProtocolVersion implements Serializable {
    public static final String HTTP = "HTTP";
    public static final HttpVersion HTTP_0_9 = new HttpVersion(0, 9);
    public static final HttpVersion HTTP_1_0 = new HttpVersion(1, 0);
    public static final HttpVersion HTTP_1_1 = new HttpVersion(1, 1);

    public HttpVersion(int i, int i2) {
        super(HTTP, i, i2);
    }

    public ProtocolVersion forVersion(int i, int i2) {
        if (i == this.b && i2 == this.c) {
            return this;
        }
        if (i == 1) {
            if (i2 == 0) {
                return HTTP_1_0;
            }
            if (i2 == 1) {
                return HTTP_1_1;
            }
        }
        if (i == 0 && i2 == 9) {
            return HTTP_0_9;
        }
        this(i, i2);
        return this;
    }
}
