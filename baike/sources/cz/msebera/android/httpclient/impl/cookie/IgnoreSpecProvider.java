package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.protocol.HttpContext;

@Immutable
public class IgnoreSpecProvider implements CookieSpecProvider {
    private volatile CookieSpec a;

    public CookieSpec create(HttpContext httpContext) {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    this.a = new IgnoreSpec();
                }
            }
        }
        return this.a;
    }
}
