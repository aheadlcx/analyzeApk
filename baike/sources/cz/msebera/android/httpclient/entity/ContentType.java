package cz.msebera.android.httpclient.entity;

import com.qiniu.android.http.Client;
import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.message.BasicHeaderValueFormatter;
import cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import cz.msebera.android.httpclient.util.TextUtils;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import qsbk.app.thirdparty.net.HttpManager;

@Immutable
public final class ContentType implements Serializable {
    public static final ContentType APPLICATION_ATOM_XML = create("application/atom+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_FORM_URLENCODED = create("application/x-www-form-urlencoded", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_JSON = create(Client.JsonMime, Consts.UTF_8);
    public static final ContentType APPLICATION_OCTET_STREAM = create("application/octet-stream", (Charset) null);
    public static final ContentType APPLICATION_SVG_XML = create("application/svg+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XHTML_XML = create("application/xhtml+xml", Consts.ISO_8859_1);
    public static final ContentType APPLICATION_XML = create("application/xml", Consts.ISO_8859_1);
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;
    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType MULTIPART_FORM_DATA = create(HttpManager.MULTIPART_FORM_DATA, Consts.ISO_8859_1);
    public static final ContentType TEXT_HTML = create("text/html", Consts.ISO_8859_1);
    public static final ContentType TEXT_PLAIN = create(HTTP.PLAIN_TEXT_TYPE, Consts.ISO_8859_1);
    public static final ContentType TEXT_XML = create("text/xml", Consts.ISO_8859_1);
    public static final ContentType WILDCARD = create("*/*", (Charset) null);
    private final String a;
    private final Charset b;
    private final NameValuePair[] c;

    ContentType(String str, Charset charset) {
        this.a = str;
        this.b = charset;
        this.c = null;
    }

    ContentType(String str, Charset charset, NameValuePair[] nameValuePairArr) {
        this.a = str;
        this.b = charset;
        this.c = nameValuePairArr;
    }

    public String getMimeType() {
        return this.a;
    }

    public Charset getCharset() {
        return this.b;
    }

    public String getParameter(String str) {
        Args.notEmpty((CharSequence) str, "Parameter name");
        if (this.c == null) {
            return null;
        }
        for (NameValuePair nameValuePair : this.c) {
            if (nameValuePair.getName().equalsIgnoreCase(str)) {
                return nameValuePair.getValue();
            }
        }
        return null;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(64);
        charArrayBuffer.append(this.a);
        if (this.c != null) {
            charArrayBuffer.append("; ");
            BasicHeaderValueFormatter.INSTANCE.formatParameters(charArrayBuffer, this.c, false);
        } else if (this.b != null) {
            charArrayBuffer.append(HTTP.CHARSET_PARAM);
            charArrayBuffer.append(this.b.name());
        }
        return charArrayBuffer.toString();
    }

    private static boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"' || charAt == ',' || charAt == ';') {
                return false;
            }
        }
        return true;
    }

    public static ContentType create(String str, Charset charset) {
        String toLowerCase = ((String) Args.notBlank(str, "MIME type")).toLowerCase(Locale.ROOT);
        Args.check(a(toLowerCase), "MIME type may not contain reserved characters");
        return new ContentType(toLowerCase, charset);
    }

    public static ContentType create(String str) {
        return new ContentType(str, (Charset) null);
    }

    public static ContentType create(String str, String str2) throws UnsupportedCharsetException {
        return create(str, !TextUtils.isBlank(str2) ? Charset.forName(str2) : null);
    }

    private static ContentType a(HeaderElement headerElement, boolean z) {
        return a(headerElement.getName(), headerElement.getParameters(), z);
    }

    private static ContentType a(String str, NameValuePair[] nameValuePairArr, boolean z) {
        Charset forName;
        for (NameValuePair nameValuePair : nameValuePairArr) {
            if (nameValuePair.getName().equalsIgnoreCase("charset")) {
                Object value = nameValuePair.getValue();
                if (!TextUtils.isBlank(value)) {
                    try {
                        forName = Charset.forName(value);
                    } catch (UnsupportedCharsetException e) {
                        if (z) {
                            throw e;
                        }
                        forName = null;
                    }
                    if (nameValuePairArr == null || nameValuePairArr.length <= 0) {
                        nameValuePairArr = null;
                    }
                    return new ContentType(str, forName, nameValuePairArr);
                }
                forName = null;
                nameValuePairArr = null;
                return new ContentType(str, forName, nameValuePairArr);
            }
        }
        forName = null;
        nameValuePairArr = null;
        return new ContentType(str, forName, nameValuePairArr);
    }

    public static ContentType create(String str, NameValuePair... nameValuePairArr) throws UnsupportedCharsetException {
        Args.check(a(((String) Args.notBlank(str, "MIME type")).toLowerCase(Locale.ROOT)), "MIME type may not contain reserved characters");
        return a(str, nameValuePairArr, true);
    }

    public static ContentType parse(String str) throws ParseException, UnsupportedCharsetException {
        Args.notNull(str, "Content type");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        HeaderElement[] parseElements = BasicHeaderValueParser.INSTANCE.parseElements(charArrayBuffer, new ParserCursor(0, str.length()));
        if (parseElements.length > 0) {
            return a(parseElements[0], true);
        }
        throw new ParseException("Invalid content type: " + str);
    }

    public static ContentType get(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        if (httpEntity == null) {
            return null;
        }
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return null;
        }
        HeaderElement[] elements = contentType.getElements();
        if (elements.length > 0) {
            return a(elements[0], true);
        }
        return null;
    }

    public static ContentType getLenient(HttpEntity httpEntity) {
        ContentType contentType = null;
        if (httpEntity != null) {
            Header contentType2 = httpEntity.getContentType();
            if (contentType2 != null) {
                try {
                    HeaderElement[] elements = contentType2.getElements();
                    if (elements.length > 0) {
                        contentType = a(elements[0], false);
                    }
                } catch (ParseException e) {
                }
            }
        }
        return contentType;
    }

    public static ContentType getOrDefault(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(httpEntity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public static ContentType getLenientOrDefault(HttpEntity httpEntity) throws ParseException, UnsupportedCharsetException {
        ContentType contentType = get(httpEntity);
        return contentType != null ? contentType : DEFAULT_TEXT;
    }

    public ContentType withCharset(Charset charset) {
        return create(getMimeType(), charset);
    }

    public ContentType withCharset(String str) {
        return create(getMimeType(), str);
    }

    public ContentType withParameters(NameValuePair... nameValuePairArr) throws UnsupportedCharsetException {
        int i = 0;
        if (nameValuePairArr.length == 0) {
            return this;
        }
        Map linkedHashMap = new LinkedHashMap();
        if (this.c != null) {
            for (NameValuePair nameValuePair : this.c) {
                linkedHashMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        int length = nameValuePairArr.length;
        while (i < length) {
            NameValuePair nameValuePair2 = nameValuePairArr[i];
            linkedHashMap.put(nameValuePair2.getName(), nameValuePair2.getValue());
            i++;
        }
        List arrayList = new ArrayList(linkedHashMap.size() + 1);
        if (!(this.b == null || linkedHashMap.containsKey("charset"))) {
            arrayList.add(new BasicNameValuePair("charset", this.b.name()));
        }
        for (Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        return a(getMimeType(), (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]), true);
    }
}
