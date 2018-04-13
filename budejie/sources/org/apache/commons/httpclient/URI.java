package org.apache.commons.httpclient;

import com.alipay.sdk.sys.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.httpclient.util.EncodingUtil;

public class URI implements Serializable, Cloneable, Comparable {
    protected static final BitSet IPv4address = new BitSet(256);
    protected static final BitSet IPv6address = new BitSet(256);
    protected static final BitSet IPv6reference = new BitSet(256);
    protected static final BitSet URI_reference = new BitSet(256);
    protected static final BitSet abs_path = new BitSet(256);
    protected static final BitSet absoluteURI = new BitSet(256);
    public static final BitSet allowed_IPv6reference = new BitSet(256);
    public static final BitSet allowed_abs_path = new BitSet(256);
    public static final BitSet allowed_authority = new BitSet(256);
    public static final BitSet allowed_fragment = new BitSet(256);
    public static final BitSet allowed_host = new BitSet(256);
    public static final BitSet allowed_opaque_part = new BitSet(256);
    public static final BitSet allowed_query = new BitSet(256);
    public static final BitSet allowed_reg_name = new BitSet(256);
    public static final BitSet allowed_rel_path = new BitSet(256);
    public static final BitSet allowed_userinfo = new BitSet(256);
    public static final BitSet allowed_within_authority = new BitSet(256);
    public static final BitSet allowed_within_path = new BitSet(256);
    public static final BitSet allowed_within_query = new BitSet(256);
    public static final BitSet allowed_within_userinfo = new BitSet(256);
    protected static final BitSet alpha = new BitSet(256);
    protected static final BitSet alphanum = new BitSet(256);
    protected static final BitSet authority = new BitSet(256);
    public static final BitSet control = new BitSet(256);
    protected static String defaultDocumentCharset = null;
    protected static String defaultDocumentCharsetByLocale = null;
    protected static String defaultDocumentCharsetByPlatform = null;
    protected static String defaultProtocolCharset = "UTF-8";
    public static final BitSet delims = new BitSet(256);
    protected static final BitSet digit = new BitSet(256);
    public static final BitSet disallowed_opaque_part = new BitSet(256);
    public static final BitSet disallowed_rel_path = new BitSet(256);
    protected static final BitSet domainlabel = toplabel;
    protected static final BitSet escaped = new BitSet(256);
    protected static final BitSet fragment = uric;
    protected static final BitSet hex = new BitSet(256);
    protected static final BitSet hier_part = new BitSet(256);
    protected static final BitSet host = new BitSet(256);
    protected static final BitSet hostname = new BitSet(256);
    protected static final BitSet hostport = new BitSet(256);
    protected static final BitSet mark = new BitSet(256);
    protected static final BitSet net_path = new BitSet(256);
    protected static final BitSet opaque_part = new BitSet(256);
    protected static final BitSet param = pchar;
    protected static final BitSet path = new BitSet(256);
    protected static final BitSet path_segments = new BitSet(256);
    protected static final BitSet pchar = new BitSet(256);
    protected static final BitSet percent = new BitSet(256);
    protected static final BitSet port = digit;
    protected static final BitSet query = uric;
    protected static final BitSet reg_name = new BitSet(256);
    protected static final BitSet rel_path = new BitSet(256);
    protected static final BitSet rel_segment = new BitSet(256);
    protected static final BitSet relativeURI = new BitSet(256);
    protected static final BitSet reserved = new BitSet(256);
    protected static char[] rootPath = new char[]{'/'};
    protected static final BitSet scheme = new BitSet(256);
    protected static final BitSet segment = new BitSet(256);
    static final long serialVersionUID = 604752400577948726L;
    protected static final BitSet server = new BitSet(256);
    public static final BitSet space = new BitSet(256);
    protected static final BitSet toplabel = new BitSet(256);
    protected static final BitSet unreserved = new BitSet(256);
    public static final BitSet unwise = new BitSet(256);
    protected static final BitSet uric = new BitSet(256);
    protected static final BitSet uric_no_slash = new BitSet(256);
    protected static final BitSet userinfo = new BitSet(256);
    public static final BitSet within_userinfo = new BitSet(256);
    protected char[] _authority;
    protected char[] _fragment;
    protected char[] _host;
    protected boolean _is_IPv4address;
    protected boolean _is_IPv6reference;
    protected boolean _is_abs_path;
    protected boolean _is_hier_part;
    protected boolean _is_hostname;
    protected boolean _is_net_path;
    protected boolean _is_opaque_part;
    protected boolean _is_reg_name;
    protected boolean _is_rel_path;
    protected boolean _is_server;
    protected char[] _opaque;
    protected char[] _path;
    protected int _port;
    protected char[] _query;
    protected char[] _scheme;
    protected char[] _uri;
    protected char[] _userinfo;
    protected int hash;
    protected String protocolCharset;

    public static class DefaultCharsetChanged extends RuntimeException {
        public static final int DOCUMENT_CHARSET = 2;
        public static final int PROTOCOL_CHARSET = 1;
        public static final int UNKNOWN = 0;
        private String reason;
        private int reasonCode;

        public DefaultCharsetChanged(int i, String str) {
            super(str);
            this.reason = str;
            this.reasonCode = i;
        }

        public int getReasonCode() {
            return this.reasonCode;
        }

        public String getReason() {
            return this.reason;
        }
    }

    public static class LocaleToCharsetMap {
        private static final Hashtable LOCALE_TO_CHARSET_MAP = new Hashtable();

        static {
            LOCALE_TO_CHARSET_MAP.put("ar", "ISO-8859-6");
            LOCALE_TO_CHARSET_MAP.put("be", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("bg", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("ca", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put(IXAdRequestInfo.CS, "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("da", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("de", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("el", "ISO-8859-7");
            LOCALE_TO_CHARSET_MAP.put("en", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("es", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("et", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fi", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("fr", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("hr", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("hu", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("is", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("it", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("iw", "ISO-8859-8");
            LOCALE_TO_CHARSET_MAP.put("ja", "Shift_JIS");
            LOCALE_TO_CHARSET_MAP.put("ko", "EUC-KR");
            LOCALE_TO_CHARSET_MAP.put("lt", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("lv", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("mk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("nl", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("no", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("pl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("pt", "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("ro", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("ru", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put(IXAdRequestInfo.SCREEN_HEIGHT, "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("sk", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sl", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sq", "ISO-8859-2");
            LOCALE_TO_CHARSET_MAP.put("sr", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put(a.h, "ISO-8859-1");
            LOCALE_TO_CHARSET_MAP.put("tr", "ISO-8859-9");
            LOCALE_TO_CHARSET_MAP.put("uk", "ISO-8859-5");
            LOCALE_TO_CHARSET_MAP.put("zh", "GB2312");
            LOCALE_TO_CHARSET_MAP.put("zh_TW", "Big5");
        }

        public static String getCharset(Locale locale) {
            String str = (String) LOCALE_TO_CHARSET_MAP.get(locale.toString());
            if (str != null) {
                return str;
            }
            return (String) LOCALE_TO_CHARSET_MAP.get(locale.getLanguage());
        }
    }

    protected URI() {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
    }

    public URI(String str, boolean z, String str2) throws URIException, NullPointerException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        this.protocolCharset = str2;
        parseUriReference(str, z);
    }

    public URI(String str, boolean z) throws URIException, NullPointerException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        parseUriReference(str, z);
    }

    public URI(char[] cArr, String str) throws URIException, NullPointerException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        this.protocolCharset = str;
        parseUriReference(new String(cArr), true);
    }

    public URI(char[] cArr) throws URIException, NullPointerException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        parseUriReference(new String(cArr), true);
    }

    public URI(String str, String str2) throws URIException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        this.protocolCharset = str2;
        parseUriReference(str, false);
    }

    public URI(String str) throws URIException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        parseUriReference(str, false);
    }

    public URI(String str, String str2, String str3) throws URIException {
        char[] cArr = null;
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        if (str == null) {
            throw new URIException(1, "scheme required");
        }
        char[] toCharArray = str.toLowerCase().toCharArray();
        if (validate(toCharArray, scheme)) {
            this._scheme = toCharArray;
            this._opaque = encode(str2, allowed_opaque_part, getProtocolCharset());
            this._is_opaque_part = true;
            if (str3 != null) {
                cArr = str3.toCharArray();
            }
            this._fragment = cArr;
            setURI();
            return;
        }
        throw new URIException(1, "incorrect scheme");
    }

    public URI(String str, String str2, String str3, String str4, String str5) throws URIException {
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        StringBuffer stringBuffer = new StringBuffer();
        if (str != null) {
            stringBuffer.append(str);
            stringBuffer.append(':');
        }
        if (str2 != null) {
            stringBuffer.append("//");
            stringBuffer.append(str2);
        }
        if (str3 != null) {
            if ((str == null && str2 == null) || str3.startsWith("/")) {
                stringBuffer.append(str3);
            } else {
                throw new URIException(1, "abs_path requested");
            }
        }
        if (str4 != null) {
            stringBuffer.append('?');
            stringBuffer.append(str4);
        }
        if (str5 != null) {
            stringBuffer.append('#');
            stringBuffer.append(str5);
        }
        parseUriReference(stringBuffer.toString(), false);
    }

    public URI(String str, String str2, String str3, int i) throws URIException {
        this(str, str2, str3, i, null, null, null);
    }

    public URI(String str, String str2, String str3, int i, String str4) throws URIException {
        this(str, str2, str3, i, str4, null, null);
    }

    public URI(String str, String str2, String str3, int i, String str4, String str5) throws URIException {
        this(str, str2, str3, i, str4, str5, null);
    }

    public URI(String str, String str2, String str3, int i, String str4, String str5, String str6) throws URIException {
        String str7;
        if (str3 == null) {
            str7 = null;
        } else {
            str7 = new StringBuffer().append(str2 != null ? new StringBuffer().append(str2).append('@').toString() : "").append(str3).append(i != -1 ? new StringBuffer().append(":").append(i).toString() : "").toString();
        }
        this(str, str7, str4, str5, str6);
    }

    public URI(String str, String str2, String str3, String str4) throws URIException {
        this(str, str2, str3, null, str4);
    }

    public URI(URI uri, String str) throws URIException {
        this(uri, new URI(str));
    }

    public URI(URI uri, String str, boolean z) throws URIException {
        this(uri, new URI(str, z));
    }

    public URI(URI uri, URI uri2) throws URIException {
        boolean z = false;
        this.hash = 0;
        this._uri = null;
        this.protocolCharset = null;
        this._scheme = null;
        this._opaque = null;
        this._authority = null;
        this._userinfo = null;
        this._host = null;
        this._port = -1;
        this._path = null;
        this._query = null;
        this._fragment = null;
        if (uri._scheme == null) {
            throw new URIException(1, "base URI required");
        }
        if (uri._scheme != null) {
            this._scheme = uri._scheme;
            this._authority = uri._authority;
        }
        if (uri._is_opaque_part || uri2._is_opaque_part) {
            this._scheme = uri._scheme;
            if (uri._is_opaque_part || uri2._is_opaque_part) {
                z = true;
            }
            this._is_opaque_part = z;
            this._opaque = uri2._opaque;
            this._fragment = uri2._fragment;
            setURI();
            return;
        }
        if (uri2._scheme != null) {
            this._scheme = uri2._scheme;
            this._is_net_path = uri2._is_net_path;
            this._authority = uri2._authority;
            if (uri2._is_server) {
                this._is_server = uri2._is_server;
                this._userinfo = uri2._userinfo;
                this._host = uri2._host;
                this._port = uri2._port;
            } else if (uri2._is_reg_name) {
                this._is_reg_name = uri2._is_reg_name;
            }
            this._is_abs_path = uri2._is_abs_path;
            this._is_rel_path = uri2._is_rel_path;
            this._path = uri2._path;
        } else if (uri._authority != null && uri2._scheme == null) {
            this._is_net_path = uri._is_net_path;
            this._authority = uri._authority;
            if (uri._is_server) {
                this._is_server = uri._is_server;
                this._userinfo = uri._userinfo;
                this._host = uri._host;
                this._port = uri._port;
            } else if (uri._is_reg_name) {
                this._is_reg_name = uri._is_reg_name;
            }
        }
        if (uri2._authority != null) {
            this._is_net_path = uri2._is_net_path;
            this._authority = uri2._authority;
            if (uri2._is_server) {
                this._is_server = uri2._is_server;
                this._userinfo = uri2._userinfo;
                this._host = uri2._host;
                this._port = uri2._port;
            } else if (uri2._is_reg_name) {
                this._is_reg_name = uri2._is_reg_name;
            }
            this._is_abs_path = uri2._is_abs_path;
            this._is_rel_path = uri2._is_rel_path;
            this._path = uri2._path;
        }
        if (uri2._scheme == null && uri2._authority == null) {
            if ((uri2._path == null || uri2._path.length == 0) && uri2._query == null) {
                this._path = uri._path;
                this._query = uri._query;
            } else {
                this._path = resolvePath(uri._path, uri2._path);
            }
        }
        if (uri2._query != null) {
            this._query = uri2._query;
        }
        if (uri2._fragment != null) {
            this._fragment = uri2._fragment;
        }
        setURI();
        parseUriReference(new String(this._uri), true);
    }

    static {
        int i;
        defaultDocumentCharset = null;
        defaultDocumentCharsetByLocale = null;
        defaultDocumentCharsetByPlatform = null;
        Locale locale = Locale.getDefault();
        if (locale != null) {
            defaultDocumentCharsetByLocale = LocaleToCharsetMap.getCharset(locale);
            defaultDocumentCharset = defaultDocumentCharsetByLocale;
        }
        try {
            defaultDocumentCharsetByPlatform = System.getProperty("file.encoding");
        } catch (SecurityException e) {
        }
        if (defaultDocumentCharset == null) {
            defaultDocumentCharset = defaultDocumentCharsetByPlatform;
        }
        percent.set(37);
        for (i = 48; i <= 57; i++) {
            digit.set(i);
        }
        for (i = 97; i <= 122; i++) {
            alpha.set(i);
        }
        for (i = 65; i <= 90; i++) {
            alpha.set(i);
        }
        alphanum.or(alpha);
        alphanum.or(digit);
        hex.or(digit);
        for (i = 97; i <= 102; i++) {
            hex.set(i);
        }
        for (i = 65; i <= 70; i++) {
            hex.set(i);
        }
        escaped.or(percent);
        escaped.or(hex);
        mark.set(45);
        mark.set(95);
        mark.set(46);
        mark.set(33);
        mark.set(126);
        mark.set(42);
        mark.set(39);
        mark.set(40);
        mark.set(41);
        unreserved.or(alphanum);
        unreserved.or(mark);
        reserved.set(59);
        reserved.set(47);
        reserved.set(63);
        reserved.set(58);
        reserved.set(64);
        reserved.set(38);
        reserved.set(61);
        reserved.set(43);
        reserved.set(36);
        reserved.set(44);
        uric.or(reserved);
        uric.or(unreserved);
        uric.or(escaped);
        pchar.or(unreserved);
        pchar.or(escaped);
        pchar.set(58);
        pchar.set(64);
        pchar.set(38);
        pchar.set(61);
        pchar.set(43);
        pchar.set(36);
        pchar.set(44);
        segment.or(pchar);
        segment.set(59);
        segment.or(param);
        path_segments.set(47);
        path_segments.or(segment);
        abs_path.set(47);
        abs_path.or(path_segments);
        uric_no_slash.or(unreserved);
        uric_no_slash.or(escaped);
        uric_no_slash.set(59);
        uric_no_slash.set(63);
        uric_no_slash.set(59);
        uric_no_slash.set(64);
        uric_no_slash.set(38);
        uric_no_slash.set(61);
        uric_no_slash.set(43);
        uric_no_slash.set(36);
        uric_no_slash.set(44);
        opaque_part.or(uric_no_slash);
        opaque_part.or(uric);
        path.or(abs_path);
        path.or(opaque_part);
        IPv4address.or(digit);
        IPv4address.set(46);
        IPv6address.or(hex);
        IPv6address.set(58);
        IPv6address.or(IPv4address);
        IPv6reference.set(91);
        IPv6reference.or(IPv6address);
        IPv6reference.set(93);
        toplabel.or(alphanum);
        toplabel.set(45);
        hostname.or(toplabel);
        hostname.set(46);
        host.or(hostname);
        host.or(IPv6reference);
        hostport.or(host);
        hostport.set(58);
        hostport.or(port);
        userinfo.or(unreserved);
        userinfo.or(escaped);
        userinfo.set(59);
        userinfo.set(58);
        userinfo.set(38);
        userinfo.set(61);
        userinfo.set(43);
        userinfo.set(36);
        userinfo.set(44);
        within_userinfo.or(userinfo);
        within_userinfo.clear(59);
        within_userinfo.clear(58);
        within_userinfo.clear(64);
        within_userinfo.clear(63);
        within_userinfo.clear(47);
        server.or(userinfo);
        server.set(64);
        server.or(hostport);
        reg_name.or(unreserved);
        reg_name.or(escaped);
        reg_name.set(36);
        reg_name.set(44);
        reg_name.set(59);
        reg_name.set(58);
        reg_name.set(64);
        reg_name.set(38);
        reg_name.set(61);
        reg_name.set(43);
        authority.or(server);
        authority.or(reg_name);
        scheme.or(alpha);
        scheme.or(digit);
        scheme.set(43);
        scheme.set(45);
        scheme.set(46);
        rel_segment.or(unreserved);
        rel_segment.or(escaped);
        rel_segment.set(59);
        rel_segment.set(64);
        rel_segment.set(38);
        rel_segment.set(61);
        rel_segment.set(43);
        rel_segment.set(36);
        rel_segment.set(44);
        rel_path.or(rel_segment);
        rel_path.or(abs_path);
        net_path.set(47);
        net_path.or(authority);
        net_path.or(abs_path);
        hier_part.or(net_path);
        hier_part.or(abs_path);
        hier_part.or(query);
        relativeURI.or(net_path);
        relativeURI.or(abs_path);
        relativeURI.or(rel_path);
        relativeURI.or(query);
        absoluteURI.or(scheme);
        absoluteURI.set(58);
        absoluteURI.or(hier_part);
        absoluteURI.or(opaque_part);
        URI_reference.or(absoluteURI);
        URI_reference.or(relativeURI);
        URI_reference.set(35);
        URI_reference.or(fragment);
        for (i = 0; i <= 31; i++) {
            control.set(i);
        }
        control.set(127);
        space.set(32);
        delims.set(60);
        delims.set(62);
        delims.set(35);
        delims.set(37);
        delims.set(34);
        unwise.set(123);
        unwise.set(125);
        unwise.set(124);
        unwise.set(92);
        unwise.set(94);
        unwise.set(91);
        unwise.set(93);
        unwise.set(96);
        disallowed_rel_path.or(uric);
        disallowed_rel_path.andNot(rel_path);
        disallowed_opaque_part.or(uric);
        disallowed_opaque_part.andNot(opaque_part);
        allowed_authority.or(authority);
        allowed_authority.clear(37);
        allowed_opaque_part.or(opaque_part);
        allowed_opaque_part.clear(37);
        allowed_reg_name.or(reg_name);
        allowed_reg_name.clear(37);
        allowed_userinfo.or(userinfo);
        allowed_userinfo.clear(37);
        allowed_within_userinfo.or(within_userinfo);
        allowed_within_userinfo.clear(37);
        allowed_IPv6reference.or(IPv6reference);
        allowed_IPv6reference.clear(91);
        allowed_IPv6reference.clear(93);
        allowed_host.or(hostname);
        allowed_host.or(allowed_IPv6reference);
        allowed_within_authority.or(server);
        allowed_within_authority.or(reg_name);
        allowed_within_authority.clear(59);
        allowed_within_authority.clear(58);
        allowed_within_authority.clear(64);
        allowed_within_authority.clear(63);
        allowed_within_authority.clear(47);
        allowed_abs_path.or(abs_path);
        allowed_abs_path.andNot(percent);
        allowed_rel_path.or(rel_path);
        allowed_rel_path.clear(37);
        allowed_within_path.or(abs_path);
        allowed_within_path.clear(47);
        allowed_within_path.clear(59);
        allowed_within_path.clear(61);
        allowed_within_path.clear(63);
        allowed_query.or(uric);
        allowed_query.clear(37);
        allowed_within_query.or(allowed_query);
        allowed_within_query.andNot(reserved);
        allowed_fragment.or(uric);
        allowed_fragment.clear(37);
    }

    protected static char[] encode(String str, BitSet bitSet, String str2) throws URIException {
        if (str == null) {
            throw new IllegalArgumentException("Original string may not be null");
        } else if (bitSet != null) {
            return EncodingUtil.getAsciiString(URLCodec.encodeUrl(bitSet, EncodingUtil.getBytes(str, str2))).toCharArray();
        } else {
            throw new IllegalArgumentException("Allowed bitset may not be null");
        }
    }

    protected static String decode(char[] cArr, String str) throws URIException {
        if (cArr != null) {
            return decode(new String(cArr), str);
        }
        throw new IllegalArgumentException("Component array of chars may not be null");
    }

    protected static String decode(String str, String str2) throws URIException {
        if (str == null) {
            throw new IllegalArgumentException("Component array of chars may not be null");
        }
        try {
            return EncodingUtil.getString(URLCodec.decodeUrl(EncodingUtil.getAsciiBytes(str)), str2);
        } catch (Throwable e) {
            throw new URIException(e.getMessage());
        }
    }

    protected boolean prevalidate(String str, BitSet bitSet) {
        if (str == null) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        for (char c : toCharArray) {
            if (bitSet.get(c)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validate(char[] cArr, BitSet bitSet) {
        return validate(cArr, 0, -1, bitSet);
    }

    protected boolean validate(char[] cArr, int i, int i2, BitSet bitSet) {
        if (i2 == -1) {
            i2 = cArr.length - 1;
        }
        while (i <= i2) {
            if (!bitSet.get(cArr[i])) {
                return false;
            }
            i++;
        }
        return true;
    }

    protected void parseUriReference(String str, boolean z) throws URIException {
        if (str == null) {
            throw new URIException("URI-Reference required");
        }
        boolean z2;
        String trim = str.trim();
        int length = trim.length();
        if (length > 0) {
            if (validate(new char[]{trim.charAt(0)}, delims) && length >= 2) {
                if (validate(new char[]{trim.charAt(length - 1)}, delims)) {
                    trim = trim.substring(1, length - 1);
                    length -= 2;
                }
            }
        }
        int indexOf = trim.indexOf(58);
        int indexOf2 = trim.indexOf(47);
        if (indexOf <= 0 || (indexOf2 >= 0 && indexOf2 < indexOf)) {
            z2 = true;
        } else {
            z2 = false;
        }
        indexOf = indexFirstOf(trim, z2 ? "/?#" : ":/?#", 0);
        if (indexOf == -1) {
            indexOf = 0;
        }
        if (indexOf <= 0 || indexOf >= length || trim.charAt(indexOf) != ':') {
            indexOf2 = 0;
        } else {
            char[] toCharArray = trim.substring(0, indexOf).toLowerCase().toCharArray();
            if (validate(toCharArray, scheme)) {
                this._scheme = toCharArray;
                indexOf++;
                indexOf2 = indexOf;
            } else {
                throw new URIException("incorrect scheme");
            }
        }
        this._is_hier_part = false;
        this._is_rel_path = false;
        this._is_abs_path = false;
        this._is_net_path = false;
        if (indexOf >= 0 && indexOf < length && trim.charAt(indexOf) == '/') {
            this._is_hier_part = true;
            if (indexOf + 2 < length && trim.charAt(indexOf + 1) == '/') {
                indexOf2 = indexFirstOf(trim, "/?#", indexOf + 2);
                if (indexOf2 == -1) {
                    indexOf2 = trim.substring(indexOf + 2).length() == 0 ? indexOf + 2 : trim.length();
                }
                parseAuthority(trim.substring(indexOf + 2, indexOf2), z);
                this._is_net_path = true;
                indexOf = indexOf2;
            }
            if (indexOf2 == indexOf) {
                this._is_abs_path = true;
            }
        }
        if (indexOf2 < length) {
            indexOf = indexFirstOf(trim, "?#", indexOf2);
            if (indexOf == -1) {
                indexOf = trim.length();
            }
            if (!this._is_abs_path) {
                if ((!z && prevalidate(trim.substring(indexOf2, indexOf), disallowed_rel_path)) || (z && validate(trim.substring(indexOf2, indexOf).toCharArray(), rel_path))) {
                    this._is_rel_path = true;
                } else if ((z || !prevalidate(trim.substring(indexOf2, indexOf), disallowed_opaque_part)) && !(z && validate(trim.substring(indexOf2, indexOf).toCharArray(), opaque_part))) {
                    this._path = null;
                } else {
                    this._is_opaque_part = true;
                }
            }
            if (z) {
                setRawPath(trim.substring(indexOf2, indexOf).toCharArray());
            } else {
                setPath(trim.substring(indexOf2, indexOf));
            }
        }
        String protocolCharset = getProtocolCharset();
        if (indexOf >= 0 && indexOf + 1 < length && trim.charAt(indexOf) == '?') {
            char[] toCharArray2;
            indexOf2 = trim.indexOf(35, indexOf + 1);
            if (indexOf2 == -1) {
                indexOf2 = trim.length();
            }
            if (z) {
                toCharArray2 = trim.substring(indexOf + 1, indexOf2).toCharArray();
            } else {
                toCharArray2 = encode(trim.substring(indexOf + 1, indexOf2), allowed_query, protocolCharset);
            }
            this._query = toCharArray2;
            indexOf = indexOf2;
        }
        if (indexOf >= 0 && indexOf + 1 <= length && trim.charAt(indexOf) == '#') {
            if (indexOf + 1 == length) {
                this._fragment = "".toCharArray();
            } else {
                this._fragment = z ? trim.substring(indexOf + 1).toCharArray() : encode(trim.substring(indexOf + 1), allowed_fragment, protocolCharset);
            }
        }
        setURI();
    }

    protected int indexFirstOf(String str, String str2) {
        return indexFirstOf(str, str2, -1);
    }

    protected int indexFirstOf(String str, String str2, int i) {
        int i2 = 0;
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        } else if (i > str.length()) {
            return -1;
        }
        int length = str.length();
        char[] toCharArray = str2.toCharArray();
        while (i2 < toCharArray.length) {
            int indexOf = str.indexOf(toCharArray[i2], i);
            if (indexOf >= 0 && indexOf < length) {
                length = indexOf;
            }
            i2++;
        }
        if (length == str.length()) {
            length = -1;
        }
        return length;
    }

    protected int indexFirstOf(char[] cArr, char c) {
        return indexFirstOf(cArr, c, 0);
    }

    protected int indexFirstOf(char[] cArr, char c, int i) {
        if (cArr == null || cArr.length == 0) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        } else if (i > cArr.length) {
            return -1;
        }
        while (i < cArr.length) {
            if (cArr[i] == c) {
                return i;
            }
            i++;
        }
        return -1;
    }

    protected void parseAuthority(String str, boolean z) throws URIException {
        char[] toCharArray;
        int i;
        this._is_IPv6reference = false;
        this._is_IPv4address = false;
        this._is_hostname = false;
        this._is_server = false;
        this._is_reg_name = false;
        String protocolCharset = getProtocolCharset();
        int indexOf = str.indexOf(64);
        if (indexOf != -1) {
            if (z) {
                toCharArray = str.substring(0, indexOf).toCharArray();
            } else {
                toCharArray = encode(str.substring(0, indexOf), allowed_userinfo, protocolCharset);
            }
            this._userinfo = toCharArray;
            i = indexOf + 1;
        } else {
            i = 0;
        }
        boolean z2;
        if (str.indexOf(91, i) >= i) {
            indexOf = str.indexOf(93, i);
            if (indexOf == -1) {
                throw new URIException(1, "IPv6reference");
            }
            indexOf++;
            this._host = z ? str.substring(i, indexOf).toCharArray() : encode(str.substring(i, indexOf), allowed_IPv6reference, protocolCharset);
            this._is_IPv6reference = true;
            z2 = true;
        } else {
            indexOf = str.indexOf(58, i);
            if (indexOf == -1) {
                indexOf = str.length();
                z2 = false;
            } else {
                z2 = true;
            }
            this._host = str.substring(i, indexOf).toCharArray();
            if (validate(this._host, IPv4address)) {
                this._is_IPv4address = true;
            } else if (validate(this._host, hostname)) {
                this._is_hostname = true;
            } else {
                this._is_reg_name = true;
            }
        }
        if (this._is_reg_name) {
            this._is_IPv6reference = false;
            this._is_IPv4address = false;
            this._is_hostname = false;
            this._is_server = false;
            if (z) {
                toCharArray = str.toString().toCharArray();
            } else {
                toCharArray = encode(str.toString(), allowed_reg_name, protocolCharset);
            }
            this._authority = toCharArray;
            return;
        }
        if (str.length() - 1 > indexOf && r3 && str.charAt(indexOf) == ':') {
            try {
                this._port = Integer.parseInt(str.substring(indexOf + 1));
            } catch (NumberFormatException e) {
                throw new URIException(1, "invalid port number");
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this._userinfo != null) {
            stringBuffer.append(this._userinfo);
            stringBuffer.append('@');
        }
        if (this._host != null) {
            stringBuffer.append(this._host);
            if (this._port != -1) {
                stringBuffer.append(':');
                stringBuffer.append(this._port);
            }
        }
        this._authority = stringBuffer.toString().toCharArray();
        this._is_server = true;
    }

    protected void setURI() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this._scheme != null) {
            stringBuffer.append(this._scheme);
            stringBuffer.append(':');
        }
        if (this._is_net_path) {
            stringBuffer.append("//");
            if (this._authority != null) {
                stringBuffer.append(this._authority);
            }
        }
        if (this._opaque != null && this._is_opaque_part) {
            stringBuffer.append(this._opaque);
        } else if (!(this._path == null || this._path.length == 0)) {
            stringBuffer.append(this._path);
        }
        if (this._query != null) {
            stringBuffer.append('?');
            stringBuffer.append(this._query);
        }
        this._uri = stringBuffer.toString().toCharArray();
        this.hash = 0;
    }

    public boolean isAbsoluteURI() {
        return this._scheme != null;
    }

    public boolean isRelativeURI() {
        return this._scheme == null;
    }

    public boolean isHierPart() {
        return this._is_hier_part;
    }

    public boolean isOpaquePart() {
        return this._is_opaque_part;
    }

    public boolean isNetPath() {
        return this._is_net_path || this._authority != null;
    }

    public boolean isAbsPath() {
        return this._is_abs_path;
    }

    public boolean isRelPath() {
        return this._is_rel_path;
    }

    public boolean hasAuthority() {
        return this._authority != null || this._is_net_path;
    }

    public boolean isRegName() {
        return this._is_reg_name;
    }

    public boolean isServer() {
        return this._is_server;
    }

    public boolean hasUserinfo() {
        return this._userinfo != null;
    }

    public boolean isHostname() {
        return this._is_hostname;
    }

    public boolean isIPv4address() {
        return this._is_IPv4address;
    }

    public boolean isIPv6reference() {
        return this._is_IPv6reference;
    }

    public boolean hasQuery() {
        return this._query != null;
    }

    public boolean hasFragment() {
        return this._fragment != null;
    }

    public static void setDefaultProtocolCharset(String str) throws DefaultCharsetChanged {
        defaultProtocolCharset = str;
        throw new DefaultCharsetChanged(1, "the default protocol charset changed");
    }

    public static String getDefaultProtocolCharset() {
        return defaultProtocolCharset;
    }

    public String getProtocolCharset() {
        return this.protocolCharset != null ? this.protocolCharset : defaultProtocolCharset;
    }

    public static void setDefaultDocumentCharset(String str) throws DefaultCharsetChanged {
        defaultDocumentCharset = str;
        throw new DefaultCharsetChanged(2, "the default document charset changed");
    }

    public static String getDefaultDocumentCharset() {
        return defaultDocumentCharset;
    }

    public static String getDefaultDocumentCharsetByLocale() {
        return defaultDocumentCharsetByLocale;
    }

    public static String getDefaultDocumentCharsetByPlatform() {
        return defaultDocumentCharsetByPlatform;
    }

    public char[] getRawScheme() {
        return this._scheme;
    }

    public String getScheme() {
        return this._scheme == null ? null : new String(this._scheme);
    }

    public void setRawAuthority(char[] cArr) throws URIException, NullPointerException {
        parseAuthority(new String(cArr), true);
        setURI();
    }

    public void setEscapedAuthority(String str) throws URIException {
        parseAuthority(str, true);
        setURI();
    }

    public char[] getRawAuthority() {
        return this._authority;
    }

    public String getEscapedAuthority() {
        return this._authority == null ? null : new String(this._authority);
    }

    public String getAuthority() throws URIException {
        return this._authority == null ? null : decode(this._authority, getProtocolCharset());
    }

    public char[] getRawUserinfo() {
        return this._userinfo;
    }

    public String getEscapedUserinfo() {
        return this._userinfo == null ? null : new String(this._userinfo);
    }

    public String getUserinfo() throws URIException {
        return this._userinfo == null ? null : decode(this._userinfo, getProtocolCharset());
    }

    public char[] getRawHost() {
        return this._host;
    }

    public String getHost() throws URIException {
        if (this._host != null) {
            return decode(this._host, getProtocolCharset());
        }
        return null;
    }

    public int getPort() {
        return this._port;
    }

    public void setRawPath(char[] cArr) throws URIException {
        if (cArr == null || cArr.length == 0) {
            this._opaque = cArr;
            this._path = cArr;
            setURI();
            return;
        }
        char[] removeFragmentIdentifier = removeFragmentIdentifier(cArr);
        if (this._is_net_path || this._is_abs_path) {
            if (removeFragmentIdentifier[0] != '/') {
                throw new URIException(1, "not absolute path");
            } else if (validate(removeFragmentIdentifier, abs_path)) {
                this._path = removeFragmentIdentifier;
            } else {
                throw new URIException(3, "escaped absolute path not valid");
            }
        } else if (this._is_rel_path) {
            int indexFirstOf = indexFirstOf(removeFragmentIdentifier, '/');
            if (indexFirstOf == 0) {
                throw new URIException(1, "incorrect path");
            } else if ((indexFirstOf <= 0 || validate(removeFragmentIdentifier, 0, indexFirstOf - 1, rel_segment) || validate(removeFragmentIdentifier, indexFirstOf, -1, abs_path)) && (indexFirstOf >= 0 || validate(removeFragmentIdentifier, 0, -1, rel_segment))) {
                this._path = removeFragmentIdentifier;
            } else {
                throw new URIException(3, "escaped relative path not valid");
            }
        } else if (!this._is_opaque_part) {
            throw new URIException(1, "incorrect path");
        } else if (uric_no_slash.get(removeFragmentIdentifier[0]) || validate(removeFragmentIdentifier, 1, -1, uric)) {
            this._opaque = removeFragmentIdentifier;
        } else {
            throw new URIException(3, "escaped opaque part not valid");
        }
        setURI();
    }

    public void setEscapedPath(String str) throws URIException {
        if (str == null) {
            this._opaque = null;
            this._path = null;
            setURI();
            return;
        }
        setRawPath(str.toCharArray());
    }

    public void setPath(String str) throws URIException {
        if (str == null || str.length() == 0) {
            char[] toCharArray = str == null ? null : str.toCharArray();
            this._opaque = toCharArray;
            this._path = toCharArray;
            setURI();
            return;
        }
        String protocolCharset = getProtocolCharset();
        if (this._is_net_path || this._is_abs_path) {
            this._path = encode(str, allowed_abs_path, protocolCharset);
        } else if (this._is_rel_path) {
            r1 = new StringBuffer(str.length());
            int indexOf = str.indexOf(47);
            if (indexOf == 0) {
                throw new URIException(1, "incorrect relative path");
            }
            if (indexOf > 0) {
                r1.append(encode(str.substring(0, indexOf), allowed_rel_path, protocolCharset));
                r1.append(encode(str.substring(indexOf), allowed_abs_path, protocolCharset));
            } else {
                r1.append(encode(str, allowed_rel_path, protocolCharset));
            }
            this._path = r1.toString().toCharArray();
        } else if (this._is_opaque_part) {
            r1 = new StringBuffer();
            r1.insert(0, encode(str.substring(0, 1), uric_no_slash, protocolCharset));
            r1.insert(1, encode(str.substring(1), uric, protocolCharset));
            this._opaque = r1.toString().toCharArray();
        } else {
            throw new URIException(1, "incorrect path");
        }
        setURI();
    }

    protected char[] resolvePath(char[] cArr, char[] cArr2) throws URIException {
        String str = cArr == null ? "" : new String(cArr);
        int lastIndexOf = str.lastIndexOf(47);
        if (lastIndexOf != -1) {
            cArr = str.substring(0, lastIndexOf + 1).toCharArray();
        }
        if (cArr2 == null || cArr2.length == 0) {
            return normalize(cArr);
        }
        if (cArr2[0] == '/') {
            return normalize(cArr2);
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + cArr2.length);
        stringBuffer.append(lastIndexOf != -1 ? str.substring(0, lastIndexOf + 1) : "/");
        stringBuffer.append(cArr2);
        return normalize(stringBuffer.toString().toCharArray());
    }

    protected char[] getRawCurrentHierPath(char[] cArr) throws URIException {
        if (this._is_opaque_part) {
            throw new URIException(1, "no hierarchy level");
        } else if (cArr == null) {
            throw new URIException(1, "empty path");
        } else {
            String str = new String(cArr);
            int indexOf = str.indexOf(47);
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf == 0) {
                return rootPath;
            }
            if (indexOf == lastIndexOf || lastIndexOf == -1) {
                return cArr;
            }
            return str.substring(0, lastIndexOf).toCharArray();
        }
    }

    public char[] getRawCurrentHierPath() throws URIException {
        return this._path == null ? null : getRawCurrentHierPath(this._path);
    }

    public String getEscapedCurrentHierPath() throws URIException {
        char[] rawCurrentHierPath = getRawCurrentHierPath();
        return rawCurrentHierPath == null ? null : new String(rawCurrentHierPath);
    }

    public String getCurrentHierPath() throws URIException {
        char[] rawCurrentHierPath = getRawCurrentHierPath();
        return rawCurrentHierPath == null ? null : decode(rawCurrentHierPath, getProtocolCharset());
    }

    public char[] getRawAboveHierPath() throws URIException {
        char[] rawCurrentHierPath = getRawCurrentHierPath();
        return rawCurrentHierPath == null ? null : getRawCurrentHierPath(rawCurrentHierPath);
    }

    public String getEscapedAboveHierPath() throws URIException {
        char[] rawAboveHierPath = getRawAboveHierPath();
        return rawAboveHierPath == null ? null : new String(rawAboveHierPath);
    }

    public String getAboveHierPath() throws URIException {
        char[] rawAboveHierPath = getRawAboveHierPath();
        return rawAboveHierPath == null ? null : decode(rawAboveHierPath, getProtocolCharset());
    }

    public char[] getRawPath() {
        return this._is_opaque_part ? this._opaque : this._path;
    }

    public String getEscapedPath() {
        char[] rawPath = getRawPath();
        return rawPath == null ? null : new String(rawPath);
    }

    public String getPath() throws URIException {
        char[] rawPath = getRawPath();
        return rawPath == null ? null : decode(rawPath, getProtocolCharset());
    }

    public char[] getRawName() {
        if (this._path == null) {
            return null;
        }
        int length;
        for (length = this._path.length - 1; length >= 0; length--) {
            if (this._path[length] == '/') {
                length++;
                break;
            }
        }
        length = 0;
        int length2 = this._path.length - length;
        Object obj = new char[length2];
        System.arraycopy(this._path, length, obj, 0, length2);
        return obj;
    }

    public String getEscapedName() {
        char[] rawName = getRawName();
        return rawName == null ? null : new String(rawName);
    }

    public String getName() throws URIException {
        return getRawName() == null ? null : decode(getRawName(), getProtocolCharset());
    }

    public char[] getRawPathQuery() {
        if (this._path == null && this._query == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this._path != null) {
            stringBuffer.append(this._path);
        }
        if (this._query != null) {
            stringBuffer.append('?');
            stringBuffer.append(this._query);
        }
        return stringBuffer.toString().toCharArray();
    }

    public String getEscapedPathQuery() {
        char[] rawPathQuery = getRawPathQuery();
        return rawPathQuery == null ? null : new String(rawPathQuery);
    }

    public String getPathQuery() throws URIException {
        char[] rawPathQuery = getRawPathQuery();
        return rawPathQuery == null ? null : decode(rawPathQuery, getProtocolCharset());
    }

    public void setRawQuery(char[] cArr) throws URIException {
        if (cArr == null || cArr.length == 0) {
            this._query = cArr;
            setURI();
            return;
        }
        char[] removeFragmentIdentifier = removeFragmentIdentifier(cArr);
        if (validate(removeFragmentIdentifier, query)) {
            this._query = removeFragmentIdentifier;
            setURI();
            return;
        }
        throw new URIException(3, "escaped query not valid");
    }

    public void setEscapedQuery(String str) throws URIException {
        if (str == null) {
            this._query = null;
            setURI();
            return;
        }
        setRawQuery(str.toCharArray());
    }

    public void setQuery(String str) throws URIException {
        if (str == null || str.length() == 0) {
            this._query = str == null ? null : str.toCharArray();
            setURI();
            return;
        }
        setRawQuery(encode(str, allowed_query, getProtocolCharset()));
    }

    public char[] getRawQuery() {
        return this._query;
    }

    public String getEscapedQuery() {
        return this._query == null ? null : new String(this._query);
    }

    public String getQuery() throws URIException {
        return this._query == null ? null : decode(this._query, getProtocolCharset());
    }

    public void setRawFragment(char[] cArr) throws URIException {
        if (cArr == null || cArr.length == 0) {
            this._fragment = cArr;
            this.hash = 0;
        } else if (validate(cArr, fragment)) {
            this._fragment = cArr;
            this.hash = 0;
        } else {
            throw new URIException(3, "escaped fragment not valid");
        }
    }

    public void setEscapedFragment(String str) throws URIException {
        if (str == null) {
            this._fragment = null;
            this.hash = 0;
            return;
        }
        setRawFragment(str.toCharArray());
    }

    public void setFragment(String str) throws URIException {
        if (str == null || str.length() == 0) {
            this._fragment = str == null ? null : str.toCharArray();
            this.hash = 0;
            return;
        }
        this._fragment = encode(str, allowed_fragment, getProtocolCharset());
        this.hash = 0;
    }

    public char[] getRawFragment() {
        return this._fragment;
    }

    public String getEscapedFragment() {
        return this._fragment == null ? null : new String(this._fragment);
    }

    public String getFragment() throws URIException {
        return this._fragment == null ? null : decode(this._fragment, getProtocolCharset());
    }

    protected char[] removeFragmentIdentifier(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        int indexOf = new String(cArr).indexOf(35);
        if (indexOf != -1) {
            return new String(cArr).substring(0, indexOf).toCharArray();
        }
        return cArr;
    }

    protected char[] normalize(char[] cArr) throws URIException {
        if (cArr == null) {
            return null;
        }
        String str = new String(cArr);
        if (str.startsWith("./")) {
            str = str.substring(1);
        } else if (str.startsWith("../")) {
            str = str.substring(2);
        } else if (str.startsWith("..")) {
            str = str.substring(2);
        }
        while (true) {
            int indexOf = str.indexOf("/./");
            if (indexOf == -1) {
                break;
            }
            str = new StringBuffer().append(str.substring(0, indexOf)).append(str.substring(indexOf + 2)).toString();
        }
        if (str.endsWith("/.")) {
            str = str.substring(0, str.length() - 1);
        }
        String str2 = str;
        int i = 0;
        while (true) {
            int indexOf2 = str2.indexOf("/../", i);
            if (indexOf2 == -1) {
                break;
            }
            int lastIndexOf = str2.lastIndexOf(47, indexOf2 - 1);
            if (lastIndexOf >= 0) {
                str2 = new StringBuffer().append(str2.substring(0, lastIndexOf)).append(str2.substring(indexOf2 + 3)).toString();
            } else {
                i = indexOf2 + 3;
            }
        }
        if (str2.endsWith("/..")) {
            i = str2.lastIndexOf(47, str2.length() - 4);
            if (i >= 0) {
                str2 = str2.substring(0, i + 1);
            }
        }
        while (true) {
            i = str2.indexOf("/../");
            if (i != -1 && str2.lastIndexOf(47, i - 1) < 0) {
                str2 = str2.substring(i + 3);
            }
        }
        if (str2.endsWith("/..") && str2.lastIndexOf(47, str2.length() - 4) < 0) {
            str2 = "/";
        }
        return str2.toCharArray();
    }

    public void normalize() throws URIException {
        if (isAbsPath()) {
            this._path = normalize(this._path);
            setURI();
        }
    }

    protected boolean equals(char[] cArr, char[] cArr2) {
        if (cArr == null && cArr2 == null) {
            return true;
        }
        if (cArr == null || cArr2 == null || cArr.length != cArr2.length) {
            return false;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof URI)) {
            return false;
        }
        URI uri = (URI) obj;
        if (!equals(this._scheme, uri._scheme)) {
            return false;
        }
        if (!equals(this._opaque, uri._opaque)) {
            return false;
        }
        if (!equals(this._authority, uri._authority)) {
            return false;
        }
        if (!equals(this._path, uri._path)) {
            return false;
        }
        if (!equals(this._query, uri._query)) {
            return false;
        }
        if (equals(this._fragment, uri._fragment)) {
            return true;
        }
        return false;
    }

    protected void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }

    protected void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
    }

    public int hashCode() {
        int i = 0;
        if (this.hash == 0) {
            char[] cArr = this._uri;
            if (cArr != null) {
                for (char c : cArr) {
                    this.hash = (this.hash * 31) + c;
                }
            }
            char[] cArr2 = this._fragment;
            if (cArr2 != null) {
                int length = cArr2.length;
                while (i < length) {
                    this.hash = (this.hash * 31) + cArr2[i];
                    i++;
                }
            }
        }
        return this.hash;
    }

    public int compareTo(Object obj) throws ClassCastException {
        URI uri = (URI) obj;
        if (equals(this._authority, uri.getRawAuthority())) {
            return toString().compareTo(uri.toString());
        }
        return -1;
    }

    public synchronized Object clone() {
        URI uri;
        uri = new URI();
        uri._uri = this._uri;
        uri._scheme = this._scheme;
        uri._opaque = this._opaque;
        uri._authority = this._authority;
        uri._userinfo = this._userinfo;
        uri._host = this._host;
        uri._port = this._port;
        uri._path = this._path;
        uri._query = this._query;
        uri._fragment = this._fragment;
        uri.protocolCharset = this.protocolCharset;
        uri._is_hier_part = this._is_hier_part;
        uri._is_opaque_part = this._is_opaque_part;
        uri._is_net_path = this._is_net_path;
        uri._is_abs_path = this._is_abs_path;
        uri._is_rel_path = this._is_rel_path;
        uri._is_reg_name = this._is_reg_name;
        uri._is_server = this._is_server;
        uri._is_hostname = this._is_hostname;
        uri._is_IPv4address = this._is_IPv4address;
        uri._is_IPv6reference = this._is_IPv6reference;
        return uri;
    }

    public char[] getRawURI() {
        return this._uri;
    }

    public String getEscapedURI() {
        return this._uri == null ? null : new String(this._uri);
    }

    public String getURI() throws URIException {
        return this._uri == null ? null : decode(this._uri, getProtocolCharset());
    }

    public char[] getRawURIReference() {
        if (this._fragment == null) {
            return this._uri;
        }
        if (this._uri == null) {
            return this._fragment;
        }
        return new StringBuffer().append(new String(this._uri)).append("#").append(new String(this._fragment)).toString().toCharArray();
    }

    public String getEscapedURIReference() {
        char[] rawURIReference = getRawURIReference();
        return rawURIReference == null ? null : new String(rawURIReference);
    }

    public String getURIReference() throws URIException {
        char[] rawURIReference = getRawURIReference();
        return rawURIReference == null ? null : decode(rawURIReference, getProtocolCharset());
    }

    public String toString() {
        return getEscapedURI();
    }
}
