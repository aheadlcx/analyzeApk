package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/jvm/internal/ByteCompanionObject;", "", "()V", "MAX_VALUE", "", "MIN_VALUE", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class ByteCompanionObject {
    public static final ByteCompanionObject INSTANCE = null;
    public static final byte MAX_VALUE = Byte.MAX_VALUE;
    public static final byte MIN_VALUE = Byte.MIN_VALUE;

    static {
        ByteCompanionObject byteCompanionObject = new ByteCompanionObject();
    }

    private ByteCompanionObject() {
        INSTANCE = this;
    }
}
