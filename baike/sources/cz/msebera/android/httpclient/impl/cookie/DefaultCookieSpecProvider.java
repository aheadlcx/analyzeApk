package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcher;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.protocol.HttpContext;

@Immutable
public class DefaultCookieSpecProvider implements CookieSpecProvider {
    private final CompatibilityLevel a;
    private final PublicSuffixMatcher b;
    private final String[] c;
    private final boolean d;
    private volatile CookieSpec e;

    public enum CompatibilityLevel {
        DEFAULT,
        IE_MEDIUM_SECURITY
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher, String[] strArr, boolean z) {
        if (compatibilityLevel == null) {
            compatibilityLevel = CompatibilityLevel.DEFAULT;
        }
        this.a = compatibilityLevel;
        this.b = publicSuffixMatcher;
        this.c = strArr;
        this.d = z;
    }

    public DefaultCookieSpecProvider(CompatibilityLevel compatibilityLevel, PublicSuffixMatcher publicSuffixMatcher) {
        this(compatibilityLevel, publicSuffixMatcher, null, false);
    }

    public DefaultCookieSpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(CompatibilityLevel.DEFAULT, publicSuffixMatcher, null, false);
    }

    public DefaultCookieSpecProvider() {
        this(CompatibilityLevel.DEFAULT, null, null, false);
    }

    public CookieSpec create(HttpContext httpContext) {
        if (this.e == null) {
            synchronized (this) {
                if (this.e == null) {
                    b bVar;
                    RFC2965Spec rFC2965Spec = new RFC2965Spec(this.d, new RFC2965VersionAttributeHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new RFC2965DomainAttributeHandler(), this.b), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler());
                    RFC2109Spec rFC2109Spec = new RFC2109Spec(this.d, new RFC2109VersionHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new RFC2109DomainHandler(), this.b), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler());
                    CommonCookieAttributeHandler[] commonCookieAttributeHandlerArr = new CommonCookieAttributeHandler[5];
                    commonCookieAttributeHandlerArr[0] = PublicSuffixDomainFilter.decorate(new BasicDomainHandler(), this.b);
                    if (this.a == CompatibilityLevel.IE_MEDIUM_SECURITY) {
                        bVar = new b(this);
                    } else {
                        bVar = new BasicPathHandler();
                    }
                    commonCookieAttributeHandlerArr[1] = bVar;
                    commonCookieAttributeHandlerArr[2] = new BasicSecureHandler();
                    commonCookieAttributeHandlerArr[3] = new BasicCommentHandler();
                    commonCookieAttributeHandlerArr[4] = new BasicExpiresHandler(this.c != null ? (String[]) this.c.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
                    this.e = new DefaultCookieSpec(rFC2965Spec, rFC2109Spec, new NetscapeDraftSpec(commonCookieAttributeHandlerArr));
                }
            }
        }
        return this.e;
    }
}
