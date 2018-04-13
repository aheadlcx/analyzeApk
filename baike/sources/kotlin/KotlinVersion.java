package kotlin;

import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0000H\u0002J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0016J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\f\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lkotlin/KotlinVersion;", "", "major", "", "minor", "(II)V", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "version", "compareTo", "other", "equals", "", "", "hashCode", "isAtLeast", "toString", "", "versionOf", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class KotlinVersion implements Comparable<KotlinVersion> {
    @NotNull
    @JvmField
    public static final KotlinVersion CURRENT = new KotlinVersion(1, 1, 2);
    public static final Companion Companion = new Companion();
    public static final int MAX_COMPONENT_VALUE = 255;
    private final int a;
    private final int b;
    private final int c;
    private final int d;

    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/KotlinVersion$Companion;", "", "()V", "CURRENT", "Lkotlin/KotlinVersion;", "MAX_COMPONENT_VALUE", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
    public static final class Companion {
        private Companion() {
        }
    }

    public KotlinVersion(int i, int i2, int i3) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.a = a(this.b, this.c, this.d);
    }

    public final int getMajor() {
        return this.b;
    }

    public final int getMinor() {
        return this.c;
    }

    public final int getPatch() {
        return this.d;
    }

    public KotlinVersion(int i, int i2) {
        this(i, i2, 0);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int a(int r5, int r6, int r7) {
        /*
        r4 = this;
        r3 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        r0 = 1;
        r1 = 0;
        if (r5 < 0) goto L_0x004e;
    L_0x0006:
        if (r5 > r3) goto L_0x004e;
    L_0x0008:
        r2 = r0;
    L_0x0009:
        if (r2 == 0) goto L_0x0054;
    L_0x000b:
        if (r6 < 0) goto L_0x0050;
    L_0x000d:
        if (r6 > r3) goto L_0x0050;
    L_0x000f:
        r2 = r0;
    L_0x0010:
        if (r2 == 0) goto L_0x0054;
    L_0x0012:
        if (r7 < 0) goto L_0x0052;
    L_0x0014:
        if (r7 > r3) goto L_0x0052;
    L_0x0016:
        r2 = r0;
    L_0x0017:
        if (r2 == 0) goto L_0x0054;
    L_0x0019:
        if (r0 != 0) goto L_0x0056;
    L_0x001b:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Version components are out of range: ";
        r0 = r0.append(r1);
        r0 = r0.append(r5);
        r1 = ".";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r1 = ".";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = r0.toString();
        r0 = new java.lang.IllegalArgumentException;
        r1 = r1.toString();
        r0.<init>(r1);
        r0 = (java.lang.Throwable) r0;
        throw r0;
    L_0x004e:
        r2 = r1;
        goto L_0x0009;
    L_0x0050:
        r2 = r1;
        goto L_0x0010;
    L_0x0052:
        r2 = r1;
        goto L_0x0017;
    L_0x0054:
        r0 = r1;
        goto L_0x0019;
    L_0x0056:
        r0 = r6 + 16;
        r0 = r5 << r0;
        r1 = r7 + 8;
        r0 = r0 << r1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.KotlinVersion.a(int, int, int):int");
    }

    @NotNull
    public String toString() {
        return this.b + "." + this.c + "." + this.d;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        Object obj2;
        if (obj instanceof KotlinVersion) {
            obj2 = obj;
        } else {
            obj2 = null;
        }
        KotlinVersion kotlinVersion = (KotlinVersion) obj2;
        if (kotlinVersion == null) {
            return false;
        }
        boolean z;
        if (this.a == kotlinVersion.a) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.a;
    }

    public int compareTo(@NotNull KotlinVersion kotlinVersion) {
        Intrinsics.checkParameterIsNotNull(kotlinVersion, "other");
        return this.a - kotlinVersion.a;
    }

    public final boolean isAtLeast(int i, int i2) {
        return this.b > i || (this.b == i && this.c >= i2);
    }

    public final boolean isAtLeast(int i, int i2, int i3) {
        return this.b > i || (this.b == i && (this.c > i2 || (this.c == i2 && this.d >= i3)));
    }
}
