package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.List;

@ThreadSafe
public class DefaultCookieSpec implements CookieSpec {
    private final RFC2965Spec a;
    private final RFC2109Spec b;
    private final NetscapeDraftSpec c;

    DefaultCookieSpec(RFC2965Spec rFC2965Spec, RFC2109Spec rFC2109Spec, NetscapeDraftSpec netscapeDraftSpec) {
        this.a = rFC2965Spec;
        this.b = rFC2109Spec;
        this.c = netscapeDraftSpec;
    }

    public DefaultCookieSpec(String[] strArr, boolean z) {
        this.a = new RFC2965Spec(z, new RFC2965VersionAttributeHandler(), new BasicPathHandler(), new RFC2965DomainAttributeHandler(), new RFC2965PortAttributeHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler(), new RFC2965CommentUrlAttributeHandler(), new RFC2965DiscardAttributeHandler());
        this.b = new RFC2109Spec(z, new RFC2109VersionHandler(), new BasicPathHandler(), new RFC2109DomainHandler(), new BasicMaxAgeHandler(), new BasicSecureHandler(), new BasicCommentHandler());
        CommonCookieAttributeHandler[] commonCookieAttributeHandlerArr = new CommonCookieAttributeHandler[5];
        commonCookieAttributeHandlerArr[0] = new BasicDomainHandler();
        commonCookieAttributeHandlerArr[1] = new BasicPathHandler();
        commonCookieAttributeHandlerArr[2] = new BasicSecureHandler();
        commonCookieAttributeHandlerArr[3] = new BasicCommentHandler();
        commonCookieAttributeHandlerArr[4] = new BasicExpiresHandler(strArr != null ? (String[]) strArr.clone() : new String[]{"EEE, dd-MMM-yy HH:mm:ss z"});
        this.c = new NetscapeDraftSpec(commonCookieAttributeHandlerArr);
    }

    public DefaultCookieSpec() {
        this(null, false);
    }

    public List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        HeaderElement[] elements = header.getElements();
        int i = 0;
        int i2 = 0;
        for (HeaderElement headerElement : elements) {
            if (headerElement.getParameterByName("version") != null) {
                i2 = 1;
            }
            if (headerElement.getParameterByName("expires") != null) {
                i = 1;
            }
        }
        if (i != 0 || r0 == 0) {
            CharArrayBuffer buffer;
            ParserCursor parserCursor;
            NetscapeDraftHeaderParser netscapeDraftHeaderParser = NetscapeDraftHeaderParser.DEFAULT;
            if (header instanceof FormattedHeader) {
                buffer = ((FormattedHeader) header).getBuffer();
                parserCursor = new ParserCursor(((FormattedHeader) header).getValuePos(), buffer.length());
            } else {
                String value = header.getValue();
                if (value == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                buffer = new CharArrayBuffer(value.length());
                buffer.append(value);
                parserCursor = new ParserCursor(0, buffer.length());
            }
            return this.c.a(new HeaderElement[]{netscapeDraftHeaderParser.parseHeader(buffer, parserCursor)}, cookieOrigin);
        } else if (SM.SET_COOKIE2.equals(header.getName())) {
            return this.a.a(elements, cookieOrigin);
        } else {
            return this.b.a(elements, cookieOrigin);
        }
    }

    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            this.c.validate(cookie, cookieOrigin);
        } else if (cookie instanceof SetCookie2) {
            this.a.validate(cookie, cookieOrigin);
        } else {
            this.b.validate(cookie, cookieOrigin);
        }
    }

    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        if (cookie.getVersion() <= 0) {
            return this.c.match(cookie, cookieOrigin);
        }
        if (cookie instanceof SetCookie2) {
            return this.a.match(cookie, cookieOrigin);
        }
        return this.b.match(cookie, cookieOrigin);
    }

    public List<Header> formatCookies(List<Cookie> list) {
        Args.notNull(list, "List of cookies");
        int i = Integer.MAX_VALUE;
        Object obj = 1;
        for (Cookie cookie : list) {
            int version;
            if (!(cookie instanceof SetCookie2)) {
                obj = null;
            }
            if (cookie.getVersion() < i) {
                version = cookie.getVersion();
            } else {
                version = i;
            }
            i = version;
        }
        if (i <= 0) {
            return this.c.formatCookies(list);
        }
        if (obj != null) {
            return this.a.formatCookies(list);
        }
        return this.b.formatCookies(list);
    }

    public int getVersion() {
        return this.a.getVersion();
    }

    public Header getVersionHeader() {
        return null;
    }

    public String toString() {
        return "default";
    }
}
