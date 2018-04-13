package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.annotation.Obsolete;
import cz.msebera.android.httpclient.conn.util.PublicSuffixMatcher;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.CookieSpecProvider;
import cz.msebera.android.httpclient.protocol.HttpContext;

@Obsolete
@Immutable
public class RFC2965SpecProvider implements CookieSpecProvider {
    private final PublicSuffixMatcher a;
    private final boolean b;
    private volatile CookieSpec c;

    public RFC2965SpecProvider(PublicSuffixMatcher publicSuffixMatcher, boolean z) {
        this.b = z;
        this.a = publicSuffixMatcher;
    }

    public RFC2965SpecProvider(PublicSuffixMatcher publicSuffixMatcher) {
        this(publicSuffixMatcher, false);
    }

    public RFC2965SpecProvider() {
        this(null, false);
    }

    public CookieSpec create(HttpContext httpContext) {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = new RFC2965Spec(this.b, new RFC2965VersionAttributeHandler(), new BasicPathHandler(), PublicSuffixDomainFilter.decorate(new RFC2965DomainAttributeHandler(), this.a), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler());
                }
            }
        }
        return this.c;
    }
}
