package kotlin;

import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0002\b\u0002J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"", "", "toString", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
public final class Unit {
    public static final Unit INSTANCE = null;

    static {
        Unit unit = new Unit();
    }

    private Unit() {
        INSTANCE = this;
    }

    @NotNull
    public String toString() {
        return "kotlin.Unit";
    }
}
