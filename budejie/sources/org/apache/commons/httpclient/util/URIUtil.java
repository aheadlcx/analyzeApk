package org.apache.commons.httpclient.util;

import java.util.BitSet;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;

public class URIUtil {
    protected static final BitSet empty = new BitSet(1);

    protected static class Coder extends URI {
        protected Coder() {
        }

        public static char[] encode(String str, BitSet bitSet, String str2) throws URIException {
            return URI.encode(str, bitSet, str2);
        }

        public static String decode(char[] cArr, String str) throws URIException {
            return URI.decode(cArr, str);
        }

        public static boolean verifyEscaped(char[] cArr) {
            int i = 0;
            while (i < cArr.length) {
                char c = cArr[i];
                if (c > '') {
                    return false;
                }
                if (c == '%') {
                    i++;
                    if (Character.digit(cArr[i], 16) == -1) {
                        return false;
                    }
                    i++;
                    if (Character.digit(cArr[i], 16) == -1) {
                        return false;
                    }
                }
                i++;
            }
            return true;
        }

        public static String replace(String str, char[] cArr, char[] cArr2) {
            for (int length = cArr.length; length > 0; length--) {
                str = replace(str, cArr[length], cArr2[length]);
            }
            return str.toString();
        }

        public static String replace(String str, char c, char c2) {
            StringBuffer stringBuffer = new StringBuffer(str.length());
            int i = 0;
            while (true) {
                int indexOf = str.indexOf(c);
                if (indexOf >= 0) {
                    stringBuffer.append(str.substring(0, indexOf));
                    stringBuffer.append(c2);
                } else {
                    stringBuffer.append(str.substring(i));
                }
                if (indexOf < 0) {
                    return stringBuffer.toString();
                }
                i = indexOf;
            }
        }
    }

    public static String getName(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String path = getPath(str);
        int lastIndexOf = path.lastIndexOf("/");
        return lastIndexOf >= 0 ? path.substring(lastIndexOf + 1, path.length()) : path;
    }

    public static String getQuery(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return null;
        }
        int indexOf = str.indexOf("//");
        String str2 = "/";
        if (indexOf >= 0 && str.lastIndexOf("/", indexOf - 1) < 0) {
            i = indexOf + 2;
        }
        indexOf = str.indexOf(str2, i);
        i = str.length();
        indexOf = str.indexOf("?", indexOf);
        if (indexOf < 0) {
            return null;
        }
        String str3;
        indexOf++;
        if (str.lastIndexOf("#") > indexOf) {
            i = str.lastIndexOf("#");
        }
        if (indexOf < 0 || indexOf == i) {
            str3 = null;
        } else {
            str3 = str.substring(indexOf, i);
        }
        return str3;
    }

    public static String getPath(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("//");
        String str2 = "/";
        if (indexOf >= 0 && str.lastIndexOf("/", indexOf - 1) < 0) {
            i = indexOf + 2;
        }
        int indexOf2 = str.indexOf(str2, i);
        i = str.length();
        if (str.indexOf(63, indexOf2) != -1) {
            i = str.indexOf(63, indexOf2);
        }
        if (str.lastIndexOf("#") > indexOf2 && str.lastIndexOf("#") < r0) {
            i = str.lastIndexOf("#");
        }
        if (indexOf2 >= 0) {
            return str.substring(indexOf2, i);
        }
        if (indexOf >= 0) {
            return "/";
        }
        return str;
    }

    public static String getPathQuery(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("//");
        String str2 = "/";
        if (indexOf >= 0 && str.lastIndexOf("/", indexOf - 1) < 0) {
            i = indexOf + 2;
        }
        int indexOf2 = str.indexOf(str2, i);
        i = str.length();
        if (str.lastIndexOf("#") > indexOf2) {
            i = str.lastIndexOf("#");
        }
        if (indexOf2 >= 0) {
            return str.substring(indexOf2, i);
        }
        if (indexOf >= 0) {
            return "/";
        }
        return str;
    }

    public static String getFromPath(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf("//");
        String str2 = "/";
        if (indexOf >= 0 && str.lastIndexOf("/", indexOf - 1) < 0) {
            i = indexOf + 2;
        }
        i = str.indexOf(str2, i);
        if (i >= 0) {
            return str.substring(i);
        }
        if (indexOf >= 0) {
            return "/";
        }
        return str;
    }

    public static String encodeAll(String str) throws URIException {
        return encodeAll(str, URI.getDefaultProtocolCharset());
    }

    public static String encodeAll(String str, String str2) throws URIException {
        return encode(str, empty, str2);
    }

    public static String encodeWithinAuthority(String str) throws URIException {
        return encodeWithinAuthority(str, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinAuthority(String str, String str2) throws URIException {
        return encode(str, URI.allowed_within_authority, str2);
    }

    public static String encodePathQuery(String str) throws URIException {
        return encodePathQuery(str, URI.getDefaultProtocolCharset());
    }

    public static String encodePathQuery(String str, String str2) throws URIException {
        int indexOf = str.indexOf(63);
        if (indexOf < 0) {
            return encode(str, URI.allowed_abs_path, str2);
        }
        return new StringBuffer().append(encode(str.substring(0, indexOf), URI.allowed_abs_path, str2)).append('?').append(encode(str.substring(indexOf + 1), URI.allowed_query, str2)).toString();
    }

    public static String encodeWithinPath(String str) throws URIException {
        return encodeWithinPath(str, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinPath(String str, String str2) throws URIException {
        return encode(str, URI.allowed_within_path, str2);
    }

    public static String encodePath(String str) throws URIException {
        return encodePath(str, URI.getDefaultProtocolCharset());
    }

    public static String encodePath(String str, String str2) throws URIException {
        return encode(str, URI.allowed_abs_path, str2);
    }

    public static String encodeWithinQuery(String str) throws URIException {
        return encodeWithinQuery(str, URI.getDefaultProtocolCharset());
    }

    public static String encodeWithinQuery(String str, String str2) throws URIException {
        return encode(str, URI.allowed_within_query, str2);
    }

    public static String encodeQuery(String str) throws URIException {
        return encodeQuery(str, URI.getDefaultProtocolCharset());
    }

    public static String encodeQuery(String str, String str2) throws URIException {
        return encode(str, URI.allowed_query, str2);
    }

    public static String encode(String str, BitSet bitSet) throws URIException {
        return encode(str, bitSet, URI.getDefaultProtocolCharset());
    }

    public static String encode(String str, BitSet bitSet, String str2) throws URIException {
        return EncodingUtil.getAsciiString(URLCodec.encodeUrl(bitSet, EncodingUtil.getBytes(str, str2)));
    }

    public static String decode(String str) throws URIException {
        try {
            return EncodingUtil.getString(URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(str)), URI.getDefaultProtocolCharset());
        } catch (Throwable e) {
            throw new URIException(e.getMessage());
        }
    }

    public static String decode(String str, String str2) throws URIException {
        return Coder.decode(str.toCharArray(), str2);
    }
}
