package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lkotlin/jvm/internal/FloatCompanionObject;", "", "()V", "MAX_VALUE", "", "getMAX_VALUE", "()F", "MIN_VALUE", "getMIN_VALUE", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY", "NaN", "getNaN", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class FloatCompanionObject {
    public static final FloatCompanionObject INSTANCE = null;
    private static final float a = Float.MIN_VALUE;
    private static final float b = Float.MAX_VALUE;
    private static final float c = 0.0f;
    private static final float d = 0.0f;
    private static final float e = 0.0f;

    static {
        FloatCompanionObject floatCompanionObject = new FloatCompanionObject();
    }

    private FloatCompanionObject() {
        INSTANCE = this;
        a = a;
        b = Float.MAX_VALUE;
        c = Float.POSITIVE_INFINITY;
        d = Float.NEGATIVE_INFINITY;
        e = Float.NaN;
    }

    public final float getMIN_VALUE() {
        return a;
    }

    public final float getMAX_VALUE() {
        return b;
    }

    public final float getPOSITIVE_INFINITY() {
        return c;
    }

    public final float getNEGATIVE_INFINITY() {
        return d;
    }

    public final float getNaN() {
        return e;
    }
}
