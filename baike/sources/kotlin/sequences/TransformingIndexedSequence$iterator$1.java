package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\t\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"kotlin/sequences/TransformingIndexedSequence$iterator$1", "", "(Lkotlin/sequences/TransformingIndexedSequence;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "iterator", "getIterator", "()Ljava/util/Iterator;", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 6})
public final class TransformingIndexedSequence$iterator$1 implements Iterator<R>, KMappedMarker {
    final /* synthetic */ TransformingIndexedSequence a;
    @NotNull
    private final Iterator<T> b;
    private int c;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    TransformingIndexedSequence$iterator$1(TransformingIndexedSequence transformingIndexedSequence) {
        this.a = transformingIndexedSequence;
        this.b = transformingIndexedSequence.a.iterator();
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.b;
    }

    public final int getIndex() {
        return this.c;
    }

    public final void setIndex(int i) {
        this.c = i;
    }

    public R next() {
        Function2 access$getTransformer$p = this.a.b;
        int i = this.c;
        this.c = i + 1;
        return access$getTransformer$p.invoke(Integer.valueOf(i), this.b.next());
    }

    public boolean hasNext() {
        return this.b.hasNext();
    }
}
