package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.annotation.Obsolete;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.protocol.HttpContext;

@Obsolete
@Immutable
public class NetscapeDraftSpecProvider implements CookieSpecProvider {
    private final String[] a;
    private volatile CookieSpec b;

    public NetscapeDraftSpecProvider(String[] strArr) {
        this.a = strArr;
    }

    public NetscapeDraftSpecProvider() {
        this(null);
    }

    public CookieSpec create(HttpContext httpContext) {
        if (this.b == null) {
            synchronized (this) {
                if (this.b == null) {
                    this.b = new NetscapeDraftSpec(this.a);
                }
            }
        }
        return this.b;
    }
}
