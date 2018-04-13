package cz.msebera.android.httpclient.impl.auth;

import com.alipay.sdk.app.statistic.c;
import com.xiaomi.mipush.sdk.Constants;
import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.AuthenticationException;
import cz.msebera.android.httpclient.auth.ChallengeState;
import cz.msebera.android.httpclient.auth.Credentials;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.BufferedHeader;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.EncodingUtils;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import qsbk.app.activity.security.ActionBarSecurityBindActivity;

@NotThreadSafe
public class DigestScheme extends RFC2617Scheme {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private boolean b;
    private String c;
    private long d;
    private String e;
    private String f;
    private String g;

    public DigestScheme(Charset charset) {
        super(charset);
        this.b = false;
    }

    @Deprecated
    public DigestScheme(ChallengeState challengeState) {
        super(challengeState);
    }

    public DigestScheme() {
        this(Consts.ASCII);
    }

    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.b = true;
        if (a().isEmpty()) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
    }

    public boolean isComplete() {
        if ("true".equalsIgnoreCase(getParameter("stale"))) {
            return false;
        }
        return this.b;
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isConnectionBased() {
        return false;
    }

    public void overrideParamter(String str, String str2) {
        a().put(str, str2);
    }

    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        return authenticate(credentials, httpRequest, new BasicHttpContext());
    }

    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
        Args.notNull(credentials, "Credentials");
        Args.notNull(httpRequest, "HTTP request");
        if (getParameter("realm") == null) {
            throw new AuthenticationException("missing realm in challenge");
        } else if (getParameter("nonce") == null) {
            throw new AuthenticationException("missing nonce in challenge");
        } else {
            a().put("methodname", httpRequest.getRequestLine().getMethod());
            a().put("uri", httpRequest.getRequestLine().getUri());
            if (getParameter("charset") == null) {
                a().put("charset", a(httpRequest));
            }
            return a(credentials, httpRequest);
        }
    }

    private static MessageDigest a(String str) throws UnsupportedDigestAlgorithmException {
        try {
            return MessageDigest.getInstance(str);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + str);
        }
    }

    private Header a(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        int i;
        String parameter = getParameter("uri");
        String parameter2 = getParameter("realm");
        String parameter3 = getParameter("nonce");
        String parameter4 = getParameter("opaque");
        String parameter5 = getParameter("methodname");
        String parameter6 = getParameter("algorithm");
        if (parameter6 == null) {
            parameter6 = "MD5";
        }
        Set hashSet = new HashSet(8);
        int i2 = -1;
        String parameter7 = getParameter("qop");
        if (parameter7 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(parameter7, Constants.ACCEPT_TIME_SEPARATOR_SP);
            while (stringTokenizer.hasMoreTokens()) {
                hashSet.add(stringTokenizer.nextToken().trim().toLowerCase(Locale.ROOT));
            }
            if ((httpRequest instanceof HttpEntityEnclosingRequest) && hashSet.contains("auth-int")) {
                i2 = 1;
            } else if (hashSet.contains(c.d)) {
                i2 = 2;
            }
            i = i2;
        } else {
            i = 0;
        }
        if (i == -1) {
            throw new AuthenticationException("None of the qop methods is supported: " + parameter7);
        }
        String parameter8 = getParameter("charset");
        if (parameter8 == null) {
            parameter8 = "ISO-8859-1";
        }
        if (parameter6.equalsIgnoreCase("MD5-sess")) {
            parameter7 = "MD5";
        } else {
            parameter7 = parameter6;
        }
        try {
            MessageDigest a = a(parameter7);
            String name = credentials.getUserPrincipal().getName();
            parameter7 = credentials.getPassword();
            if (parameter3.equals(this.c)) {
                this.d++;
            } else {
                this.d = 1;
                this.e = null;
                this.c = parameter3;
            }
            Appendable stringBuilder = new StringBuilder(256);
            Formatter formatter = new Formatter(stringBuilder, Locale.US);
            formatter.format("%08x", new Object[]{Long.valueOf(this.d)});
            formatter.close();
            String stringBuilder2 = stringBuilder.toString();
            if (this.e == null) {
                this.e = createCnonce();
            }
            this.f = null;
            this.g = null;
            if (parameter6.equalsIgnoreCase("MD5-sess")) {
                stringBuilder.setLength(0);
                stringBuilder.append(name).append(':').append(parameter2).append(':').append(parameter7);
                parameter7 = a(a.digest(EncodingUtils.getBytes(stringBuilder.toString(), parameter8)));
                stringBuilder.setLength(0);
                stringBuilder.append(parameter7).append(':').append(parameter3).append(':').append(this.e);
                this.f = stringBuilder.toString();
            } else {
                stringBuilder.setLength(0);
                stringBuilder.append(name).append(':').append(parameter2).append(':').append(parameter7);
                this.f = stringBuilder.toString();
            }
            String a2 = a(a.digest(EncodingUtils.getBytes(this.f, parameter8)));
            if (i == 2) {
                this.g = parameter5 + ':' + parameter;
            } else if (i == 1) {
                HttpEntity httpEntity = null;
                if (httpRequest instanceof HttpEntityEnclosingRequest) {
                    httpEntity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
                }
                if (httpEntity == null || httpEntity.isRepeatable()) {
                    OutputStream bVar = new b(a);
                    if (httpEntity != null) {
                        try {
                            httpEntity.writeTo(bVar);
                        } catch (Throwable e) {
                            throw new AuthenticationException("I/O error reading entity content", e);
                        }
                    }
                    bVar.close();
                    this.g = parameter5 + ':' + parameter + ':' + a(bVar.getDigest());
                } else if (hashSet.contains(c.d)) {
                    i = 2;
                    this.g = parameter5 + ':' + parameter;
                } else {
                    throw new AuthenticationException("Qop auth-int cannot be used with a non-repeatable entity");
                }
            } else {
                this.g = parameter5 + ':' + parameter;
            }
            parameter7 = a(a.digest(EncodingUtils.getBytes(this.g, parameter8)));
            if (i == 0) {
                stringBuilder.setLength(0);
                stringBuilder.append(a2).append(':').append(parameter3).append(':').append(parameter7);
                parameter8 = stringBuilder.toString();
            } else {
                stringBuilder.setLength(0);
                stringBuilder.append(a2).append(':').append(parameter3).append(':').append(stringBuilder2).append(':').append(this.e).append(':').append(i == 1 ? "auth-int" : c.d).append(':').append(parameter7);
                parameter8 = stringBuilder.toString();
            }
            parameter8 = a(a.digest(EncodingUtils.getAsciiBytes(parameter8)));
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(128);
            if (isProxy()) {
                charArrayBuffer.append("Proxy-Authorization");
            } else {
                charArrayBuffer.append("Authorization");
            }
            charArrayBuffer.append(": Digest ");
            List arrayList = new ArrayList(20);
            arrayList.add(new BasicNameValuePair("username", name));
            arrayList.add(new BasicNameValuePair("realm", parameter2));
            arrayList.add(new BasicNameValuePair("nonce", parameter3));
            arrayList.add(new BasicNameValuePair("uri", parameter));
            arrayList.add(new BasicNameValuePair(ActionBarSecurityBindActivity.KEY_RESPONSE, parameter8));
            if (i != 0) {
                arrayList.add(new BasicNameValuePair("qop", i == 1 ? "auth-int" : c.d));
                arrayList.add(new BasicNameValuePair("nc", stringBuilder2));
                arrayList.add(new BasicNameValuePair("cnonce", this.e));
            }
            arrayList.add(new BasicNameValuePair("algorithm", parameter6));
            if (parameter4 != null) {
                arrayList.add(new BasicNameValuePair("opaque", parameter4));
            }
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                boolean z;
                NameValuePair nameValuePair = (BasicNameValuePair) arrayList.get(i2);
                if (i2 > 0) {
                    charArrayBuffer.append(", ");
                }
                String name2 = nameValuePair.getName();
                Object obj = ("nc".equals(name2) || "qop".equals(name2) || "algorithm".equals(name2)) ? 1 : null;
                BasicHeaderValueFormatter basicHeaderValueFormatter = BasicHeaderValueFormatter.INSTANCE;
                if (obj == null) {
                    z = true;
                } else {
                    z = false;
                }
                basicHeaderValueFormatter.formatNameValuePair(charArrayBuffer, nameValuePair, z);
            }
            return new BufferedHeader(charArrayBuffer);
        } catch (UnsupportedDigestAlgorithmException e2) {
            throw new AuthenticationException("Unsuppported digest algorithm: " + parameter7);
        }
    }

    static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[(length * 2)];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i] & 15;
            cArr[i * 2] = a[(bArr[i] & 240) >> 4];
            cArr[(i * 2) + 1] = a[i2];
        }
        return new String(cArr);
    }

    public static String createCnonce() {
        byte[] bArr = new byte[8];
        new SecureRandom().nextBytes(bArr);
        return a(bArr);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DIGEST [complete=").append(this.b).append(", nonce=").append(this.c).append(", nc=").append(this.d).append("]");
        return stringBuilder.toString();
    }
}
