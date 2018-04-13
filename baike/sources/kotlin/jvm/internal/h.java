package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.LongIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayLongIterator;", "Lkotlin/collections/LongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextLong", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
final class h extends LongIterator {
    private int a;
    private final long[] b;

    public h(@NotNull long[] jArr) {
        Intrinsics.checkParameterIsNotNull(jArr, "array");
        this.b = jArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public long nextLong() {
        long[] jArr = this.b;
        int i = this.a;
        this.a = i + 1;
        return jArr[i];
    }
}
