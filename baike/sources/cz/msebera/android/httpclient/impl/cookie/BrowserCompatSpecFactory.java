package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecFactory;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.cookie.params.CookieSpecPNames;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.HttpContext;
import java.util.Collection;

@Immutable
@Deprecated
public class BrowserCompatSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    private final BrowserCompatSpecFactory$SecurityLevel a;
    private final CookieSpec b;

    public BrowserCompatSpecFactory(String[] strArr, BrowserCompatSpecFactory$SecurityLevel browserCompatSpecFactory$SecurityLevel) {
        this.a = browserCompatSpecFactory$SecurityLevel;
        this.b = new BrowserCompatSpec(strArr, browserCompatSpecFactory$SecurityLevel);
    }

    public BrowserCompatSpecFactory(String[] strArr) {
        this(null, BrowserCompatSpecFactory$SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public BrowserCompatSpecFactory() {
        this(null, BrowserCompatSpecFactory$SecurityLevel.SECURITYLEVEL_DEFAULT);
    }

    public CookieSpec newInstance(HttpParams httpParams) {
        if (httpParams == null) {
            return new BrowserCompatSpec(null, this.a);
        }
        String[] strArr;
        Collection collection = (Collection) httpParams.getParameter(CookieSpecPNames.DATE_PATTERNS);
        if (collection != null) {
            strArr = (String[]) collection.toArray(new String[collection.size()]);
        } else {
            strArr = null;
        }
        return new BrowserCompatSpec(strArr, this.a);
    }

    public CookieSpec create(HttpContext httpContext) {
        return this.b;
    }
}
