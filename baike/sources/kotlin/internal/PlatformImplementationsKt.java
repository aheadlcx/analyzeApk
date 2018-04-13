package kotlin.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "getJavaVersion", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 6})
public final class PlatformImplementationsKt {
    @NotNull
    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS;

    static {
        Object newInstance;
        PlatformImplementations platformImplementations;
        int a = a();
        if (a >= 65544) {
            try {
                newInstance = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
                if (newInstance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                }
                platformImplementations = (PlatformImplementations) newInstance;
                IMPLEMENTATIONS = platformImplementations;
            } catch (ClassNotFoundException e) {
            }
        }
        if (a >= 65543) {
            try {
                newInstance = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                if (newInstance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                }
                platformImplementations = (PlatformImplementations) newInstance;
                IMPLEMENTATIONS = platformImplementations;
            } catch (ClassNotFoundException e2) {
            }
        }
        platformImplementations = new PlatformImplementations();
        IMPLEMENTATIONS = platformImplementations;
    }

    private static final int a() {
        String property = System.getProperty("java.specification.version");
        if (property == null) {
            return 65542;
        }
        int indexOf$default = u.indexOf$default((CharSequence) property, '.', 0, false, 6, null);
        int parseInt;
        if (indexOf$default < 0) {
            try {
                parseInt = Integer.parseInt(property) * 65536;
            } catch (NumberFormatException e) {
                parseInt = 65542;
            }
            return parseInt;
        }
        parseInt = u.indexOf$default((CharSequence) property, '.', indexOf$default + 1, false, 4, null);
        if (parseInt < 0) {
            parseInt = property.length();
        }
        if (property == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring = property.substring(0, indexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        int i = indexOf$default + 1;
        if (property == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String substring2 = property.substring(i, parseInt);
        Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        try {
            return (Integer.parseInt(substring) * 65536) + Integer.parseInt(substring2);
        } catch (NumberFormatException e2) {
            return 65542;
        }
    }
}
