package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0012\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0016\u0010\u0005\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0013"}, d2 = {"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
class c<T extends Comparable<? super T>> implements ClosedRange<T> {
    @NotNull
    private final T a;
    @NotNull
    private final T b;

    public c(@NotNull T t, @NotNull T t2) {
        Intrinsics.checkParameterIsNotNull(t, "start");
        Intrinsics.checkParameterIsNotNull(t2, "endInclusive");
        this.a = t;
        this.b = t2;
    }

    public boolean contains(@NotNull T t) {
        Intrinsics.checkParameterIsNotNull(t, "value");
        return DefaultImpls.contains(this, t);
    }

    public boolean isEmpty() {
        return DefaultImpls.isEmpty(this);
    }

    @NotNull
    public T getStart() {
        return this.a;
    }

    @NotNull
    public T getEndInclusive() {
        return this.b;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof c) && ((isEmpty() && ((c) obj).isEmpty()) || (Intrinsics.areEqual(getStart(), ((c) obj).getStart()) && Intrinsics.areEqual(getEndInclusive(), ((c) obj).getEndInclusive())));
    }

    public int hashCode() {
        return isEmpty() ? -1 : (getStart().hashCode() * 31) + getEndInclusive().hashCode();
    }

    @NotNull
    public String toString() {
        return getStart() + ".." + getEndInclusive();
    }
}
