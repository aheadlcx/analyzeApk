package kotlin.text;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/text/Charsets;", "", "()V", "ISO_8859_1", "Ljava/nio/charset/Charset;", "US_ASCII", "UTF_16", "UTF_16BE", "UTF_16LE", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "UTF_32BE", "UTF32_BE", "UTF_32LE", "UTF32_LE", "UTF_8", "utf_32", "utf_32be", "utf_32le", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class Charsets {
    public static final Charsets INSTANCE = null;
    @NotNull
    @JvmField
    public static final Charset ISO_8859_1 = null;
    @NotNull
    @JvmField
    public static final Charset US_ASCII = null;
    @NotNull
    @JvmField
    public static final Charset UTF_16 = null;
    @NotNull
    @JvmField
    public static final Charset UTF_16BE = null;
    @NotNull
    @JvmField
    public static final Charset UTF_16LE = null;
    @NotNull
    @JvmField
    public static final Charset UTF_8 = null;
    private static Charset a;
    private static Charset b;
    private static Charset c;

    static {
        Charsets charsets = new Charsets();
    }

    private Charsets() {
        INSTANCE = this;
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-8\")");
        UTF_8 = forName;
        forName = Charset.forName(HTTP.UTF_16);
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-16\")");
        UTF_16 = forName;
        forName = Charset.forName("UTF-16BE");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-16BE\")");
        UTF_16BE = forName;
        forName = Charset.forName("UTF-16LE");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-16LE\")");
        UTF_16LE = forName;
        forName = Charset.forName("US-ASCII");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"US-ASCII\")");
        US_ASCII = forName;
        forName = Charset.forName("ISO-8859-1");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"ISO-8859-1\")");
        ISO_8859_1 = forName;
    }

    @NotNull
    @JvmName(name = "UTF32")
    public final Charset UTF32() {
        Charset charset = a;
        if (charset != null) {
            return charset;
        }
        this = this;
        charset = Charset.forName("UTF-32");
        Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(\"UTF-32\")");
        a = charset;
        return charset;
    }

    @NotNull
    @JvmName(name = "UTF32_LE")
    public final Charset UTF32_LE() {
        Charset charset = b;
        if (charset != null) {
            return charset;
        }
        this = this;
        charset = Charset.forName("UTF-32LE");
        Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(\"UTF-32LE\")");
        b = charset;
        return charset;
    }

    @NotNull
    @JvmName(name = "UTF32_BE")
    public final Charset UTF32_BE() {
        Charset charset = c;
        if (charset != null) {
            return charset;
        }
        this = this;
        charset = Charset.forName("UTF-32BE");
        Intrinsics.checkExpressionValueIsNotNull(charset, "Charset.forName(\"UTF-32BE\")");
        c = charset;
        return charset;
    }
}