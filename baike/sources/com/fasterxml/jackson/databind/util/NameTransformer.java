package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer$1 */
    final class AnonymousClass1 extends NameTransformer {
        final /* synthetic */ String val$prefix;
        final /* synthetic */ String val$suffix;

        AnonymousClass1(String str, String str2) {
            this.val$prefix = str;
            this.val$suffix = str2;
        }

        public String transform(String str) {
            return this.val$prefix + str + this.val$suffix;
        }

        public String reverse(String str) {
            if (str.startsWith(this.val$prefix)) {
                String substring = str.substring(this.val$prefix.length());
                if (substring.endsWith(this.val$suffix)) {
                    return substring.substring(0, substring.length() - this.val$suffix.length());
                }
            }
            return null;
        }

        public String toString() {
            return "[PreAndSuffixTransformer('" + this.val$prefix + "','" + this.val$suffix + "')]";
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer$2 */
    final class AnonymousClass2 extends NameTransformer {
        final /* synthetic */ String val$prefix;

        AnonymousClass2(String str) {
            this.val$prefix = str;
        }

        public String transform(String str) {
            return this.val$prefix + str;
        }

        public String reverse(String str) {
            if (str.startsWith(this.val$prefix)) {
                return str.substring(this.val$prefix.length());
            }
            return null;
        }

        public String toString() {
            return "[PrefixTransformer('" + this.val$prefix + "')]";
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.util.NameTransformer$3 */
    final class AnonymousClass3 extends NameTransformer {
        final /* synthetic */ String val$suffix;

        AnonymousClass3(String str) {
            this.val$suffix = str;
        }

        public String transform(String str) {
            return str + this.val$suffix;
        }

        public String reverse(String str) {
            if (str.endsWith(this.val$suffix)) {
                return str.substring(0, str.length() - this.val$suffix.length());
            }
            return null;
        }

        public String toString() {
            return "[SuffixTransformer('" + this.val$suffix + "')]";
        }
    }

    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }

        public String reverse(String str) {
            String reverse = this._t1.reverse(str);
            if (reverse != null) {
                return this._t2.reverse(reverse);
            }
            return reverse;
        }

        public String toString() {
            return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
        }
    }

    protected static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        protected NopTransformer() {
        }

        public String transform(String str) {
            return str;
        }

        public String reverse(String str) {
            return str;
        }
    }

    public abstract String reverse(String str);

    public abstract String transform(String str);

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(String str, String str2) {
        Object obj = 1;
        Object obj2 = (str == null || str.length() <= 0) ? null : 1;
        if (str2 == null || str2.length() <= 0) {
            obj = null;
        }
        if (obj2 == null) {
            return obj != null ? new AnonymousClass3(str2) : NOP;
        } else {
            if (obj != null) {
                return new AnonymousClass1(str, str2);
            }
            return new AnonymousClass2(str);
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }
}
