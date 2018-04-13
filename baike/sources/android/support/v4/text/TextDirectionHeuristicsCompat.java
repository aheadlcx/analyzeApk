package android.support.v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR = new e(a.INSTANCE_RTL, false);
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR = new e(b.INSTANCE, false);
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL = new e(b.INSTANCE, true);
    public static final TextDirectionHeuristicCompat LOCALE = f.INSTANCE;
    public static final TextDirectionHeuristicCompat LTR = new e(null, false);
    public static final TextDirectionHeuristicCompat RTL = new e(null, true);

    private interface c {
        int checkRtl(CharSequence charSequence, int i, int i2);
    }

    private static class a implements c {
        public static final a INSTANCE_LTR = new a(false);
        public static final a INSTANCE_RTL = new a(true);
        private final boolean a;

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i + i2;
            int i4 = 0;
            while (i < i3) {
                switch (TextDirectionHeuristicsCompat.a(Character.getDirectionality(charSequence.charAt(i)))) {
                    case 0:
                        if (!this.a) {
                            i4 = 1;
                            break;
                        }
                        return 0;
                    case 1:
                        if (this.a) {
                            i4 = 1;
                            break;
                        }
                        return 1;
                    default:
                        break;
                }
                i++;
            }
            if (i4 == 0) {
                return 2;
            }
            if (this.a) {
                return 1;
            }
            return 0;
        }

        private a(boolean z) {
            this.a = z;
        }
    }

    private static class b implements c {
        public static final b INSTANCE = new b();

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i + i2;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = TextDirectionHeuristicsCompat.b(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }

        private b() {
        }
    }

    private static abstract class d implements TextDirectionHeuristicCompat {
        private final c a;

        protected abstract boolean a();

        public d(c cVar) {
            this.a = cVar;
        }

        public boolean isRtl(char[] cArr, int i, int i2) {
            return isRtl(CharBuffer.wrap(cArr), i, i2);
        }

        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            } else if (this.a == null) {
                return a();
            } else {
                return a(charSequence, i, i2);
            }
        }

        private boolean a(CharSequence charSequence, int i, int i2) {
            switch (this.a.checkRtl(charSequence, i, i2)) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    return a();
            }
        }
    }

    private static class e extends d {
        private final boolean a;

        e(c cVar, boolean z) {
            super(cVar);
            this.a = z;
        }

        protected boolean a() {
            return this.a;
        }
    }

    private static class f extends d {
        public static final f INSTANCE = new f();

        public f() {
            super(null);
        }

        protected boolean a() {
            if (TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
                return true;
            }
            return false;
        }
    }

    static int a(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
            case 2:
                return 0;
            default:
                return 2;
        }
    }

    static int b(int i) {
        switch (i) {
            case 0:
            case 14:
            case 15:
                return 1;
            case 1:
            case 2:
            case 16:
            case 17:
                return 0;
            default:
                return 2;
        }
    }

    private TextDirectionHeuristicsCompat() {
    }
}
