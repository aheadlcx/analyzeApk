package org.apache.commons.httpclient;

import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConstants {
    public static final String DEFAULT_CONTENT_CHARSET = "ISO-8859-1";
    public static final String HTTP_ELEMENT_CHARSET = "US-ASCII";
    private static final Log LOG;
    static Class class$org$apache$commons$httpclient$HttpConstants;

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static {
        Class class$;
        if (class$org$apache$commons$httpclient$HttpConstants == null) {
            class$ = class$("org.apache.commons.httpclient.HttpConstants");
            class$org$apache$commons$httpclient$HttpConstants = class$;
        } else {
            class$ = class$org$apache$commons$httpclient$HttpConstants;
        }
        LOG = LogFactory.getLog(class$);
    }

    public static byte[] getBytes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
            }
            return str.getBytes();
        }
    }

    public static String getString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return new String(bArr, i, i2, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Unsupported encoding: US-ASCII. System default encoding used");
            }
            return new String(bArr, i, i2);
        }
    }

    public static String getString(byte[] bArr) {
        return getString(bArr, 0, bArr.length);
    }

    public static byte[] getContentBytes(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        if (str2 == null || str2.equals("")) {
            str2 = "ISO-8859-1";
        }
        try {
            return str.getBytes(str2);
        } catch (UnsupportedEncodingException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(new StringBuffer().append("Unsupported encoding: ").append(str2).append(". HTTP default encoding used").toString());
            }
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e2) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
                }
                return str.getBytes();
            }
        }
    }

    public static String getContentString(byte[] bArr, int i, int i2, String str) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        if (str == null || str.equals("")) {
            str = "ISO-8859-1";
        }
        try {
            return new String(bArr, i, i2, str);
        } catch (UnsupportedEncodingException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(new StringBuffer().append("Unsupported encoding: ").append(str).append(". Default HTTP encoding used").toString());
            }
            try {
                return new String(bArr, i, i2, "ISO-8859-1");
            } catch (UnsupportedEncodingException e2) {
                if (LOG.isWarnEnabled()) {
                    LOG.warn("Unsupported encoding: ISO-8859-1. System encoding used");
                }
                return new String(bArr, i, i2);
            }
        }
    }

    public static String getContentString(byte[] bArr, String str) {
        return getContentString(bArr, 0, bArr.length, str);
    }

    public static byte[] getContentBytes(String str) {
        return getContentBytes(str, null);
    }

    public static String getContentString(byte[] bArr, int i, int i2) {
        return getContentString(bArr, i, i2, null);
    }

    public static String getContentString(byte[] bArr) {
        return getContentString(bArr, null);
    }

    public static byte[] getAsciiBytes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("HttpClient requires ASCII support");
        }
    }

    public static String getAsciiString(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("Parameter may not be null");
        }
        try {
            return new String(bArr, i, i2, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("HttpClient requires ASCII support");
        }
    }

    public static String getAsciiString(byte[] bArr) {
        return getAsciiString(bArr, 0, bArr.length);
    }
}
