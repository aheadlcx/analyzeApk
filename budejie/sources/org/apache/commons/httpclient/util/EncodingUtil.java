package org.apache.commons.httpclient.util;

import com.ali.auth.third.login.LoginConstants;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncodingUtil {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$util$EncodingUtil;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$util$EncodingUtil == null) {
            class$ = class$("org.apache.commons.httpclient.util.EncodingUtil");
            class$org$apache$commons$httpclient$util$EncodingUtil = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$util$EncodingUtil;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static String formUrlEncode(NameValuePair[] nameValuePairArr, String str) {
        String doFormUrlEncode;
        try {
            doFormUrlEncode = doFormUrlEncode(nameValuePairArr, str);
        } catch (UnsupportedEncodingException e) {
            LOG.error(new StringBuffer().append("Encoding not supported: ").append(str).toString());
            try {
                doFormUrlEncode = doFormUrlEncode(nameValuePairArr, "ISO-8859-1");
            } catch (UnsupportedEncodingException e2) {
                throw new HttpClientError("Encoding not supported: ISO-8859-1");
            }
        }
        return doFormUrlEncode;
    }

    private static String doFormUrlEncode(NameValuePair[] nameValuePairArr, String str) throws UnsupportedEncodingException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < nameValuePairArr.length; i++) {
            URLCodec uRLCodec = new URLCodec();
            NameValuePair nameValuePair = nameValuePairArr[i];
            if (nameValuePair.getName() != null) {
                if (i > 0) {
                    stringBuffer.append("&");
                }
                stringBuffer.append(uRLCodec.encode(nameValuePair.getName(), str));
                stringBuffer.append(LoginConstants.EQUAL);
                if (nameValuePair.getValue() != null) {
                    stringBuffer.append(uRLCodec.encode(nameValuePair.getValue(), str));
                }
            }
        }
        return stringBuffer.toString();
    }

    public static String getString(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else {
            try {
                return new String(bArr, i, i2, str);
            } catch (UnsupportedEncodingException e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append("Unsupported encoding: ").append(str).append(". System encoding used").toString());
                }
                return new String(bArr, i, i2);
            }
        }
    }

    public static String getString(byte[] bArr, String str) {
        return getString(bArr, 0, bArr.length, str);
    }

    public static byte[] getBytes(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("data may not be null");
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else {
            try {
                return str.getBytes(str2);
            } catch (UnsupportedEncodingException e) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn(new StringBuffer().append("Unsupported encoding: ").append(str2).append(". System encoding used.").toString());
                }
                return str.getBytes();
            }
        }
    }

    public static byte[] getAsciiBytes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new HttpClientError("HttpClient requires ASCII support");
        }
    }

    public static String getAsciiString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return new String(bArr, i, i2, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new HttpClientError("HttpClient requires ASCII support");
        }
    }

    public static String getAsciiString(byte[] bArr) {
        return getAsciiString(bArr, 0, bArr.length);
    }

    private EncodingUtil() {
    }
}
