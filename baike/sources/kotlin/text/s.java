package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000F\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\n\n\u0002\b\u0005\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0005H\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\r\u0010\b\u001a\u00020\t*\u00020\u0003H\b\u001a\r\u0010\n\u001a\u00020\u000b*\u00020\u0003H\b\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u0010\u001a\r\u0010\u0011\u001a\u00020\u0012*\u00020\u0003H\b\u001a\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0014\u001a\r\u0010\u0015\u001a\u00020\u0016*\u00020\u0003H\b\u001a\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0016*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u0019\u001a\u00020\r*\u00020\u0003H\b\u001a\u0015\u0010\u0019\u001a\u00020\r*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u001a\u001a\u0004\u0018\u00010\r*\u00020\u0003H\u0007¢\u0006\u0002\u0010\u001b\u001a\u001b\u0010\u001a\u001a\u0004\u0018\u00010\r*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u001c\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0003H\b\u001a\u0015\u0010\u001d\u001a\u00020\u001e*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u001e*\u00020\u0003H\u0007¢\u0006\u0002\u0010 \u001a\u001b\u0010\u001f\u001a\u0004\u0018\u00010\u001e*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010!\u001a\r\u0010\"\u001a\u00020#*\u00020\u0003H\b\u001a\u0015\u0010\"\u001a\u00020#*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0013\u0010$\u001a\u0004\u0018\u00010#*\u00020\u0003H\u0007¢\u0006\u0002\u0010%\u001a\u001b\u0010$\u001a\u0004\u0018\u00010#*\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010&\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\r2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020\u001e2\u0006\u0010\f\u001a\u00020\rH\b\u001a\u0015\u0010'\u001a\u00020\u0003*\u00020#2\u0006\u0010\f\u001a\u00020\rH\b¨\u0006("}, d2 = {"screenFloatValue", "T", "str", "", "parse", "Lkotlin/Function1;", "screenFloatValue$StringsKt__StringNumberConversionsKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "toBoolean", "", "toByte", "", "radix", "", "toByteOrNull", "(Ljava/lang/String;)Ljava/lang/Byte;", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toDouble", "", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "toFloat", "", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toInt", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLong", "", "toLongOrNull", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShort", "", "toShortOrNull", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "toString", "kotlin-stdlib"}, k = 5, mv = {1, 1, 6}, xi = 1, xs = "kotlin/text/StringsKt")
class s extends r {
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toByteOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer toIntOrNull = toIntOrNull(str, i);
        if (toIntOrNull == null) {
            return null;
        }
        int intValue = toIntOrNull.intValue();
        if (intValue < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toShortOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer toIntOrNull = toIntOrNull(str, i);
        if (toIntOrNull == null) {
            return null;
        }
        int intValue = toIntOrNull.intValue();
        if (intValue < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toIntOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str, int i) {
        int i2 = -2147483647;
        int i3 = 1;
        int i4 = 0;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        c.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i5;
        char charAt = str.charAt(0);
        if (charAt >= '0') {
            i5 = 0;
            i3 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                i2 = Integer.MIN_VALUE;
                i5 = 1;
            } else if (charAt != '+') {
                return null;
            } else {
                i5 = 0;
            }
        }
        int i6 = i2 / i;
        length--;
        if (i3 <= length) {
            while (true) {
                int digitOf = c.digitOf(str.charAt(i3), i);
                if (digitOf >= 0) {
                    if (i4 >= i6) {
                        i4 *= i;
                        if (i4 >= i2 + digitOf) {
                            i4 -= digitOf;
                            if (i3 == length) {
                                break;
                            }
                            i3++;
                        } else {
                            return null;
                        }
                    }
                    return null;
                }
                return null;
            }
        }
        return i5 != 0 ? Integer.valueOf(i4) : Integer.valueOf(-i4);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toLongOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str, int i) {
        long j = -9223372036854775807L;
        int i2 = 1;
        int i3 = 0;
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        c.checkRadix(i);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        char charAt = str.charAt(0);
        if (charAt >= '0') {
            i2 = 0;
        } else if (length == 1) {
            return null;
        } else {
            if (charAt == '-') {
                j = Long.MIN_VALUE;
                i3 = 1;
            } else if (charAt != '+') {
                return null;
            }
        }
        long j2 = j / ((long) i);
        long j3 = 0;
        length--;
        if (i2 <= length) {
            while (true) {
                int digitOf = c.digitOf(str.charAt(i2), i);
                if (digitOf >= 0) {
                    if (j3 >= j2) {
                        j3 *= (long) i;
                        if (j3 >= ((long) digitOf) + j) {
                            j3 -= (long) digitOf;
                            if (i2 == length) {
                                break;
                            }
                            i2++;
                        } else {
                            return null;
                        }
                    }
                    return null;
                }
                return null;
            }
        }
        return i3 != 0 ? Long.valueOf(j3) : Long.valueOf(-j3);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        try {
            return k.value.matches((CharSequence) str) ? Float.valueOf(Float.parseFloat(str)) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        try {
            return k.value.matches((CharSequence) str) ? Double.valueOf(Double.parseDouble(str)) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
