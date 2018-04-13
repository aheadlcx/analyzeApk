package cz.msebera.android.httpclient.client.utils;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.message.TokenParser;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import kotlin.text.Typography;

@Immutable
public class URLEncodedUtils {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final BitSet a = new BitSet(256);
    private static final BitSet b = new BitSet(256);
    private static final BitSet c = new BitSet(256);
    private static final BitSet d = new BitSet(256);
    private static final BitSet e = new BitSet(256);
    private static final BitSet f = new BitSet(256);
    private static final BitSet g = new BitSet(256);

    public static List<NameValuePair> parse(URI uri, String str) {
        String rawQuery = uri.getRawQuery();
        if (rawQuery == null || rawQuery.isEmpty()) {
            return Collections.emptyList();
        }
        return parse(rawQuery, Charset.forName(str));
    }

    public static List<NameValuePair> parse(HttpEntity httpEntity) throws IOException {
        int i = 1024;
        ContentType contentType = ContentType.get(httpEntity);
        if (contentType == null || !contentType.getMimeType().equalsIgnoreCase("application/x-www-form-urlencoded")) {
            return Collections.emptyList();
        }
        boolean z;
        long contentLength = httpEntity.getContentLength();
        if (contentLength <= 2147483647L) {
            z = true;
        } else {
            z = false;
        }
        CharArrayBuffer charArrayBuffer = "HTTP entity is too large";
        Args.check(z, charArrayBuffer);
        Charset charset = contentType.getCharset() != null ? contentType.getCharset() : HTTP.DEF_CONTENT_CHARSET;
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return Collections.emptyList();
        }
        try {
            if (contentLength > 0) {
                i = (int) contentLength;
            }
            charArrayBuffer = new CharArrayBuffer(i);
            Reader inputStreamReader = new InputStreamReader(content, charset);
            char[] cArr = new char[1024];
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (read == -1) {
                    break;
                }
                charArrayBuffer.append(cArr, 0, read);
            }
            if (charArrayBuffer.length() == 0) {
                return Collections.emptyList();
            }
            return parse(charArrayBuffer, charset, Typography.amp);
        } finally {
            content.close();
        }
    }

    public static boolean isEncoded(HttpEntity httpEntity) {
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return false;
        }
        HeaderElement[] elements = contentType.getElements();
        if (elements.length > 0) {
            return elements[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
        }
        return false;
    }

    @Deprecated
    public static void parse(List<NameValuePair> list, Scanner scanner, String str) {
        parse(list, scanner, "[&;]", str);
    }

    @Deprecated
    public static void parse(List<NameValuePair> list, Scanner scanner, String str, String str2) {
        scanner.useDelimiter(str);
        while (scanner.hasNext()) {
            String a;
            String next = scanner.next();
            int indexOf = next.indexOf("=");
            if (indexOf != -1) {
                a = a(next.substring(0, indexOf).trim(), str2);
                next = a(next.substring(indexOf + 1).trim(), str2);
            } else {
                a = a(next.trim(), str2);
                next = null;
            }
            list.add(new BasicNameValuePair(a, next));
        }
    }

    public static List<NameValuePair> parse(String str, Charset charset) {
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return parse(charArrayBuffer, charset, Typography.amp, ';');
    }

    public static List<NameValuePair> parse(String str, Charset charset, char... cArr) {
        if (str == null) {
            return Collections.emptyList();
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        return parse(charArrayBuffer, charset, cArr);
    }

    public static List<NameValuePair> parse(CharArrayBuffer charArrayBuffer, Charset charset, char... cArr) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        TokenParser tokenParser = TokenParser.INSTANCE;
        BitSet bitSet = new BitSet();
        for (char c : cArr) {
            bitSet.set(c);
        }
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        List<NameValuePair> arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            bitSet.set(61);
            String parseToken = tokenParser.parseToken(charArrayBuffer, parserCursor, bitSet);
            String str = null;
            if (!parserCursor.atEnd()) {
                char charAt = charArrayBuffer.charAt(parserCursor.getPos());
                parserCursor.updatePos(parserCursor.getPos() + 1);
                if (charAt == '=') {
                    bitSet.clear(61);
                    str = tokenParser.parseValue(charArrayBuffer, parserCursor, bitSet);
                    if (!parserCursor.atEnd()) {
                        parserCursor.updatePos(parserCursor.getPos() + 1);
                    }
                }
            }
            if (!parseToken.isEmpty()) {
                arrayList.add(new BasicNameValuePair(d(parseToken, charset), d(str, charset)));
            }
        }
        return arrayList;
    }

    public static String format(List<? extends NameValuePair> list, String str) {
        return format((List) list, (char) Typography.amp, str);
    }

    public static String format(List<? extends NameValuePair> list, char c, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String b = b(nameValuePair.getName(), str);
            String b2 = b(nameValuePair.getValue(), str);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(b);
            if (b2 != null) {
                stringBuilder.append("=");
                stringBuilder.append(b2);
            }
        }
        return stringBuilder.toString();
    }

    public static String format(Iterable<? extends NameValuePair> iterable, Charset charset) {
        return format((Iterable) iterable, (char) Typography.amp, charset);
    }

    public static String format(Iterable<? extends NameValuePair> iterable, char c, Charset charset) {
        StringBuilder stringBuilder = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String e = e(nameValuePair.getName(), charset);
            String e2 = e(nameValuePair.getValue(), charset);
            if (stringBuilder.length() > 0) {
                stringBuilder.append(c);
            }
            stringBuilder.append(e);
            if (e2 != null) {
                stringBuilder.append("=");
                stringBuilder.append(e2);
            }
        }
        return stringBuilder.toString();
    }

    static {
        int i;
        for (i = 97; i <= 122; i++) {
            a.set(i);
        }
        for (i = 65; i <= 90; i++) {
            a.set(i);
        }
        for (i = 48; i <= 57; i++) {
            a.set(i);
        }
        a.set(95);
        a.set(45);
        a.set(46);
        a.set(42);
        g.or(a);
        a.set(33);
        a.set(126);
        a.set(39);
        a.set(40);
        a.set(41);
        b.set(44);
        b.set(59);
        b.set(58);
        b.set(36);
        b.set(38);
        b.set(43);
        b.set(61);
        c.or(a);
        c.or(b);
        d.or(a);
        d.set(47);
        d.set(59);
        d.set(58);
        d.set(64);
        d.set(38);
        d.set(61);
        d.set(43);
        d.set(36);
        d.set(44);
        f.set(59);
        f.set(47);
        f.set(63);
        f.set(58);
        f.set(64);
        f.set(38);
        f.set(61);
        f.set(43);
        f.set(36);
        f.set(44);
        f.set(91);
        f.set(93);
        e.or(f);
        e.or(a);
    }

    private static String a(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            int i = encode.get() & 255;
            if (bitSet.get(i)) {
                stringBuilder.append((char) i);
            } else if (z && i == 32) {
                stringBuilder.append('+');
            } else {
                stringBuilder.append("%");
                char toUpperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                char toUpperCase2 = Character.toUpperCase(Character.forDigit(i & 15, 16));
                stringBuilder.append(toUpperCase);
                stringBuilder.append(toUpperCase2);
            }
        }
        return stringBuilder.toString();
    }

    private static String a(String str, Charset charset, boolean z) {
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            char c = wrap.get();
            if (c == '%' && wrap.remaining() >= 2) {
                c = wrap.get();
                char c2 = wrap.get();
                int digit = Character.digit(c, 16);
                int digit2 = Character.digit(c2, 16);
                if (digit == -1 || digit2 == -1) {
                    allocate.put((byte) 37);
                    allocate.put((byte) c);
                    allocate.put((byte) c2);
                } else {
                    allocate.put((byte) ((digit << 4) + digit2));
                }
            } else if (z && c == '+') {
                allocate.put((byte) 32);
            } else {
                allocate.put((byte) c);
            }
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    private static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return a(str, str2 != null ? Charset.forName(str2) : Consts.UTF_8, true);
    }

    private static String d(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return a(str, charset, true);
    }

    private static String b(String str, String str2) {
        if (str == null) {
            return null;
        }
        return a(str, str2 != null ? Charset.forName(str2) : Consts.UTF_8, g, true);
    }

    private static String e(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Consts.UTF_8;
        }
        return a(str, charset, g, true);
    }

    static String a(String str, Charset charset) {
        return a(str, charset, c, false);
    }

    static String b(String str, Charset charset) {
        return a(str, charset, e, false);
    }

    static String c(String str, Charset charset) {
        return a(str, charset, d, false);
    }
}
