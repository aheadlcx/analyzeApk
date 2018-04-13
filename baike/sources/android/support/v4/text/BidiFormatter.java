package android.support.v4.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;
import kotlin.text.Typography;

public final class BidiFormatter {
    private static TextDirectionHeuristicCompat a = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
    private static final String b = Character.toString('‎');
    private static final String c = Character.toString('‏');
    private static final BidiFormatter d = new BidiFormatter(false, 2, a);
    private static final BidiFormatter e = new BidiFormatter(true, 2, a);
    private final boolean f;
    private final int g;
    private final TextDirectionHeuristicCompat h;

    public static final class Builder {
        private boolean a;
        private int b;
        private TextDirectionHeuristicCompat c;

        public Builder() {
            a(BidiFormatter.b(Locale.getDefault()));
        }

        public Builder(boolean z) {
            a(z);
        }

        public Builder(Locale locale) {
            a(BidiFormatter.b(locale));
        }

        private void a(boolean z) {
            this.a = z;
            this.c = BidiFormatter.a;
            this.b = 2;
        }

        public Builder stereoReset(boolean z) {
            if (z) {
                this.b |= 2;
            } else {
                this.b &= -3;
            }
            return this;
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.c = textDirectionHeuristicCompat;
            return this;
        }

        private static BidiFormatter b(boolean z) {
            return z ? BidiFormatter.e : BidiFormatter.d;
        }

        public BidiFormatter build() {
            if (this.b == 2 && this.c == BidiFormatter.a) {
                return b(this.a);
            }
            return new BidiFormatter(this.a, this.b, this.c);
        }
    }

    private static class a {
        private static final byte[] a = new byte[1792];
        private final CharSequence b;
        private final boolean c;
        private final int d;
        private int e;
        private char f;

        static {
            for (int i = 0; i < 1792; i++) {
                a[i] = Character.getDirectionality(i);
            }
        }

        a(CharSequence charSequence, boolean z) {
            this.b = charSequence;
            this.c = z;
            this.d = charSequence.length();
        }

        int a() {
            this.e = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.e < this.d && i == 0) {
                switch (c()) {
                    case (byte) 0:
                        if (i3 != 0) {
                            i = i3;
                            break;
                        }
                        return -1;
                    case (byte) 1:
                    case (byte) 2:
                        if (i3 != 0) {
                            i = i3;
                            break;
                        }
                        return 1;
                    case (byte) 9:
                        break;
                    case (byte) 14:
                    case (byte) 15:
                        i3++;
                        i2 = -1;
                        break;
                    case (byte) 16:
                    case (byte) 17:
                        i3++;
                        i2 = 1;
                        break;
                    case (byte) 18:
                        i3--;
                        i2 = 0;
                        break;
                    default:
                        i = i3;
                        break;
                }
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.e > 0) {
                switch (d()) {
                    case (byte) 14:
                    case (byte) 15:
                        if (i != i3) {
                            i3--;
                            break;
                        }
                        return -1;
                    case (byte) 16:
                    case (byte) 17:
                        if (i != i3) {
                            i3--;
                            break;
                        }
                        return 1;
                    case (byte) 18:
                        i3++;
                        break;
                    default:
                        break;
                }
            }
            return 0;
        }

        int b() {
            this.e = this.d;
            int i = 0;
            int i2 = 0;
            while (this.e > 0) {
                switch (d()) {
                    case (byte) 0:
                        if (i2 != 0) {
                            if (i != 0) {
                                break;
                            }
                            i = i2;
                            break;
                        }
                        return -1;
                    case (byte) 1:
                    case (byte) 2:
                        if (i2 != 0) {
                            if (i != 0) {
                                break;
                            }
                            i = i2;
                            break;
                        }
                        return 1;
                    case (byte) 9:
                        break;
                    case (byte) 14:
                    case (byte) 15:
                        if (i != i2) {
                            i2--;
                            break;
                        }
                        return -1;
                    case (byte) 16:
                    case (byte) 17:
                        if (i != i2) {
                            i2--;
                            break;
                        }
                        return 1;
                    case (byte) 18:
                        i2++;
                        break;
                    default:
                        if (i != 0) {
                            break;
                        }
                        i = i2;
                        break;
                }
            }
            return 0;
        }

        private static byte a(char c) {
            return c < '܀' ? a[c] : Character.getDirectionality(c);
        }

        byte c() {
            this.f = this.b.charAt(this.e);
            if (Character.isHighSurrogate(this.f)) {
                int codePointAt = Character.codePointAt(this.b, this.e);
                this.e += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.e++;
            byte a = a(this.f);
            if (!this.c) {
                return a;
            }
            if (this.f == Typography.less) {
                return e();
            }
            if (this.f == Typography.amp) {
                return g();
            }
            return a;
        }

        byte d() {
            this.f = this.b.charAt(this.e - 1);
            if (Character.isLowSurrogate(this.f)) {
                int codePointBefore = Character.codePointBefore(this.b, this.e);
                this.e -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.e--;
            byte a = a(this.f);
            if (!this.c) {
                return a;
            }
            if (this.f == Typography.greater) {
                return f();
            }
            if (this.f == ';') {
                return h();
            }
            return a;
        }

        private byte e() {
            int i = this.e;
            while (this.e < this.d) {
                CharSequence charSequence = this.b;
                int i2 = this.e;
                this.e = i2 + 1;
                this.f = charSequence.charAt(i2);
                if (this.f == Typography.greater) {
                    return (byte) 12;
                }
                if (this.f == '\"' || this.f == '\'') {
                    char c = this.f;
                    while (this.e < this.d) {
                        CharSequence charSequence2 = this.b;
                        int i3 = this.e;
                        this.e = i3 + 1;
                        char charAt = charSequence2.charAt(i3);
                        this.f = charAt;
                        if (charAt == c) {
                            break;
                        }
                    }
                }
            }
            this.e = i;
            this.f = Typography.less;
            return (byte) 13;
        }

        private byte f() {
            int i = this.e;
            while (this.e > 0) {
                CharSequence charSequence = this.b;
                int i2 = this.e - 1;
                this.e = i2;
                this.f = charSequence.charAt(i2);
                if (this.f == Typography.less) {
                    return (byte) 12;
                }
                if (this.f == Typography.greater) {
                    break;
                } else if (this.f == '\"' || this.f == '\'') {
                    char c = this.f;
                    while (this.e > 0) {
                        CharSequence charSequence2 = this.b;
                        int i3 = this.e - 1;
                        this.e = i3;
                        char charAt = charSequence2.charAt(i3);
                        this.f = charAt;
                        if (charAt == c) {
                            break;
                        }
                    }
                }
            }
            this.e = i;
            this.f = Typography.greater;
            return (byte) 13;
        }

        private byte g() {
            while (this.e < this.d) {
                CharSequence charSequence = this.b;
                int i = this.e;
                this.e = i + 1;
                char charAt = charSequence.charAt(i);
                this.f = charAt;
                if (charAt == ';') {
                    break;
                }
            }
            return (byte) 12;
        }

        private byte h() {
            int i = this.e;
            while (this.e > 0) {
                CharSequence charSequence = this.b;
                int i2 = this.e - 1;
                this.e = i2;
                this.f = charSequence.charAt(i2);
                if (this.f != Typography.amp) {
                    if (this.f == ';') {
                        break;
                    }
                }
                return (byte) 12;
            }
            this.e = i;
            this.f = ';';
            return (byte) 13;
        }
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(boolean z) {
        return new Builder(z).build();
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    private BidiFormatter(boolean z, int i, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.f = z;
        this.g = i;
        this.h = textDirectionHeuristicCompat;
    }

    public boolean isRtlContext() {
        return this.f;
    }

    public boolean getStereoReset() {
        return (this.g & 2) != 0;
    }

    private String a(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (!this.f && (isRtl || a(charSequence) == 1)) {
            return b;
        }
        if (!this.f || (isRtl && a(charSequence) != -1)) {
            return "";
        }
        return c;
    }

    private String b(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (!this.f && (isRtl || b(charSequence) == 1)) {
            return b;
        }
        if (!this.f || (isRtl && b(charSequence) != -1)) {
            return "";
        }
        return c;
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtl(CharSequence charSequence) {
        return this.h.isRtl(charSequence, 0, charSequence.length());
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z).toString();
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z) {
            spannableStringBuilder.append(b(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (isRtl != this.f) {
            spannableStringBuilder.append(isRtl ? '‫' : '‪');
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append('‬');
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append(a(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(str, textDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(charSequence, textDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, boolean z) {
        return unicodeWrap(str, this.h, z);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, boolean z) {
        return unicodeWrap(charSequence, this.h, z);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.h, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.h, true);
    }

    private static boolean b(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    private static int a(CharSequence charSequence) {
        return new a(charSequence, false).b();
    }

    private static int b(CharSequence charSequence) {
        return new a(charSequence, false).a();
    }
}
