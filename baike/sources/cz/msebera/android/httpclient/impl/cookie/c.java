package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.FormattedHeader;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.annotation.ThreadSafe;
import cz.msebera.android.httpclient.cookie.CommonCookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.Cookie;
import cz.msebera.android.httpclient.cookie.CookieAttributeHandler;
import cz.msebera.android.httpclient.cookie.CookieOrigin;
import cz.msebera.android.httpclient.cookie.CookiePriorityComparator;
import cz.msebera.android.httpclient.cookie.CookieSpec;
import cz.msebera.android.httpclient.cookie.MalformedCookieException;
import cz.msebera.android.httpclient.cookie.SM;
import cz.msebera.android.httpclient.cookie.SetCookie;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.message.TokenParser;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
class c implements CookieSpec {
    private static final BitSet a = TokenParser.INIT_BITSET(61, 59);
    private static final BitSet b = TokenParser.INIT_BITSET(59);
    private static final BitSet c = TokenParser.INIT_BITSET(32, 34, 44, 59, 92);
    private final CookieAttributeHandler[] d;
    private final Map<String, CookieAttributeHandler> e;
    private final TokenParser f;

    c(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        this.d = (CookieAttributeHandler[]) commonCookieAttributeHandlerArr.clone();
        this.e = new ConcurrentHashMap(commonCookieAttributeHandlerArr.length);
        for (CommonCookieAttributeHandler commonCookieAttributeHandler : commonCookieAttributeHandlerArr) {
            this.e.put(commonCookieAttributeHandler.getAttributeName().toLowerCase(Locale.ROOT), commonCookieAttributeHandler);
        }
        this.f = TokenParser.INSTANCE;
    }

    static String a(CookieOrigin cookieOrigin) {
        String path = cookieOrigin.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        if (lastIndexOf < 0) {
            return path;
        }
        if (lastIndexOf == 0) {
            lastIndexOf = 1;
        }
        return path.substring(0, lastIndexOf);
    }

    static String b(CookieOrigin cookieOrigin) {
        return cookieOrigin.getHost();
    }

    public final List<Cookie> parse(Header header, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(header, "Header");
        Args.notNull(cookieOrigin, "Cookie origin");
        if (header.getName().equalsIgnoreCase(SM.SET_COOKIE)) {
            ParserCursor parserCursor;
            CharArrayBuffer charArrayBuffer;
            String value;
            if (header instanceof FormattedHeader) {
                CharArrayBuffer buffer = ((FormattedHeader) header).getBuffer();
                parserCursor = new ParserCursor(((FormattedHeader) header).getValuePos(), buffer.length());
                charArrayBuffer = buffer;
            } else {
                value = header.getValue();
                if (value == null) {
                    throw new MalformedCookieException("Header value is null");
                }
                charArrayBuffer = new CharArrayBuffer(value.length());
                charArrayBuffer.append(value);
                parserCursor = new ParserCursor(0, charArrayBuffer.length());
            }
            String parseToken = this.f.parseToken(charArrayBuffer, parserCursor, a);
            if (parseToken.length() == 0) {
                throw new MalformedCookieException("Cookie name is invalid: '" + header.toString() + "'");
            } else if (parserCursor.atEnd()) {
                throw new MalformedCookieException("Cookie value is invalid: '" + header.toString() + "'");
            } else {
                char charAt = charArrayBuffer.charAt(parserCursor.getPos());
                parserCursor.updatePos(parserCursor.getPos() + 1);
                if (charAt != '=') {
                    throw new MalformedCookieException("Cookie value is invalid: '" + header.toString() + "'");
                }
                String parseValue = this.f.parseValue(charArrayBuffer, parserCursor, b);
                if (!parserCursor.atEnd()) {
                    parserCursor.updatePos(parserCursor.getPos() + 1);
                }
                SetCookie basicClientCookie = new BasicClientCookie(parseToken, parseValue);
                basicClientCookie.setPath(a(cookieOrigin));
                basicClientCookie.setDomain(b(cookieOrigin));
                basicClientCookie.setCreationDate(new Date());
                Map linkedHashMap = new LinkedHashMap();
                while (!parserCursor.atEnd()) {
                    String parseToken2 = this.f.parseToken(charArrayBuffer, parserCursor, a);
                    parseToken = null;
                    if (!parserCursor.atEnd()) {
                        char charAt2 = charArrayBuffer.charAt(parserCursor.getPos());
                        parserCursor.updatePos(parserCursor.getPos() + 1);
                        if (charAt2 == '=') {
                            parseToken = this.f.parseToken(charArrayBuffer, parserCursor, b);
                            if (!parserCursor.atEnd()) {
                                parserCursor.updatePos(parserCursor.getPos() + 1);
                            }
                        }
                    }
                    basicClientCookie.setAttribute(parseToken2.toLowerCase(Locale.ROOT), parseToken);
                    linkedHashMap.put(parseToken2, parseToken);
                }
                if (linkedHashMap.containsKey("max-age")) {
                    linkedHashMap.remove("expires");
                }
                for (Entry entry : linkedHashMap.entrySet()) {
                    String str = (String) entry.getKey();
                    value = (String) entry.getValue();
                    CookieAttributeHandler cookieAttributeHandler = (CookieAttributeHandler) this.e.get(str);
                    if (cookieAttributeHandler != null) {
                        cookieAttributeHandler.parse(basicClientCookie, value);
                    }
                }
                return Collections.singletonList(basicClientCookie);
            }
        }
        throw new MalformedCookieException("Unrecognized cookie header: '" + header.toString() + "'");
    }

    public final void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler validate : this.d) {
            validate.validate(cookie, cookieOrigin);
        }
    }

    public final boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, SM.COOKIE);
        Args.notNull(cookieOrigin, "Cookie origin");
        for (CookieAttributeHandler match : this.d) {
            if (!match.match(cookie, cookieOrigin)) {
                return false;
            }
        }
        return true;
    }

    public List<Header> formatCookies(List<Cookie> list) {
        Args.notEmpty((Collection) list, "List of cookies");
        if (list.size() > 1) {
            List<Cookie> arrayList = new ArrayList(list);
            Collections.sort(arrayList, CookiePriorityComparator.INSTANCE);
            list = arrayList;
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(list.size() * 20);
        charArrayBuffer.append(SM.COOKIE);
        charArrayBuffer.append(": ");
        for (int i = 0; i < list.size(); i++) {
            Cookie cookie = (Cookie) list.get(i);
            if (i > 0) {
                charArrayBuffer.append(';');
                charArrayBuffer.append((char) TokenParser.SP);
            }
            charArrayBuffer.append(cookie.getName());
            String value = cookie.getValue();
            if (value != null) {
                charArrayBuffer.append('=');
                if (a((CharSequence) value)) {
                    charArrayBuffer.append('\"');
                    for (int i2 = 0; i2 < value.length(); i2++) {
                        char charAt = value.charAt(i2);
                        if (charAt == '\"' || charAt == TokenParser.ESCAPE) {
                            charArrayBuffer.append((char) TokenParser.ESCAPE);
                        }
                        charArrayBuffer.append(charAt);
                    }
                    charArrayBuffer.append('\"');
                } else {
                    charArrayBuffer.append(value);
                }
            }
        }
        List<Header> arrayList2 = new ArrayList(1);
        arrayList2.add(new BufferedHeader(charArrayBuffer));
        return arrayList2;
    }

    boolean a(CharSequence charSequence) {
        return a(charSequence, c);
    }

    boolean a(CharSequence charSequence, BitSet bitSet) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (bitSet.get(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public final int getVersion() {
        return 0;
    }

    public final Header getVersionHeader() {
        return null;
    }
}
