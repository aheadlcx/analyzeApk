package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class SubSequence<T> implements DropTakeSequence<T>, Sequence<T> {
    private final Sequence<T> a;
    private final int b;
    private final int c;

    public SubSequence(@NotNull Sequence<? extends T> sequence, int i, int i2) {
        Object obj = 1;
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        this.a = sequence;
        this.b = i;
        this.c = i2;
        if ((this.b >= 0 ? 1 : null) == null) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + this.b).toString());
        }
        if ((this.c >= 0 ? 1 : null) == null) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + this.c).toString());
        }
        if (this.c < this.b) {
            obj = null;
        }
        if (obj == null) {
            throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + this.c + " < " + this.b).toString());
        }
    }

    private final int a() {
        return this.c - this.b;
    }

    @NotNull
    public Sequence<T> drop(int i) {
        return i >= a() ? e.emptySequence() : new SubSequence(this.a, this.b + i, this.c);
    }

    @NotNull
    public Sequence<T> take(int i) {
        return i >= a() ? this : new SubSequence(this.a, this.b, this.b + i);
    }

    @NotNull
    public Iterator<T> iterator() {
        return new SubSequence$iterator$1(this);
    }
}
