package org.apache.commons.io;

import java.nio.charset.Charset;

public class a {
    @Deprecated
    public static final Charset a = Charset.forName("ISO-8859-1");
    @Deprecated
    public static final Charset b = Charset.forName("US-ASCII");
    @Deprecated
    public static final Charset c = Charset.forName("UTF-16");
    @Deprecated
    public static final Charset d = Charset.forName("UTF-16BE");
    @Deprecated
    public static final Charset e = Charset.forName("UTF-16LE");
    @Deprecated
    public static final Charset f = Charset.forName("UTF-8");

    public static Charset a(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset a(String str) {
        return str == null ? Charset.defaultCharset() : Charset.forName(str);
    }
}
