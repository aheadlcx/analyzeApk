package org.apache.commons.httpclient.auth;

import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.alipay.sdk.app.statistic.c;
import com.budejie.www.R$styleable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ParameterFormatter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DigestScheme extends RFC2617Scheme {
    private static final char[] HEXADECIMAL = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Log LOG;
    private static final String NC = "00000001";
    private static final int QOP_AUTH = 2;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_MISSING = 0;
    static Class class$org$apache$commons$httpclient$auth$DigestScheme;
    private String cnonce;
    private boolean complete;
    private final ParameterFormatter formatter;
    private int qopVariant;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$auth$DigestScheme == null) {
            class$ = class$("org.apache.commons.httpclient.auth.DigestScheme");
            class$org$apache$commons$httpclient$auth$DigestScheme = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$auth$DigestScheme;
        }
        LOG = LogFactory.getLog(class$);
    }

    public DigestScheme() {
        this.qopVariant = 0;
        this.complete = false;
        this.formatter = new ParameterFormatter();
    }

    public String getID() {
        String realm = getRealm();
        String parameter = getParameter("nonce");
        if (parameter != null) {
            return new StringBuffer().append(realm).append("-").append(parameter).toString();
        }
        return realm;
    }

    public DigestScheme(String str) throws MalformedChallengeException {
        this();
        processChallenge(str);
    }

    public void processChallenge(String str) throws MalformedChallengeException {
        super.processChallenge(str);
        if (getParameter("realm") == null) {
            throw new MalformedChallengeException("missing realm in challange");
        } else if (getParameter("nonce") == null) {
            throw new MalformedChallengeException("missing nonce in challange");
        } else {
            boolean z = false;
            String parameter = getParameter("qop");
            if (parameter != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    parameter = stringTokenizer.nextToken().trim();
                    if (parameter.equals(c.d)) {
                        this.qopVariant = 2;
                        break;
                    } else if (parameter.equals("auth-int")) {
                        this.qopVariant = 1;
                    } else {
                        LOG.warn(new StringBuffer().append("Unsupported qop detected: ").append(parameter).toString());
                        z = true;
                    }
                }
            }
            if (z && this.qopVariant == 0) {
                throw new MalformedChallengeException("None of the qop methods is supported");
            }
            this.cnonce = createCnonce();
            this.complete = true;
        }
    }

    public boolean isComplete() {
        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equalsIgnoreCase(getParameter("stale"))) {
            return false;
        }
        return this.complete;
    }

    public String getSchemeName() {
        return "digest";
    }

    public boolean isConnectionBased() {
        return false;
    }

    public String authenticate(Credentials credentials, String str, String str2) throws AuthenticationException {
        LOG.trace("enter DigestScheme.authenticate(Credentials, String, String)");
        try {
            UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
            getParameters().put("methodname", str);
            getParameters().put("uri", str2);
            return new StringBuffer().append("Digest ").append(createDigestHeader(usernamePasswordCredentials.getUserName(), createDigest(usernamePasswordCredentials.getUserName(), usernamePasswordCredentials.getPassword()))).toString();
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for digest authentication: ").append(credentials.getClass().getName()).toString());
        }
    }

    public String authenticate(Credentials credentials, HttpMethod httpMethod) throws AuthenticationException {
        LOG.trace("enter DigestScheme.authenticate(Credentials, HttpMethod)");
        try {
            UsernamePasswordCredentials usernamePasswordCredentials = (UsernamePasswordCredentials) credentials;
            getParameters().put("methodname", httpMethod.getName());
            StringBuffer stringBuffer = new StringBuffer(httpMethod.getPath());
            String queryString = httpMethod.getQueryString();
            if (queryString != null) {
                if (queryString.indexOf("?") != 0) {
                    stringBuffer.append("?");
                }
                stringBuffer.append(httpMethod.getQueryString());
            }
            getParameters().put("uri", stringBuffer.toString());
            if (getParameter("charset") == null) {
                getParameters().put("charset", httpMethod.getParams().getCredentialCharset());
            }
            return new StringBuffer().append("Digest ").append(createDigestHeader(usernamePasswordCredentials.getUserName(), createDigest(usernamePasswordCredentials.getUserName(), usernamePasswordCredentials.getPassword()))).toString();
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException(new StringBuffer().append("Credentials cannot be used for digest authentication: ").append(credentials.getClass().getName()).toString());
        }
    }

    private String createDigest(String str, String str2) throws AuthenticationException {
        LOG.trace("enter DigestScheme.createDigest(String, String, Map)");
        String str3 = "MD5";
        String parameter = getParameter("uri");
        String parameter2 = getParameter("realm");
        String parameter3 = getParameter("nonce");
        String parameter4 = getParameter("qop");
        String parameter5 = getParameter("methodname");
        str3 = getParameter("algorithm");
        if (str3 == null) {
            str3 = "MD5";
        }
        String parameter6 = getParameter("charset");
        if (parameter6 == null) {
            parameter6 = "ISO-8859-1";
        }
        if (this.qopVariant == 1) {
            LOG.warn("qop=auth-int is not supported");
            throw new AuthenticationException("Unsupported qop in HTTP Digest authentication");
        }
        try {
            StringBuffer stringBuffer;
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuffer stringBuffer2 = new StringBuffer(((str.length() + parameter2.length()) + str2.length()) + 2);
            stringBuffer2.append(str);
            stringBuffer2.append(':');
            stringBuffer2.append(parameter2);
            stringBuffer2.append(':');
            stringBuffer2.append(str2);
            parameter2 = stringBuffer2.toString();
            if (str3.equals("MD5-sess")) {
                str3 = encode(instance.digest(EncodingUtil.getBytes(parameter2, parameter6)));
                stringBuffer = new StringBuffer(((str3.length() + parameter3.length()) + this.cnonce.length()) + 2);
                stringBuffer.append(str3);
                stringBuffer.append(':');
                stringBuffer.append(parameter3);
                stringBuffer.append(':');
                stringBuffer.append(this.cnonce);
                str3 = stringBuffer.toString();
            } else {
                if (!str3.equals("MD5")) {
                    LOG.warn(new StringBuffer().append("Unhandled algorithm ").append(str3).append(" requested").toString());
                }
                str3 = parameter2;
            }
            parameter6 = encode(instance.digest(EncodingUtil.getBytes(str3, parameter6)));
            str3 = null;
            if (this.qopVariant == 1) {
                LOG.error("Unhandled qop auth-int");
            } else {
                str3 = new StringBuffer().append(parameter5).append(":").append(parameter).toString();
            }
            str3 = encode(instance.digest(EncodingUtil.getAsciiBytes(str3)));
            if (this.qopVariant == 0) {
                LOG.debug("Using null qop method");
                stringBuffer = new StringBuffer((parameter6.length() + parameter3.length()) + str3.length());
                stringBuffer.append(parameter6);
                stringBuffer.append(':');
                stringBuffer.append(parameter3);
                stringBuffer.append(':');
                stringBuffer.append(str3);
                str3 = stringBuffer.toString();
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug(new StringBuffer().append("Using qop method ").append(parameter4).toString());
                }
                parameter2 = getQopVariantString();
                StringBuffer stringBuffer3 = new StringBuffer((((((parameter6.length() + parameter3.length()) + NC.length()) + this.cnonce.length()) + parameter2.length()) + str3.length()) + 5);
                stringBuffer3.append(parameter6);
                stringBuffer3.append(':');
                stringBuffer3.append(parameter3);
                stringBuffer3.append(':');
                stringBuffer3.append(NC);
                stringBuffer3.append(':');
                stringBuffer3.append(this.cnonce);
                stringBuffer3.append(':');
                stringBuffer3.append(parameter2);
                stringBuffer3.append(':');
                stringBuffer3.append(str3);
                str3 = stringBuffer3.toString();
            }
            return encode(instance.digest(EncodingUtil.getAsciiBytes(str3)));
        } catch (Exception e) {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
    }

    private String createDigestHeader(String str, String str2) throws AuthenticationException {
        LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
        String parameter = getParameter("uri");
        String parameter2 = getParameter("realm");
        String parameter3 = getParameter("nonce");
        String parameter4 = getParameter("opaque");
        String parameter5 = getParameter("algorithm");
        List arrayList = new ArrayList(20);
        arrayList.add(new NameValuePair(HistoryOpenHelper.COLUMN_USERNAME, str));
        arrayList.add(new NameValuePair("realm", parameter2));
        arrayList.add(new NameValuePair("nonce", parameter3));
        arrayList.add(new NameValuePair("uri", parameter));
        arrayList.add(new NameValuePair("response", str2));
        if (this.qopVariant != 0) {
            arrayList.add(new NameValuePair("qop", getQopVariantString()));
            arrayList.add(new NameValuePair("nc", NC));
            arrayList.add(new NameValuePair("cnonce", this.cnonce));
        }
        if (parameter5 != null) {
            arrayList.add(new NameValuePair("algorithm", parameter5));
        }
        if (parameter4 != null) {
            arrayList.add(new NameValuePair("opaque", parameter4));
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj;
            boolean z;
            NameValuePair nameValuePair = (NameValuePair) arrayList.get(i);
            if (i > 0) {
                stringBuffer.append(", ");
            }
            if ("nc".equals(nameValuePair.getName()) || "qop".equals(nameValuePair.getName())) {
                obj = 1;
            } else {
                obj = null;
            }
            ParameterFormatter parameterFormatter = this.formatter;
            if (obj == null) {
                z = true;
            } else {
                z = false;
            }
            parameterFormatter.setAlwaysUseQuotes(z);
            this.formatter.format(stringBuffer, nameValuePair);
        }
        return stringBuffer.toString();
    }

    private String getQopVariantString() {
        if (this.qopVariant == 1) {
            return "auth-int";
        }
        return c.d;
    }

    private static String encode(byte[] bArr) {
        LOG.trace("enter DigestScheme.encode(byte[])");
        if (bArr.length != 16) {
            return null;
        }
        char[] cArr = new char[32];
        for (int i = 0; i < 16; i++) {
            int i2 = bArr[i] & 15;
            cArr[i * 2] = HEXADECIMAL[(bArr[i] & R$styleable.Theme_Custom_shape_cmt_like4_bg) >> 4];
            cArr[(i * 2) + 1] = HEXADECIMAL[i2];
        }
        return new String(cArr);
    }

    public static String createCnonce() {
        LOG.trace("enter DigestScheme.createCnonce()");
        String str = "MD5";
        try {
            return encode(MessageDigest.getInstance("MD5").digest(EncodingUtil.getAsciiBytes(Long.toString(System.currentTimeMillis()))));
        } catch (NoSuchAlgorithmException e) {
            throw new HttpClientError("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
    }
}
