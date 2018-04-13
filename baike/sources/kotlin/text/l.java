package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 6}, xi = 1, xs = "kotlin/text/StringsKt")
class l {
    @NotNull
    public static /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return trimMargin(str, str2);
    }

    @NotNull
    public static final String trimMargin(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(str2, "marginPrefix");
        return replaceIndentByMargin(str, "", str2);
    }

    @NotNull
    public static /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return replaceIndentByMargin(str, str2, str3);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String replaceIndentByMargin(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String r13, @org.jetbrains.annotations.NotNull java.lang.String r14) {
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0);
        r0 = "newIndent";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0);
        r0 = "marginPrefix";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0);
        r0 = r14;
        r0 = (java.lang.CharSequence) r0;
        r0 = kotlin.text.t.isBlank(r0);
        if (r0 != 0) goto L_0x0029;
    L_0x0018:
        r0 = 1;
    L_0x0019:
        if (r0 != 0) goto L_0x002b;
    L_0x001b:
        r1 = "marginPrefix must be non-blank string.";
        r0 = new java.lang.IllegalArgumentException;
        r1 = r1.toString();
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x0029:
        r0 = 0;
        goto L_0x0019;
    L_0x002b:
        r0 = r12;
        r0 = (java.lang.CharSequence) r0;
        r0 = kotlin.text.u.lines(r0);
        r1 = r12.length();
        r2 = r13.length();
        r3 = r0.size();
        r2 = r2 * r3;
        r8 = r1 + r2;
        r9 = b(r13);
        r10 = kotlin.collections.q.getLastIndex(r0);
        r0 = (java.lang.Iterable) r0;
        r1 = new java.util.ArrayList;
        r1.<init>();
        r6 = r1;
        r6 = (java.util.Collection) r6;
        r1 = 0;
        r11 = r0.iterator();
    L_0x0058:
        r0 = r11.hasNext();
        if (r0 == 0) goto L_0x00d2;
    L_0x005e:
        r0 = r11.next();
        r7 = r1 + 1;
        r0 = (java.lang.String) r0;
        if (r1 == 0) goto L_0x006a;
    L_0x0068:
        if (r1 != r10) goto L_0x007b;
    L_0x006a:
        r1 = r0;
        r1 = (java.lang.CharSequence) r1;
        r1 = kotlin.text.t.isBlank(r1);
        if (r1 == 0) goto L_0x007b;
    L_0x0073:
        r1 = 0;
    L_0x0074:
        if (r1 == 0) goto L_0x0079;
    L_0x0076:
        r6.add(r1);
    L_0x0079:
        r1 = r7;
        goto L_0x0058;
    L_0x007b:
        r1 = r0;
        r1 = (java.lang.CharSequence) r1;
        r2 = 0;
        r3 = r1.length();
        r4 = r3 + -1;
        if (r2 > r4) goto L_0x00ab;
    L_0x0087:
        r3 = r1.charAt(r2);
        r3 = kotlin.text.c.isWhitespace(r3);
        if (r3 != 0) goto L_0x00a4;
    L_0x0091:
        r3 = 1;
    L_0x0092:
        if (r3 == 0) goto L_0x00a6;
    L_0x0094:
        r1 = -1;
        if (r2 != r1) goto L_0x00ad;
    L_0x0097:
        r1 = 0;
    L_0x0098:
        if (r1 == 0) goto L_0x00a2;
    L_0x009a:
        r1 = r9.invoke(r1);
        r1 = (java.lang.String) r1;
        if (r1 != 0) goto L_0x0074;
    L_0x00a2:
        r1 = r0;
        goto L_0x0074;
    L_0x00a4:
        r3 = 0;
        goto L_0x0092;
    L_0x00a6:
        if (r2 == r4) goto L_0x00ab;
    L_0x00a8:
        r2 = r2 + 1;
        goto L_0x0087;
    L_0x00ab:
        r2 = -1;
        goto L_0x0094;
    L_0x00ad:
        r3 = 0;
        r4 = 4;
        r5 = 0;
        r1 = r14;
        r1 = kotlin.text.t.startsWith$default(r0, r1, r2, r3, r4, r5);
        if (r1 == 0) goto L_0x00d0;
    L_0x00b7:
        r1 = r14.length();
        r1 = r1 + r2;
        if (r0 != 0) goto L_0x00c6;
    L_0x00be:
        r0 = new kotlin.TypeCastException;
        r1 = "null cannot be cast to non-null type java.lang.String";
        r0.<init>(r1);
        throw r0;
    L_0x00c6:
        r1 = r0.substring(r1);
        r2 = "(this as java.lang.String).substring(startIndex)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2);
        goto L_0x0098;
    L_0x00d0:
        r1 = 0;
        goto L_0x0098;
    L_0x00d2:
        r6 = (java.util.List) r6;
        r0 = r6;
        r0 = (java.lang.Iterable) r0;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r8);
        r1 = (java.lang.Appendable) r1;
        r2 = "\n";
        r2 = (java.lang.CharSequence) r2;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r9 = 0;
        r0 = kotlin.collections.v.joinTo$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = (java.lang.StringBuilder) r0;
        r0 = r0.toString();
        r1 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.l.replaceIndentByMargin(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    @NotNull
    public static final String trimIndent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return replaceIndent(str, "");
    }

    @NotNull
    public static /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String replaceIndent(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12) {
        /*
        r5 = 0;
        r3 = 0;
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0);
        r0 = "newIndent";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0);
        r0 = r11;
        r0 = (java.lang.CharSequence) r0;
        r1 = kotlin.text.u.lines(r0);
        r0 = r1;
        r0 = (java.lang.Iterable) r0;
        r2 = new java.util.ArrayList;
        r2.<init>();
        r2 = (java.util.Collection) r2;
        r6 = r0.iterator();
    L_0x0022:
        r0 = r6.hasNext();
        if (r0 == 0) goto L_0x0040;
    L_0x0028:
        r4 = r6.next();
        r0 = r4;
        r0 = (java.lang.String) r0;
        r0 = (java.lang.CharSequence) r0;
        r0 = kotlin.text.t.isBlank(r0);
        if (r0 != 0) goto L_0x003e;
    L_0x0037:
        r0 = 1;
    L_0x0038:
        if (r0 == 0) goto L_0x0022;
    L_0x003a:
        r2.add(r4);
        goto L_0x0022;
    L_0x003e:
        r0 = r5;
        goto L_0x0038;
    L_0x0040:
        r2 = (java.util.List) r2;
        r2 = (java.lang.Iterable) r2;
        r0 = new java.util.ArrayList;
        r4 = 10;
        r4 = kotlin.collections.r.collectionSizeOrDefault(r2, r4);
        r0.<init>(r4);
        r0 = (java.util.Collection) r0;
        r4 = r2.iterator();
    L_0x0056:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x006e;
    L_0x005c:
        r2 = r4.next();
        r2 = (java.lang.String) r2;
        r2 = a(r2);
        r2 = java.lang.Integer.valueOf(r2);
        r0.add(r2);
        goto L_0x0056;
    L_0x006e:
        r0 = (java.util.List) r0;
        r0 = (java.lang.Iterable) r0;
        r0 = kotlin.collections.v.min(r0);
        r0 = (java.lang.Integer) r0;
        if (r0 == 0) goto L_0x00c7;
    L_0x007a:
        r0 = r0.intValue();
        r4 = r0;
    L_0x007f:
        r0 = r11.length();
        r2 = r12.length();
        r6 = r1.size();
        r2 = r2 * r6;
        r7 = r0 + r2;
        r8 = b(r12);
        r9 = kotlin.collections.q.getLastIndex(r1);
        r1 = (java.lang.Iterable) r1;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r0 = (java.util.Collection) r0;
        r10 = r1.iterator();
        r2 = r5;
    L_0x00a4:
        r1 = r10.hasNext();
        if (r1 == 0) goto L_0x00d9;
    L_0x00aa:
        r1 = r10.next();
        r6 = r2 + 1;
        r1 = (java.lang.String) r1;
        if (r2 == 0) goto L_0x00b6;
    L_0x00b4:
        if (r2 != r9) goto L_0x00c9;
    L_0x00b6:
        r2 = r1;
        r2 = (java.lang.CharSequence) r2;
        r2 = kotlin.text.t.isBlank(r2);
        if (r2 == 0) goto L_0x00c9;
    L_0x00bf:
        r2 = r3;
    L_0x00c0:
        if (r2 == 0) goto L_0x00c5;
    L_0x00c2:
        r0.add(r2);
    L_0x00c5:
        r2 = r6;
        goto L_0x00a4;
    L_0x00c7:
        r4 = r5;
        goto L_0x007f;
    L_0x00c9:
        r2 = kotlin.text.z.drop(r1, r4);
        if (r2 == 0) goto L_0x00d7;
    L_0x00cf:
        r2 = r8.invoke(r2);
        r2 = (java.lang.String) r2;
        if (r2 != 0) goto L_0x00c0;
    L_0x00d7:
        r2 = r1;
        goto L_0x00c0;
    L_0x00d9:
        r0 = (java.util.List) r0;
        r0 = (java.lang.Iterable) r0;
        r1 = new java.lang.StringBuilder;
        r1.<init>(r7);
        r1 = (java.lang.Appendable) r1;
        r2 = "\n";
        r2 = (java.lang.CharSequence) r2;
        r8 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r4 = r3;
        r6 = r3;
        r7 = r3;
        r9 = r3;
        r0 = kotlin.collections.v.joinTo$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9);
        r0 = (java.lang.StringBuilder) r0;
        r0 = r0.toString();
        r1 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.l.replaceIndent(java.lang.String, java.lang.String):java.lang.String");
    }

    @NotNull
    public static /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return prependIndent(str, str2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Intrinsics.checkParameterIsNotNull(str2, "indent");
        return k.joinToString$default(k.map(u.lineSequence(str), new o(str2)), "\n", null, null, 0, null, null, 62, null);
    }

    private static final int a(@NotNull String str) {
        int i;
        CharSequence charSequence = str;
        int length = charSequence.length() - 1;
        if (0 <= length) {
            int i2 = 0;
            while (true) {
                if ((!c.isWhitespace(charSequence.charAt(i2)) ? 1 : null) == null) {
                    if (i2 == length) {
                        break;
                    }
                    i2++;
                } else {
                    break;
                }
            }
            i = i2;
            return i != -1 ? str.length() : i;
        }
        i = -1;
        if (i != -1) {
        }
    }

    private static final Function1<String, String> b(String str) {
        if ((((CharSequence) str).length() == 0 ? 1 : null) != null) {
            return m.INSTANCE;
        }
        return new n(str);
    }
}
