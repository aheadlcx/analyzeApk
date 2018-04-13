package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.ChallengeState;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@NotThreadSafe
public abstract class RFC2617Scheme extends AuthSchemeBase implements Serializable {
    private final Map<String, String> a;
    private transient Charset b;

    @Deprecated
    public RFC2617Scheme(ChallengeState challengeState) {
        super(challengeState);
        this.a = new HashMap();
        this.b = Consts.ASCII;
    }

    public RFC2617Scheme(Charset charset) {
        this.a = new HashMap();
        if (charset == null) {
            charset = Consts.ASCII;
        }
        this.b = charset;
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public Charset getCredentialsCharset() {
        return this.b != null ? this.b : Consts.ASCII;
    }

    String a(HttpRequest httpRequest) {
        String str = (String) httpRequest.getParams().getParameter(AuthPNames.CREDENTIAL_CHARSET);
        if (str == null) {
            return getCredentialsCharset().name();
        }
        return str;
    }

    protected void a(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        HeaderElement[] parseElements = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new ParserCursor(i, charArrayBuffer.length()));
        this.a.clear();
        for (HeaderElement headerElement : parseElements) {
            this.a.put(headerElement.getName().toLowerCase(Locale.ROOT), headerElement.getValue());
        }
    }

    protected Map<String, String> a() {
        return this.a;
    }

    public String getParameter(String str) {
        if (str == null) {
            return null;
        }
        return (String) this.a.get(str.toLowerCase(Locale.ROOT));
    }

    public String getRealm() {
        return getParameter("realm");
    }
}
