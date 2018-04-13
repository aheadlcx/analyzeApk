package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lkotlin/jvm/internal/DoubleCompanionObject;", "", "()V", "MAX_VALUE", "", "getMAX_VALUE", "()D", "MIN_VALUE", "getMIN_VALUE", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY", "NaN", "getNaN", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class DoubleCompanionObject {
    public static final DoubleCompanionObject INSTANCE = null;
    private static final double a = Double.MIN_VALUE;
    private static final double b = Double.MAX_VALUE;
    private static final double c = 0.0d;
    private static final double d = 0.0d;
    private static final double e = 0.0d;

    static {
        DoubleCompanionObject doubleCompanionObject = new DoubleCompanionObject();
    }

    private DoubleCompanionObject() {
        INSTANCE = this;
        a = a;
        b = b;
        c = Double.POSITIVE_INFINITY;
        d = Double.NEGATIVE_INFINITY;
        e = Double.NaN;
    }

    public final double getMIN_VALUE() {
        return a;
    }

    public final double getMAX_VALUE() {
        return b;
    }

    public final double getPOSITIVE_INFINITY() {
        return c;
    }

    public final double getNEGATIVE_INFINITY() {
        return d;
    }

    public final double getNaN() {
        return e;
    }
}
